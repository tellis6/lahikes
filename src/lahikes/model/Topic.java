package lahikes.model;

public class Topic {
	
	Integer id;
	
	String subject = "";
	
	String author = "";
	
	String content = "";
	
	int replyCount;
	
	String date;
	
	Integer fid;

	public Topic(Integer id, String subject, String author, String content, int replyCount, String date, Integer fid) {
		super();
		this.id = id;
		this.subject = subject;
		this.author = author;
		this.content = content;
		this.replyCount = replyCount;
		this.date = date;
		this.fid = fid;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id ) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public Integer getFid() {
		return fid;
	}
	
	public void setFid(Integer fid ) {
		this.fid = fid;
	}
	
	public void incReplyCount() {
		this.replyCount++;
	}

}
