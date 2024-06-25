/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.category.service;

import java.util.List;

import com.ccsw.tutorialCapgemini_LudotecaTan.category.model.Category;
import com.ccsw.tutorialCapgemini_LudotecaTan.category.model.CategoryDto;

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
 *  Define los métodos que cualquier implementación de este servicio debe proporcionar
 */

public interface CategoryService {
	
	/**
     * Recupera una {@link Category} a partir de su ID
     *
     * @param id PK de la entidad
     * @return {@link Category}
     */
    Category get(Long id);
	
	 /**
     * Método para recuperar todas las categorías {@link Category}
     *
     * @return {@link List} de {@link Category}
     */
	 List<Category> findAll();

    /**
     * Método para crear o actualizar una categoría {@link Category}
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, CategoryDto dto);

    /**
     * Método para borrar una categoría {@link Category}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

}
