package com.example.mySportClub.servlet;

import com.example.mySportClub.form.AccountRegistryForm;
import com.example.mySportClub.domain.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "accountRegistryHandler", value = "/accountRegistryHandler")
//@WebServlet(name = "guestbook_overview", value = "/guestbook_overview")
public class AccountRegistryHandlerServlet extends HttpServlet {
    private final List<User> userEntries = new ArrayList<User>();

    public void init() {
        userEntries.add(new User("user", "pass", "Jon", "Doe"));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userID");

        if (userId != null) {
            boolean userExist = false;

            for (User user: userEntries) {
                if (Objects.equals(user.userId(), userId)) {
                    userExist = true;
                }
            }

            HttpSession session = request.getSession();
            if (userExist) {
                session.setAttribute("registry", new AccountRegistryForm(userId, false));
                response.sendRedirect("account.jsp");
            } else {
                String password = request.getParameter("password");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");

                if (password != null && firstName != null && lastName != null) {
                    userEntries.add(new User(userId, password, firstName, lastName));

                    // in our hotelSoftwareProject we call beans as forms
                    session.setAttribute("registry", new AccountRegistryForm(userId, true));
                    response.sendRedirect("welcome.jsp");
                } else {
                    session.setAttribute("registry", new AccountRegistryForm(userId, false));
                    response.sendRedirect("account.jsp");
                }
            }
        }
    }

    public void destroy() {
    }
}
