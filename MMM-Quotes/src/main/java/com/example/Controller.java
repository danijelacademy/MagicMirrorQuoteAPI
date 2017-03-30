package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017-03-30.
 */

@RestController
public class Controller {

    @Autowired
    Repository repository;

    @GetMapping("/Quotes")
    public Quote randomizeQuotes() {
        List<Quote> quotes = repository.getQuotes();

        Random rand = new Random();
        int randomNumber = rand.nextInt(quotes.size());



        return quotes.get(randomNumber);

    }

}

