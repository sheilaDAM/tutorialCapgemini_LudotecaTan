/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.category;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

@Tag(name = "Category", description = "API of Category")
@RequestMapping(value = "/category")
@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

    /**
     * Método para probar el servicio
     * 
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public String prueba() {

        return "Probando el Controller";
    }

}
