package org.example.formservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class DbDemoServlet extends HttpServlet {

    private Connection connection;

    @Override
    public void init() throws ServletException {
        super.init();

        String pilote = getServletContext().getInitParameter("jdbcDriver");
        String url = getServletContext().getInitParameter("dbUrl");
        String user = getServletContext().getInitParameter("dbUser");
        String password = getServletContext().getInitParameter("dbPassword");

        try{
            Class.forName(pilote);
             connection= DriverManager.getConnection(url,user,password);
            System.out.println("DbDemoServlet: Connection to DB established successfully!");
        } catch (ClassNotFoundException e){
            throw new ServletException("Could not load JDBC Driver", e);
        } catch (SQLException e) {
            throw new ServletException("Could not connect to DB", e);
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String city = request.getParameter("city");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out =response.getWriter();

        //Insertion dans ma DB
        String query= "INSERT INTO persons(name, email, city) VALUES(?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3,city);

            int rowsInserted =ps.executeUpdate();
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("  <meta charset='UTF-8'>");
            out.println("  <title>DB Demo - Insert</title>");
            out.println("  <link rel='stylesheet' href='css/style.css'>");
            out.println("</head>");
            out.println("<body>");

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

            out.println("  <main>");
            out.println("    <section class='form-container'>");
            out.println("      <div class='container'>");

            if (rowsInserted > 0) {
                out.println("        <h1>Data Inserted Successfully!</h1>");
                out.println("        <div class='confirmation-details'>");
                out.println("          <p><strong>Name:</strong> " + name + "</p>");
                out.println("          <p><strong>Email:</strong> " + email + "</p>");
                out.println("          <p><strong>City:</strong> " + city + "</p>");
                out.println("        </div>");
            } else {
                out.println("        <h1>Error: No Data Inserted!</h1>");
            }

            out.println("        <div class='action-links'>");
            out.println("          <a href='dbDemo' class='btn'>View All Persons</a>");
            out.println("          <a href='dbForm.html' class='btn'>Go Back to Form</a>");
            out.println("        </div>");

            out.println("      </div>");
            out.println("    </section>");
            out.println("  </main>");

            out.println("  <footer>");
            out.println("    <div class='container'>");
            out.println("      <p>&copy; 2025 MonProjetJEE. All rights reserved.</p>");
            out.println("    </div>");
            out.println("  </footer>");

            out.println("</body>");
            out.println("</html>");

        }catch(SQLException e){
            throw new ServletException(e);
        }

    }
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String selectQuery = "SELECT id, name, email, city FROM persons";
        try (Statement stmt= connection.createStatement(); ResultSet rs =stmt.executeQuery(selectQuery)){

            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("  <meta charset='UTF-8'>");
            out.println("  <title>DB Demo - All Persons</title>");
            out.println("  <link rel='stylesheet' href='css/style.css'>");
            out.println("</head>");
            out.println("<body>");

            // Header and Navigation
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
            // Main Content
            out.println("  <main>");
            out.println("    <section class='form-container'>");
            out.println("      <div class='container'>");
            out.println("        <h1>List of All Persons</h1>");
            // Tableau de données avec en-tête et corps
            out.println("        <table class='data-table' cellspacing='0' cellpadding='5'>");
            out.println("          <thead>");
            out.println("            <tr>");
            out.println("              <th>ID</th>");
            out.println("              <th>Name</th>");
            out.println("              <th>Email</th>");
            out.println("              <th>City</th>");
            out.println("            </tr>");
            out.println("          </thead>");
            out.println("          <tbody>");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String city = rs.getString("city");
                out.println("            <tr>");
                out.println("              <td>" + id + "</td>");
                out.println("              <td>" + name + "</td>");
                out.println("              <td>" + email + "</td>");
                out.println("              <td>" + city + "</td>");
                out.println("            </tr>");
            }
            out.println("          </tbody>");
            out.println("        </table>");

// Liens d'action pour naviguer
            out.println("        <div class='action-links'>");
            out.println("          <a href='dbForm.html' class='btn'>Insert Another Person</a>");
            out.println("        </div>");

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
        } catch (SQLException e){
            throw new ServletException("Error reading data from DB", e);
        }
}


    @Override
    public void destroy() {

        if (connection != null){
            try {
                connection.close();
                System.out.println("DbDemoServlet: Connection closed successfully!");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        super.destroy();
    }
}
