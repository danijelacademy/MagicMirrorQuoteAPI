package com.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017-03-30.
 */

@Component
public class Repository {

    @Autowired
    private DataSource dataSource;


    //FÖRSTA METOD BÖRJAR HÄR.

    public List<Quote> getQuotes() {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT* FROM Mirror.Quotes")) { //Hämtar alla rader i DB
            try (ResultSet rs = ps.executeQuery()) {
                List<Quote> quotes = new ArrayList<>(); //Skapar en array
                while(rs.next()){
                    quotes.add(rsQuote(rs)); //Lagrar resultaten i arrayen
                }
                return quotes;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    private Quote rsQuote(ResultSet rs) throws SQLException {
        return new Quote(
                rs.getString(2)//Quote
        );

    }


}