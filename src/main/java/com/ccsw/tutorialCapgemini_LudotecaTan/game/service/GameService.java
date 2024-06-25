/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.game.service;

import java.util.List;

import com.ccsw.tutorialCapgemini_LudotecaTan.game.model.Game;
import com.ccsw.tutorialCapgemini_LudotecaTan.game.model.GameDto;

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

public interface GameService {
	
    /**
     * Recupera los juegos filtrando opcionalmente por título y/o categoría
     *
     * @param title título del juego
     * @param idCategory PK de la categoría
     * @return {@link List} de {@link Game}
     */
    List<Game> find(String title, Long idCategory);

    /**
     * Guarda o modifica un juego, dependiendo de si el identificador está o no informado
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, GameDto dto);

}
