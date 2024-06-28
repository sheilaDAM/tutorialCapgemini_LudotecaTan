/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import com.ccsw.tutorialCapgemini_LudotecaTan.common.pagination.PageableRequest;
import com.ccsw.tutorialCapgemini_LudotecaTan.config.ResponsePage;
import com.ccsw.tutorialCapgemini_LudotecaTan.game.model.GameDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.model.LoanDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.model.LoanSearchDto;

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
	public static final Long NEW_LOAN_ID = 13L;
	public static final Long NEW_CLIENT_ID = 1L;
	public static final Long NEW_GAME_ID = 1L;
	public static final String NEW_CLIENT_NAME = "Nuevo Cliente";
	public static final String NEW_GAME_TITLE = "Nuevo Juego";
	public static final LocalDate NEW_START_DATE = LocalDate.of(2024, 7, 26);
	public static final LocalDate NEW_END_DATE = LocalDate.of(2024, 7, 30);

	private static final int TOTAL_LOANS = 12;

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

		ResponseEntity<List<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.GET,
				null, responseTypeList);

		assertNotNull(response);
		assertEquals(TOTAL_LOANS, response.getBody().size());
	}

	@Test
	public void saveWithoutIdShouldCreateNewLoan() {

		long newLoanId = TOTAL_LOANS + 1;
		long newLoanSize = TOTAL_LOANS + 1;

		// Creamos el objeto LoanDto que se va a enviar en la solicitud para guardar
		LoanDto dto = new LoanDto();

		ClientDto clientDto = new ClientDto();
		clientDto.setId(NEW_CLIENT_ID);

		GameDto gameDto = new GameDto();
		gameDto.setId(NEW_GAME_ID);

		dto.setClient(clientDto);
		dto.setGame(gameDto);
		dto.setStartLoanDate(NEW_START_DATE);
		dto.setEndLoanDate(NEW_END_DATE);

		// Enviamos una solicitud PUT para simular la creación del nuevo préstamo con
		// los datos de LoanDto
		restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

		// Definimos los parámetros de consulta paginada que se realizará después para
		// obtener la lista de préstamos
		// La paginación nos permite obtener una porció de los datos en vez de todos a
		// la vez
		LoanSearchDto loanSearchDto = new LoanSearchDto(); // objeto que contiene la configuración de la paginación
		loanSearchDto.setPageable(new PageableRequest(0, (int) newLoanSize)); // Define el número de página (0) y el
																				// tamaño de la página, es decir,
																				// cuántos elementos deseamos mostrar en
																				// la página .

		// Enviamos una solicitud POST para obtener la lista de préstamos con los
		// parámetros definidos
		ResponseEntity<ResponsePage<LoanDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
				HttpMethod.POST, new HttpEntity<>(loanSearchDto), responseTypePage);

		/*
		 * El servidor recibe la solicitud y extrae los parámetros de búsqueda y
		 * paginación. Utiliza estos parámetros para buscar y paginar los datos en la
		 * base de datos. Devuelve una página de resultados que contiene una lista de
		 * LoanDto.
		 */

		assertNotNull(response); // Verificamosque la respuesta no es nula
		assertEquals(newLoanSize, response.getBody().getTotalElements()); // Verificamos que el número total de
																			// préstamos es el esperado

		// Buscamos si el nuevo préstamo se creó y aparece en la lista préstamos
		LoanDto loan = response.getBody().getContent().stream().filter(item -> item.getId().equals(newLoanId))
				.findFirst().orElse(null);

		assertNotNull(loan);
		assertEquals(newLoanId, loan.getId()); // Verificamos de que el ID del préstamo es el esperado
		assertEquals(clientDto.getId(), loan.getClient().getId());
		assertEquals(gameDto.getId(), loan.getGame().getId());
		assertEquals(NEW_START_DATE, loan.getStartLoanDate());
		assertEquals(NEW_END_DATE, loan.getEndLoanDate());
	}

	@Test
	public void deleteWithExistsIdShouldDeleteLoan() {
		// Definimos el ID del préstamo que vamos a eliminar
		Long loanIdToDelete = DELETE_LOAN_ID;

		// Verificamos que el préstamo existe antes de eliminarlo
		ResponseEntity<List<LoanDto>> initialResponse = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
				HttpMethod.GET, null, responseTypeList);

		assertNotNull(initialResponse);
		// Verificamos que el préstamo elegido está en la lista de préstamos.
		assertTrue(initialResponse.getBody().stream().anyMatch(loan -> loan.getId().equals(loanIdToDelete)));

		// Enviamos una solicitud DELETE para eliminar el préstamo
		restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "/" + loanIdToDelete, HttpMethod.DELETE, null,
				Void.class);

		// Verificamos que el préstamo ya no existe después de eliminarlo
		ResponseEntity<List<LoanDto>> finalResponse = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
				HttpMethod.GET, null, responseTypeList);

		assertNotNull(finalResponse);
		// Verificamos que el préstamos ya no existe en la lista de préstamos y se
		// eliminó correctamente
		assertFalse(finalResponse.getBody().stream().anyMatch(loan -> loan.getId().equals(loanIdToDelete)));
	}

}
