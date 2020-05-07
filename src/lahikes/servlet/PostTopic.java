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
import javax.servlet.http.HttpSession;

@WebServlet("/PostTopic")
public class PostTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostTopic() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get the parameter id (will see it in the url heading "?id=...")
		Integer id = Integer.valueOf( request.getParameter( "id" ) );
		
		//get user session info - type of user (reg or admin)
		HttpSession session = request.getSession();
        String utype = (String) session.getAttribute( "utype" );
        String fName = "";
		String log = "Login";       
        
		//must be logged in to post a topic, so if user type is null then send to login page, otherwise set to "logout"
        if( utype == null )
        	request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
        else
        	log = "Logout";
		
        Connection c = null;
        try
        {
        	//connect to sql database with username and password
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu82";
            String username = "cs3220stu82";
            String password = "eSkffhp3";           
            c = DriverManager.getConnection( url, username, password );
            
            // execute the sql statement to get the forum name so that their can be a navigate back to forum link on the jsp
            String sql = "select name from lah_forums\n" + 
            		"where id = ?;";          
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            //set the fName variable to the current forum name to use as the link in the jsp page
            while( rs.next() ) 
            	fName = rs.getString( "name" );
            
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
        
        //set the attributes to use in the jsp page
        request.setAttribute( "log", log ); 
        request.setAttribute( "utype", utype );
        request.setAttribute( "fName", fName );
        
        //route to jsp
        request.getRequestDispatcher("/WEB-INF/PostTopic.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get the parameter id (will see it in the url heading "?id=...")
		Integer id = Integer.valueOf( request.getParameter( "id" ) );
		
		//get user session info - type of user (reg or admin)
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute( "name" );
	
		Connection c = null;
        try
        {
        	//connect to sql database with username and password
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu82";
            String username = "cs3220stu82";
            String password = "eSkffhp3"; 
            c = DriverManager.getConnection( url, username, password );
           
            //make a sql statement that creates a new entry into topics with the parameters (name and content) from the jsp page form
            String sql = "insert into lah_topics (forum_id, subject, author_id, num_of_replies, last_post_time, message) values (?, ?, (select id from lah_users where first_name = ?), 0, now(), ?);";              
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setInt( 1, id );
            pstmt.setString( 2, request.getParameter( "subject" ) );
            pstmt.setString( 3, name );
            pstmt.setString( 4, request.getParameter( "content" ) );
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
		
        //route to topics page with the id set in the url in order to use it in the topics page
		response.sendRedirect( "Topics?id=" + id );

	}

}
