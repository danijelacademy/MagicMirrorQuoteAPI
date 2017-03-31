package com.example.Domain;

import javax.validation.constraints.Size;

/**
 * Created by Administrator on 2017-03-30.
 */
public class Quote {

    public String quote;

    public String quoteType;


    public Quote (String quote, String quoteType){

        this.quote=quote;
        this.quoteType=quoteType;
    }
    public Quote(){

    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }
}
