/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.category.repository;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.tutorialCapgemini_LudotecaTan.category.model.Category;

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
 *  El acceso a datos (BBDD) se debe hacer siempre a través de un Repository. En este caso hemos levantado una BBDD en memoria.
 *  Como utilizamos una librería llamada H2 que nos permite levantar una BBDD en memoria persistiendo los datos en memoria o en disco,
 *  para ver el contenido de la base de datos podemos acceder a un IDE web autopublicado por H2 en la ruta:
 *  http://localhost:8080/h2-console
 */

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
