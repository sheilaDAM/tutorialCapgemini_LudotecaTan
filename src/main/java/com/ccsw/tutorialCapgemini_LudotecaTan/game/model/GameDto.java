/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.game.model;

import com.ccsw.tutorialCapgemini_LudotecaTan.author.model.AuthorDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.category.model.CategoryDto;

/**
 * Project info :)
 *
 * @project Ludoteca Tán
 * @author Sheila Isabel Jiménez Nieto
 * @since 25 jun 2024
 * @lastModified 25 jun 2024
 * @version 1.0
 *
 **/

public class GameDto {
	
	  private Long id;

	    private String title;

	    private String age;

	    private CategoryDto category;

	    private AuthorDto author;

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
	     * @return title
	     */
	    public String getTitle() {

	        return this.title;
	    }

	    /**
	     * @param title new value of {@link #getTitle}.
	     */
	    public void setTitle(String title) {

	        this.title = title;
	    }

	    /**
	     * @return age
	     */
	    public String getAge() {

	        return this.age;
	    }

	    /**
	     * @param age new value of {@link #getAge}.
	     */
	    public void setAge(String age) {

	        this.age = age;
	    }

	    /**
	     * @return category
	     */
	    public CategoryDto getCategory() {

	        return this.category;
	    }

	    /**
	     * @param category new value of {@link #getCategory}.
	     */
	    public void setCategory(CategoryDto category) {

	        this.category = category;
	    }

	    /**
	     * @return author
	     */
	    public AuthorDto getAuthor() {

	        return this.author;
	    }

	    /**
	     * @param author new value of {@link #getAuthor}.
	     */
	    public void setAuthor(AuthorDto author) {

	        this.author = author;
	    }

}
