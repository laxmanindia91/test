package TestPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
 
public class TestServ extends HttpServlet {
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
        Context envContext = null;
        try 
        {
            envContext = new InitialContext();
            Context initContext  = (Context)envContext.lookup("java:/comp/env");
            //DataSource ds = (DataSource)initContext.lookup("jdbc/testDB");
            DataSource ds = (DataSource)envContext.lookup("java:/comp/env/jdbc/testdb");
            Connection con = ds.getConnection();
                         
            Statement stmt = con.createStatement();
            String query = "select * from anutable";
            ResultSet rs = stmt.executeQuery(query);
             
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.print("<center><h1>User Details</h1></center>");
            out.print("<html><body><table border=\"1\" cellspacing=10 cellpadding=5>");
           out.print("<th>User ID</th>");
           out.print("<th>User Name</th>");
           out.print("<th>Password</th>");
            
             
            while(rs.next())
            {
            	//out.print(rs.getInt(1));
            	//out.print(rs.getString(2));
            	//out.print(rs.getString(3));
                out.print("<tr>");
                out.print("<td>" + rs.getInt(1) + "</td>");
                out.print("<td>" + rs.getString(2) + "</td>");
                out.print("<td>" + rs.getString(3) + "</td>");
                out.print("</tr>");               
            }
          //  out.print("</table></body></html>");      
        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}