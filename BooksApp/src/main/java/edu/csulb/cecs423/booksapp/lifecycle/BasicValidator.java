/*
 * Copyright 2011 California State University Long Beach (CSULB) ALL RIGHTS RESERVED
 * Use of this software is authorized for CSULB students in Dr. Monge's classes, so long
 * as this copyright notice remains intact. Students must request permission from Dr. Monge
 * if the code is to be used in other venues outside of Dr. Monge's classes.
 */

package edu.csulb.cecs423.booksapp.lifecycle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author Alvaro Monge <alvaro.monge at csulb.edu>
 */
@Named
@RequestScoped
public class BasicValidator {

    /** Creates a new instance of BasicValidator */
    public BasicValidator() {
    }

    /**
     * TODO: currently only checks for @ symbol, need to implement a more sophisticated validator
     * Validate an e-mail address to be in the (basic) correct format.
     * @param context the FacesContext
     * @param toValidate the UIComponent being validated
     * @param value the value (email address) of the component
     * @throws ValidatorException the Exception to throw b/c the value is not an e-mail address
     */
    public void validateEmail(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {
        String emailStr = (String) value;
        if (-1 == emailStr.indexOf("@")) {
            FacesMessage message = new FacesMessage("Invalid email address");
            throw new ValidatorException(message);
        }
    }

}
