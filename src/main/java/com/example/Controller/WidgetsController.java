package com.example.Controller;


import com.example.Domain.Quote;
import com.example.Domain.User;
import com.example.Repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class WidgetsController {

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
        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/index.html");
        }

        return new ModelAndView("dashboard");
    }

    @GetMapping("/magicRoute")
    public ModelAndView magicRoute (HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/index.html");
        }

        return new ModelAndView("addSlRoute");
    }

    @GetMapping("/deleteQuote/{id}")
    public ModelAndView deleteQuote (HttpSession session, @PathVariable Integer id ) throws Exception {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/index.html");
        }
        repository.deleteQuote(id);
        return new ModelAndView("redirect:/quotes");
    }


    @GetMapping("/magicCountdown")
    public ModelAndView magicCountdown (HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/index.html");
        }

        return new ModelAndView("addCountDown");
    }

    @GetMapping("/newQuote")
    public ModelAndView insertNewQuote(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/index.html");
        }

        return new ModelAndView("addNewQuote").addObject("quote", new Quote());
    }

    @PostMapping("/newQuote")
    public ModelAndView postNewQuote(@Valid Quote quote, BindingResult bindingResult, HttpSession session) throws Exception {
        // @Valid anger att validering ska ske på quote objektet/parametern enligt valideringsannotations i Quote classen.
        // BindingResult innehåller resultat av denna validering och eventuella valideringsfel.

        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/index.html");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("addNewQuote").addObject("quote", quote);
        }

        //Sök igenom strängen efter dash
        boolean dash = false;
        for(int i =0; i<quote.getQuote().length();i++){
            if(quote.getQuote().charAt(i)=='-'){
              dash=true;
            }
        }

        //ornda fnuttar på strängen

        if(!dash) { //om string inte har dash
            if (quote.getQuote().charAt(0) != '"' && quote.getQuote().charAt(quote.getQuote().length() - 1) != '"') {
                repository.addQuote('"' + quote.getQuote() + '"', quote.getQuoteType());
            } else if (quote.getQuote().charAt(0) != '"' && quote.getQuote().charAt(quote.getQuote().length() - 1) == '"') {

                repository.addQuote('"' + quote.getQuote(), quote.getQuoteType());
            } else if (quote.getQuote().charAt(0) == '"' && quote.getQuote().charAt(quote.getQuote().length() - 1) != '"') {
                repository.addQuote(quote.getQuote() + '"', quote.getQuoteType());
            } else {
                repository.addQuote(quote.getQuote(), quote.getQuoteType());
            }
        }

        else{ //annars har dash
            String quo="";


            if(quote.getQuote().charAt(0)!='"' && quote.getQuote().charAt(quote.getQuote().lastIndexOf('-')-1)!='"') { //funkar
                quo=quo+'"'+quote.getQuote().substring(0, quote.getQuote().lastIndexOf('-')-1)+
                        '"'+quote.getQuote().substring(quote.getQuote().lastIndexOf('-')-1);
                repository.addQuote(quo, quote.getQuoteType());
            }
            else if(quote.getQuote().charAt(0)=='"'&&quote.getQuote().charAt(quote.getQuote().lastIndexOf('-')-1)!='"'){ //funkar
                quo=quo+quote.getQuote().substring(0, quote.getQuote().lastIndexOf('-')-1)+
                        '"'+quote.getQuote().substring(quote.getQuote().lastIndexOf('-')-1);
                repository.addQuote(quo, quote.getQuoteType());
            }
            else if(quote.getQuote().charAt(0)!='"'){
                quo=quo+'"'+quote.getQuote();
                repository.addQuote(quo, quote.getQuoteType());
            }

        }

        return new ModelAndView("redirect:/quotes");
    }

}

