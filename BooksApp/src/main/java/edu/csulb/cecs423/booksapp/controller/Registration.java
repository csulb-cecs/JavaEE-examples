/*
 *  Copyright (C) 2011 Alvaro Monge (amonge at csulb dot edu)
 *  California State University Long Beach (CSULB) ALL RIGHTS RESERVED
 * 
 *  Use of this software is authorized for CSULB students in Dr. Monge's classes, so long
 *  as this copyright notice remains intact. Students must request permission from Dr. Monge
 *  if the code is to be used in other venues outside of Dr. Monge's classes.
 * 
 *  This program is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * 
 */

package edu.csulb.cecs423.booksapp.controller;

import edu.csulb.cecs423.booksapp.exceptions.UserExistsException;
import edu.csulb.cecs423.booksapp.model.Bookstore;
import edu.csulb.cecs423.booksapp.model.User;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Alvaro Monge (amonge at csulb dot edu)
 */
@Named
@RequestScoped
public class Registration implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger("SecurityBacking");

    private User newUser; // used during registration and while session is active
    @EJB
    private Bookstore bookstore;

    private String passwordConfirmation;

    /** Creates a new instance of Registration */
    public Registration() {
        newUser = new User();
    }

    /**
     * JSF Action that uses the information submitted in the registration page to add user as a registered Bookstore user.
     * @return either failure, success, or register depending on successful registration.
     */
    public String registerUser() {
        String result = "failure";
        if (newUser.isInformationValid(passwordConfirmation)) {
            newUser.setPassword(edu.csulb.cecs423.booksapp.controller.MD5Hash.hashPassword(newUser.getPassword()));
            try {
                bookstore.registerUser(newUser, "bookstore.user");
                result = "success";
            } catch (UserExistsException uee) {
                logger.log(Level.SEVERE, null, uee);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("A user with that information already exists, try again."));
                result = "register";
            } catch (Exception e) {
                logger.log(Level.SEVERE, null, e);
                result = "failure";
            }
        }

        return result;
    }

    /**
     * @return the user
     */
    public User getNewUser() {
        return newUser;
    }

    /**
     * @param user the user to set
     */
    public void setNewUser(User user) {
        this.newUser = user;
    }

    /**
     * @return the passwordConfirmation
     */
    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    /**
     * @param passwordConfirmation the passwordConfirmation to set
     */
    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

}
