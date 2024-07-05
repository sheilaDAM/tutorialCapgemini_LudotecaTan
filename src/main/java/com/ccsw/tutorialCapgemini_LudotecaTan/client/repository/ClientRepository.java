/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.client.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.tutorialCapgemini_LudotecaTan.client.model.Client;

/**
 * Project info :)
 *
 * @project Ludoteca Tán
 * @author Sheila Isabel Jiménez Nieto
 * @since 26 jun 2024
 * @lastModified 26 jun 2024
 * @version 1.0
 *
 **/

/*
 *  El acceso a datos (BBDD) se debe hacer siempre a través de un Repository. En este caso hemos levantado una BBDD en memoria.
 *  Como utilizamos una librería llamada H2 que nos permite levantar una BBDD en memoria persistiendo los datos en memoria o en disco,
 *  para ver el contenido de la base de datos podemos acceder a un IDE web autopublicado por H2 en la ruta:
 *  http://localhost:8080/h2-console
 */

public interface ClientRepository extends CrudRepository<Client, Long> {
	
	//añadimos una consulta a bbdd para verificar si el nombre de un Nuevo cliente ya existe
	/*
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Client c WHERE c.name = :name")
	boolean clientAlreadyExistsByName(@Param("name") String name);
	*/
	
	//verificar si el nombre de un Nuevo cliente ya existe ignorando mayúsculas y minúsculas:
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Client c WHERE LOWER(c.name) = LOWER(:name)")
	boolean clientAlreadyExistsByName(@Param("name") String name);

	
	/*
	 * Esta consulta cuenta el número de clientes que ya existe en la bbdd con el nombre dado,
	 * CASE WHEN es similar a si utilizáramos if-else, se evalúa una condición.
	 * si esa condición es mayor que cero THEN TRUE es que sí, y devuelve TRUE, ELSE FALSE, es si no existe, no será >0,
	 * por tanto devolverá FALSE y luego el END termina la declaración CASE. 
	 * La consulta es sobre la tabla Client (FROM Client y luego la condición)
	 * @Param("name") es el parámetro que le pasamos a la consulta como :name, al llamar al método para utilizarlo,
	 * se le pasará el parámetro string.
	 */

}
