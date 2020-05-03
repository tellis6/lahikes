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
		HttpSession session = request.getSession();
        String utype = (String) session.getAttribute( "utype" );
        String log = "Login";     
        
        if( utype == null )
        	request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
        else
        	log = "Logout";
		
        request.setAttribute( "log", log ); 
        request.setAttribute( "utype", utype );  
        request.getRequestDispatcher("/WEB-INF/PostForum.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu82";
            String username = "cs3220stu82";
            String password = "eSkffhp3"; 

            String sql = "insert into lah_forums (name) values (?);";

            c = DriverManager.getConnection( url, username, password );
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
		
		response.sendRedirect( "Forums");
		
	}
}
