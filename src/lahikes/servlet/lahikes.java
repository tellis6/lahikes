package lahikes.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/lahikes", loadOnStartup = 2)
public class lahikes extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public lahikes() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
		super.init( config );

		//intialize jdbc driver for sql commands
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
		request.getRequestDispatcher("Home").forward(request, response);
	}

}
