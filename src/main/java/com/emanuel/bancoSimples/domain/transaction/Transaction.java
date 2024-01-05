package com.emanuel.bancoSimples.domain.transaction;

import com.emanuel.bancoSimples.dtos.TransactionDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name="transactions")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Float amount;

    private String senderCpf;
    private String receiverCpf;
    private LocalDateTime timeCreated;

    public Transaction(TransactionDTO transaction){
        this.amount = transaction.amount();
        this.senderCpf = transaction.senderCpf();
        this.receiverCpf = transaction.receiverCpf();
    }

}
