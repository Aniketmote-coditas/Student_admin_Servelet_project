import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/profile")
public class profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession httpSession = req.getSession();
        PrintWriter out = resp.getWriter();
        String str = (String)httpSession.getAttribute("email");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "1122");

            Statement statement = con.createStatement();
            String query = "select * from Register where email='"+str+"'";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()){

                out.println("<h1> " +rs.getString(1)+"</h1>");
                out.println("<h1> " +rs.getString(2)+"</h1>");
                out.println("<h1> " +rs.getString(3)+"</h1>");
                out.println("<h1> " +rs.getString(4)+"</h1>");
                out.println("<h1> " +rs.getString(5)+"</h1>");

            }


        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }
    }
}
