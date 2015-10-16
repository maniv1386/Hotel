package com.agoda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Model {
	
	

	    private static Utils hotelDetails = new Utils();

	    static {  // load at startup
	        loadCSV();
	    }

	    /**
	     * This method return a list of hotels from city parameter.
	     * If the city is invalid, return empty list.
	     */
	    public static ArrayList<Hotel> getHotelByCity(String city) {
	        if (!hotelDetails.containsKey(city))
	            return new ArrayList<Hotel>();
	        else
	            return hotelDetails.get(city);
	    }

	    /**
	     * Returns a list of hotels based on the sort type
	     * If the city is invalid, return empty list.
	     * If sortType parameter is not "asc" or "desc" , return natural order.
	     * If sortType is "asc"  return the hotel list sorted in ascending order according to price.
	     * If sortType is "desc"  return the hotel list sorted in descending order according to price.
	     */
	    public static ArrayList<Hotel> getSortedHotelByCity(String city, String sortType) {

	        if (!hotelDetails.containsKey(city))
	            return new ArrayList<Hotel>();
	        else {
	            String sort = sortType.toLowerCase();
	            ArrayList<Hotel> hotelList = new ArrayList<Hotel>(hotelDetails.get(city));

	            if (sort.equals("asc"))
	                Collections.sort(hotelList);
	            else if (sort.equals("desc"))
	                Collections.sort(hotelList, Collections.reverseOrder());
	            return hotelList;
	        }
	    }

	    /**
	     * This method loads the csv file to a hashmap with city name as the key.
	     */
	    private static void loadCSV() {
	        String csvFile = "hoteldb.csv";
	        InputStream is = Model.class.getClassLoader().getResourceAsStream(csvFile);
	        BufferedReader br = null;
	        String line;
	        String cvsSplitBy = ",";

	        try {
	            br = new BufferedReader(new InputStreamReader(is));
	            String[] headersrow = br.readLine().split(cvsSplitBy);
	            HashMap<String, Integer> headersmap = new HashMap<String, Integer>(); 
	            for (int i = 0; i < headersrow.length; i++) {
	                headersmap.put(headersrow[i], i);
	            }

	            while ((line = br.readLine()) != null) {
	                String[] row = line.split(cvsSplitBy);
	                Hotel hotel = new Hotel();
	                for (int i = 0; i < row.length; i++) {
	                    if (i == headersmap.get("CITY"))
	                        hotel.setCity(row[i]);
	                    else if (i == headersmap.get("HOTELID"))
	                        hotel.setHotelId(Integer.parseInt(row[i]));
	                    else if (i == headersmap.get("ROOM"))
	                        hotel.setRoom(row[i]);
	                    else if (i == headersmap.get("PRICE"))
	                        hotel.setPrice(Double.parseDouble(row[i]));
	                }
	                if (!hotelDetails.containsKey(hotel.getCity())) {
	                    ArrayList<Hotel> arr = new ArrayList<Hotel>();
	                    arr.add(hotel);
	                    hotelDetails.put(hotel.getCity(), arr);
	                } else {
	                    hotelDetails.get(hotel.getCity()).add(hotel);
	                }

	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	}



