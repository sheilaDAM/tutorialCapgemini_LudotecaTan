/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan;

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

public class LoanSearchDto {
	
	private PageableRequest pageable;

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }

}
