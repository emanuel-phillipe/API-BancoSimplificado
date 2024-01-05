package com.emanuel.bancoSimples.controllers;

import com.emanuel.bancoSimples.domain.user.User;
import com.emanuel.bancoSimples.dtos.UserDeleteDTO;
import com.emanuel.bancoSimples.domain.user.UserRepository;
import com.emanuel.bancoSimples.dtos.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity getUsers(){
        return ResponseEntity.ok(this.userRepository.findAll());
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid UserDTO data){

        /*CpfDTO cpfChecker = new CPFChecker().checkUserCpfInfo(data.cpf(), data.birth().replace("/", ""));
        System.out.println(cpfChecker);*/

        User newUser = new User(data);

        try {
            this.userRepository.save(newUser);

            return new ResponseEntity<>("Usuário criado", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Usuário existente", HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestBody @Valid UserDeleteDTO data){
        User userToDelete = userRepository.findByCpf(data.cpf());

        if (userToDelete == null) {
            return new ResponseEntity<>("Usuário inexistente", HttpStatus.NOT_FOUND);
        }

        this.userRepository.delete(userToDelete);
        return new ResponseEntity<>("Usuário deletado", HttpStatus.OK);
    }

    @PutMapping
    @Transactional
    public ResponseEntity editUserInfo(@RequestBody @Valid UserDTO data){
        User user = this.userRepository.findByCpf(data.cpf());

        if (user == null){
            return new ResponseEntity<>("Usuário inexistente", HttpStatus.NOT_FOUND);
        }

        user.setEmail(data.email());
        user.setPhone(data.phone());

        return new ResponseEntity<>("Informações modificadas", HttpStatus.OK);

    }
}
