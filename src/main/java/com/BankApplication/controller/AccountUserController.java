package com.BankApplication.controller;

import com.BankApplication.model.AccountUser;
import com.BankApplication.model.AccountUserResource;
import com.BankApplication.model.LoginRequest;
import com.BankApplication.model.LoginResponse;
import com.BankApplication.service.AccountUserService;
import com.BankApplication.service.MessageService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/users")
@CrossOrigin
public class AccountUserController {

    @Autowired
    private AccountUserService userService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/getResetCode")
    public void getResetCode(@RequestParam String username ) throws MessagingException{
        AccountUser user = userService.getAccountUserByUsername(username).getBody();
        StringBuilder randomCode = new StringBuilder();
        int count = 1;
        while(count <= 6 ){
            String x = String.valueOf(new Random().nextInt(10));
            randomCode.append(x);
            count++;
        }
        messageService.sendResetCode(user.getUsername(), randomCode.toString());

    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AccountUser>> getAllAccountUsers(){
        return userService.getAllAccountUsers();
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<AccountUser> getAccountUserById(@PathVariable int id){
        return userService.getAccountUserById(id);
    }

    @GetMapping("/name")
    public ResponseEntity<AccountUser> getAccountUserByUsername(@RequestParam String username){
        return userService.getAccountUserByUsername(username);
    }

    @PostMapping("/register")
    public ResponseEntity<AccountUser> postAccountUser(@RequestBody AccountUser user) throws MessagingException {
        return userService.postAccountUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginRequest) throws MessagingException {
        return userService.authenticate(loginRequest);
    }
    @PutMapping("/all/{id}")
    public ResponseEntity<AccountUser> updateAccountUser(@PathVariable int id, @RequestBody AccountUser user){
        return userService.updateAccountUser(id, user);
    }

    @DeleteMapping("/all/{id}")
    public ResponseEntity<AccountUser> deleteAccountUser(@PathVariable int id){
        return userService.deleteAccountUser(id);
    }

    @GetMapping("/resource/{id}")
    public ResponseEntity<AccountUserResource> getUserAccountResource(@PathVariable int id){
        AccountUserResource resource = new AccountUserResource();
        AccountUser user = userService.getAccountUserById(id).getBody();
        resource.setAccountUser(user);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).getAccountUserById(id)).withSelfRel();
        Link updateLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).updateAccountUser(id, user)).withRel("updateUser");
        Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).deleteAccountUser(id)).withRel("deleteUser");
        Link getAll = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).getAllAccountUsers()).withRel("getAll");

        resource.add(selfLink, updateLink, deleteLink, getAll);

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}