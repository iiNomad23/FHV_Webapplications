package com.example.mySportClub.servlet;

import com.example.mySportClub.domain.User;
import com.example.mySportClub.form.AccountRegistryForm;

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
public class AccountRegistryHandlerServlet extends HttpServlet {
    private final List<User> userEntries = new ArrayList<User>();

    public void init() {
        this.userEntries.add(new User("user", "pass", "Jon", "Doe"));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userID");

        if (userId != null) {
            boolean userExist = false;

            for (User user: this.userEntries) {
                if (Objects.equals(user.userId(), userId)) {
                    userExist = true;
                }
            }

            HttpSession session = request.getSession();
            if (userExist) {
                session.setAttribute("registry", new AccountRegistryForm(userId, "User registry failed.", false, userExist));
            } else {
                String password = request.getParameter("password");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");

                if (password != null && firstName != null && lastName != null) {
                    this.userEntries.add(new User(userId, password, firstName, lastName));

                    // in our hotelSoftwareProject we call beans as forms
                    session.setAttribute("registry", new AccountRegistryForm(userId, "User registry successful.", true, userExist));
                } else {
                    session.setAttribute("registry", new AccountRegistryForm(userId, "User registry failed.", false, userExist));
                }
            }

            session.setAttribute("users", this.userEntries);

            response.sendRedirect("welcome.jsp");
        }
    }

    public void destroy() {
    }
}
