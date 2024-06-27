/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.tutorialCapgemini_LudotecaTan.client.model.ClientDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.config.ResponsePage;
import com.ccsw.tutorialCapgemini_LudotecaTan.game.model.GameDto;
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

/*
 * Test de integración, para pruebas completas de un determinado endpoint. En
 * estos casos de prueba, debemos comprobar si cada método ejecuta su función de
 * manera correcta, ya sea recuperando datos, creándolos, modificándolos y/o
 * borrándolos.
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LoanIT {

	public static final String LOCALHOST = "http://localhost:";
	public static final String SERVICE_PATH = "/loan";

	public static final Long DELETE_LOAN_ID = 6L;
	public static final Long MODIFY_LOAN_ID = 3L;
	public static final Long NEW_LOAN_ID = 8L;
	public static final Long NEW_CLIENT_ID = 8L;
	public static final Long NEW_GAME_ID = 8L;
	public static final String NEW_CLIENT_NAME = "Nuevo Cliente";
	public static final String NEW_GAME_TITLE = "Nuevo Juego";
	public static final LocalDate NEW_START_DATE = LocalDate.of(2024, 6, 20);
	public static final LocalDate NEW_END_DATE = LocalDate.of(2024, 6, 25);

	private static final int TOTAL_LOANS = 12;
	private static final int PAGE_SIZE = 5;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	ParameterizedTypeReference<ResponsePage<LoanDto>> responseTypePage = new ParameterizedTypeReference<ResponsePage<LoanDto>>() {
	};

	ParameterizedTypeReference<List<LoanDto>> responseTypeList = new ParameterizedTypeReference<List<LoanDto>>() {
	};

	@Test
	public void findAllShouldReturnAllLoan() {

		ResponseEntity<List<LoanDto>> response = restTemplate.exchange(
	            LOCALHOST + port + SERVICE_PATH, 
	            HttpMethod.GET, 
	            null, responseTypeList);

	        assertNotNull(response);
	        assertEquals(TOTAL_LOANS, response.getBody().size());
	}
	
	@Test
    public void saveWithoutIdShouldCreateNewLoan() {

        LoanDto dto = new LoanDto();
     
        ClientDto clientDto = new ClientDto();
        clientDto.setId(NEW_CLIENT_ID);
        
        GameDto gameDto = new GameDto();
        gameDto.setId(NEW_GAME_ID);
        
       // dto.setClient(clientDto);
        //dto.setGame(gameDto);
        dto.setStartLoanDate(NEW_START_DATE);
        dto.setEndLoanDate(NEW_END_DATE);

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        ResponseEntity<List<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
            HttpMethod.GET, null, responseTypeList);
        assertNotNull(response);
        assertEquals(TOTAL_LOANS + 1, response.getBody().size());

        LoanDto loanSearch = response.getBody().stream().filter(item -> item.getId().equals(NEW_LOAN_ID))
            .findFirst().orElse(null);
        assertNotNull(loanSearch);
        //assertEquals(NEW_CLIENT_ID, loanSearch.getClientId());
        //assertEquals(NEW_GAME_ID, loanSearch.getGameId());
        assertEquals(NEW_START_DATE, loanSearch.getStartLoanDate());
        assertEquals(NEW_END_DATE, loanSearch.getEndLoanDate());
    }

}
