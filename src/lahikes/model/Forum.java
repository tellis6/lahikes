package lahikes.model;

public class Forum {
	
	Integer id;
	
	String title;
	
	int topicCount;

	public Forum(Integer id, String title, int topicCount) {
		this.id = id;
		this.title = title;
		this.topicCount = topicCount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}
	
	public void incTopicCount() {
		this.topicCount++;
	}

}
