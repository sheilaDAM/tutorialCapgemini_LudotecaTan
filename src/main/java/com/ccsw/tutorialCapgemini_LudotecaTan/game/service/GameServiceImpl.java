/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.game.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Game> find(String title, Long idCategory) {

        return (List<Game>) this.gameRepository.findAll();
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

        this.gameRepository.save(game);
    }


}
