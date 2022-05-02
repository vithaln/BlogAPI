package com.example.blog.payloads;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {


	private Integer category_Id;
	
	@NotEmpty
	@Size(min=3,max=20,message="please enter your title must be atleast 3 chars and max 20!!")
	private String category_Title;
	
	@NotEmpty
	@Size(min=3,max=20,message="please enter your description must be atleast 3 chars and max 20!!")
	private String category_Description;
}
