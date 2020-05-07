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
		
		//get the parameter id (will see it in the url heading "?id=...")
		Integer id = Integer.valueOf(request.getParameter("id"));
		
		//create new lists to store database info into
		List<Topic> topics = new ArrayList<Topic>();
		List<Reply> replies = new ArrayList<Reply>();
		
		//get session user type in order to determine if one is logged in and what permissions they have
		HttpSession session = request.getSession();
        String utype = (String) session.getAttribute( "utype" );
        String log = "Login";
        
        //if user has a usertype they are logged in so log should read "logout"
        if( utype != null )
        	log = "Logout";
        
        Connection c = null;
        try
        {
        	//connect to the sql database with username and password
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu82";
            String username = "cs3220stu82";
            String password = "eSkffhp3";           
            c = DriverManager.getConnection( url, username, password );
            
            //this sql statement will get the info from trail_topic table and create a list of topic objects
            String sql = 
            		"select t.id, t.subject, u.first_name, t.message, t.num_of_replies, date_format(t.last_post_time, '%m/%d/%Y %l:%i%p'), t.trail_id\n" + 
            		"    from trail_topics t \n" + 
            		"    inner join lah_users u on t.author_id = u.id\n" + 
            		"    where t.trail_id = ?;";           
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            //here is where we create the list of topic objects to store database info into
            while( rs.next() ) 
                topics.add( new Topic( 
                		rs.getInt( "t.id" ), 
                		rs.getString( "t.subject" ), 
                		rs.getString( "u.first_name" ), 
                		rs.getString( "t.message" ), 
                		rs.getInt( "t.num_of_replies" ), 
                		rs.getString( "date_format(t.last_post_time, '%m/%d/%Y %l:%i%p')" ), 
                		rs.getInt( "t.trail_id" )));
            
            //this sql command will get the info from the trail_reply table and store in new reply object list
            String sql2 = "select u.first_name, r.content, date_format(r.timestamp, '%m/%d/%Y %l:%i%p'), r.trail_topics_id"
            		+ " from trail_replies r"
            		+ " inner join trail_topics t on r.trail_topics_id = t.id"
            		+ " inner join lah_users u on u.id = r.author_id"
            		+ " where t.trail_id = ?;";         
            PreparedStatement pstmt2 = c.prepareStatement( sql2 );
            pstmt2.setInt(1, id);
            ResultSet rs2 = pstmt2.executeQuery();

            //this is the process of adding trail_reply table info into the new reply objects 
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
		
        //here we set all of our attributes to be able to use in the jsp, including our topics and replies objects
        request.setAttribute( "id", id );
        request.setAttribute( "log", log ); 
        request.setAttribute( "utype", utype );  
        request.setAttribute( "topics", topics );
        request.setAttribute( "replies", replies );
        
        //send to jsp page
        request.getRequestDispatcher("/WEB-INF/Result.jsp").forward(request, response);
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get the parameter id (will see it in the url heading "?id=...")
		Integer id = Integer.valueOf( request.getParameter( "id" ) );
		
		//get session user type in order to determine if one is logged in and what permissions they have, also get the user's name
    	HttpSession session = request.getSession();
    	String utype = (String) session.getAttribute( "utype" );
    	String name = (String) session.getAttribute( "name" );
    	
    	//if user is not logged in he cannot add a topic or result so must be rerouted to the login page
    	if( utype == null )
        	response.sendRedirect("Login");		
    	
        else {
		Connection c = null;
        try
        {
        	//connect to sql database in order to create new entries into the database
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu82";
            String username = "cs3220stu82";
            String password = "eSkffhp3"; 
            c = DriverManager.getConnection( url, username, password ); 
            
            //if there is something in the subject field of the jsp form, then a user is trying to create a tropic so sql command to add new entry into database
            if (request.getParameter( "subject" ) != null && !request.getParameter( "subject" ).isEmpty()) {
            String sql = "insert into trail_topics (trail_id , subject, author_id, num_of_replies, last_post_time, message) values (?, ?, (select id from lah_users where first_name = ?), 0, now(), ?);";       
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setInt( 1, id );
            pstmt.setString( 2, request.getParameter( "subject" ) ); //get the subject field from the jsp form
            pstmt.setString( 3, name ); //get the name form the session
            pstmt.setString( 4, request.getParameter( "content" ) ); //get the content field from the jsp form
            pstmt.executeUpdate();
            }

            //if the message field has content then the user is trying to reply, so execute sql statement to add reply entry into the database
            else if (request.getParameter( "message" ) != null && !request.getParameter( "message" ).isEmpty()) {
            String sql2 = "insert into trail_replies (trail_topics_id , author_id, content, timestamp) values (?, (select id from lah_users where first_name = ?), ?, now());";
            PreparedStatement pstmt2 = c.prepareStatement( sql2 );
            pstmt2.setInt( 1, Integer.valueOf(request.getParameter( "tid" ))); //get the id of the trail_topic the user is replying to from the jsp
            pstmt2.setString( 2, name ); //get the name from the session
            pstmt2.setString( 3, request.getParameter( "message" ) ); //get the message field from the jsp page
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

        	//redirect user to the doGet method above carrying over the id to load the same trail
        	response.sendRedirect("Result?id=" + id);	
        	
        	}
	}
}
