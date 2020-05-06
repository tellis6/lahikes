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

import lahikes.model.Reply;
import lahikes.model.Topic;

@WebServlet("/Result")
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Result() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = Integer.valueOf(request.getParameter("id"));
		List<Topic> topics = new ArrayList<Topic>();
		List<Reply> replies = new ArrayList<Reply>();
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
            		"select t.id, t.subject, u.first_name, t.message, t.num_of_replies, date_format(t.last_post_time, '%m/%d/%Y %l:%i%p'), t.trail_id\n" + 
            		"    from trail_topics t \n" + 
            		"    inner join lah_users u on t.author_id = u.id\n" + 
            		"    where t.trail_id = ?;";           
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
                		rs.getInt( "t.trail_id" )));
            
            String sql2 = "select u.first_name, r.content, date_format(r.timestamp, '%m/%d/%Y %l:%i%p'), r.trail_topics_id"
            		+ " from trail_replies r"
            		+ " inner join trail_topics t on r.trail_topics_id = t.id"
            		+ " inner join lah_users u on u.id = r.author_id"
            		+ " where t.trail_id = ?;";         
            PreparedStatement pstmt2 = c.prepareStatement( sql2 );
            pstmt2.setInt(1, id);
            ResultSet rs2 = pstmt2.executeQuery();

            while( rs2.next() ) {
                replies.add( new Reply( 
                		rs2.getString( "u.first_name" ), 
                		rs2.getString( "r.content" ), 
                		rs2.getString( "date_format(r.timestamp, '%m/%d/%Y %l:%i%p')" ), 
                		rs2.getInt( "r.trail_topics_id" )));   
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
		
        request.setAttribute( "id", id );
        request.setAttribute( "log", log ); 
        request.setAttribute( "utype", utype );  
        request.setAttribute( "topics", topics );
        request.setAttribute( "replies", replies );
        request.getRequestDispatcher("/WEB-INF/Result.jsp").forward(request, response);
        
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
            c = DriverManager.getConnection( url, username, password ); 
            
            if (request.getParameter( "subject" ) != null && !request.getParameter( "subject" ).isEmpty()) {
            String sql = "insert into trail_topics (trail_id , subject, author_id, num_of_replies, last_post_time, message) values (?, ?, (select id from lah_users where first_name = ?), 0, now(), ?);";       
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setInt( 1, id );
            pstmt.setString( 2, request.getParameter( "subject" ) );
            pstmt.setString( 3, name );
            pstmt.setString( 4, request.getParameter( "content" ) );
            pstmt.executeUpdate();
            }

            else if (request.getParameter( "message" ) != null && !request.getParameter( "message" ).isEmpty()) {
            String sql2 = "insert into trail_replies (trail_topics_id , author_id, content, timestamp) values (?, (select id from lah_users where first_name = ?), ?, now());";
            PreparedStatement pstmt2 = c.prepareStatement( sql2 );
            pstmt2.setInt( 1, Integer.valueOf(request.getParameter( "tid" )));
            pstmt2.setString( 2, name );
            pstmt2.setString( 3, request.getParameter( "message" ) );          
            pstmt2.executeUpdate();
            }

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

        	response.sendRedirect("Result?id=" + id);	
        	
        	}
	}
}
