package lahikes.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lahikes.model.Forum;

@WebServlet("/Forums")
public class Forums extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Forums() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		List<Forum> forums = new ArrayList<Forum>();
		forums.add( new Forum(1, "General Discussion", 0));
		forums.add( new Forum(2, "Trail Information", 0));
		
		getServletContext().setAttribute("forums", forums);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Forums.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
