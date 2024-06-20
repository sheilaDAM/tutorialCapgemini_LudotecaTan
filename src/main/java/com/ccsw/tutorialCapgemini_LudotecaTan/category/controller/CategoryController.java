/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.category.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorialCapgemini_LudotecaTan.category.model.Category;
import com.ccsw.tutorialCapgemini_LudotecaTan.category.model.CategoryDto;
import com.ccsw.tutorialCapgemini_LudotecaTan.category.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Project info :)
 *
 * @project Ludoteca Tán
 * @author Sheila Isabel Jiménez Nieto
 * @since 19 jun 2024
 * @lastModified 19 jun 2024
 * @version 1.0
 *
 **/

/*
 * Esta clase utiliza CategoryService para manejar solicitudes HTTP.
 * 
 * La anotación @Autowired en el constructor indica a Spring que debe inyectar un bean de tipo CategoryService cuando 
 * crea una instancia de CategoryController. Spring busca un bean que implemente CategoryService en su contexto de aplicación. 
 * Encuentra CategoryServiceImpl y la inyecta en categoryService en el controlador.
 */

@Tag(name = "Category", description = "API of Category") //para consultar la documentación de la API que vamos creando --> http://localhost:8080/swagger-ui/index.html
@RequestMapping(value = "/category")
@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
	
	
	  @Autowired //para crear una instancia de CategoryService y así el controlador pueda usar el servicio 
	  private CategoryService categoryService;
	  
	  @Autowired
	  ModelMapper mapper; //En el contexto de la conversión de objetos, DozerBeanMapper y ModelMapper son herramientas que facilitan el mapeo entre diferentes objetos de Java. Son muy útiles para transformar entidades JPA a DTOs (Data Transfer Objects) y viceversa.

    

    /**
     * Método para recuperar todas las categorías {@link Category}
     *
     * @return {@link List} de {@link CategoryDto}
     */
    @Operation(summary = "Find", description = "Method that return a list of Categories")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<CategoryDto> findAll() {
    	
    	 List<Category> categories = this.categoryService.findAll();

    	 // Convertimos cada Category a CategoryDto usando ModelMapper
         return categories.stream().map(e -> mapper.map(e, CategoryDto.class)).collect(Collectors.toList());
         
         /*
          * Este enfoque de convertir una entity en objeto dto facilita la separación de las capas de la aplicación, 
          * permitiendo que las entidades JPA permanezcan en la capa de persistencia mientras que los DTOs se utilizan 
          * para transferir datos entre la capa de servicio y la capa de presentación o API.
          */
     
    	
    	/*
    	 * Cuando se llama a categoryService.findAll() en el controlador, en realidad se está llamando a findAll() 
    	 * de la instancia de CategoryServiceImpl inyectada. Spring resuelve esta llamada debido a que CategoryServiceImpl 
    	 * es la única implementación de CategoryService registrada en el contexto de aplicación.
    	 */
    	
    }

    /**
     * Método para crear o actualizar una categoría
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    
    //Si utilizamos la ruta con PUT --> http://localhost:8080/category --> esto crea una categoría nueva (es el @RequestMapping "" con comillas vacías, porque tras la ruta general /category no sigue nada
    //Si utilizamos la ruta con PUT --> http://localhost:8080/category/id --> esto modifica una categoría existente según el id que se le ponga (un número entero)
    
    @Operation(summary = "Save or Update", description = "Method that saves or updates a Category")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody CategoryDto dto) {
    	
    	  this.categoryService.save(id, dto);

    }

    /**
     * Método para borrar una categoría {@link Category}
     *
     * @param id PK de la entidad
     */
    @Operation(summary = "Delete", description = "Method that deletes a Category")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {

        this.categoryService.delete(id);
    
    }
    
    // ----------------------------------------------------------------------------------------------------
    
	  /**
     * Método para probar el servicio
     * 
     */
	/*
    @RequestMapping(path = "", method = RequestMethod.GET)
    public String prueba() {

        return "Probando el Controller";
    }
    */
	

}
