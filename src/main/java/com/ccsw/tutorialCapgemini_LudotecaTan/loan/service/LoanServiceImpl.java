/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ccsw.tutorialCapgemini_LudotecaTan.client.service.ClientService;
import com.ccsw.tutorialCapgemini_LudotecaTan.game.service.GameService;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.exception.LoanConflictException;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.model.Loan;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.model.LoanDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.model.LoanSearchDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.repository.LoanRepository;

import jakarta.transaction.Transactional;

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

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

	@Autowired
	LoanRepository loanRepository;

	@Autowired
	ClientService clientService;

	@Autowired
	GameService gameService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Loan get(Long id) {

		return this.loanRepository.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Loan> findAll() {

		return (List<Loan>) this.loanRepository.findAll();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<Loan> findPage(LoanSearchDto dto) {

		return this.loanRepository.findAll(dto.getPageable().getPageable());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(Long id, LoanDto dto) throws LoanConflictException {

		Loan loan;

		if (id == null) {
			loan = new Loan();
		} else {
			loan = this.get(id);
		}

		BeanUtils.copyProperties(dto, loan, "id", "client", "game");

		loan.setClient(clientService.get(dto.getClient().getId()));
		loan.setGame(gameService.get(dto.getGame().getId()));

		// Validamos el préstamo antes de guardarlo

		validateLoan(loan);

		this.loanRepository.save(loan);

	}

	public void validateLoan(Loan loan) throws LoanConflictException {
		LocalDate startDate = loan.getStartLoanDate();
		LocalDate endDate = loan.getEndLoanDate();

		System.out.println("Fecha nuevo préstamo: " + loan.getStartLoanDate() + " - " + loan.getEndLoanDate());

		// Validación 1: El mismo juego no puede estar prestado a dos clientes distintos
		// en un mismo día ni para ninguno de los días que contemplan las fechas
		// actuales del rango
		List<Loan> conflictingGameLoans = loanRepository
				.findLoansWithTheSameDateRangeThatCurrent(loan.getGame().getId(), startDate, endDate);

		System.out.println("Préstamos conflictivos encontrados: " + conflictingGameLoans.size());
		for (Loan conflictingLoan : conflictingGameLoans) {
			System.out.println("Préstamo conflictivo: " + conflictingLoan.getStartLoanDate() + " a "
					+ conflictingLoan.getEndLoanDate() + " Cliente: " + conflictingLoan.getClient().getName());
			System.out.println("El juego " + conflictingLoan.getGame().getTitle() + " ya está prestado al "
					+ "Cliente: " + conflictingLoan.getClient().getName());
			if (!conflictingLoan.getClient().getId().equals(loan.getClient().getId())) {
				throw new LoanConflictException("El juego ya está prestado a otro cliente en el rango de fechas: "
						+ conflictingLoan.getStartLoanDate() + " a " + conflictingLoan.getEndLoanDate());
			}
		}

		// Validación 2: Un cliente no puede tener más de 2 préstamos en un mismo día o
		// dentro del rango de fechas del nuevo préstamo
		List<Loan> clientConflictLoans = loanRepository.findLoansWithTheSameDateRangeForClient(loan.getClient().getId(),
				startDate, endDate);
		System.out.println("Validación 2 Préstamos conflictivos encontrados: " + clientConflictLoans.size());
		if (clientConflictLoans.size() >= 2
				|| (clientConflictLoans.size() == 2 && !clientConflictLoans.stream().allMatch(l -> l.getId().equals(loan.getId())))) {
			throw new LoanConflictException(
					"El cliente ya tiene más de 2 préstamos en el rango de fechas: " + startDate + " a " + endDate);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) throws Exception {

		if (this.get(id) == null) {
			throw new Exception("Not exists");
		}

		this.loanRepository.deleteById(id);
	}

}
