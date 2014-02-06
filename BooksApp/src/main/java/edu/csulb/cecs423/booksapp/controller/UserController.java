/*
 * Copyright 2014 California State University Long Beach (CSULB) ALL RIGHTS RESERVED
 * Use of this software is authorized for CSULB students in Dr. Alvaro Monge's classes, so long
 * as this copyright notice remains intact. Students must request permission from Dr. Monge
 * if the code is to be used in other venues outside of Dr. Monge's classes.
 */

package edu.csulb.cecs423.booksapp.controller;

import edu.csulb.cecs423.booksapp.model.Bookstore;
import edu.csulb.cecs423.booksapp.model.User;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alvaro Monge <alvaro.monge@csulb.edu>
 */
@Named
@SessionScoped
public class UserController implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger("UserController");

    private User user; // used during registration and while session is active

    @EJB
    private Bookstore bookstore;

    /** Creates a new instance of UserIdentity */
    public UserController() {
        user = null;
    }
    /**
     * Determine if the user is authenticated and if so, make sure the session scope includes the User object for the authenticated user
     * @return true if the user making a request is authenticated, false otherwise.
     */
    public boolean isIsUserAuthenticated() {
        boolean isAuthenticated = true;
        if (null == this.user) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            String userName = request.getRemoteUser();
            if (userName == null) {
                isAuthenticated = false;
            } else {
                this.user = bookstore.find(userName);
                isAuthenticated = (this.user != null);
                if (isAuthenticated) {
                    logger.log(Level.SEVERE, "UserIdentiy::isUserAuthenticated: Changed session, so now userIdentiy object has user=authenticated user");
                }
            }
        }

        return isAuthenticated;
    }

    /**
     * Determine if current authenticated user has the role of manager
     * @return true if user has role of manager, false otherwise.
     */
    public boolean isIsManager() {
        boolean isManager = false;
        if (this.isIsUserAuthenticated()) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            isManager = request.isUserInRole("bookstore.manager");
        }

        return isManager;
    }

    /**
     * Logout the user and invalidate the session
     * @return success if user is logged out and session invalidated, failure otherwise.
     */
    public String logout() {
        String result = "failure";

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            user = null;
            result = "success";
        } catch (ServletException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }

        return result;
    }


    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}
