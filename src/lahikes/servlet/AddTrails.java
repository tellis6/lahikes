package lahikes.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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



}