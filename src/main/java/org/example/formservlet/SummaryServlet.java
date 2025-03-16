package org.example.formservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SummaryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupère la session existante, sans en créer une nouvelle
        HttpSession session = request.getSession(false);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("  <meta charset='UTF-8'>");
        out.println("  <title>Summary - MonProjetJEE</title>");
        out.println("  <link rel='stylesheet' href='css/style.css'>");
        out.println("</head>");
        out.println("<body>");

        // Header et navigation cohérents avec l'application
        out.println("  <header>");
        out.println("    <nav class='navbar'>");
        out.println("      <div class='container'>");
        out.println("        <a href='index.html' class='logo'>MonProjetJEE</a>");
        out.println("        <ul class='nav-links'>");
        out.println("          <li><a href='index.html'>Home</a></li>");
        out.println("          <li><a href='Form.html'>Register</a></li>");
        out.println("          <li><a href='dbForm.html'>DB Demo</a></li>");
        out.println("        </ul>");
        out.println("      </div>");
        out.println("    </nav>");
        out.println("  </header>");

        // Corps principal : affichage du récapitulatif
        out.println("  <main>");
        out.println("    <section class='form-container'>");
        out.println("      <div class='container'>");
        out.println("        <h1>Summary of Your Data</h1>");

        if (session == null) {
            out.println("        <p>No session found. Please start the wizard from the beginning.</p>");
            out.println("        <p><a href='Step1.html' class='btn'>Go to Step 1</a></p>");
        } else {
            // Récupération des attributs stockés dans la session
            String fullName = (String) session.getAttribute("fullName");
            String email = (String) session.getAttribute("email");
            String address = (String) session.getAttribute("address");
            String city = (String) session.getAttribute("city");


            if (fullName == null || email == null || address == null || city == null) {
                out.println("        <p>Some information is missing. Please complete all steps.</p>");
                out.println("        <p><a href='Step1.html' class='btn'>Restart Wizard</a></p>");
            } else {
                out.println("        <p><strong>Full Name:</strong> " + fullName + "</p>");
                out.println("        <p><strong>Email:</strong> " + email + "</p>");
                out.println("        <p><strong>Address:</strong> " + address + "</p>");
                out.println("        <p><strong>City:</strong> " + city + "</p>");
                out.println("        <p>Session ID: " + session.getId() + "</p>");
                out.println("        <p><a href='sessionInfo' class='btn'>View Session Info</a></p>");
            }
        }

        out.println("      </div>");
        out.println("    </section>");
        out.println("  </main>");

        // Footer
        out.println("  <footer>");
        out.println("    <div class='container'>");
        out.println("      <p>&copy; 2025 MonProjetJEE. All rights reserved.</p>");
        out.println("    </div>");
        out.println("  </footer>");

        out.println("</body>");
        out.println("</html>");
    }
}