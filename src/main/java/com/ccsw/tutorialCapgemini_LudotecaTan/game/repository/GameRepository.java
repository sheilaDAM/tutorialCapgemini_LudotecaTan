/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.game.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.tutorialCapgemini_LudotecaTan.game.model.Game;

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

public interface GameRepository extends CrudRepository<Game, Long>,  JpaSpecificationExecutor<Game> {
	
	 @Override
	 @EntityGraph(attributePaths = {"category", "author"})
	 List<Game> findAll(Specification<Game> spec);

}
