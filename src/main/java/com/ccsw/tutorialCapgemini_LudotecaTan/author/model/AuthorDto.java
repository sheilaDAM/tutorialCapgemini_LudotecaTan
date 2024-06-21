/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.author.model;

/**
 * Project info :)
 *
 * @project Ludoteca Tán
 * @author Sheila Isabel Jiménez Nieto
 * @since 21 jun 2024
 * @lastModified 21 jun 2024
 * @version 1.0
 *
 **/

public class AuthorDto {

    private Long id;

    private String name;

    private String nationality;

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

    /**
     * @return nationality
     */
    public String getNationality() {

        return this.nationality;
    }

    /**
     * @param nationality new value of {@link #getNationality}.
     */
    public void setNationality(String nationality) {

        this.nationality = nationality;
    }

}
