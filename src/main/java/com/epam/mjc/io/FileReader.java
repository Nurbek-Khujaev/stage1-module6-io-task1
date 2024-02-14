package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {

        try (BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file))) {

            String name = null;
            Integer age = null;
            String email = null;
            Long phone = null;

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(": ", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    switch (key) {
                        case "Name":
                            name = value;
                            break;
                        case "Age":
                            age = Integer.parseInt(value);
                            break;
                        case "Email":
                            email = value;
                            break;
                        case "Phone":
                            phone = Long.parseLong(value);
                            break;
                        default:
                            System.out.println("unknown key " + key);
                            break;
                    }
                }
            }

            return new Profile(name, age, email, phone);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}



