package JDBC;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Update")
public class Update extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        String uname = request.getParameter("username");   // ✅
        String mno   = request.getParameter("mobileno");   // ✅
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employee", "root", "");

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE empData SET empName=?, mobileno=? WHERE empid=?");

            ps.setString(1, uname);
            ps.setString(2, mno);
            ps.setInt(3, id);   // ✅ VERY IMPORTANT

            int count = ps.executeUpdate();

            if (count == 1) {
                pw.println("<h2>Data Updated Successfully</h2>");
                pw.println("<a href='Select'>Show Users</a>");
            } else {
                pw.println("<h2>Update Failed</h2>");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}