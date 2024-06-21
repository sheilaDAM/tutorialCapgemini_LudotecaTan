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
		list.add(mock(Category.class)); //mock(Category.class) crea una instancia simulada de la clase Category, agregando un mock (simulación) de un objeto Category, simulando que hay una categoría en la bbdd

		when(categoryRepository.findAll()).thenReturn(list); //Cuando se llame al método findAll() que devuelva la lista (en este caso del test, devolverá 1, que es lo que pasamos en el mock anterior)

		List<Category> categories = categoryService.findAll();

		assertNotNull(categories); //comprueba que la lista obtenida no sea null
		assertEquals(1, categories.size()); //verifica que el tamaño de la lista sea 1
	}

	@Test
	public void saveNotExistsCategoryIdShouldInsert() {

		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setName(CATEGORY_NAME);

		ArgumentCaptor<Category> category = ArgumentCaptor.forClass(Category.class); //Captura los argumentos que se pasan a un método en un mock.

		categoryService.save(null, categoryDto);

		verify(categoryRepository).save(category.capture()); //Verifica que un método en un mock fue llamado con ciertos argumentos.

		assertEquals(CATEGORY_NAME, category.getValue().getName()); //Verifica que el método save del categoryRepository fue llamado y captura el argumento pasado
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
	
	
	@Test
	public void deleteExistsCategoryIdShouldDelete() throws Exception {

	      Category category = mock(Category.class);
	      when(categoryRepository.findById(EXISTS_CATEGORY_ID)).thenReturn(Optional.of(category)); //Optional.of(category): Crea un Optional que contiene el mock Category, simulando que el repositorio encuentra una categoría con el ID dado.

	      categoryService.delete(EXISTS_CATEGORY_ID);

	      verify(categoryRepository).deleteById(EXISTS_CATEGORY_ID); //Verifica que el método deleteById del repositorio fue llamado con el ID especificado, confirmando que el servicio intentó eliminar la categoría.
	}

}
