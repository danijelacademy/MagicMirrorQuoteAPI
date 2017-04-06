package com.example.Controller;


import com.example.Domain.Quote;
import com.example.Repository.Repository;
import com.sun.jersey.json.impl.writer.JsonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class ApiController {

    @Autowired
    Repository repository;

    @GetMapping("/api/quotes")
    public Quote randomizeQuotes() {
        List<Quote> quotes = repository.getQuotes();

        Random rand = new Random();
        int randomNumber = rand.nextInt(quotes.size());

        return quotes.get(randomNumber);
    }


    @GetMapping("/api/sl/{username}")
    public BestObject returnSL(@PathVariable String test) {

<<<<<<< HEAD
        String slUrl =getSlUrl.returnUrl(test);
        BestObject bestObject = new BestObject();
        bestObject.url = slUrl;

        return bestObject;
    }

=======
        return returnSL(username);
>>>>>>> 60603593a2a39be445c6c748a4e33b16c39cd6f1

    }

