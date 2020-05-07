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
		
		//get user session info - name and type of user (reg or admin)
		HttpSession session = request.getSession();
        String utype = (String) session.getAttribute( "utype" );
        String log = "Login";
        
      //Logout if there is a user type (meaning someone is logged in already)
        if( utype != null )
        	log = "Logout";
        
        //set the login/logout attribute for the jsp page
        request.setAttribute( "log", log );
        
        //route to jsp 
		request.getRequestDispatcher("/WEB-INF/Search.jsp").forward(request, response);
	}


}
