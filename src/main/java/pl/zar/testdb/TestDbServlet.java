package pl.zar.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
    private static final long serialVersionUID=1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        //setup connection variables
        String user = "jedyn";
        String pass = "jedyn";


        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimeZone=UTC";
        String driver = "com.mysql.jdbc.Driver";

        //set connection
        try{
            PrintWriter out = response.getWriter();

            out.println("Connecting to : " + jdbcUrl);

            Class.forName(driver);

            Connection myConn = DriverManager.getConnection(jdbcUrl,user,pass);

            out.println("Connected to: " + jdbcUrl);
            myConn.close();

        }catch(Exception exc){
            exc.printStackTrace();
            throw new ServletException(exc);
        }
    }
}
