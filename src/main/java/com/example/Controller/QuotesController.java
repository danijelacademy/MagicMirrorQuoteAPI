package com.example.Controller;


import com.example.Domain.Quote;
import com.example.Domain.User;
import com.example.Repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
    public ModelAndView postNewQuote(@Valid Quote quote, BindingResult bindingResult, HttpSession session) throws Exception {
        // @Valid anger att validering ska ske på quote objektet/parametern enligt valideringsannotations i Quote classen.
        // BindingResult innehåller resultat av denna validering och eventuella valideringsfel.

        if (session.getAttribute("user") == null) { // kollar om user fortfarande är inloggad
            return new ModelAndView("redirect:/index.html");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("addNewQuote").addObject("quote", quote);
        }

        repository.addQuote(quote.getQuote(), quote.getQuoteType());

        return new ModelAndView("redirect:/quotes");
    }

}

