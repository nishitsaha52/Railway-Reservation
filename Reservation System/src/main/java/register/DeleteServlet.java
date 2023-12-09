package register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final static String query = "delete from train where trn_no = ?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");

        int trn_no = Integer.parseInt(req.getParameter("trn_no"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Reservation", "root", "2580");
             PreparedStatement ps = con.prepareStatement(query);) {
            ps.setInt(1, trn_no);
            int count = ps.executeUpdate();
            pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");

            if (count == 1) {
            	// Show a pop-up message for a successful edit with an "OK" button
                pw.println("<script type='text/javascript'>");
                pw.println("alert('Record Edited Successfully');");
                pw.println("window.location.href='updatetrain';"); // Redirect to UpdateTable.java after clicking "OK"
                pw.println("</script>");
            } else {
                pw.println("<h2 class='bg-danger text-light text-center'>Record Not Deleted</h2>");
            }
        } catch (SQLException se) {
            pw.println("<h2 class='bg-danger text-light text-center'>" + se.getMessage() + "</h2>");
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        pw.println("</div>");
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
