/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan.model;

import java.time.LocalDate;

import com.ccsw.tutorialCapgemini_LudotecaTan.client.model.Client;
import com.ccsw.tutorialCapgemini_LudotecaTan.game.model.Game;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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

/*
 * Esta clase representa el objeto "Préstamo" del proyecto Ludoteca Tán
 */

@Entity
@Table(name = "loan")
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	//1 mismo cliente podrá tener hasta un máximo de 2 juegos por día (o rango de fecha que coincida) 
	//Si ya tiene dos juegos prestados dentro de cierto rango de fecha, no se podrá prestar otro en esas mismas fecha.
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	//1 juego puede pertenecer a varios préstamos, pero no a dos clientes diferentes en un mismo día,
	//no puede estar prestado a más de un cliente para ninguno de los días que contemplan las fechas actuales de rango
	@ManyToOne
	@JoinColumn(name = "game_id", nullable = false)
	private Game game;

	@Column(name = "start_loan_date", nullable = false)
	private LocalDate startLoanDate;

	@Column(name = "end_loan_date", nullable = false)
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
