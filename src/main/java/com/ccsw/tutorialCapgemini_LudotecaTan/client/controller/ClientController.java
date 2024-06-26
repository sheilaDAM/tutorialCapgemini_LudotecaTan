/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.client.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorialCapgemini_LudotecaTan.client.model.Client;
import com.ccsw.tutorialCapgemini_LudotecaTan.client.model.ClientDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.client.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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

@Tag(name = "Client", description = "API of Client") // para consultar la documentación de la API que vamos creando -->
														// http://localhost:8080/swagger-ui/index.html
@RequestMapping(value = "/client")
@RestController
@CrossOrigin(origins = "*")
public class ClientController {

	@Autowired // para crear una instancia de ClientService y así el controlador pueda usar el
				// servicio
	private ClientService clientService;

	@Autowired
	ModelMapper mapper; // En el contexto de la conversión de objetos, DozerBeanMapper y ModelMapper son
						// herramientas que facilitan el mapeo entre diferentes objetos de Java. Son muy
						// útiles para transformar entidades JPA a DTOs (Data Transfer Objects) y
						// viceversa.

	/**
	 * Método para recuperar todas los {@link Client}
	 *
	 * @return {@link List} de {@link ClientDto}
	 */
	@Operation(summary = "Find", description = "Method that return a list of Clients")
	@RequestMapping(path = "", method = RequestMethod.GET)
	public List<ClientDto> findAll() {

		List<Client> clients = this.clientService.findAll();

		// Convertimos cada Client a ClientDto usando ModelMapper
		return clients.stream().map(e -> mapper.map(e, ClientDto.class)).collect(Collectors.toList());

		/*
		 * Este enfoque de convertir una entity en objeto dto facilita la separación de
		 * las capas de la aplicación, permitiendo que las entidades JPA permanezcan en
		 * la capa de persistencia mientras que los DTOs se utilizan para transferir
		 * datos entre la capa de servicio y la capa de presentación o API.
		 */

		/*
		 * Cuando se llama a clientService.findAll() en el controlador, en realidad se
		 * está llamando a findAll() de la instancia de ClientServiceImpl inyectada.
		 * Spring resuelve esta llamada debido a que ClientServiceImpl es la única
		 * implementación de ClientService registrada en el contexto de aplicación.
		 */

	}
	
	 /**
     * Método para crear o actualizar un cliente
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    
    //Si utilizamos la ruta con PUT --> http://localhost:8080/client --> esto crea un cliente nuevo (es el @RequestMapping "" con comillas vacías, porque tras la ruta general /client no sigue nada
    //Si utilizamos la ruta con PUT --> http://localhost:8080/client/id --> esto modifica un cliente existente según el id que se le ponga (un número entero)
    
    @Operation(summary = "Save or Update", description = "Method that saves or updates a Client")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public ResponseEntity<Void> save(@PathVariable(name = "id", required = false) Long id, @RequestBody ClientDto clientDto) {
    	
    	try {
            clientService.save(id, clientDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }
    
    /**
     * Método para borrar un {@link Client}
     *
     * @param id PK de la entidad
     */
    @Operation(summary = "Delete", description = "Method that deletes a Client")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {

        this.clientService.delete(id);
    
    }

}
