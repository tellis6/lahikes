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

@WebServlet("/Topics")
public class Topics extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Topics() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	//get user session info - name and type of user (reg or admin)
    	HttpSession session = request.getSession();
        String utype = (String) session.getAttribute( "utype" );
        String log = "Login";
        
        //Logout if there is a user type (meaning someone is logged in already)
        if( utype != null )
        	log = "Logout";
        
        //get the parameter id (will see it in the url heading "?id=...")
		Integer id = Integer.valueOf(request.getParameter("id"));
		
		//Create a Topic list to store database info into and display in the jsp page
		List<Topic> topics = new ArrayList<Topic>();
		
		//this variable will be used as a heading in the jsp page so users can see what forum they are in
		String fName = "";
		
        Connection c = null;
        try
        {
        	//Connect to sql databasse with username and password
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu82";
            String username = "cs3220stu82";
            String password = "eSkffhp3";           
            c = DriverManager.getConnection( url, username, password );
            
            //sql command to get all database info from topics table to create list of Topic objects to send to jsp page
            String sql = 
            		"select t.id, t.subject, u.first_name, t.message, t.num_of_replies, date_format(t.last_post_time, '%m/%d/%Y %l:%i%p'), t.forum_id\n" + 
            		"    from lah_topics t \n" + 
            		"    inner join lah_users u on t.author_id = u.id\n" + 
            		"    where t.forum_id = ?;";           
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            //here we load all the info into a list of topics
            while( rs.next() ) 
                topics.add( new Topic( 
                		rs.getInt( "t.id" ), 
                		rs.getString( "t.subject" ), 
                		rs.getString( "u.first_name" ), 
                		rs.getString( "t.message" ), 
                		rs.getInt( "t.num_of_replies" ), 
                		rs.getString( "date_format(t.last_post_time, '%m/%d/%Y %l:%i%p')" ), 
                		rs.getInt( "t.forum_id" )));   
            
            //sql command to get the name of the forum the topic is under so we can display at the top of the page
            String sql2 = "select name from lah_forums\n" + 
            		"where id = ?;";
            PreparedStatement pstmt2 = c.prepareStatement( sql2 );
            pstmt2.setInt(1, id);
            ResultSet rs2 = pstmt2.executeQuery();

            //load the forum name into the fName variable
            while( rs2.next() ) 
            	fName = rs2.getString( "name" );
            
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
               
        //set all of our attributes to be sent over to the jsp page including our topics object list
        request.setAttribute( "log", log );
        request.setAttribute( "utype", utype );
        request.setAttribute( "fName", fName );       
        request.setAttribute( "topics", topics );
        
        //route to the jsp page
		request.getRequestDispatcher("/WEB-INF/Topics.jsp").forward(request, response);

	}


}
