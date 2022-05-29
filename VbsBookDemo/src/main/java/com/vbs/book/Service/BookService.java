/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vbs.book.Service;

import com.vbs.book.Model.Book;
import com.vbs.book.Repository.BookRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository repo;
    @PersistenceContext
    public EntityManager em;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }
    
    public Book add(Book b){
        return repo.save(b);
    }
    
    public void delete(Integer id){
        repo.deleteById(id);
    }
    
    public List<Book> getAll(){
        return repo.findAll();
    }
    
    public Book update(Book b){
        return repo.save(b);
    }
    
    public Book getBy(Integer id){
        Optional<Book> list = repo.findById(id);
        if(list.isPresent()){
            return list.get();         
        }
        else{
            return new Book();
            //Exception
        }
    }
}
