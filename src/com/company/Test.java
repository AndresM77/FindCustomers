package com.company;

import java.util.ArrayList;

public class Test {
    public static final String FILE = "test.txt";
    public static final String OUT_FILE = "testout.txt";
    public static final int MAX_DISTANCE = 100;

    public static void main(String args[]) {
        readTest();
        ParseJSONTest();
        nearByTest();
        sortingTest();
        writeTest();
    }

    private static void writeTest() {
        String readData = FindCustomersByLocation.readFile(FILE);
        ArrayList<Customer> customers = FindCustomersByLocation.parseJSON(readData);
        FindCustomersByLocation.write(customers, OUT_FILE);
        readData = FindCustomersByLocation.readFile(FILE);
        String expectedData =
                "{ latitude: \"36.1111111\", user_id: 1, name: \"Ian Doe\", longitude: \"-118.234234\" }\n" +
                "{ latitude: \"33.7451022\", user_id: 3, name: \"Ian Pat\", longitude: \"-124.238335\" }\n" +
                "{ latitude: \"37.5302756\", user_id: 2, name: \"John Doe\", longitude: \"-122.4097222\" }\n";
        if (readData.equals(expectedData)) {
            System.out.println("writeTest: Success!");
        } else {
            System.out.println("writeTest: Failure");
        }
        System.out.println(readData);
    }

    private static void sortingTest() {
        String readData = FindCustomersByLocation.readFile(FILE);
        ArrayList<Customer> customers = FindCustomersByLocation.parseJSON(readData);
        customers = FindCustomersByLocation.sortCustomers(customers);
        String customersToString = "";
        for (int i = 0; i < customers.size(); i++) {
            customersToString += customers.get(i).toString();
            //Need to account for newline done by println in write
            customersToString += "\n";
        }

        String expectedData =
                "{ latitude: \"36.1111111\", user_id: 1, name: \"Ian Doe\", longitude: \"-118.234234\" }\n" +
                "{ latitude: \"37.5302756\", user_id: 2, name: \"John Doe\", longitude: \"-122.4097222\" }\n" +
                "{ latitude: \"33.7451022\", user_id: 3, name: \"Ian Pat\", longitude: \"-124.238335\" }\n";
        if (customersToString.equals(expectedData)) {
            System.out.println("sortingTest: Success!");
        } else {
            System.out.println("sortingTest: Failure");
        }
        System.out.println(customersToString);
    }

    private static void ParseJSONTest() {
        //This test not only checks proper parsing but also whether customer's tostring works as well
        //Thus this tests the function write as well
        String readData = FindCustomersByLocation.readFile(FILE);
        ArrayList<Customer> customers = FindCustomersByLocation.parseJSON(readData);
        String customersToString = "";
        for (int i = 0; i < customers.size(); i++) {
            customersToString += customers.get(i).toString();
            //Need to account for newline done by println in write
            customersToString += "\n";
        }

        String expectedData =
                "{ latitude: \"36.1111111\", user_id: 1, name: \"Ian Doe\", longitude: \"-118.234234\" }\n" +
                "{ latitude: \"33.7451022\", user_id: 3, name: \"Ian Pat\", longitude: \"-124.238335\" }\n" +
                "{ latitude: \"37.5302756\", user_id: 2, name: \"John Doe\", longitude: \"-122.4097222\" }\n";
        if (customersToString.equals(expectedData)) {
            System.out.println("customersToString: Success!");
        } else {
            System.out.println("customersToString: Failure");
        }
        System.out.println(customersToString);
    }


    private static void nearByTest() {
        //The validity of the expected data was checked with the following online calculator for lat long distances:
        //https://www.nhc.noaa.gov/gccalc.shtml
        String readData = FindCustomersByLocation.readFile(FILE);
        ArrayList<Customer> customers = FindCustomersByLocation.parseJSON(readData);
        customers = FindCustomersByLocation.onlyNearBy(customers, MAX_DISTANCE);

        String customersToString = "";
        for (int i = 0; i < customers.size(); i++) {
            customersToString += customers.get(i).toString();
            //Need to account for newline done by println in write
            customersToString += "\n";
        }

        String expectedData =
                "{ latitude: \"33.7451022\", user_id: 3, name: \"Ian Pat\", longitude: \"-124.238335\" }\n" +
                "{ latitude: \"37.5302756\", user_id: 2, name: \"John Doe\", longitude: \"-122.4097222\" }\n";
        if (customersToString.equals(expectedData)) {
            System.out.println("nearByTest: Success!");
        } else {
            System.out.println("nearByTest: Failure");
        }
        System.out.println(customersToString);
    }

    private static void readTest() {
        String readData = FindCustomersByLocation.readFile(FILE);
        String expectedData =
                "{ latitude: \"36.1111111\", user_id: 1, name: \"Ian Doe\", longitude: \"-118.234234\" }\n" +
                "{ latitude: \"33.7451022\", user_id: 3, name: \"Ian Pat\", longitude: \"-124.238335\" }\n" +
                "{ latitude: \"37.5302756\", user_id: 2, name: \"John Doe\", longitude: \"-122.4097222\" }\n";
        if (readData.equals(expectedData)) {
            System.out.println("readTest: Success!");
        } else {
            System.out.println("readTest: Failure");
        }
        System.out.println(readData);
    }
}
