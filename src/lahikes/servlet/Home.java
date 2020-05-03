package lahikes.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/Home", loadOnStartup = 2)
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Home() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
		super.init( config );

        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        String utype = (String) session.getAttribute( "utype" );
        String log = "Login";
        
        if( utype != null )
        	log = "Logout";
        
        request.setAttribute( "log", log );
		request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
	}

}