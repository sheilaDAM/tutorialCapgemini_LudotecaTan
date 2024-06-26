/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.game.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.tutorialCapgemini_LudotecaTan.author.service.AuthorService;
import com.ccsw.tutorialCapgemini_LudotecaTan.category.service.CategoryService;
import com.ccsw.tutorialCapgemini_LudotecaTan.common.criteria.SearchCriteria;
import com.ccsw.tutorialCapgemini_LudotecaTan.game.GameSpecification;
import com.ccsw.tutorialCapgemini_LudotecaTan.game.model.Game;
import com.ccsw.tutorialCapgemini_LudotecaTan.game.model.GameDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.game.repository.GameRepository;

import jakarta.transaction.Transactional;

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

@Service
@Transactional
public class GameServiceImpl implements GameService {

	@Autowired
	GameRepository gameRepository;

	@Autowired
	AuthorService authorService;

	@Autowired
	CategoryService categoryService;
	
	 /**
     * {@inheritDoc}
     */
    @Override
    public Game get(Long id) {

        return this.gameRepository.findById(id).orElse(null);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Game> find(String title, Long idCategory) {

		GameSpecification titleSpec = new GameSpecification(new SearchCriteria("title", ":", title));
		GameSpecification categorySpec = new GameSpecification(new SearchCriteria("category.id", ":", idCategory));

		Specification<Game> spec = Specification.where(titleSpec).and(categorySpec);

		return this.gameRepository.findAll(spec);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(Long id, GameDto dto) {

		Game game;

		if (id == null) {
			game = new Game();
		} else {
			game = this.gameRepository.findById(id).orElse(null);
		}

		BeanUtils.copyProperties(dto, game, "id", "author", "category");

		game.setAuthor(authorService.get(dto.getAuthor().getId()));
		game.setCategory(categoryService.get(dto.getCategory().getId()));

		this.gameRepository.save(game);
	}

}
