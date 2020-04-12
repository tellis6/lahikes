package lahikes.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lahikes.model.Forum;
import lahikes.model.Topic;

@WebServlet("/PostTopic")
public class PostTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    int idSeed = 100;
       
    public PostTopic() {
        super();
    }
    
    private Forum getForum(Integer id)
    {
        @SuppressWarnings("unchecked")
		List<Forum> forums = (List<Forum>) getServletContext().getAttribute("forums");

        for(Forum forum : forums)
            if(forum.getId().equals(id) ) return forum;

        return null;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = Integer.valueOf( request.getParameter( "id" ) );
        Integer idt = idSeed++;
       
        request.setAttribute("idt", idt);
        request.setAttribute("forum", getForum(id));
        request.setAttribute("id", id);
        
        request.getRequestDispatcher("/WEB-INF/PostTopic.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pattern = "MM/dd/yyyy h:mma";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		String author = request.getParameter( "author" );
		String subject = request.getParameter( "subject" );
		String content = request.getParameter( "content" );
		Integer uid = Integer.valueOf(request.getParameter( "id" ));
		Integer id = Integer.valueOf( request.getParameter( "fid" ) );

		Topic topic = new Topic( uid, subject, author, content, 0, date, id);
		@SuppressWarnings("unchecked")
		List<Topic> topics = (List<Topic>) getServletContext().getAttribute( "topics" );
		topics.add( topic );
		
		getForum(id).incTopicCount();
		
		response.sendRedirect( "Topics?id=" + id );

	}

}
