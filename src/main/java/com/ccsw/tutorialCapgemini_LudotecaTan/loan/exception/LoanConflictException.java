/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan.exception;

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
public class LoanConflictException  extends Exception {
    public LoanConflictException(String message) {
        super(message);
    }
}
