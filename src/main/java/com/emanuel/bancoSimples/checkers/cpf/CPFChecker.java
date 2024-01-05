package com.emanuel.bancoSimples.checkers.cpf;

import com.emanuel.bancoSimples.dtos.CpfDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CPFChecker {

    public CpfDTO checkUserCpfInfo(String cpf, String birth){
        RestTemplate restTemplate = new RestTemplate();

        var urlGetCpfInfo = String.format("https://www.sintegraws.com.br/api/v1/execute-api.php?token=%s&cpf=%s&data-nascimento=%s&plugin=CPF", "C9C711CB-414A-4E25-8447-7F475E833324", cpf, birth);

        ResponseEntity<CpfDTO> response = restTemplate.getForEntity(urlGetCpfInfo, CpfDTO.class);

        return response.getBody();
    }
}

//
