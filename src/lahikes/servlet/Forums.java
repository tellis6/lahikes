package lahikes.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lahikes.model.Forum;

@WebServlet("/Forums")
public class Forums extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Forums() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get user session info - name and type of user (reg or admin)
		HttpSession session = request.getSession();
        String utype = (String) session.getAttribute( "utype" );
        String log = "Login";
        
      //Logout if there is a user type (meaning someone is logged in already)
        if( utype != null )
        	log = "Logout";
				
        //Create a Forum list
        List<Forum> forums = new ArrayList<Forum>();
        
        Connection c = null;
        try
        {
        	//Connect to sql databasse with username and password
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu82";
            String username = "cs3220stu82";
            String password = "eSkffhp3";
            c = DriverManager.getConnection( url, username, password );
            
            //Create sql statement
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( 
            		"select f.id, f.name, count(t.id) "
            		+ "from lah_topics t "
            		+ "right join lah_forums f on t.forum_id = f.id "
            		+ "group by f.id;" );

            //while reading though results create a forum with the database information
            while( rs.next() )
                forums.add( new Forum( rs.getInt( "f.id" ), rs.getString( "f.name" ), rs.getInt( "count(t.id)" ) ));
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

        //set our session attributes (log and utype) and set our forums attribute to use in the jsp
        request.setAttribute( "log", log );
        request.setAttribute( "utype", utype );
        request.setAttribute( "forums", forums );
        
        //route to jsp
        request.getRequestDispatcher( "/WEB-INF/Forums.jsp" ).forward( request, response );
	}

}
