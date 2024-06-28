/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan.model;

import java.time.LocalDate;

import com.ccsw.tutorialCapgemini_LudotecaTan.client.model.ClientDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.game.model.GameDto;

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

	private ClientDto client;

	private GameDto game;
	
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
	public ClientDto getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(ClientDto client) {
		this.client = client;
	}

	/**
	 * @return the game
	 */
	public GameDto getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(GameDto game) {
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
