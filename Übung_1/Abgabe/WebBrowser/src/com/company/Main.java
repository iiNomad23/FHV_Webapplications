package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The Main class contains methods for getting
 * user inputs and entity outputs.
 *
 * @author Marco Prescher
 * @version 1.0
 */

public class Main {

    public static void main(String[] args) {
        String url = null;

        try {
            url = getURLInput();
        } catch (IOException e) {
            System.out.println("Error while reading the input: " + e.getMessage());
            System.exit(1);
        }

        String requestResult = Browser.performHttpRequest(Browser.AvailableMethods.GET, Browser.getHostFromURL(url), url);

        System.out.println("Entity:\n\n" + Browser.removeHTTPHeader(requestResult));
    }

    public static String getURLInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String url = null;
        boolean isValidURL = false;

        System.out.println("Please enter URL:");

        while (!isValidURL) {
            url = reader.readLine();

            if (Browser.isValidURL(url)) {
                isValidURL = true;
            } else {
                System.out.println("Please enter a valid URL:");
            }
        }

        return url;
    }
}
