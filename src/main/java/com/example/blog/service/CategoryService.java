package com.example.blog.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.blog.payloads.CategoryDto;

public interface CategoryService {

	/**
	 * create
	 * get
	 * update
	 * getBy id
	 * delete
	 */
	
	//Create
	 CategoryDto  CreatecategoryDto(CategoryDto categoryDto);
	 
	 //getAll
	 List<CategoryDto>  GetAllcategories();
	 
	 //update
	 CategoryDto  UpdateCategory(CategoryDto categoryDto,Integer Category_Id);
	 
	 //get by id
	 CategoryDto  GetByIdCategory(Integer Category_Id);
	 
	 //delete
	 void  DeletecategoryDto(Integer Category_Id);
	 
//	 //@Query("select mandal from Mandals mandal where mandal.state=:state and mandal.district=:district and mandal.name=:name")
//	 @Query("select cate from Categories cate where cate.category_Title=:category_Title and cate.category_Description=:category_Description")
//	 CategoryDto Postdata_Without_duplicatevalue(@Param("category_Title")String category_Title,@Param("category_Description")String category_Description);
//}
}