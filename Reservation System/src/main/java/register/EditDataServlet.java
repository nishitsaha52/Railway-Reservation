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

@WebServlet("/edit")
public class EditDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final static String query = "update train set tName=?, origin=?, dest=?, aTime=?, dTime=?, 1A=?, 2A=?, 3A=?, 3E=?, SL=? where trn_no=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int trn_no = Integer.parseInt(req.getParameter("trn_no"));
        String tName = req.getParameter("tName");
        String origin = req.getParameter("origin");
        String dest = req.getParameter("dest");
        String aTime = req.getParameter("aTime");
        String dTime = req.getParameter("dTime");
        String oneA = req.getParameter("1A");
        String twoA = req.getParameter("2A");
        String threeA = req.getParameter("3A");
        String threeE = req.getParameter("3E");
        String sl = req.getParameter("SL");
        PrintWriter pw = res.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Reservation", "root", "2580");
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, tName);
            ps.setString(2, origin);
            ps.setString(3, dest);
            ps.setString(4, aTime);
            ps.setString(5, dTime);
            ps.setString(6, oneA);
            ps.setString(7, twoA);
            ps.setString(8, threeA);
            ps.setString(9, threeE);
            ps.setString(10, sl);
            ps.setInt(11, trn_no);

            int count = ps.executeUpdate();
            if (count == 1) {
                // Show a pop-up message for a successful edit with an "OK" button
                pw.println("<script type='text/javascript'>");
                pw.println("alert('Record Edited Successfully');");
                pw.println("window.location.href='updatetrain';"); // Redirect to UpdateTable.java after clicking "OK"
                pw.println("</script>");
            } else {
                pw.println("<h2 class='bg-danger text-light text-center'>Record Not Edited</h2>");
            }
        }catch(SQLException se) {
            pw.println("<h2 class='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
            se.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        }
        pw.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
        pw.println("&nbsp; &nbsp;");
        pw.println("<a href='showdata'><button class='btn btn-outline-success'>Show User</button></a>");
        pw.println("</div>");
        //close the stram
        pw.close();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req,res);
    }
}