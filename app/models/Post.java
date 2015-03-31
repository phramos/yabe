package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class Post extends Model {
	public String title;
	public Date postedAt;

	@Lob
	public String content;

	@ManyToOne
	public User author;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	public List<Comment> comments;

	public Post(User author, String title, String content) {
		super();
		this.author = author;
		this.title = title;
		this.content = content;
		this.postedAt = new Date();
		this.comments = new ArrayList<Comment>();
	}
	
	public Post previous() {
	    return Post.find("postedAt < ? order by postedAt desc", postedAt).first();
	}
	 
	public Post next() {
	    return Post.find("postedAt > ? order by postedAt asc", postedAt).first();
	}

	public Post addComment(String author, String content) {
		Comment newComment = new Comment(this, author, content).save();
		this.comments.add(newComment);
		this.save();
		return this;
	}
}