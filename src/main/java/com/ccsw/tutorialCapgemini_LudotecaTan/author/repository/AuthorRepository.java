/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.author.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.tutorialCapgemini_LudotecaTan.author.model.Author;

/**
 * Project info :)
 *
 * @project Ludoteca Tán
 * @author Sheila Isabel Jiménez Nieto
 * @since 25 jun 2024
 * @lastModified 25 jun 2024
 * @version 1.0
 *
 **/

public interface AuthorRepository extends CrudRepository<Author, Long> {
	//Si extiende de JpaRepository ya tendrías el PagingAndSortingRepository para poder paginar y ordenar, además de incorporar CrudRepository
	 /**
     * Método para recuperar un listado paginado de {@link Author}
     *
     * @param pageable pageable
     * @return {@link Page} de {@link Author}
     */
    Page<Author> findAll(Pageable pageable); //pasándole un objeto de tipo Pageable nos devuelve una Page.

}
