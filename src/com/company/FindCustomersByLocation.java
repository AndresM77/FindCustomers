package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class FindCustomersByLocation {
    //Declaring constants
    private static final String FILE = "CustomerList.txt";
    private static final String NEW_FILE = "output.txt";
    private static final double OFFICE_LAT = 37.788802;
    private static final double OFFICE_LONG = -122.4025067;
    private static final double NAUT_TO_KM = 1.852;
    private static final int MAX_DISTANCE = 100;

    public static void main(String[] args) {
        //Initialize local Variables
        String jsonData;
        ArrayList<Customer> customers;

        //Read in and parse data from txt files
        jsonData = readFile(FILE);
        customers = parseJSON(jsonData);
        customers = onlyNearBy(customers, MAX_DISTANCE);
        customers = sortCustomers(customers);

        if (customers == null) {
            System.out.println("Invalid file, json not formatted properly");
        } else {
            System.out.println(customers);
            write(customers, NEW_FILE);
        }

    }

    public static ArrayList<Customer> sortCustomers(ArrayList<Customer> customers) {
        customers.sort(new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return o1.uid.compareTo(o2.uid);
            }
        });
        return customers;
    }

    public static void write(ArrayList<Customer> customers, String file) {
        PrintWriter writer = null;
        try {
            //Creating a new file to write to
            writer = new PrintWriter(new FileWriter(file));
            //Writing to new file customer by customer with a new line each time
            for (int i = 0; i < customers.size(); i++) {
                writer.println(customers.get(i));
            }
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            //Closes file writer if it was instantiated
            if (writer != null)
                writer.close();
        }
    }

    public static ArrayList<Customer> onlyNearBy(ArrayList<Customer> customers, int proximity) {
        for (int i = 0; i < customers.size(); i++) {
            //Initialize variables with radian versions of lat long
            double x1 = Math.toRadians(customers.get(i).lat);
            double y1 = Math.toRadians(customers.get(i).lon);
            double x2 = Math.toRadians(OFFICE_LAT);
            double y2 = Math.toRadians(OFFICE_LONG);

            //Do calculations
            double angle = Math.acos(Math.sin(x1) * Math.sin(x2) + Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2));
            angle = Math.toDegrees(angle);
            //Each degree corresponds to a nautical mile
            double distance = angle * 60;
            //Convert to kilometers
            distance = distance * NAUT_TO_KM;

            //Remove customer from list if customer is too far
            if (distance > proximity) {
                customers.remove(i);
                i--;
            }
        }

        return customers;
    }

    //Reads data from CustomerList.txt
    public static String readFile(String filename) {
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
                //Releasing memory for file reader if instantiated
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output;
    }

    //Places data into customer objects
    public static ArrayList<Customer> parseJSON(String file) {
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

            //Searching for longitutde
            start = file.indexOf("longitude: \"", end) + 12;
            end = file.indexOf("\" }", start);
            double lon = Double.parseDouble(file.substring(start, end));

            //Create new customer object with parsed values
            customers.add(new Customer(lat, lon, uid, name));
        }

        return customers;
    }


}


