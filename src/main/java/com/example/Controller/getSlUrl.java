package com.example.Controller;

public class getSlUrl {

    public static String returnUrl (String username){
        String returnval = "";
        if(username.equals("Matilda")){
            returnval = "http://api.sl.se/api2/TravelplannerV2/trip.json?key=cf85888d5497444795b1ba969383ebcd&originId=9112&destId=9509&searchForArrival=0";
        }else if(username.equals("Robin")){
            returnval = "http://api.sl.se/api2/TravelplannerV2/trip.json?key=cf85888d5497444795b1ba969383ebcd&originId=9112&destId=9509&searchForArrival=0";
        }else
            returnval = "http://api.sl.se/api2/TravelplannerV2/trip.json?key=cf85888d5497444795b1ba969383ebcd&originId=9204&destId=1002&searchForArrival=0";

        return returnval;
    }
}
