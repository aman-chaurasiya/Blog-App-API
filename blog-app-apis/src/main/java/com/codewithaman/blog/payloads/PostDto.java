package com.codewithaman.blog.payloads;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.codewithaman.blog.entities.Category;
import com.codewithaman.blog.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PostDto {

	private String postId;
	private String title;
	private String content;
	private String imageName = "default.png";
	private Date post_date;

	private CategoryDto category;

	private UserDto user;

}
