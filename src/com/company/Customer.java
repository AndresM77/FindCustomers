package com.company;
import java.lang.Integer;

public class Customer implements Comparable<Customer> {
    Double lat;
    Double lon;
    String name;
    //Defined as an integer for comparisons
    Integer uid;

    public Customer(Double lat, Double lon, int uid, String name) {
        this.lat = lat;
        this.lon = lon;
        this.uid = uid;
        this.name = name;
    }

    public String toString() {
        return ("{ latitude: \"" + lat + "\", user_id: " + uid + ", name: \"" + name + "\", longitude: \"" + lon + "\" }");
    }

    @Override
    public int compareTo(Customer o) {
        return uid.compareTo(o.uid);
    }
}
