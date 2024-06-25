/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import com.ccsw.tutorialCapgemini_LudotecaTan.category.model.Category;
import com.ccsw.tutorialCapgemini_LudotecaTan.category.model.CategoryDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.category.repository.CategoryRepository;

import java.util.List;


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

/*
 * Proporciona la implementación concreta de los métodos definidos en CategoryService.
 */

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	/*
    private long SEQUENCE = 1;
    private Map<Long, CategoryDto> categories = new HashMap<Long, CategoryDto>();
    */
	
	@Autowired
    CategoryRepository categoryRepository;
	
	 /**
     * {@inheritDoc}
     */
    @Override
    public Category get(Long id) {

          return this.categoryRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
	@Override
    public List<Category> findAll() {

          return (List<Category>) this.categoryRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
	@Override
    public void save(Long id, CategoryDto dto) {

		 Category category = null;

         if (id == null) {
            category = new Category();
         } else {
            //category = this.categoryRepository.findById(id).orElse(null);
        	 category = this.get(id);
         }

         category.setName(dto.getName());

         this.categoryRepository.save(category);
    }

    /**
     * {@inheritDoc}
     */
	@Override
    public void delete(Long id) throws Exception {

          //if(this.categoryRepository.findById(id).orElse(null) == null){
		  if(this.get(id) == null){
             throw new Exception("Not exists");
          }

          this.categoryRepository.deleteById(id);
    }

}
