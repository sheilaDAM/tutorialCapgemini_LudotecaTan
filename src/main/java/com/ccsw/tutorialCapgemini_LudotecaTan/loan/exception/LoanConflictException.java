/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Project info :)
 *
 * @project Ludoteca Tán
 * @author Sheila Isabel Jiménez Nieto
 * @since 2 jul 2024
 * @lastModified 2 jul 2024
 * @version 1.0
 *
 **/

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class LoanConflictException extends RuntimeException {
    public LoanConflictException(String message) {
        super(message);
    }
}
