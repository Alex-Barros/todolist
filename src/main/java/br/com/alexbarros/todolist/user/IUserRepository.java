package br.com.alexbarros.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


// JpaRepository é uma interface do Spring Data JPA que já possui métodos prontos para manipulação de dados no banco de dados
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
   UserModel findByUsername(String username);
}
