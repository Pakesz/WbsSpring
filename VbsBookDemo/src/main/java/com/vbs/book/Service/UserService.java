/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vbs.book.Service;

import com.vbs.book.Model.User;
import com.vbs.book.Repository.UserRepository;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class UserService {
    private final UserRepository repo;
    @PersistenceContext
    public EntityManager em;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }
    
    
    
    public User add(User u){
        try{
            return repo.save(u);
        }
        catch(Exception ex){
            System.out.println("Exception van");
            return repo.save(u);
        }
    }
    
    public void delete(Integer id){
        repo.deleteById(id);
    }
    
    public List<User> getAll(){
        return repo.findAll();
    }
    
    public User update(User u){
        return repo.save(u);
    }
    
    public User getBy(Integer id){
        Optional<User> list = repo.findById(id);
        if(list.isPresent()){
            return list.get();         
        }
        else{
            return new User();
            //Exception
        }
    }
    
    public User registerUser(User u) throws invalidStatusException, emailAlreadyTakenException{
        
        if(!(u.getStatus().equals("pending") || u.getStatus().equals("registered") || u.getStatus().equals("archived"))){
            throw new invalidStatusException();
        }
        else{
            try{
                StoredProcedureQuery spq = em.createStoredProcedureQuery("addUser");

                spq.registerStoredProcedureParameter("eIN", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("fIN", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("lIN", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("idOUT", Integer.class, ParameterMode.OUT);

                spq.setParameter("eIN", u.getEmail());
                spq.setParameter("fIN", u.getFirstname());
                spq.setParameter("lIN", u.getLastname());

                return em.find(User.class, Integer.parseInt(spq.getOutputParameterValue("idOUT").toString()));
            }
            catch(Exception ex){
                   throw new emailAlreadyTakenException(); 
            }
        }
    }
    
    
    
}
