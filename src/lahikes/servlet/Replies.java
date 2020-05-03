package lahikes.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lahikes.model.Topic;
import lahikes.model.Reply;

@WebServlet("/Replies")
public class Replies extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Replies() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	Integer id = Integer.valueOf(request.getParameter("id"));
		List<Topic> topics = new ArrayList<Topic>();
		List<Reply> replies = new ArrayList<Reply>();
		String fName = "";
		String tName = "";
		int fId = 0;
		
		HttpSession session = request.getSession();
        String utype = (String) session.getAttribute( "utype" );
        String log = "Login";
        if( utype != null )
        	log = "Logout";

		Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu82";
            String username = "cs3220stu82";
            String password = "eSkffhp3";           

            c = DriverManager.getConnection( url, username, password );
            String sql = 
            		"select t.id, t.subject, u.first_name, t.message, t.num_of_replies, date_format(t.last_post_time, '%m/%d/%Y %l:%i%p'), t.forum_id\n" + 
            		"    from lah_topics t \n" + 
            		"    inner join lah_users u on t.author_id = u.id\n" + 
            		"    where t.id = ?;";           
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while( rs.next() ) 
                topics.add( new Topic( 
                		rs.getInt( "t.id" ), 
                		rs.getString( "t.subject" ), 
                		rs.getString( "u.first_name" ), 
                		rs.getString( "t.message" ), 
                		rs.getInt( "t.num_of_replies" ), 
                		rs.getString( "date_format(t.last_post_time, '%m/%d/%Y %l:%i%p')" ), 
                		rs.getInt( "t.forum_id" )));
            
            String sql2 = "select u.first_name, r.content, date_format(r.timestamp, '%m/%d/%Y %l:%i%p'), r.topic_id"
            		+ " from lah_replies r"
            		+ " inner join lah_topics t on r.topic_id = t.id"
            		+ " inner join lah_users u on u.id = r.author_id"
            		+ " where t.id = ?;";         
            PreparedStatement pstmt2 = c.prepareStatement( sql2 );
            pstmt2.setInt(1, id);
            ResultSet rs2 = pstmt2.executeQuery();

            while( rs2.next() ) 
                replies.add( new Reply( 
                		rs2.getString( "u.first_name" ), 
                		rs2.getString( "r.content" ), 
                		rs2.getString( "date_format(r.timestamp, '%m/%d/%Y %l:%i%p')" ), 
                		rs2.getInt( "r.topic_id" )));   
            
            String sql3 = "select f.name, f.id, t.subject "
            		+ "from lah_forums f\n" + 
            		"inner join lah_topics t on f.id = t.forum_id\n" + 
            		"where t.id = ?;";
            PreparedStatement pstmt3 = c.prepareStatement( sql3 );
            pstmt3.setInt(1, id);
            ResultSet rs3 = pstmt3.executeQuery();

            while( rs3.next() ) {
            	fName = rs3.getString( "f.name" );
            	tName = rs3.getString( "t.subject" );
            	fId = rs3.getInt( "f.id" );
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
            
        request.setAttribute( "fId", fId );
        request.setAttribute( "log", log );
        request.setAttribute( "fName", fName );
        request.setAttribute( "tName", tName );
        request.setAttribute( "topics", topics );
        request.setAttribute( "replies", replies );
		request.getRequestDispatcher("/WEB-INF/Replies.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = Integer.valueOf( request.getParameter( "id" ) );
    	HttpSession session = request.getSession();
    	String utype = (String) session.getAttribute( "utype" );
    	String name = (String) session.getAttribute( "name" );
    	
    	if( utype == null )
        	response.sendRedirect("Login");		
        else {
		Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu82";
            String username = "cs3220stu82";
            String password = "eSkffhp3"; 

            String sql2 = "insert into lah_replies (topic_id, author_id, content, timestamp) values (?, (select id from lah_users where first_name = ?), ?, now());";

            c = DriverManager.getConnection( url, username, password );
            PreparedStatement pstmt2 = c.prepareStatement( sql2 );
            pstmt2.setInt( 1, id );
            pstmt2.setString( 2, name );
            pstmt2.setString( 3, request.getParameter( "message" ) );
            pstmt2.executeUpdate();
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

        	response.sendRedirect("Replies?id=" + id);	
        	
        	}
	}
}
