/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.game;

import org.springframework.data.jpa.domain.Specification;

import com.ccsw.tutorialCapgemini_LudotecaTan.common.criteria.SearchCriteria;
import com.ccsw.tutorialCapgemini_LudotecaTan.game.model.Game;

import jakarta.persistence.criteria.*;
import jakarta.persistence.criteria.Root;

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

public class GameSpecification implements Specification<Game> {
	
	/*
	 * Specification es una interfaz que forma parte del módulo de Spring Data JPA y que se utiliza
	 * para construir consultas dinámicas (consultas que se construyen y ejecutan en tiempo de ejecución).
	 * A diferencia de las consultas estáticas, que están definidas de manera fija en el código, 
	 * las consultas dinámicas se generan según las necesidades del usuario o las condiciones específicas del momento.
	 * 
	 * Esta interfaz, también nos permite combinar condiciones de consulta utilizando operadores lógicos como AND, OR, etc.
	 * Clase para construir una consulta en función de ciertos criterios.
	 * Esta clase recoge un criterio de filtrado y construye un predicado, y que en principio solo permite generar comparaciones de igualdad ( : )
	   Un predicado es una función o expresión que devuelve un valor booleano (true o false),  se utiliza para definir condiciones o criterios 
	   que deben cumplirse para que una entidad sea seleccionada. 
	 */

    private static final long serialVersionUID = 1L;

    private final SearchCriteria criteria;

    public GameSpecification(SearchCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Game> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(":") && criteria.getValue() != null) {
            Path<String> path = getPath(root);
            if (path.getJavaType() == String.class) {
                return builder.like(path, "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(path, criteria.getValue());
            }
        }
        return null;
    }

    private Path<String> getPath(Root<Game> root) {
        String key = criteria.getKey();
        String[] split = key.split("[.]", 0);

        Path<String> expression = root.get(split[0]);
        for (int i = 1; i < split.length; i++) {
            expression = expression.get(split[i]);
        }

        return expression;
    }


}
