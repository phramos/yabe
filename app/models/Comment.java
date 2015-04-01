package models;

import play.*;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

@Entity
public class Comment extends Model {
	
	@Required
	public String author;
	
	@Required
	public Date postedAt;

	@Lob
	@Required
	@MaxSize(10000)
	public String content;

	@ManyToOne
	@Required
	public Post post;

	public Comment(Post post, String author, String content) {
		super();
		this.author = author;
		this.content = content;
		this.post = post;
		this.postedAt = new Date();
	}

	@Override
	public String toString() {
		return this.content;
	}
}
