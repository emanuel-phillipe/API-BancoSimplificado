package com.emanuel.bancoSimples.dtos;

public record TransactionDTO(
        Float amount,
        String senderCpf,
        String receiverCpf
) {
}
