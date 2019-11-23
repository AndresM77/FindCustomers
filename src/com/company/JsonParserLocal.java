package com.company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParserLocal {
    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("CustomerList.txt");

        Object jsonObj = parser.parse(reader);

        System.out.println(jsonObj.toString());
        reader.close();
    }
}
