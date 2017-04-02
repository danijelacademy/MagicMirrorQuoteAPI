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


import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class QuotesController {

    @Autowired
    Repository repository;

    @GetMapping("/quotes")
    public ModelAndView listQuotes(HttpSession session) { // Spring injectar session automatiskt.
        if (session.getAttribute("user") == null) { // kollar om user fortfarande är inloggad
            return new ModelAndView("redirect:/index.html");
        }

        List<Quote> quotes = repository.getQuotes();

        return new ModelAndView("listQuotes").addObject("quotes", quotes);
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard(HttpSession session) {
        if (session.getAttribute("user") == null) { // kollar om user fortfarande är inloggad
            return new ModelAndView("redirect:/index.html");
        }

        return new ModelAndView("dashboard");
    }

    @GetMapping("/newQuote")
    public ModelAndView insertNewQuote(HttpSession session) {
        if (session.getAttribute("user") == null) { // kollar om user fortfarande är inloggad
            return new ModelAndView("redirect:/index.html");
        }

        return new ModelAndView("addNewQuote").addObject("quote", new Quote());
    }

    @PostMapping("/newQuote")
    public ModelAndView postNewQuote(@RequestParam Quote quote, HttpSession session) throws Exception {
        if (session.getAttribute("user") == null) { // kollar om user fortfarande är inloggad
            return new ModelAndView("redirect:/index.html");
        }

        repository.addQuote(quote.quote, quote.quoteType);

        return new ModelAndView("addNewQuote").addObject("quote", quote);
    }

}

