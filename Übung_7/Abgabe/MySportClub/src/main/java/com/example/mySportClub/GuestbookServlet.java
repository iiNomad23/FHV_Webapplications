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
    private final List<GuestBookEntry> guestBookEntries = new ArrayList<GuestBookEntry>();

    public void init() {
        guestBookEntries.add(new GuestBookEntry("Marco", "marco.john@hotmail.com", "Lorenzo Von Matterhorn."));
        guestBookEntries.add(new GuestBookEntry("Prescher", "prescher.doe@hotmail.com", "Hallo?"));
    }

    @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        out.println("<html>");

        //#region html header
        out.println("<head>" +
                "   <meta charset='UTF-8'>" +

                "   <title>MySportClub</title>" +

                "   <link rel='stylesheet' href='./css/bootstrap.min.css'>" +
                "   <link rel='stylesheet' href='./css/font-awesome.min.css'>" +
                "   <link rel='stylesheet' href='./css/layout.css'>" +
                "   <link rel='stylesheet' href='./css/print.css'>" +

                "   <script src='./js/jquery-3.6.0.min.js'></script>" +
                "   <script src='./js/bootstrap.min.js'></script>" +
                "   <script src='./js/main.js'></script>" +
                "</head>"
        );
        //#endregion

        out.println("<body>");

        //#region navbar
        out.println("<nav class='navbar navbar-expand-md navbar-dark fixed-top bg-myColor'>" +
                "    <a class='navbar-brand' href='./index.html'>" +
                "        <i class='fa fa-soccer-ball-o'></i>" +
                "        MySportClub" +
                "    </a>" +

                "    <button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbar'" +
                "            aria-controls='navbar' aria-expanded='false' aria-label='Toggle navigation'>" +
                "        <span class='navbar-toggler-icon'></span>" +
                "    </button>" +

                "    <div class='collapse navbar-collapse' id='navbar'>" +
                "        <ul class='navbar-nav mr-auto'>" +
                "            <li class='nav-item'>" +
                "                <a class='nav-link' href='./index.html'>Home <span class='sr-only'>(current)</span></a>" +
                "            </li>" +
                "            <li class='nav-item'>" +
                "                <a class='nav-link' href='clubs/climbing_club.html'>Climbing</a>" +
                "            </li>" +
                "            <li class='nav-item'>" +
                "                <a class='nav-link' href='clubs/cycling_club.html'>Cycling</a>" +
                "            </li>" +
                "            <li class='nav-item'>" +
                "                <a class='nav-link' href='clubs/friendlyFire_eSport_club.html'>Friendly Fire</a>" +
                "            </li>" +
                "        </ul>" +

                "        <ul class='navbar-nav right'>" +
                "            <li class='nav-item active'>" +
                "                <a class='nav-link' href='guestbook_overview'>Guestbook</a>" +
                "            </li>" +
                "            <li class='nav-item'>" +
                "                <a class='nav-link' href='./account.html'>Registry</a>" +
                "            </li>" +
                "            <li class='nav-item'>" +
                "                <a class='nav-link' href='mailto:thisIsAEmail@hotmail.com'>" +
                "                    <i class='fa fa-envelope-o' aria-hidden='true'></i> Contact" +
                "                </a>" +
                "            </li>" +
                "        </ul>" +
                "    </div>" +
                "</nav>"
        );
        //#endregion

        //#region content
        out.println("<main role='main'>");

        // header --------------------------------------------------------
        out.println("<div class='jumbotron my-jumbotron-sub'>" +
                "   <div class='container'>" +
                "       <h1>Guestbook</h1>" +
                "       <p>A collection of comments from different users.</p>" +
                "   </div>" +
                "</div>"
        );

        out.println("<div class='container'>");

        // add entry form ------------------------------------------------
        out.println("<form class='needs-validation' action='guestbook_overview' method='post'>" +
                "   <div class='form-row'>" +
                "        <div class='col-md mb-3'>" +
                "            <label for='test'>Name</label>" +
                "            <input type='text' class='form-control' name='test' id='name' placeholder='John Doe' required>" +
                "            <div class='invalid-feedback'>Please provide a valid name.</div>" +
                "        </div>" +
                "        <div class='col-md mb-3'>" +
                "            <label for='email'>E-Mail</label>" +
                "            <input type='email' class='form-control' name='email' id='email' placeholder='john.doe@email.com' required>" +
                "            <div class='invalid-feedback'>Please provide a valid email.</div>" +
                "        </div>" +
                "   </div>" +
                "   <div class='form-row'>" +
                "        <div class='col-md mb-3'>" +
                "            <label for='message'>Message</label>" +
                "            <textarea type='text' class='form-control' name='message' id='message' placeholder='Good activities!' required></textarea>" +
                "            <div class='invalid-feedback'>Please provide a valid message.</div>" +
                "        </div>" +
                "   </div>" +
                "   <button class='btn my-btn-primary' type='submit'>Add Entry</button>" +
                "</form>"
        );

        // entry table ---------------------------------------------------
        StringBuilder rows = new StringBuilder();
        for (GuestBookEntry entries : guestBookEntries) {
            rows.append("<tr>");

            rows.append("<td>" + entries.getName() + "</td>");
            rows.append("<td>" + entries.getEmail() + "</td>");
            rows.append("<td>" + entries.getMessage() + "</td>");

            rows.append("</tr>");
        }

        out.println("<br><div class='row'>" +
                "    <div class='col-md'>" +
                "        <h3>Entries</h3>" +
                "    </div>" +
                "</div>" +
                "<table class='table table-striped table-sm'>" +
                "    <thead>" +
                "    <tr>" +
                "        <th style='width: 25%'>Name</th>" +
                "        <th style='width: 25%'>E-Mail</th>" +
                "        <th style='width: 50%'>Comment</th>" +
                "    </tr>" +
                "    </thead>" +

                "    <tbody>" +
                rows +
                "    </tbody>" +
                "</table>"
        );

        out.print("</div>");
        out.println("</main>");

        // footer --------------------------------------------------------
        out.println("<footer class='container'>" +
                "    <br><br>" +
                "    <hr>" +
                "    <p>" +
                "        &copy; MySportClub 2021" +
                "        <span class='versionNum'>v1.2</span>" +
                "    </p>" +
                "</footer>"
        );
        //#endregion

        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("test");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        if (name != null && email != null && message != null) {
            guestBookEntries.add(new GuestBookEntry(name, email, message));
        }

        doGet(request, response);
    }

    public void destroy() {
    }
}
