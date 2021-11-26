package com.example.mySportClub;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "index.html", value = "/index.html")
public class CookieHandler extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("cache-control", "no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");

        boolean isCookieRedirect = false;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("lastVisitedPage")) {
                    isCookieRedirect = true;

                    response.sendRedirect(c.getValue());
                    break;
                }
            }
        }

        if (!isCookieRedirect) {
            response.sendRedirect("home.html");
        }
    }
}
