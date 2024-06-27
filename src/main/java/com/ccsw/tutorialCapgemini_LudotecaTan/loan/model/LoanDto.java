/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan.model;

import java.time.LocalDate;

import com.ccsw.tutorialCapgemini_LudotecaTan.client.model.Client;
import com.ccsw.tutorialCapgemini_LudotecaTan.game.model.Game;

/**
 * Project info :)
 *
 * @project Ludoteca Tán
 * @author Sheila Isabel Jiménez Nieto
 * @since 27 jun 2024
 * @lastModified 27 jun 2024
 * @version 1.0
 *
 **/

public class LoanDto {
	
	private Long id;

	private Client client;

	private Game game;
	
	private LocalDate startLoanDate;

	private LocalDate endLoanDate;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the startLoanDate
	 */
	public LocalDate getStartLoanDate() {
		return startLoanDate;
	}

	/**
	 * @param startLoanDate the startLoanDate to set
	 */
	public void setStartLoanDate(LocalDate startLoanDate) {
		this.startLoanDate = startLoanDate;
	}

	/**
	 * @return the endLoanDate
	 */
	public LocalDate getEndLoanDate() {
		return endLoanDate;
	}

	/**
	 * @param endLoanDate the endLoanDate to set
	 */
	public void setEndLoanDate(LocalDate endLoanDate) {
		this.endLoanDate = endLoanDate;
	}
	
	

}
