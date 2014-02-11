/*
 * Copyright 2014 California State University Long Beach (CSULB) ALL RIGHTS RESERVED
 * Use of this software is authorized for CSULB students in Dr. Alvaro Monge's classes, so long
 * as this copyright notice remains intact. Students must request permission from Dr. Monge
 * if the code is to be used in other venues outside of Dr. Monge's classes.
 */

package edu.csulb.cecs423.booksapp.model;

import edu.csulb.cecs423.booksapp.exceptions.UserExistsException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Bookstore is a stateless session EJB that encapsulates the basic bookstore
 * services to retrieve all books and create a book.
 * 
 * @author Alvaro Monge <alvaro.monge@csulb.edu>
 */
@Stateless
@RolesAllowed({"bookstore.user", "bookstore.manager"})
public class Bookstore {
    @PersistenceContext(unitName = "edu.csulb.cecs423_BooksAppPU")
    private EntityManager em;

    /**
     * gets the list of books currently in the Bookstore, anyone is allowed to request this
     * @return the list of Books in the Bookstore
     */
    public List<Book> getBooks() {
        TypedQuery<Book> query = em.createNamedQuery(Book.FIND_ALL_BOOKS, Book.class);
        return query.getResultList();
    }

    /**
     * creates a book on the database, but only if the user requesting it has the role bookstore.manager
     * @param book the book to be added to the database.
     * @return the book object if persisted correctly, <code>null</code> otherwise.
     */
    @RolesAllowed("bookstore.manager")
    public Book createBook(Book book) {
        if (book.isValid()) {
            em.persist(book);
        } else {
            book = null;
        }
        return book;
    }

    /**
     * TODO: not the best place for this since this EJB is also doing bookstore stuff
     * 
     * registers a user assigning a group that will give user authority to 
     * perform functions associated with the group.
     * @param user the User to be registered
     * @param groupName the group that User is to be registered with
     * @throws UserExistsException when there is already a user w/same username
     */
    @PermitAll
    public void registerUser(User user, String groupName) throws UserExistsException {
        // 1: Use security EJB to add username/password to security
        // 2: if successful, then add as a registered bookstore user

        if (null == em.find(User.class, user.getEmail())) {
            Group group = em.find(Group.class, groupName);
            if (group == null) {
                group = new Group(groupName);
            }
            user.addGroup(group);
            group.addUser(user);
            em.persist(user);
            em.flush();
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "A user exists already with email addresss {0}", user.getEmail());
            throw new UserExistsException();
        }
    }

    /**
     * finds a user given the username
     * @param username the string what is the username to be found
     * @return the user with the matching username; <code>null</code> otherwise
     */
    @PermitAll
    public User find(String username) {
        return em.find(User.class, username);
    }
}
