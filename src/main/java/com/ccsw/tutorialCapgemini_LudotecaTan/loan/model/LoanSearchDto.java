/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan.model;

import com.ccsw.tutorialCapgemini_LudotecaTan.common.pagination.PageableRequest;

/**
 * Project info :)
 *
 * @project Ludoteca Tán
 * @author Sheila Isabel Jiménez Nieto
 * @since 27 jun 2024
 * @lastModified 27 jun 2024
 * @version 1.0
 *
 **/

/*
 * clase que contiene un objeto PageableRequest. Esta clase se utiliza para encapsular 
 * los parámetros de paginación que se utilizarán al realizar la búsqueda de préstamos.
 */

public class LoanSearchDto {
	
	private PageableRequest pageable; //Clase que encapsula la información de paginación, como el número de página y el tamaño de la página

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }

}
