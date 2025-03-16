package org.example.formservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class Step2Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session == null){
            resp.sendRedirect("Step1.html");
            return;
        }
        String address = req.getParameter("address");
        String city = req.getParameter("city");

        session.setAttribute("address", address);
        session.setAttribute("city", city);
        resp.sendRedirect("summary");
    }
}
