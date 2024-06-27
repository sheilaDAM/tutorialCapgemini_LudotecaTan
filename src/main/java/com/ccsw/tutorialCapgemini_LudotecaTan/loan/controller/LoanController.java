/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorialCapgemini_LudotecaTan.author.model.Author;
import com.ccsw.tutorialCapgemini_LudotecaTan.author.model.AuthorDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.LoanSearchDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.model.Loan;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.model.LoanDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.service.LoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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

@Tag(name = "Loan", description = "API of Loan (Préstamo)")
@RequestMapping(value = "/loan")
@RestController
@CrossOrigin(origins = "*")
public class LoanController {

	@Autowired
	LoanService loanService;

	@Autowired
	ModelMapper mapper;

	/**
	 * Recupera un listado de {@link Loan}
	 *
	 * @return {@link List} de {@link LoanDto}
	 */
	@Operation(summary = "Find", description = "Method that return a list of Loans")
	@RequestMapping(path = "", method = RequestMethod.GET)
	public List<AuthorDto> findAll() {

		List<Loan> loans = this.loanService.findAll();

		return loans.stream().map(e -> mapper.map(e, AuthorDto.class)).collect(Collectors.toList());
	}

	/**
	 * Método para recuperar un listado paginado de {@link Author}
	 *
	 * @param dto dto de búsqueda
	 * @return {@link Page} de {@link LoanDto}
	 */
	@Operation(summary = "Find Page", description = "Method that return a page of Loans")
	@RequestMapping(path = "", method = RequestMethod.POST)
	public Page<LoanDto> findPage(@RequestBody LoanSearchDto dto) {

		Page<Loan> page = this.loanService.findPage(dto);

		return new PageImpl<>(
				page.getContent().stream().map(e -> mapper.map(e, LoanDto.class)).collect(Collectors.toList()),
				page.getPageable(), page.getTotalElements());
	}

}
