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
		
		Integer id = Integer.valueOf( request.getParameter( "id" ) );
		HttpSession session = request.getSession();
        String utype = (String) session.getAttribute( "utype" );
		String fName = "";
		String log = "Login";       
        
        if( utype == null )
        	request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
        else
        	log = "Logout";
		
        Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu82";
            String username = "cs3220stu82";
            String password = "eSkffhp3";           

            c = DriverManager.getConnection( url, username, password );
            String sql = "select name from lah_forums\n" + 
            		"where id = ?;";          
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

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
        
        request.setAttribute( "log", log ); 
        request.setAttribute( "utype", utype );
        request.setAttribute( "fName", fName );
        request.getRequestDispatcher("/WEB-INF/PostTopic.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = Integer.valueOf( request.getParameter( "id" ) );
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute( "name" );
	
		Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu82";
            String username = "cs3220stu82";
            String password = "eSkffhp3"; 
            
            String sql = "insert into lah_topics (forum_id, subject, author_id, num_of_replies, last_post_time, message) values (?, ?, (select id from lah_users where first_name = ?), 0, now(), ?);";

            c = DriverManager.getConnection( url, username, password );          
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
		
		response.sendRedirect( "Topics?id=" + id );

	}

}
