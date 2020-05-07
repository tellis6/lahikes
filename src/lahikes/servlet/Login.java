package lahikes.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//route to jsp
		request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get parameters from the jsp page form(username and password)
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		
		//set variables to null
		String utype = null;
		String name = null;
		
		Connection c = null;
        try
        {
        	//connect to sql database with username and password below
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu82";
            String username = "cs3220stu82";
            String password = "eSkffhp3";           
            c = DriverManager.getConnection( url, username, password );
            
            //execute a sql statement that checks for the given user and password in the database
            String sql = "select first_name, user_type from lah_users\n" + 
            		"where username = ? and password = ?;";          
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();

            //get the usertype and first name form the given username and password and save in variables
            while( rs.next() ) {
            	utype = rs.getString( "user_type" );
            	name = rs.getString( "first_name" );
            }
            
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
        finally
        {
            try
            {
                if( c != null ) c.close();
            }
            catch( SQLException e )
            {
                throw new ServletException( e );
            }
        }
        
        
        if(utype != null) {
        	//if login successfull then utype will have a value, so we set the session attributes with the value and send to home page
			request.getSession().setAttribute("utype", utype);
			request.getSession().setAttribute("name", name);
			response.sendRedirect("Home");
		}
		else {
			//if unsuccesfull login then utype will be null so we redirect to try login again
			response.sendRedirect("Login");
		}
	}

}
