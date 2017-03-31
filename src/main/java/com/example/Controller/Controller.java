package com.example.Controller;


import com.example.Domain.Quote;
import com.example.Domain.User;
import com.example.Repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.Random;
import java.util.logging.Level;

/**
 * Created by Administrator on 2017-03-30.
 */

@RestController
public class Controller {

    @Autowired
    Repository repository;

    @GetMapping("/quotes")
    public Quote randomizeQuotes() {
        List<Quote> quotes = repository.getQuotes();

        Random rand = new Random();
        int randomNumber = rand.nextInt(quotes.size());



        return quotes.get(randomNumber);

    }

    @PostMapping("/login")
    public ModelAndView getUserLogin(@RequestParam String username, @RequestParam String password) throws Exception {
        User login = repository.getUser(username, password);
        if (login == null)
            return new ModelAndView("redirect:/index.html");

        return new ModelAndView("page1").addObject("User",username);

    }

    @PostMapping("/newQuote") //Kolla här
    public ModelAndView postNewQuote(@RequestParam Quote quote) throws Exception {
        repository.addQuote(quote.quote, quote.quoteType);
        return new ModelAndView("quotes")
                .addObject("quote", quote);

    }

    @GetMapping("/newQuote")
    public ModelAndView insertNewQuote(){
        return new ModelAndView("quotes").addObject("quote", new Quote());
    }



}

