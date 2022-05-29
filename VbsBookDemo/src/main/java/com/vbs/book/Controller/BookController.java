/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vbs.book.Controller;

import com.vbs.book.Model.Book;
import com.vbs.book.Service.BookService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }
    
    @GetMapping(value = "/getBook")
    public List<Book> getT(){
        return service.getAll();
    }
    
    @PostMapping(value ="/addBook")
    public Book addG(@RequestBody Book b){
        return service.add(b);
    }
    
    @RequestMapping(value = "/getBookBy/{id}", method = RequestMethod.GET)
    public Book getBy(@PathVariable Integer id){
        return service.getBy(id);
    }
    
    @PostMapping(value ="/changeBook")
    public Book updateBook(@RequestBody Book b){
        return service.update(b);
    }
    
    @GetMapping(value = "/removeBook/{id}")
    public void removeBook(@PathVariable Integer id){
        service.delete(id);
    }
    
}
