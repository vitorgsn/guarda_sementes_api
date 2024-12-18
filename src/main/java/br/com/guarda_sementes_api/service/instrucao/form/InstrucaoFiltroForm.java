package br.com.guarda_sementes_api.service.instrucao.form;

public record InstrucaoFiltroForm(
        Long insNrId,
        String insTxTitulo,
        String insTxInstrucao
) {
}
