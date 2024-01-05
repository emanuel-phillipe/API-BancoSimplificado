package com.emanuel.bancoSimples.dtos;

public record UserDTO(
        String name,
        String email,
        String phone,
        String cpf,
        String birth) {
}
