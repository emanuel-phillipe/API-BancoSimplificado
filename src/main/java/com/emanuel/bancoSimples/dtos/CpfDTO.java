package com.emanuel.bancoSimples.dtos;

import lombok.Getter;

@Getter
public class CpfDTO {
    private String code;
    private String status;
    private String message;
    private String cpf;
    private String nome;
    private String genero;
    private String data_nascimento;
    private String situacao_cadastral;
    private String data_inscricao;
    private String digito_verificador;
    private String comprovante;
    private String version;
}

//{
//        "code": "0",
//        "status": "OK",
//        "message": "Pesquisa realizada com sucesso.",
//        "cpf": "026.315.210-60",
//        "nome": "ISABELLA ALVES",
//        "genero": "F",
//        "data_nascimento": "13/02/1985",
//        "situacao_cadastral": "REGULAR",
//        "data_inscricao": "18/05/2001",
//        "digito_verificador": "00",
//        "comprovante": "93BA.8U8B.6CAA.47F1",
//        "version": "2"
//        }
//
