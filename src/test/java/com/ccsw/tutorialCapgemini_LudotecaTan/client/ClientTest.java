/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.tutorialCapgemini_LudotecaTan.client.model.Client;
import com.ccsw.tutorialCapgemini_LudotecaTan.client.model.ClientDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.client.repository.ClientRepository;
import com.ccsw.tutorialCapgemini_LudotecaTan.client.service.ClientServiceImpl;


/**
 * Project info :)
 *
 * @project Ludoteca Tán
 * @author Sheila Isabel Jiménez Nieto
 * @since 26 jun 2024
 * @lastModified 26 jun 2024
 * @version 1.0
 *
 **/

/*
 * Clase para pruebas, en este caso pruebas unitarias, relativas a la calidad estática del código de una 
 * determinada operación. Prueba la lógica dentro de la operación de negocio, sea de consulta, creación, 
 * modificación y/o borrado de un cliente. 
 */

@ExtendWith(MockitoExtension.class)
public class ClientTest {
	
	public static final String CLIENT_NAME = "Cliente test";
	public static final Long EXISTS_CLIENT_ID = 1L;
    public static final Long NOT_EXISTS_CLIENT_ID = 0L;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    //El propósito del siguiente método es comprobar si existe o no un cliente pasándole un id determinado
    @Test
    public void getExistsClientIdShouldReturnClient() {

          Client client = mock(Client.class);
          when(client.getId()).thenReturn(EXISTS_CLIENT_ID);
          when(clientRepository.findById(EXISTS_CLIENT_ID)).thenReturn(Optional.of(client));

          Client clientResponse = clientService.get(EXISTS_CLIENT_ID);

          assertNotNull(clientResponse);

          assertEquals(EXISTS_CLIENT_ID, clientResponse.getId());
    }

    @Test
    public void getNotExistsClientIdShouldReturnNull() {

          when(clientRepository.findById(NOT_EXISTS_CLIENT_ID)).thenReturn(Optional.empty());

          Client client = clientService.get(NOT_EXISTS_CLIENT_ID);

          assertNull(client);
    }
    
    @Test
	public void findAllShouldReturnAllClients() {

		List<Client> list = new ArrayList<>();
		list.add(mock(Client.class)); //mock(Category.class) crea una instancia simulada de la clase Client, agregando un mock (simulación) de un objeto Client, simulando que hay un cliente en la bbdd

		when(clientRepository.findAll()).thenReturn(list); //Cuando se llame al método findAll() que devuelva la lista (en este caso del test, devolverá 1, que es lo que pasamos en el mock anterior)

		List<Client> clients = clientService.findAll();

		assertNotNull(clients); //comprueba que la lista obtenida no sea null
		assertEquals(1, clients.size()); //verifica que el tamaño de la lista sea 1
	}
    
	@Test
	public void saveNotExistsClientIdShouldInsert() throws Exception {

		ClientDto clientDto = new ClientDto();
		clientDto.setName(CLIENT_NAME);

		ArgumentCaptor<Client> client = ArgumentCaptor.forClass(Client.class); //Captura los argumentos que se pasan a un método en un mock.

		clientService.save(null, clientDto);

		verify(clientRepository).save(client.capture()); //Verifica que un método en un mock fue llamado con ciertos argumentos.

		assertEquals(CLIENT_NAME, client.getValue().getName()); //Verifica que el método save del clientRepository fue llamado y captura el argumento pasado
	}
    
    @Test
	public void saveExistsClientIdShouldUpdate() throws Exception {

	  ClientDto clientDto = new ClientDto();
	  clientDto.setName(CLIENT_NAME);

	  Client client = mock(Client.class);
	  when(clientRepository.findById(EXISTS_CLIENT_ID)).thenReturn(Optional.of(client));

	  clientService.save(EXISTS_CLIENT_ID, clientDto);

	  verify(clientRepository).save(client);
	}
    
    @Test
	public void deleteExistsClientIdShouldDelete() throws Exception {

	      Client client = mock(Client.class);
	      when(clientRepository.findById(EXISTS_CLIENT_ID)).thenReturn(Optional.of(client)); //Optional.of(client): Crea un Optional que contiene el mock Client, simulando que el repositorio encuentra un cliente con el ID dado.

	      clientService.delete(EXISTS_CLIENT_ID);

	      verify(clientRepository).deleteById(EXISTS_CLIENT_ID); //Verifica que el método deleteById del repositorio fue llamado con el ID especificado, confirmando que el servicio intentó eliminar el cliente.
	}
	

}
