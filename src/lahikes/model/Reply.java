package lahikes.model;

public class Reply {
	
	String name;
	
	String message;
	
	String date;
	
	Integer tid;

	public Reply(String name, String message, String date, Integer tid) {
		super();
		this.name = name;
		this.message = message;
		this.date = date;
		this.tid = tid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public Integer getTid() {
		return tid;
	}
	
	public void setTid(Integer tid ) {
		this.tid = tid;
	}

}
