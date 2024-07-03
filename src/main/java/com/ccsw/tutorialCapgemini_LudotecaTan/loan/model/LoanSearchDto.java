/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan.model;

import java.time.LocalDate;

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

	private String gameTitle;
    private Long clientId;
    private LocalDate startDate;
    private LocalDate endDate;
	
    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }
    
    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}
