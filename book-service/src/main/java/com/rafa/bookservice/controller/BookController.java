package com.rafa.bookservice.controller;

import com.rafa.bookservice.model.Book;
import com.rafa.bookservice.proxy.CambioProxy;
import com.rafa.bookservice.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioProxy proxy;

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    // http://localhost:8100/book-service/1/BRL
    @Operation(summary = "Find a specific book by your id")
    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable String currency
    ){
        var port = environment.getProperty("local.server.port");

        var book = repository.getBookById(id);
        book.setCurrency(currency);

        if(book == null)
            throw new RuntimeException("Book Not Found");

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);
        var cambio = proxy.getCambio(book.getPrice(),"USD", currency);
        book.setPrice(cambio.getConvertedValue());
        book.setEnviroment("book port: " + port + " cambio port: " + cambio.getEnviroment());

        return book;
    }

/*    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable String currency
    ){
        var port = environment.getProperty("local.server.port");

        var book = repository.getBookById(id);
        book.setCurrency(currency);
        book.setEnviroment(port);

        if(book == null)
            throw new RuntimeException("Book Not Found");

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);
        var cambioResponse = new RestTemplate()
                .getForEntity("http://localhost:8000/cambio-service/" + "{amount}/{from}/{to}",
                        CambioResponse.class, params);
        var cambio = cambioResponse.getBody();
        book.setPrice(cambio.getConvertedValue());
        return book;
    }*/
}
