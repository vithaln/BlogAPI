package com.example.blog.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.CategoryDto;
import com.example.blog.service.CategoryService;

@RestController
@RequestMapping("/Categry")
public class CategoryController {
	
	@Autowired
	private CategoryService CategoryService;
	
	@PostMapping("/category")
	public ResponseEntity<CategoryDto> CreateCategories(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto createCategory=this.CategoryService.CreatecategoryDto(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
		
	}
	@PutMapping("/{Category_Id}")
	public ResponseEntity<CategoryDto> UpdateCategories(@Valid @RequestBody CategoryDto categoryDto, Integer Category_Id){
		
		CategoryDto updatedCategory=this.CategoryService.UpdateCategory(categoryDto,Category_Id);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
		
	}
	
	//delete
	@DeleteMapping("/{Category_Id}")
	public ResponseEntity<ApiResponse> deleteCategories(@PathVariable Integer Category_Id){
		
		this.CategoryService.DeletecategoryDto(Category_Id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted successfulyy!!",true),HttpStatus.OK);
		
	}
	
	@GetMapping("/GetAllCategories")
	public ResponseEntity<List<CategoryDto>> GetAllCategories(){
		
		List<CategoryDto> categorys=this.CategoryService.GetAllcategories();
		return ResponseEntity.ok(categorys);
		
	}
	
	//get by id
	/**
	@GetMapping("/{Category_Id}")
	public ResponseEntity<CategoryDto> GetCategoryByid(@PathVariable Integer Category_Id){
		
		CategoryDto category=this.CategoryService.GetByIdCategory(Category_Id);
		
		return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
		
	}*/
	/////////////
	@PostMapping("/cat")
	public ResponseEntity<CategoryDto> GetCategoryByid(@PathParam("Category_Id") Integer Category_Id){
		
		CategoryDto category=this.CategoryService.GetByIdCategory(Category_Id);
		
		return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
		
	}

}
