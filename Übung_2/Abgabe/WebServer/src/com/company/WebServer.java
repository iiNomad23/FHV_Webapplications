package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class WebServer {
    public static void createWebServer(int port) throws IOException {
        try {
            ServerSocket socket = new ServerSocket(port);

            while (true) {
                Socket connection = socket.accept();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                PrintWriter writer = new PrintWriter(connection.getOutputStream());

                new Thread(new ClientHandler(connection, reader, writer)).start();
            }
        } catch (IOException e) {
            System.out.println("Error starting the server: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void writeHTTPHeader(String status, Writer writer, File file) {
        try {
            writer.write(status + "\r\n");
            writer.write("Server: MyWebServer/1.1\r\n");

            if (Objects.equals(status, "HTTP/1.1 200 OK")) {
                writer.write("Content-Length: " + file.length() + "\r\n");
            }

            writer.write("Content-Type: text/html\r\n\r\n");
        } catch (IOException e) {
            System.out.println("Error writing HTTP header: " + e.getMessage());
            System.out.println("Exiting...");
            System.exit(1);
        }
    }
}