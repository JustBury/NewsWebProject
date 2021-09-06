package by.myproject.news.bean;

import java.io.Serializable;
import java.util.Objects;

public class News implements Serializable {

	private static final long serialVersionUID = 1L;
	private int newsId;
	private String title;
	private String brief;
	private String content;
	private User author;
	private String linkImage;
	private String status;
	private String dataPublication;


	public News() {}
		
	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getLinkImage() {
		return linkImage;
	}

	public void setLinkImage(String linkImage) {
		this.linkImage = linkImage;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDataPublication() {
		return dataPublication;
	}

	public void setDataPublication(String dataPublication) {
		this.dataPublication = dataPublication;
	}
	
	@Override
	public int hashCode() {
		final int prime = 43;
	    int result = 1;
	    result = prime * result + newsId; 
	    result = prime * result + ((author == null) ? 0 : author.hashCode());            
	    result = prime * result + ((brief == null) ? 0 : brief.hashCode());
	    result = prime * result + ((content == null) ? 0 : content.hashCode());
	    result = prime * result + ((title == null) ? 0 : title.hashCode());
	    result = prime * result + ((linkImage == null) ? 0 : linkImage.hashCode());
	    result = prime * result + ((status == null) ? 0 : status.hashCode());
	    result = prime * result + ((dataPublication == null) ? 0 : dataPublication.hashCode());
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		return Objects.equals(author, other.author) && Objects.equals(brief, other.brief) &&(newsId == other.newsId)
				&& Objects.equals(content, other.content) && Objects.equals(title, other.title) && Objects.equals(linkImage, other.linkImage)
				&& Objects.equals(status, other.status) && Objects.equals(dataPublication, other.dataPublication);
	}	
	
	@Override
	public String toString() {
		return getClass().getName() + "@" + "NewsId:" + newsId + ", title:" + title + ", author:" + author.toString() + ", status:"
	+ status + ", dataPublication:" + dataPublication;
		
	}

}
