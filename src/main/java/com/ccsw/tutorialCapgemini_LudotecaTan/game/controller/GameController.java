/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.game.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorialCapgemini_LudotecaTan.game.model.Game;
import com.ccsw.tutorialCapgemini_LudotecaTan.game.model.GameDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.game.service.GameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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

@Tag(name = "Game", description = "API of Game")
@RequestMapping(value = "/game")
@RestController
@CrossOrigin(origins = "*")
public class GameController {

	@Autowired
	GameService gameService;

	@Autowired
	ModelMapper mapper;

	/**
	 * Método para recuperar una lista de {@link Game}
	 *
	 * @param title      título del juego
	 * @param idCategory PK de la categoría
	 * @return {@link List} de {@link GameDto}
	 */
	@Operation(summary = "Find", description = "Method that return a filtered list of Games")
	@RequestMapping(path = "", method = RequestMethod.GET)
	public List<GameDto> find(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "idCategory", required = false) Long idCategory) {

		List<Game> games = gameService.find(title, idCategory);

		return games.stream().map(e -> mapper.map(e, GameDto.class)).collect(Collectors.toList()); // Esta línea
																									// transforma la
																									// lista de Game en
																									// una lista de
																									// GameDto

		/*
		 * games.stream(): Convierte la lista de Game en un stream, lo que permite
		 * realizar operaciones funcionales sobre la lista. .map(e -> mapper.map(e,
		 * GameDto.class)): Aplica una función a cada elemento del stream. En este caso,
		 * usa el mapper (probablemente un ModelMapper o similar) para convertir cada
		 * objeto Game en un objeto GameDto. e -> mapper.map(e, GameDto.class): Esta es
		 * una expresión lambda que toma cada objeto Game (e) y lo convierte en un
		 * GameDto utilizando el mapper. .collect(Collectors.toList()): Recoge los
		 * elementos transformados del stream y los convierte de nuevo en una lista.
		 */

	}

	/**
	 * Método para crear o actualizar un {@link Game}
	 *
	 * @param id  PK de la entidad
	 * @param dto datos de la entidad
	 */
	@Operation(summary = "Save or Update", description = "Method that saves or updates a Game")
	@RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
	public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody GameDto dto) {

		gameService.save(id, dto);
	}

}
