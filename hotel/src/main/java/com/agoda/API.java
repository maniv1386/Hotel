package com.agoda;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/hotel")
public class API {
	/**
     * Handle GET request and return a version.
     * Return type is plain text.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getVersion() {
        return "Welcome";
    }

    /**
     * Handle GET request with two query parameters.
     * This method return a list of hotels from city.
     * Return type is plain text.
     */
    @Path("/city")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHotelQueryParam(@QueryParam("city") String city, @QueryParam("sort") String sortType) {

        if (city == null || sortType == null)
            return "";

        ArrayList<Hotel> hotellist;
        if (sortType.equalsIgnoreCase("asc") || sortType.equalsIgnoreCase("desc"))
            hotellist = Model.getSortedHotelByCity(city, sortType);
        else
            hotellist = Model.getHotelByCity(city);

        StringBuilder returnString = new StringBuilder();
        for (Hotel h : hotellist) {
            returnString.append(h.getInfo());
            returnString.append("\n");
        }
        return returnString.toString();
    }

    /**
     * Handle GET request with path parameter.
     * This method return a list of hotels from city.
     * Return type is plain text.
     */
    @Path("/city/{city}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHotelPathParam(@PathParam("city") String city) {

        ArrayList<Hotel> hotellist = Model.getHotelByCity(city);
        StringBuilder returnString = new StringBuilder();
        for (Hotel h : hotellist) {
            returnString.append(h.getInfo());
            returnString.append("\n");
        }
        return returnString.toString();
    }

    /**
     * Handle GET request with two path parameters (city and sort type).
     * This method return a list of sorted hotels from city.
     * Sort param must be either "desc" or "asc" (case insensitive).
     * Return type is plain text.
     */
    @Path("/city/{city}/{sort}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getSortedHotelPathParam(@PathParam("city") String city, @PathParam("sort") String sort) {

        ArrayList<Hotel> hotellist = Model.getSortedHotelByCity(city, sort);
        StringBuilder returnString = new StringBuilder();
        for (Hotel h : hotellist) {
            returnString.append(h.getInfo());
            returnString.append("\n");
        }
        return returnString.toString();
    }

}
