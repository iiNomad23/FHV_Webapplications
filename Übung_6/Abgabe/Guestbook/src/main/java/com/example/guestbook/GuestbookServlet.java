package com.example.guestbook;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "guestbook_overview", value = "/guestbook_overview")
public class GuestbookServlet extends HttpServlet {
    private List<GuestBookEntry> guestBookEntries = new ArrayList<GuestBookEntry>();

    public void init() {
        guestBookEntries.add(new GuestBookEntry("Jeremias", "jere.huan@hotmail.com", "Geil"));
        guestBookEntries.add(new GuestBookEntry("Heel", "heel.huan@hotmail.com", "Geil"));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        out.println("<html>");

        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>MySportClub</title>");
        out.println("</head>");

        out.println("<body>");
        out.println("<div class='container'>");
        for (GuestBookEntry entries : guestBookEntries) {
            out.println("<div class='row'>");
            out.println("<p>" + entries.getName() + "</p>");
            out.println("<p>" + entries.getEmail() + "</p>");
            out.println("<p>" + entries.getText() + "</p>");
            out.println("<div/>");
        }
        out.print("</div>");
        out.println("</body>");

        out.println("</html>");
    }

    public void destroy() {
    }
}
