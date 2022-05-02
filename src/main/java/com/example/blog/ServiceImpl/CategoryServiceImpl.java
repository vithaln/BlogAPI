package com.example.blog.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entities.Category;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.payloads.CategoryDto;
import com.example.blog.repository.CategoryRepo;
import com.example.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public CategoryDto CreatecategoryDto(CategoryDto categoryDto) {

		Category cat=this.modelmapper.map(categoryDto, Category.class);
		Category addedCat=this.categoryRepo.save(cat);
		
		return this.modelmapper.map(addedCat,CategoryDto.class);
		}

	

	@Override
	public CategoryDto UpdateCategory(CategoryDto categoryDto, Integer Category_Id) {

		Category  cat=this.categoryRepo.findById(Category_Id).orElseThrow(()-> new ResourceNotFoundException("category","CategoryId",Category_Id));
		cat.setCategory_Title(categoryDto.getCategory_Title());
		cat.setCategory_Description(categoryDto.getCategory_Description());
		
		Category updatedCat=this.categoryRepo.save(cat);
		
		return this.modelmapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto GetByIdCategory(Integer Category_Id) {

		Category cat=this.categoryRepo.findById(Category_Id).orElseThrow(()->new ResourceNotFoundException("category","categoryid",Category_Id));
		
		return this.modelmapper.map(cat, CategoryDto.class);
	}
	@Override
	public List<CategoryDto> GetAllcategories() {
		List<Category> Categories=this.categoryRepo.findAll();
		
		//Categories.stream().map((cat)->this.modelmapper.map(cat, CategoryDto.class)).collect(Collecters.toList());
		List<CategoryDto> categoriesdtos=Categories.stream().map(cat ->this.modelmapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		
		return categoriesdtos;
	}

	@Override
	public void DeletecategoryDto(Integer Category_Id) 
	{
		
	Category cat=this.categoryRepo.findById(Category_Id).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",Category_Id));
	this.categoryRepo.delete(cat);

	}
	

////////////////////////////////
//	public String Postdata_Without_duplicatevalues(CategoryDto categoryDto) {
//	
//		CategoryDto cates=categoryRepo
//		
//		
//
//		return null;
//	}

}
