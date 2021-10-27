package com.company;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientHandler implements Runnable {
    private final Socket connection;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public ClientHandler(Socket connection, BufferedReader reader, PrintWriter writer) {
        this.connection = connection;
        this.reader = reader;
        this.writer = writer;
    }

    private static Path getFilePath(String path) {
        if ("/".equals(path)) {
            path = ".\\webroot/index.html";
        } else {
            path = ".\\webroot" + path.replaceAll("/", "\\\\");
        }

        return Paths.get(path);
    }

    @Override
    public void run() {
        try {
            // read input
            StringBuilder httpRequest = new StringBuilder();
            String line;
            while ((line = this.reader.readLine()) != null && !line.isEmpty()) {
                httpRequest.append(line).append("\r\n");
            }

            String request = httpRequest.toString();

            String[] requestLines = request.split("\r\n");
            String[] requestLine = requestLines[0].split(" ");

            String path = requestLine[1];
            String host = requestLines[1].split(" ")[1];

            System.out.println("Thread " + Thread.currentThread().getName() + ", Client " + this.connection.toString() + ", Path " + path + ", Host " + host);

            File file = new File(String.valueOf(getFilePath(path)));
            BufferedReader fileReader = new BufferedReader(new FileReader(file));

            // write HTTP header
            WebServer.writeHTTPHeader("HTTP/1.1 200 OK", this.writer, file);

            // send file content
            while ((line = fileReader.readLine()) != null) {
                this.writer.write(line);
                this.writer.write("\r\n");
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            WebServer.writeHTTPHeader("HTTP/1.1 404 Not Found", this.writer, null);
            this.writer.write("<html><body><h1>404 - Not Found</h1></body></html>\r\n");
        } catch (IOException e) {
            WebServer.writeHTTPHeader("HTTP/1.1 500 Internal Server Error", this.writer, null);
            this.writer.write("<html><body><h1>500 - Internal Server Error</h1></body></html>\r\n");
        } finally {
            this.writer.flush();

            try {
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing the connection of <" + this.connection + ">");
                System.out.println("Exiting...");
                System.exit(1);
            }
        }
    }
}
