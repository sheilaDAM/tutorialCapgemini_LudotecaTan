/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.author.model;

import com.ccsw.tutorialCapgemini_LudotecaTan.common.pagination.PageableRequest;

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

public class AuthorSearchDto {
	
	private PageableRequest pageable;

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }

}
