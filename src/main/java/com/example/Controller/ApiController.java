package com.example.Controller;


import com.example.Domain.Quote;
import com.example.Repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class ApiController {

    @Autowired
    Repository repository;

    @GetMapping("/api/quotes")
    public Quote randomizeQuotes(

    ) {
        List<Quote> quotes = repository.getQuotes();

        Random rand = new Random();
        int randomNumber = rand.nextInt(quotes.size());

        return quotes.get(randomNumber);
    }

    @GetMapping("/api/sl")
    public String returnSL() {

        String slUrl = "http://api.sl.se/api2/TravelplannerV2/trip.json?key=cf85888d5497444795b1ba969383ebcd&originId=9112&destId=9509&searchForArrival=0";

        return slUrl;
    }

}
