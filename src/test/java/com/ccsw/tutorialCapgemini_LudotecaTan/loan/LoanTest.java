/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.tutorialCapgemini_LudotecaTan.loan.model.Loan;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.repository.LoanRepository;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.service.LoanServiceImpl;

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

@ExtendWith(MockitoExtension.class)
public class LoanTest {

	public static final Long EXISTS_LOAN_ID = 1L;
	public static final Long NOT_EXISTS_LOAN_ID = 0L;

	@Mock
	private LoanRepository loanRepository;

	@InjectMocks
	private LoanServiceImpl loanServiceImpl;

	@Test
	public void getExistsAuthorIdShouldReturnAuthor() {

		Loan loan = mock(Loan.class);
		when(loan.getId()).thenReturn(EXISTS_LOAN_ID);
		when(loanRepository.findById(EXISTS_LOAN_ID)).thenReturn(Optional.of(loan));

		Loan loanResponse = loanServiceImpl.get(EXISTS_LOAN_ID);

		assertNotNull(loanResponse);

		assertEquals(EXISTS_LOAN_ID, loanResponse.getId());
	}

	@Test
	public void getNotExistsLoanIdShouldReturnNull() {

		when(loanRepository.findById(NOT_EXISTS_LOAN_ID)).thenReturn(Optional.empty());

		Loan loan = loanServiceImpl.get(NOT_EXISTS_LOAN_ID);

		assertNull(loan);
	}

}
