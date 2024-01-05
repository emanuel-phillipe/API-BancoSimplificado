package com.emanuel.bancoSimples.domain.user;

import com.emanuel.bancoSimples.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name="users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @JoinColumn(name = "email", unique = true, nullable = false)
    private String email;

    @JoinColumn(name = "phone", unique = true, nullable = false)
    private String phone;

    @JoinColumn(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @JoinColumn(name = "birth", unique = false, nullable = false)
    private String birth;

    private Float balance;

    public User(UserDTO user){
        this.name = user.name();
        this.email = user.email();
        this.phone = user.phone();
        this.cpf = user.cpf();
        this.birth = user.birth();
        this.balance = 00.00F;
    }
}
