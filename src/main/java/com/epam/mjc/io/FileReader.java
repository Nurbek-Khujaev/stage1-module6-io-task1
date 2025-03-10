package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;




public class FileReader {

    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());


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
                            if (LOGGER.isLoggable(java.util.logging.Level.WARNING)) {
                                LOGGER.warning(String.format("Unrecognized key: %s", key));
                            }
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



