package lahikes.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PostForum")
public class PostForum extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PostForum() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get user session info - name and type of user (reg or admin)
		HttpSession session = request.getSession();
        String utype = (String) session.getAttribute( "utype" );
        String log = "Login";     
        
        //must be logged in to post a forum. if no utype then not logged in so send to login page. otherwise the option is to "logout"
        if( utype == null )
        	request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
        else
        	log = "Logout";
		
        //set the attributes for the jsp page
        request.setAttribute( "log", log ); 
        request.setAttribute( "utype", utype );  
        
        //go to jsp page
        request.getRequestDispatcher("/WEB-INF/PostForum.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection c = null;
        try
        {
        	//connect to sql database with username and password
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu82";
            String username = "cs3220stu82";
            String password = "eSkffhp3"; 
            c = DriverManager.getConnection( url, username, password );
            
            //execute a sql statement that adds a new forum getting the parameter "title" from the jsp page form
            String sql = "insert into lah_forums (name) values (?);";       
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setString( 1, request.getParameter( "title" ) );
            pstmt.executeUpdate();
            c.close();
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
		
        //route to forum page
		response.sendRedirect( "Forums");
		
	}
}
