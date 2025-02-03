package br.com.guarda_sementes_api.service.endereco.dto;

public interface EnderecoDadosCompletos {
    Long getEndNrId();
    String getEndTxBairro();
    String getEndTxLogradouro();
    String getEndTxNumero();
    String getEndTxReferencia();
    Boolean getEndBlEnderecoPadrao();
    Long getCidNrId();
    String getCidTxNome();
    Long getEstNrId();
    String getEstTxNome();
    String getEstTxSigla();
}
