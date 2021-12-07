package com.example.mySportClub.servlet;

import com.example.mySportClub.domain.User;
import com.example.mySportClub.form.LoginForm;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "loginHandler", value = "/loginHandler")
public class LoginHandlerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dispatchto = request.getParameter("dispatchto");

        if (dispatchto != null) {
            HttpSession session = request.getSession();

            @SuppressWarnings("unchecked")
            List<User> userEntries = (List<User>) session.getAttribute("users");
            if (userEntries == null) {
                userEntries = new ArrayList<User>(Collections.singletonList(
                        new User("user", "pass", "Jon", "Doe")
                ));
            }

            // clicked on sub sport page
            if (dispatchto.contains("clubs/")) {
                LoginForm login = (LoginForm) session.getAttribute("login");

                if (login == null || !login.isValidLogin()) {
                    session.setAttribute("originURL", dispatchto);
                    dispatchto = dispatchto.startsWith("../") ? "../login.jsp" : "login.jsp";
                }
            }

            session.setAttribute("users", userEntries);

            response.sendRedirect(dispatchto);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dispatchto = "login.jsp";

        // login request
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<User> userEntries = (List<User>) session.getAttribute("users");
        if (userEntries == null) {
            userEntries = new ArrayList<User>(Collections.singletonList(
                    new User("user", "pass", "Jon", "Doe")
            ));
        }

        String userId = request.getParameter("userID");
        String password = request.getParameter("password");

        LoginForm login = new LoginForm(userId, password, false);

        if (userId != null && password != null) {
            for (User user : userEntries) {
                if (Objects.equals(user.userId(), userId) && Objects.equals(user.password(), password)) {
                    String originURL = (String) session.getAttribute("originURL");
                    dispatchto = originURL != null ? originURL : "home.html";

                    login.loginSuccessful();

                    break;
                }
            }
        }

        session.setAttribute("login", login);

        response.sendRedirect(dispatchto);
    }

    public void destroy() {
    }
}
