package register;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardNumber = request.getParameter("card-number");
        String cardHolder = request.getParameter("card-holder");
        String expirationDate = request.getParameter("expiration-date");
        String cvv = request.getParameter("cvv");

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Reservation", "root", "2580");

            // Simulate payment processing
            boolean paymentSuccessful = performPaymentProcessing(cardNumber, cardHolder, expirationDate, cvv);

            // Retrieve reservation details from request attributes
            String origin = (String) request.getAttribute("origin");
            String dest = (String) request.getAttribute("dest");
            String jclass = (String) request.getAttribute("jclass");
            String jdate = (String) request.getAttribute("jdate");
            String no_of_pass = (String) request.getAttribute("no_of_pass");

            // Prepare the response for the popup
            response.setContentType("text/html");

            if (paymentSuccessful) {
                response.getWriter().println("<html>");
                response.getWriter().println("<body>");
                response.getWriter().println("<script>");
                response.getWriter().println("alert('Payment Successful');");
                response.getWriter().println("alert('Origin: " + origin + "\\n" +
                        "Destination: " + dest + "\\n" +
                        "Class: " + jclass + "\\n" +
                        "Date: " + jdate + "\\n" +
                        "Number of Passengers: " + no_of_pass + "');");
                response.getWriter().println("window.location.href='UserHome.html';");
                response.getWriter().println("</script>");
                response.getWriter().println("</body>");
                response.getWriter().println("</html>");
            } else {
                response.getWriter().println("<html>");
                response.getWriter().println("<body>");
                response.getWriter().println("<script>");
                response.getWriter().println("alert('Payment Failed');");
                response.getWriter().println("window.location.href='UserHome.html';");
                response.getWriter().println("</script>");
                response.getWriter().println("</body>");
                response.getWriter().println("</html>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions here
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean performPaymentProcessing(String cardNumber, String cardHolder, String expirationDate, String cvv) {
        // Implement your payment processing logic here.
        // Insert payment details into the database, validate, etc.
        // Return true if payment is successful, else return false.
        // You can use a database connection pool for better performance.
        return true; // Replace with actual payment processing logic.
    }
}
