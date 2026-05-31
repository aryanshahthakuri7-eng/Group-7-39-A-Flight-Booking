/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 * Controller class managing the flight search data binding and loading.
 */
import dao.FlightDAO;
import java.util.ArrayList;
import model.SearchFlight;
import view.FlightSearch;

public final class FlightController {

    private final FlightSearch view;
    private final FlightDAO dao;

    public FlightController(FlightSearch view){

        this.view=view;
        dao=new FlightDAO();

        loadFlights();

    }

    /**
     * Loads flight data from the DAO database and populates the view search flight cards.
     * It binds flight details like name, departure time, arrival time, class, and price.
     */
    public void loadFlights(){

        ArrayList<SearchFlight> list=
                dao.getFlights();

        if(list.size()>=3){

            SearchFlight f1=list.get(0);

            view.getFlightName1()
                    .setText(f1.getFlightName());

            view.getDepartureTime1()
                    .setText(f1.getDepartureTime());

            view.getArrivalTime1()
                    .setText(f1.getArrivalTime());

            view.getClass1()
                    .setText(f1.getFlightClass());

            view.getPrice1()
                    .setText("NPR "+f1.getPrice());




            SearchFlight f2=list.get(1);

            view.getFlightName2()
                    .setText(f2.getFlightName());

            view.getDepartureTime2()
                    .setText(f2.getDepartureTime());

            view.getArrivalTime2()
                    .setText(f2.getArrivalTime());

            view.getClass2()
                    .setText(f2.getFlightClass());

            view.getPrice2()
                    .setText("NPR "+f2.getPrice());




            SearchFlight f3=list.get(2);

            view.getFlightName3()
                    .setText(f3.getFlightName());

            view.getDepartureTime3()
                    .setText(f3.getDepartureTime());

            view.getArrivalTime3()
                    .setText(f3.getArrivalTime());

            view.getClass3()
                    .setText(f3.getFlightClass());

            view.getPrice3()
                    .setText("NPR "+f3.getPrice());

        }

    }

}