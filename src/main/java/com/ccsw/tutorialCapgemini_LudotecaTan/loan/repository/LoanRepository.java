/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ccsw.tutorialCapgemini_LudotecaTan.loan.model.Loan;


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

public interface LoanRepository extends JpaRepository<Loan, Long>, JpaSpecificationExecutor<Loan> {

	/**
	 * Método para recuperar un listado paginado de {@link Loan}
	 *
	 * @param pageable pageable
	 * @return {@link Page} de {@link Loan}
	 */

	@EntityGraph(attributePaths = { "client", "game" })
	Page<Loan> findAll(Pageable pageable); // pasándole un objeto de tipo Pageable nos devuelve una Page.

	/**
	 * Método para comprobar que la fecha de un nuevo préstamo de un juego no
	 * coincida y/o solape con las fechas de un préstamo/s que ya existan para ese
	 * juego en la bbdd {@link Loan}
	 *
	 * @param pageable pageable
	 * @return {@link Page} de {@link Loan}
	 */
	@Query("SELECT l FROM Loan l WHERE l.game.id = :gameId AND l.startLoanDate <= :newEndDate AND l.endLoanDate >= :newStartDate")
	List<Loan> findLoansWithTheSameDateRangeThatCurrent(@Param("gameId") Long gameId,
			@Param("newStartDate") LocalDate newStartDate, @Param("newEndDate") LocalDate newEndDate);

	/**
	 * Método para comprobar que un mismo cliente no tenga más de dos juegos
	 * prestados en un mismo día o más de dos préstamos para ninguno de los días que
	 * contemplan las fechas actuales del rango {@link Loan}
	 *
	 * @param pageable pageable
	 * @return {@link Page} de {@link Loan}
	 */
	@Query("SELECT l FROM Loan l WHERE l.client.id = :clientId AND l.startLoanDate <= :newEndDate AND l.endLoanDate >= :newStartDate")
	List<Loan> findLoansWithTheSameDateRangeForClient(@Param("clientId") Long clientId,
			@Param("newStartDate") LocalDate newStartDate, @Param("newEndDate") LocalDate newEndDate);

	/**
	 * Método para recuperar un listado paginado y filtrado de {@link Loan}.
	 *
	 * @param spec     Specification que contiene los criterios de búsqueda y filtrado.
	 * @param pageable Pageable que contiene la información de paginación y ordenación.
	 * @return {@link Page} de {@link Loan} que coincide con los criterios de búsqueda y paginación.
	 */
	@EntityGraph(attributePaths = { "game.title", "client.id", "startLoanDate", "endLoanDate" })
	Page<Loan> findAll(Specification<Loan> spec, Pageable pageable);
}
