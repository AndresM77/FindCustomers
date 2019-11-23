package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class FindCustomersByLocation {

    public static void main(String[] args) {
        //Initialize local Variables
        String jsonData;
        ArrayList<Customer> customers;

        //Read in and parse data from txt files
        jsonData = ReadFile("CustomerList.txt");
        customers = ParseJSON(jsonData);
        customers = OnlyNearBy(customers);

        if (customers == null) {
            System.out.println("Invalid file, json not formatted properly");
        } else {
            customers.sort(new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o1.uid.compareTo(o2.uid);
                }
            });

            System.out.println(customers);
        }

    }

    private static ArrayList<Customer> OnlyNearBy(ArrayList<Customer> customers) {
        
        return customers;
    }

    //Reads data from CustomerList.txt
    public static String ReadFile(String filename) {
        String output = "";
        BufferedReader reader = null;
        try {
            //Reading in the next line
            String curr;
            reader = new BufferedReader(new FileReader(filename));
            while ((curr = reader.readLine()) != null) {
                //Adds current line to output
                output += curr;
                //Keep format
                output += "\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Releasing memory for file reader
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output;
    }

    //Places data into customer objects
    private static ArrayList<Customer> ParseJSON(String file) {
        ArrayList<Customer> customers = new ArrayList<>();
        int start = 0;
        int end = 0;
        // I move forward by increasing the index rather than substringing for efficiency in runtime
        // Loop will exit when no more data to parse
        while (file.indexOf("latitude: \"", end) != -1) {
            //Searching for latitude
            start = file.indexOf("latitude: \"", end) + 11;
            end = file.indexOf("\",", start);
            double lat = Double.parseDouble(file.substring(start, end));

            //Searching for user_id
            start = file.indexOf("user_id: ", end) + 9;
            end = file.indexOf(",", start);
            int uid = Integer.parseInt(file.substring(start, end));

            //Searching for name
            start = file.indexOf("name: \"", end) + 7;
            end = file.indexOf("\",", start);
            String name = file.substring(start, end);

            //Searching for longtitutde
            start = file.indexOf("longitude: \"", end) + 12;
            end = file.indexOf("\" }", start);
            double lon = Double.parseDouble(file.substring(start, end));

            //Create new customer object with parsed values
            customers.add(new Customer(lat, lon, uid, name));
        }

        return customers;
    }


}


