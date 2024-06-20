/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.tutorialCapgemini_LudotecaTan.category.model.Category;
import com.ccsw.tutorialCapgemini_LudotecaTan.category.model.CategoryDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.category.repository.CategoryRepository;
import com.ccsw.tutorialCapgemini_LudotecaTan.category.service.CategoryServiceImpl;

/**
 * Project info :)
 *
 * @project Ludoteca Tán
 * @author Sheila Isabel Jiménez Nieto
 * @since 20 jun 2024
 * @lastModified 20 jun 2024
 * @version 1.0
 *
 **/

@ExtendWith(MockitoExtension.class)
public class CategoryTest {

	@Mock
	private CategoryRepository categoryRepository;

	@InjectMocks
	private CategoryServiceImpl categoryService;

	public static final String CATEGORY_NAME = "CAT1"; //para comprobar si se crea bien una nueva categoría
	public static final Long EXISTS_CATEGORY_ID = 1L;  //para comprobar cuando se quiere editar una categoría existente

	@Test
	public void findAllShouldReturnAllCategories() {

		List<Category> list = new ArrayList<>();
		list.add(mock(Category.class));

		when(categoryRepository.findAll()).thenReturn(list);

		List<Category> categories = categoryService.findAll();

		assertNotNull(categories);
		assertEquals(1, categories.size());
	}

	@Test
	public void saveNotExistsCategoryIdShouldInsert() {

		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setName(CATEGORY_NAME);

		ArgumentCaptor<Category> category = ArgumentCaptor.forClass(Category.class);

		categoryService.save(null, categoryDto);

		verify(categoryRepository).save(category.capture());

		assertEquals(CATEGORY_NAME, category.getValue().getName());
	}
	
	@Test
	public void saveExistsCategoryIdShouldUpdate() {

	  CategoryDto categoryDto = new CategoryDto();
	  categoryDto.setName(CATEGORY_NAME);

	  Category category = mock(Category.class);
	  when(categoryRepository.findById(EXISTS_CATEGORY_ID)).thenReturn(Optional.of(category));

	  categoryService.save(EXISTS_CATEGORY_ID, categoryDto);

	  verify(categoryRepository).save(category);
	}

}
