package lahikes.servlet;

import java.io.IOException;
import java.io.InputStream;
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
import javax.servlet.http.Part;


@WebServlet("/AddTrails")
public class AddTrails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddTrails() {
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
        
        //set the log attribute to use in the jsp
        request.setAttribute( "log", log );
        
        //route to jsp
		request.getRequestDispatcher("/WEB-INF/AddTrails.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
						
				//get user session info - type of user (reg or admin)
//				HttpSession session = request.getSession();
//				String name = (String) session.getAttribute( "name" );
		
		
				// input stream of the upload file
//				InputStream inputStream = null;	
				
				// obtains the upload file part in this multipart request
//				Part filePart = request.getPart("photo");
//				if (filePart != null) {				
					// obtains input stream of the upload file
//					inputStream = filePart.getInputStream();
//				}
			
//				Connection c = null;
//		        try
//		        {
		        	//connect to sql database with username and password
//		            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu82";
//		            String username = "cs3220stu82";
//		            String password = "eSkffhp3"; 
//		            c = DriverManager.getConnection( url, username, password );
		           
		            //make a sql statement that creates a new entry into topics with the parameters (name and content) from the jsp page form
//		            String sql = "insert into user_trails (author_id, name, summary, longitude, latitude, length, conditions, photo) values ((select id from lah_users where first_name = ?), ?, ?, ?, ?, ?, ?, ?);";              
//		            PreparedStatement pstmt = c.prepareStatement( sql );
//		            pstmt.setString( 1, name );
//		            pstmt.setString( 2, request.getParameter( "trailname" ) );
//		            pstmt.setString( 3, request.getParameter( "summary" ) );
//		            pstmt.setInt( 4, Integer.valueOf(request.getParameter( "long" )) );
//		            pstmt.setInt( 5, Integer.valueOf(request.getParameter( "lat" )) );
//		            pstmt.setInt( 6, Integer.valueOf(request.getParameter( "miles" )) );
//		            pstmt.setString( 7, request.getParameter( "condition" ) );
//		            
//		            if (inputStream != null) {
						// fetches input stream of the upload file for the blob column
//		            	pstmt.setBlob(3, inputStream);
//					}
		            
//		            pstmt.executeUpdate();
//		            c.close();
//		        }
//		        catch( SQLException e )
//		        {
//		            throw new ServletException( e );
//		        }
//		        finally
//		        {
//		            try
//		            {
//		                if( c != null ) c.close();
//		            }
//		            catch( SQLException e )
//		            {
//		                throw new ServletException( e );
//		            }
//		        }
				
		        //route to home page
				response.sendRedirect( "Home");
				
	}

}