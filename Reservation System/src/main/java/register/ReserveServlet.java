package register;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/reserve")
public class ReserveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String origin = request.getParameter("origin");
        String dest = request.getParameter("dest");
        String jclass = request.getParameter("jclass");
        String jdate = request.getParameter("jdate");
        String no_of_pass = request.getParameter("no_of_pass");

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Reservation", "root", "2580");
            PreparedStatement pst = con.prepareStatement("INSERT INTO reserve (origin, dest, jclass, jdate, no_of_pass) VALUES (?, ?, ?, ?, ?)");
            pst.setString(1, origin);
            pst.setString(2, dest);
            pst.setString(3, jclass);
            pst.setString(4, jdate);
            pst.setString(5, no_of_pass);

            int rowCount = pst.executeUpdate();

            if (rowCount > 0) {
                // Set request attributes for the data you want to display in PaymentServlet
                request.setAttribute("origin", origin);
                request.setAttribute("dest", dest);
                request.setAttribute("jclass", jclass);
                request.setAttribute("jdate", jdate);
                request.setAttribute("no_of_pass", no_of_pass);

                // Redirect to Payment.html
                request.getRequestDispatcher("Payment.html").forward(request, response);
            } else {
                request.setAttribute("status", "failed");
                // Handle the failure case here
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle class not found exception
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle SQL exception while closing connection
            }
        }
    }
}
