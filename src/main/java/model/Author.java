package model;

import java.sql.Date;
public class Author {
	private String authorID;
	private String fullName;
	private Date birth;
	private String biography;
	
	
	public Author() {
	}
	public Author(String authorID, String fullName, Date birth, String biography) {
		this.authorID = authorID;
		this.fullName = fullName;
		this.birth = birth;
		this.biography = biography;
	}
	public String getAuthorID() {
		return authorID;
	}
	public void setAuthorID(String authorID) {
		this.authorID = authorID;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	@Override
	public String toString() {
		return "Author [authorID=" + authorID + ", fullName=" + fullName + ", birth=" + birth + ", biography="
				+ biography + "]";
	}
	
	
	
}
