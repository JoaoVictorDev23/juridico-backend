package com.juridico.gestao.servicesImpl;

import com.juridico.gestao.DTO.*;
import com.juridico.gestao.Entity.*;
import com.juridico.gestao.repositories.*;
import com.juridico.gestao.services.DadosprocessoService;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class DadosprocessoServiceImpl implements DadosprocessoService {

    @Autowired
    DadosProcessosRepository dadosRepository;
    @Autowired
    ProvisaoRepository provisaoRepository;
    @Autowired
    RiscosRepository riscosRepository;
    @Autowired
    ExtrajudicialRepository extrajudicialRepository;
    @Autowired
    FinanceiroRepository financeiroRepository;


    @Transactional
    @Override
    public void createDados(DadosprocessoDTO dadosprocessoDTO) {
        Dadosprocesso dadosexisting = this.dadosRepository.findByNumeroCnj(dadosprocessoDTO.numeroCnj());
        if (dadosexisting != null) {
            throw new RuntimeException("Processo já existe!");
        }

        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        Dadosprocesso newDados = new Dadosprocesso(dadosprocessoDTO);
        newDados.setEmailCadastro(emailUsuario);
        newDados.setEmailUpdate(emailUsuario);

        // Configurar o financeiro se estiver presente
        if (newDados.getFinanceiro() != null) {
            newDados.getFinanceiro().setDadosprocesso(newDados);

            // Iterar sobre as contas do financeiro
            for (Conta conta : newDados.getFinanceiro().getContas()) {
                conta.setFinanceiro(newDados.getFinanceiro());

                // Configurar parcelas apenas se for Acordo
                if (conta instanceof Acordo) {
                    Acordo acordo = (Acordo) conta;
                    for (Parcelas parcela : acordo.getParcelas()) {
                        parcela.setConta(acordo);
                    }
                }
            }
        }

        // Salvar o novo dado do processo
        dadosRepository.save(newDados);
    }

    @Transactional
    @Override
    public void updateDados(DadosprocessoDTO dadosprocessoDTO) {
        Dadosprocesso existingDados = dadosRepository.findByNumeroCnj(dadosprocessoDTO.numeroCnj());
        if (existingDados == null) {
            throw new RuntimeException("Processo inexistente!");
        }
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        existingDados.setStatus(dadosprocessoDTO.status());
        existingDados.setPasta(dadosprocessoDTO.pasta());
        existingDados.setArea(dadosprocessoDTO.area());
        existingDados.setTipoCliente(dadosprocessoDTO.tipoCliente());
        existingDados.setSetor(dadosprocessoDTO.setor());
        existingDados.setObjeto(dadosprocessoDTO.objeto());
        existingDados.setComarca(dadosprocessoDTO.comarca());
        existingDados.setInstancia(dadosprocessoDTO.instancia());
        existingDados.setObservacao(dadosprocessoDTO.observacao());
        existingDados.setStatusProcesso(dadosprocessoDTO.statusProcesso());
        existingDados.setValorCausa(dadosprocessoDTO.valorCausa());
        existingDados.setDataProtocolo(dadosprocessoDTO.dataProtocolo());
        existingDados.setDataBaixa(dadosprocessoDTO.dataBaixa());
        existingDados.setProbabilidade(dadosprocessoDTO.probabilidade());
        existingDados.setClassificacao(dadosprocessoDTO.classificacao());
        existingDados.setEmailUpdate(emailUsuario);

//         Duvida se Adiciono
//        existingDados.setParteCliente(dadosprocessoDTO.parteCliente());
//        existingDados.setParteAdversa(dadosprocessoDTO.parteAdversa());
//


        dadosRepository.save(existingDados);
    }

    @Override
    @Transactional
    public void updateRiscos(RiscosDTO riscosDTO) {
        Riscos riscosexisting = riscosRepository.findByNumeroCnj(riscosDTO.numeroCnj());
        if (riscosexisting == null) {
            throw new RuntimeException("Processo não localizado para este risco!");
        }
        riscosexisting.setRiscoMinimo(riscosDTO.riscoMinimo());
        riscosexisting.setRiscoRemoto(riscosDTO.riscoRemoto());
        riscosexisting.setRiscoPossivel(riscosDTO.riscoPossivel());
        riscosexisting.setRiscoProvavel(riscosDTO.riscoProvavel());
        riscosexisting.setRiscoMaximo(riscosDTO.riscoMaximo());
        riscosexisting.setRiscoEstimado(riscosDTO.riscoEstimado());
        riscosexisting.setValorCausaRisco(riscosDTO.valorCausaRisco());

        riscosRepository.save(riscosexisting);
    }

    @Override
    public void UpdateProvisao(ProvisaoDTO provisaoDTO) {
        Provisao provisaoexisting = provisaoRepository.findByNumeroCnj(provisaoDTO.numeroCnj());
        if(provisaoexisting == null){
            throw new RuntimeException("Processo não localizado para a provisão");
        }
        provisaoexisting.setArea(provisaoDTO.area());
        provisaoexisting.setAdverso(provisaoDTO.adversa());
        provisaoexisting.setStatus(provisaoDTO.status());
        provisaoexisting.setInicio(provisaoDTO.inicio());
        provisaoexisting.setFim(provisaoDTO.fim());
        provisaoexisting.setSaldoRescisao(provisaoDTO.saldoRescisao());
        provisaoexisting.setFgts40(provisaoDTO.fgts40());
        provisaoexisting.setAvisoPrevio(provisaoDTO.avisoPrevio());
        provisaoexisting.setMargem500(provisaoDTO.margem500());


        provisaoRepository.save(provisaoexisting);
    }


    @Override
    public void updateFinanceiro(FinanceiroDTO financeiroDTO) {
        Financeiro financeiroexisting = financeiroRepository.findByNumeroCnj(financeiroDTO.numeroCnj());
        if(financeiroexisting == null){
            throw new RuntimeException("Processo não localizado");
        }
        financeiroDTO.contas().forEach(conta -> conta.setFinanceiro(financeiroexisting));

        financeiroexisting.setContas(financeiroDTO.contas());
        financeiroRepository.save(financeiroexisting);


    }

    @Override
    public List<Dadosprocesso> getAllProcessos() {
        return dadosRepository.findAll();
    }
    @Override
    public DadosprocessoDTO buscarPorNumeroCnj(String numeroCnj) {
        Dadosprocesso dadosprocesso = dadosRepository.findByNumeroCnj(numeroCnj);
        if (dadosprocesso == null) {
            throw new RuntimeException("Processo não encontrado para o número CNJ: " + numeroCnj);
        }
        // Convertendo Dadosprocesso para DadosprocessoDTO
        DadosprocessoDTO dadosprocessoDTO =new DadosprocessoDTO(dadosprocesso);
        return dadosprocessoDTO;
    }


    public byte[] exportToExcel() throws IOException {
        List<Dadosprocesso> processos = dadosRepository.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Processos");

        // Cabeçalho
        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID", "Status", "Número CNJ", "Pasta", "Área", "Tipo Cliente", "Setor", "Objeto",
                "Comarca", "Instância", "Observação", "Status Processo", "Valor Causa", "Data Protocolo", "Data Baixa",
                "Probabilidade", "Classificação", "Email Cadastro", "Email Update",
                "Risco Mínimo", "Risco Remoto", "Risco Possível", "Risco Provável", "Risco Máximo", "Risco Estimado", "Valor Causa Risco",
                "Número CNJ Provisão", "Área Provisão", "Adverso", "Status Provisão", "Início", "Fim", "Saldo Rescisão", "Tempo Trabalhado",
                "FGTS 40%", "Aviso Prévio", "Margem 500", "Valor Total Provisionado",
                "Número CNJ Financeiro", "Dados Processo", "Contas Descrição", "Contas Valor"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Dados
        int rowNum = 1;
        for (Dadosprocesso processo : processos) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(processo.getId());
            row.createCell(1).setCellValue(processo.getStatus());
            row.createCell(2).setCellValue(processo.getNumeroCnj());
            row.createCell(3).setCellValue(processo.getPasta());
            row.createCell(4).setCellValue(processo.getArea());
            row.createCell(5).setCellValue(processo.getTipoCliente());
            row.createCell(6).setCellValue(processo.getSetor());
            row.createCell(7).setCellValue(processo.getObjeto());
            row.createCell(8).setCellValue(processo.getComarca());
            row.createCell(9).setCellValue(processo.getInstancia());
            row.createCell(10).setCellValue(processo.getObservacao());
            row.createCell(11).setCellValue(processo.getStatusProcesso());
            row.createCell(12).setCellValue(processo.getValorCausa());
            row.createCell(13).setCellValue(processo.getDataProtocolo().toString());
            row.createCell(14).setCellValue(processo.getDataBaixa().toString());
            row.createCell(15).setCellValue(processo.getProbabilidade());
            row.createCell(16).setCellValue(processo.getClassificacao());
            row.createCell(17).setCellValue(processo.getEmailCadastro());
            row.createCell(18).setCellValue(processo.getEmailUpdate());

            // Dados de Riscos
            Riscos riscos = processo.getRiscos();
            if (riscos != null) {
                row.createCell(19).setCellValue(riscos.getRiscoMinimo());
                row.createCell(20).setCellValue(riscos.getRiscoRemoto());
                row.createCell(21).setCellValue(riscos.getRiscoPossivel());
                row.createCell(22).setCellValue(riscos.getRiscoProvavel());
                row.createCell(23).setCellValue(riscos.getRiscoMaximo());
                row.createCell(24).setCellValue(riscos.getRiscoEstimado());
                row.createCell(25).setCellValue(riscos.getValorCausaRisco());
            }

            // Dados de Provisao
            Provisao provisao = processo.getProvisao();
            if (provisao != null) {
                row.createCell(26).setCellValue(provisao.getNumeroCnj());
                row.createCell(27).setCellValue(provisao.getArea());
                row.createCell(28).setCellValue(provisao.getAdverso());
                row.createCell(29).setCellValue(provisao.getStatus());
                row.createCell(30).setCellValue(provisao.getInicio() != null ? provisao.getInicio().toString() : "");
                row.createCell(31).setCellValue(provisao.getFim() != null ? provisao.getFim().toString() : "");
                row.createCell(32).setCellValue(provisao.getSaldoRescisao());
                row.createCell(33).setCellValue(provisao.getTempoTrabalhado());
                row.createCell(34).setCellValue(provisao.getFgts40());
                row.createCell(35).setCellValue(provisao.getAvisoPrevio());
                row.createCell(36).setCellValue(provisao.getMargem500());
                row.createCell(37).setCellValue(provisao.getValorTProvisionado());
            }

            // Dados de Financeiro
            Financeiro financeiro = processo.getFinanceiro();
            if (financeiro != null) {
                row.createCell(38).setCellValue(financeiro.getNumeroCnj());

            }
        }

        // Auto-ajustar largura das colunas
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }

}
