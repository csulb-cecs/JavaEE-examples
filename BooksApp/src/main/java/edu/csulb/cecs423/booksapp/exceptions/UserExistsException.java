/*
 * Copyright 2014 California State University Long Beach (CSULB) ALL RIGHTS RESERVED
 * Use of this software is authorized for CSULB students in Dr. Alvaro Monge's classes, so long
 * as this copyright notice remains intact. Students must request permission from Dr. Monge
 * if the code is to be used in other venues outside of Dr. Monge's classes.
 */

package edu.csulb.cecs423.booksapp.exceptions;

/**
 *
 * @author Alvaro Monge <alvaro.monge@csulb.edu>
 */
public class UserExistsException extends Exception {

    /**
     * Creates a new instance of <code>UserExistsException</code> without detail
     * message.
     */
    public UserExistsException() {
        super("User with the given email address already exists");
    }

    /**
     * Constructs an instance of <code>UserExistsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserExistsException(String msg) {
        super(msg);
    }
}
