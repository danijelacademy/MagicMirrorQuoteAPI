package com.example.Controller;


import com.example.Domain.Quote;
import com.example.Repository.Repository;
import com.sun.jersey.json.impl.writer.JsonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/api/sl")
    public BestObject returnSL() {

        String slUrl = "http://api.sl.se/api2/TravelplannerV2/trip.json?key=cf85888d5497444795b1ba969383ebcd&originId=9180&destId=9302&searchForArrival=0";
        BestObject bestObject = new BestObject();
        bestObject.url = slUrl;

        return bestObject;
    }


    public class BestObject {

        public String url;
    }
}
