/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.client.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.tutorialCapgemini_LudotecaTan.client.model.Client;
import com.ccsw.tutorialCapgemini_LudotecaTan.client.model.ClientDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.client.repository.ClientRepository;

import jakarta.transaction.Transactional;

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

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Client get(Long id) {

		return this.clientRepository.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Client> findAll() {

		return (List<Client>) this.clientRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(Long id, ClientDto dto) throws Exception {

		if (clientRepository.clientAlreadyExistsByName(dto.getName())) {
            throw new Exception("Client with this name already exists");
        }

        Client client = (id == null) ? new Client() : this.get(id);

        BeanUtils.copyProperties(dto, client, "id");
        this.clientRepository.save(client);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) throws Exception {

		if (this.get(id) == null) {
			throw new Exception("Not exists");
		}

		this.clientRepository.deleteById(id);
	}

}
