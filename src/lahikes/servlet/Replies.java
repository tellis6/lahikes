package lahikes.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lahikes.model.Topic;
import lahikes.model.Forum;
import lahikes.model.Reply;

@WebServlet("/Replies")
public class Replies extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Replies() {
        super();
    }
    
    private Topic getTopic(Integer id)
    {
        @SuppressWarnings("unchecked")
		List<Topic> topics = (List<Topic>) getServletContext().getAttribute("topics");

        for(Topic topic : topics)
            if(topic.getId().equals(id)) return topic;

        return null;
    }
    
    private Forum getForum(Integer id)
    {
        @SuppressWarnings("unchecked")
		List<Forum> forums = (List<Forum>) getServletContext().getAttribute("forums");

        for(Forum forum : forums)
            if(forum.getId().equals(id)) return forum;

        return null;
    }
    
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		List<Reply> replies = new ArrayList<Reply>();
		
		getServletContext().setAttribute( "replies", replies);	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = Integer.valueOf(request.getParameter("id"));
		
		request.setAttribute("topic", getTopic(id));
		request.setAttribute("forum", getForum(getTopic(id).getFid()));
		request.setAttribute("reply", getTopic(id));
		
        request.getRequestDispatcher("/WEB-INF/Replies.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pattern = "MM/dd/yyyy h:mma";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		String name = request.getParameter("name");
		String message = request.getParameter("message");
		Integer tid = Integer.parseInt(request.getParameter("id"));

		Reply reply = new Reply(name, message, date, tid);
		@SuppressWarnings("unchecked")
		List<Reply> replies = (List<Reply>) getServletContext().getAttribute("replies");
		replies.add(reply);
		
		getTopic(tid).incReplyCount();

	    response.sendRedirect("Replies?id=" + tid);	
	}

}
