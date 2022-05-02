package com.example.blog.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="post")
@Getter
@Setter
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int postId;
	
	@Column(name="post_Title",length=100,nullable=false)
	private String title;
	
	@Column(name="post_content")
	private String content;
	
	
	private String imageName;
	
	@Column(name="post_date")
	private Date addedDate;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Category category;

}
