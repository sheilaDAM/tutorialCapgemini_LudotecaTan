/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ccsw.tutorialCapgemini_LudotecaTan.author.model.Author;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.LoanSearchDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.model.Loan;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.model.LoanDto;
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

		return this.loanRepository.findAllInPage(dto.getPageable().getPageable());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(Long id, LoanDto dto) {

		Loan loan;

		if (id == null) {
			loan = new Loan();
		} else {
			loan = this.get(id);
		}

		BeanUtils.copyProperties(dto, loan, "id");

		this.loanRepository.save(loan);

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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Loan get(Long id) {

		return this.loanRepository.findById(id).orElse(null);
	}

}
