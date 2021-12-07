package com.example.mySportClub.servlet;

import com.example.mySportClub.form.LoginForm;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "index.html", value = "/index.html")
public class CookieHandlerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("cache-control", "no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");

        boolean isCookieRedirect = false;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("lastVisitedPage")) {
                    isCookieRedirect = true;

                    String redirectURL = c.getValue();
                    if (redirectURL.contains("clubs/")) {
                        HttpSession session = request.getSession();
                        LoginForm login = (LoginForm) session.getAttribute("login");

                        if (login == null || !login.isValidLogin()) {
                            session.setAttribute("originURL", redirectURL.replace("/MySportClub_war_exploded/", ""));
                            redirectURL = "/MySportClub_war_exploded/login.jsp";
                        }
                    }

                    response.sendRedirect(redirectURL);
                    break;
                }
            }
        }

        if (!isCookieRedirect) {
            response.sendRedirect("home.html");
        }
    }
}
