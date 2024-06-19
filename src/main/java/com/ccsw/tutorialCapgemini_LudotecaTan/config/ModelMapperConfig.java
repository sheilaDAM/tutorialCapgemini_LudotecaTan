/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Project info :)
 *
 * @project Ludoteca Tán
 * @author Sheila Isabel Jiménez Nieto
 * @since 18 jun 2024
 * @lastModified 18 jun 2024
 * @version 1.0
 *
 **/

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper() {

        return new ModelMapper();
    }


}
