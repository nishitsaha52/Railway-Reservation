package register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final static String query = "select * from train where trn_no=?";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        int trn_no = Integer.parseInt(req.getParameter("trn_no"));

        pw.println("<!DOCTYPE html>");
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>Edit Train Information</title>");
        // Add responsive meta tag
        pw.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
        pw.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
        pw.println("<link rel='stylesheet' type='text/css' href='edit.css'>");
        pw.println("</head>");
        pw.println("<body>");

        // Navigation Bar
        pw.println("<nav class='navbar navbar-expand-lg navbar-light bg-light'>");
        pw.println("    <img src='images/logo.png' alt='Logo' height='75' width='75'>");
        pw.println("    <button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>");
        pw.println("        <span class='navbar-toggler-icon'></span>");
        pw.println("    </button>");
        pw.println("    <div class='collapse navbar-collapse justify-content-end' id='navbarNav'>");
        pw.println("        <ul class='navbar-nav'>");
        pw.println("            <li class='nav-item active'>");
        pw.println("                <a class='nav-link m-2' href='updatetrain' style='background-color: blue; color: white;'>Home</a>");
        pw.println("            </li>");
        pw.println("            <li class='nav-item active'>");
        pw.println("                <a class='nav-link m-2' href='#' style='background-color: blue; color: white;'>About</a>");
        pw.println("            </li>");
        pw.println("            <li class='nav-item active'>");
        pw.println("                <a class='nav-link m-2' href='#' style='background-color: blue; color: white;'>Contact</a>");
        pw.println("            </li>");
        pw.println("        </ul>");
        pw.println("    </div>");
        pw.println("</nav>");

        // Center align the form
        pw.println("<div class='container text-center mt-5'>");
        pw.println("<h2>Edit Train Information</h2>");
        pw.println("<form action='edit?trn_no=" + trn_no + "' method='post'>");
        pw.println("<table class='table table-hover table-striped'>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Reservation", "root", "2580");
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, trn_no);
            ResultSet rs = ps.executeQuery();
            rs.next();

            // Generate form fields dynamically based on ResultSet
            String[] fieldNames = {"trn_no", "tName", "origin", "dest", "aTime", "dTime", "1A", "2A", "3A", "3E", "SL"};
            for (String fieldName : fieldNames) {
                pw.println("<tr>");
                pw.println("<td>" + (fieldName.equals("trn_no") ? "Train Number" :
                                fieldName.equals("tName") ? "Train Name" :
                                fieldName.equals("origin") ? "Source" :
                                fieldName.equals("dest") ? "Destination" :
                                fieldName.equals("aTime") ? "Arrival Time" :
                                fieldName.equals("dTime") ? "Departure Time" :
                                fieldName.equals("1A") ? "Class 1A" :
                                fieldName.equals("2A") ? "Class 2A" :
                                fieldName.equals("3A") ? "Class 3A" :
                                fieldName.equals("3E") ? "Class 3E" :
                                fieldName.equals("SL") ? "Class SL" : fieldName) + "</td>");
                pw.println("<td><input type='text' name='" + fieldName + "' value='" + rs.getString(fieldName) + "'></td>");
                pw.println("</tr>");
            }

            pw.println("<tr>");
            pw.println("<td><button type='submit' class='btn btn-success float-left'>Edit</button></td>"); // Align Edit button to the left
            pw.println("</tr>");

        } catch (SQLException se) {
            pw.println("<div class='alert alert-danger' role='alert'>" + se.getMessage() + "</div>");
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        pw.println("</table>");
        pw.println("</form>");
        pw.println("<td><a href='updatetrain'><button class='btn btn-success float-right'>Back</button></a></td>");
        pw.println("</div>");

        // Footer
        pw.println("<footer class='footer bg-light mt-5 text-center'>");
        pw.println("<div class='container'>");
        pw.println("<p>&copy; 2023 Train Reservation System</p>");
        pw.println("</div>");
        pw.println("</footer>");

        pw.println("</body>");
        pw.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
