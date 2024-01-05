package com.emanuel.bancoSimples.controllers;

import com.emanuel.bancoSimples.domain.transaction.Transaction;
import com.emanuel.bancoSimples.dtos.TransactionDeleteDTO;
import com.emanuel.bancoSimples.domain.transaction.TransactionRepository;
import com.emanuel.bancoSimples.dtos.TransactionDTO;
import com.emanuel.bancoSimples.domain.user.User;
import com.emanuel.bancoSimples.domain.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity getAllTransactions(){return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);}

    @PostMapping
    @Transactional
    public ResponseEntity createNewTransaction(@RequestBody @Valid TransactionDTO data){
        Transaction newTransaction = new Transaction(data);

        User sender = this.userRepository.findByCpf(data.senderCpf());
        User receiver = this.userRepository.findByCpf(data.receiverCpf());

        if (sender == null || receiver == null){
            return new ResponseEntity<>("Usuário inexistente", HttpStatus.NOT_FOUND);
        }

        var senderBalanceAfterTransaction = sender.getBalance() - data.amount();

        if (senderBalanceAfterTransaction < 0){
            return new ResponseEntity<>("Saldo insuficiente", HttpStatus.NOT_ACCEPTABLE);
        }else {
            sender.setBalance(senderBalanceAfterTransaction);
            var receiverBalanceAfterTransaction = receiver.getBalance() + data.amount();
            receiver.setBalance(receiverBalanceAfterTransaction);

            newTransaction.setTimeCreated(LocalDateTime.now());
            this.transactionRepository.save(newTransaction);

            return new ResponseEntity<>("Transferência concluída", HttpStatus.OK);
        }
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity deleteTransactionAndResetUsersBalance(@RequestBody @Valid TransactionDeleteDTO data){
        Optional<Transaction> optionalTransactionToDelete = transactionRepository.findById(data.uuid());

        if (!optionalTransactionToDelete.isPresent()){
            return new ResponseEntity<>("Transação inexistente", HttpStatus.NOT_FOUND);
        }else {

            Transaction transactionToDelete = optionalTransactionToDelete.get();

            var datetimeNow = LocalDateTime.now();
            var daysDifferenceBetweenTransactionDate = ChronoUnit.DAYS.between(transactionToDelete.getTimeCreated(), datetimeNow);

            if (daysDifferenceBetweenTransactionDate > 7) {
                return new ResponseEntity<>("Transação feita há " + daysDifferenceBetweenTransactionDate + " dias", HttpStatus.UNAUTHORIZED);
            }

            User sender = this.userRepository.findByCpf(transactionToDelete.getSenderCpf());
            User receiver = this.userRepository.findByCpf(transactionToDelete.getReceiverCpf());

            var newSenderBalance = sender.getBalance() + transactionToDelete.getAmount();
            var newReceiverBalance = receiver.getBalance() - transactionToDelete.getAmount();

            this.transactionRepository.delete(transactionToDelete);
            sender.setBalance(newSenderBalance);
            receiver.setBalance(newReceiverBalance);

            return new ResponseEntity<>("Transação retornada", HttpStatus.OK);
        }
    }
}
