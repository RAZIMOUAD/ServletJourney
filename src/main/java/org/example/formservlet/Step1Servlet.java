package org.example.formservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class Step1Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");

        session.setMaxInactiveInterval(5*60);
        session.setAttribute("fullname", fullname);
        session.setAttribute("email", email);
        response.sendRedirect("Step2.html");
    }
}
