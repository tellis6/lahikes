package lahikes.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Search() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        String utype = (String) session.getAttribute( "utype" );
        String log = "Login";
        
        if( utype != null )
        	log = "Logout";
        
        request.setAttribute( "log", log );
		request.getRequestDispatcher("/WEB-INF/Search.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
