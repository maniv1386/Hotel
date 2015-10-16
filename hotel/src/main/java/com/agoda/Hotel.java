package com.agoda;

public class Hotel implements Comparable {
	private int hotelId;
    private String city;
    private String room;
    private double price;

    public Hotel() {
    }

    public Hotel(int id, String hotel_city, String hotel_room, double price) {
        hotelId = id;
        city = hotel_city;
        room = hotel_room;
        this.price = price;
    }

    public String getInfo() {
        return hotelId + " " + city + " " + room + " " + price;
    }

    public int getHotelId() {
        return hotelId;
    }

    public String getCity() {
        return city;
    }

    public String getRoom() {
        return room;
    }

    public double getPrice() {
        return price;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int compareTo(Object o) {
        Hotel other = (Hotel) o;
        if (this.getPrice() < other.getPrice())
            return -1;
        else if (this.getPrice() > other.getPrice())
            return 1;
        else
            return 0;
    }


}
