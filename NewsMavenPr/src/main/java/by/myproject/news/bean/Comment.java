package by.myproject.news.bean;

import java.io.Serializable;
import java.util.Objects;

public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int idComment;
	private String content;
	private User authorComment;
	private String datePublication;

	public Comment() {
	}

	public int getIdComment() {
		return idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthorComment() {
		return authorComment;
	}

	public void setAuthorComment(User authorComment) {
		this.authorComment = authorComment;
	}

	public String getDatePublication() {
		return datePublication;
	}

	public void setDatePublication(String datePublication) {
		this.datePublication = datePublication;
	}

	@Override
	public int hashCode() {
		final int prime = 19;
		int result = 1;
		result = prime * result * idComment;
		result = prime * result + ((authorComment == null) ? 0 : authorComment.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((datePublication == null) ? 0 : datePublication.hashCode());
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
		Comment other = (Comment) obj;
		return Objects.equals(authorComment, other.authorComment) && Objects.equals(content, other.content)
				&& idComment == other.idComment && Objects.equals(datePublication, other.datePublication);
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + "Id: " + idComment + "content: " + content + "Author: " + authorComment
				+ "Date of publication: " + datePublication;

	}

}
