/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vbs.book.Controller;

import com.vbs.book.Model.Book;
import com.vbs.book.Model.User;
import com.vbs.book.Service.UserService;
import com.vbs.book.Service.emailAlreadyTakenException;
import com.vbs.book.Service.invalidStatusException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    private final UserService service;
    private final JavaMailSender emailSender;

    public UserController(UserService service, JavaMailSender emailSender) {
        this.service = service;
        this.emailSender = emailSender;
    }

    
    
    @GetMapping(value = "/getUsers")
    public List<User> getT(){
        return service.getAll();
    }
    
    @PostMapping(value ="/register")
    public User addG(@RequestBody User u){
        return service.add(u);
    }
    
    @RequestMapping(value = "/getUserBy/{id}", method = RequestMethod.GET)
    public User getBy(@PathVariable Integer id){
        return service.getBy(id);
    }
    
    @PostMapping(value ="/changeUser")
    public User updateUser(@RequestBody User u){
        return service.update(u);
    }
    
    @GetMapping(value = "/removeUser/{id}")
    public void removeUser(@PathVariable Integer id){
        service.delete(id);
    }
    
    @PostMapping(value ="/registerSpq")
    public ResponseEntity addSPW(@RequestBody User u){
        try{
            service.registerUser(u);
            return new ResponseEntity<>("Succesfully created", HttpStatus.OK);
        }
        catch(invalidStatusException ex){
           return new ResponseEntity<>("Status is invalid", HttpStatus.NOT_ACCEPTABLE);
        }
        catch(emailAlreadyTakenException ex){
            return new ResponseEntity<>("Email is already taken", HttpStatus.NOT_EXTENDED);
        }
    }
    
    @GetMapping(value = "email")
    public void emailsend(){
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("norbi1119@gmail.com");
        message.setTo("norbi1119@gmail.com"); 
        message.setSubject("Springboot email"); 
        message.setText("This message is send by an automatic springboot endpoint call.");
        emailSender.send(message);
    }
    
    
//    @PostMapping(value ="/addUser")
//    ResponseEntity<String> age(@RequestBody User u) {      
//        try{ 
//            User user = service.add(u);
//            System.out.println(user.getEmail());
//            return new ResponseEntity<>( "The date is fine" , HttpStatus.OK);             
//        }
//        catch(Exception ex){
//            System.out.println(ex.getMessage());
//            return new ResponseEntity<>("The date is in the future", HttpStatus.BAD_REQUEST);
//        }    
//    }
}
