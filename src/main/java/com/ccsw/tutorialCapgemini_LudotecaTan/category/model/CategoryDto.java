/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.category.model;

/**
 * Project info :)
 *
 * @project Ludoteca Tán
 * @author Sheila Isabel Jiménez Nieto
 * @since 20 jun 2024
 * @lastModified 20 jun 2024
 * @version 1.0
 *
 **/

/*
 * La clase CategoryDto se utiliza para transferir datos entre las diferentes capas de la aplicación 
 * (por ejemplo, entre la capa de servicio y la capa de presentación). Es una representación ligera de la entidad, 
 * normalmente utilizada para evitar exponer directamente la entidad JPA a las capas externas.
 * Esto ayuda a evitar la manipulación directa de la entidad y mejora la seguridad.
 * Encapsula los datos que se transferirán entre las capas de la aplicación. Esto puede incluir solo 
 * un subconjunto de los campos de la entidad o campos adicionales necesarios solo para la transferencia de datos.
 * Permiten la transferencia de datos en un formato que es más conveniente para las capas superiores de la aplicación 
 * (por ejemplo, controladores y vistas).
 */
public class CategoryDto {

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
