package br.com.alexbarros.todolist.user;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired // injeta a dependencia do repositorio
    private IUserRepository userRepository; // injeta a dependencia do repositorio

    @PostMapping("/") // o create vem atraves do metodo POST, pq esta criando um novo usuario
    public ResponseEntity create(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user != null) {
            // System.out.println("Usuario ja existe!");
            // Msg de erro e Status code do erro
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existe!"); // 400
        }

        var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated); // 201
    }
}
