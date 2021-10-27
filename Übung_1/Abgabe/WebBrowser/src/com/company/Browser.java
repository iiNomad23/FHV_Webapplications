package com.company;

import java.io.*;
import java.net.IDN;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * The Browser class contains methods for basic
 * operations like making an HTTP request or validating a URL.
 *
 * @author Marco Prescher
 * @version 1.0
 */

public class Browser {
    public enum AvailableMethods {
        GET,
    }

    public static String performHttpRequest(AvailableMethods method, String host, String url) {
        try {
            System.out.println("Connecting to Server <" + host + "> at <" + InetAddress.getByName(host).getHostAddress() + ">\n");
        } catch (UnknownHostException e) {
            System.out.println("\nError while resolving host address (IP address) of <" + host + ">");
            System.out.println("Trying send HTTP request...\n");
        }

        StringBuilder httpResponse = new StringBuilder();

        // Open TCP connection to host
        try {
            Socket socket = new Socket(IDN.toASCII(host), 80);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("GET / HTTP/1.0");
            out.println("Host: " + host);
            out.println("\r\n\r\n");
            out.flush();

            // Write HTTP response into StringBuilder
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            int c = 0;
            while ((c = reader.read()) != -1) {
                httpResponse.append((char) c);
            }

            reader.close();
        } catch (UnknownHostException e) {
            System.out.println("Error while connecting to <" + host + ">");
            System.out.println(e.toString());
            System.exit(1);
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
            System.exit(1);
        }

        return httpResponse.toString();
    }

    public static String removeHTTPHeader(String httpResponse) {
        String[] result = httpResponse.split("(\r?\n){2}|\n{2}", 2);

        // If response is empty or has nothing apart from header, exit program
        if (httpResponse.length() == 0 || result.length < 2 || result[1].isEmpty()) {
            System.out.println("Error while loading the content");
            System.out.println("A possible redirect (HTTP 301, 302, 307, 308) may be the cause");
            System.exit(1);
        }

        String statusCode = getStatusCodeFromResponse(httpResponse);
        if (!statusCode.equals("200")) {
            System.out.println("Error_StatusCode: " + statusCode);
            System.exit(1);
        }

        return result[1];
    }

    public static String getStatusCodeFromResponse(String httpResponse) {
        return httpResponse.substring(9, 12);
    }

    public static boolean isValidURL(String url) {
        return url.matches("^https?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }

    public static String getHostFromURL(String url) {
        return url.replaceFirst("^https?://", "").split("/", 2)[0];
    }
}