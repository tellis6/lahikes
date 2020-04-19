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

import lahikes.model.Topic;
import lahikes.model.Forum;

@WebServlet("/Topics")
public class Topics extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Topics() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		List<Topic> topics = new ArrayList<Topic>();
		
		getServletContext().setAttribute("topics", topics);	
	}
    
    private Forum getForum(Integer id)
    {
        @SuppressWarnings("unchecked")
		List<Forum> forums = (List<Forum>) getServletContext().getAttribute("forums");

        for(Forum forum : forums)
            if(forum.getId().equals(id)) return forum;

        return null;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = Integer.valueOf(request.getParameter("id"));
		
		request.setAttribute("forum", getForum(id));
		request.getRequestDispatcher("/WEB-INF/Topics.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
