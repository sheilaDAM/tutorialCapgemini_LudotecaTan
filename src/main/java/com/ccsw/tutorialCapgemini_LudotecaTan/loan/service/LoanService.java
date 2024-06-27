/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.tutorialCapgemini_LudotecaTan.loan.LoanSearchDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.model.Loan;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.model.LoanDto;

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

public interface LoanService {

	/**
	 * Recupera un {@link Loan} a través de su ID
	 *
	 * @param id PK de la entidad
	 * @return {@link Loan}
	 */
	Loan get(Long id);

	/**
	 * Recupera un listado de autores {@link Loan}
	 *
	 * @return {@link List} de {@link Loan}
	 */
	List<Loan> findAll();

	/**
	 * Método para recuperar un listado paginado de {@link Loan}
	 *
	 * @param dto dto de búsqueda
	 * @return {@link Page} de {@link Loan}
	 */
	Page<Loan> findPage(LoanSearchDto dto);

	/**
	 * Método para crear o actualizar un {@link Loan}
	 *
	 * @param id  PK de la entidad
	 * @param dto datos de la entidad
	 */
	void save(Long id, LoanDto dto);

	/**
	 * Método para crear o actualizar un {@link Loan}
	 *
	 * @param id PK de la entidad
	 */
	void delete(Long id) throws Exception;

}
