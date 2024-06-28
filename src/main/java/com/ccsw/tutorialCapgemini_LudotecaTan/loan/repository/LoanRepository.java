/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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

}
