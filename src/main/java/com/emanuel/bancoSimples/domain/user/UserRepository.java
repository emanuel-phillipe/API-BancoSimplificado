package com.emanuel.bancoSimples.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByCpf(String cpf);
}
