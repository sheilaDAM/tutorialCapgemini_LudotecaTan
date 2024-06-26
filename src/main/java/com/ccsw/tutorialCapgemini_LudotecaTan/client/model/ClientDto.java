/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.client.model;

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

public class ClientDto {

	private Long id;

	private String name;

	/**
	 * @return id
	 */
	public Long getId() {

		return this.id;
	}

	/**
	 * @param id new value of {@link #getId}.
	 */
	public void setId(Long id) {

		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {

		return this.name;
	}

	/**
	 * @param name new value of {@link #getName}.
	 */
	public void setName(String name) {

		this.name = name;
	}

}
