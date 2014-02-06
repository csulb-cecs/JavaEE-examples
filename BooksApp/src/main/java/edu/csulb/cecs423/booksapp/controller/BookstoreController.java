/*
 * Copyright 2014 California State University Long Beach (CSULB) ALL RIGHTS RESERVED
 * Use of this software is authorized for CSULB students in Dr. Alvaro Monge's classes, so long
 * as this copyright notice remains intact. Students must request permission from Dr. Monge
 * if the code is to be used in other venues outside of Dr. Monge's classes.
 */

package edu.csulb.cecs423.booksapp.controller;

import edu.csulb.cecs423.booksapp.model.Book;
import edu.csulb.cecs423.booksapp.model.Bookstore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Alvaro Monge <alvaro.monge@csulb.edu>
 */
@Named
@RequestScoped
public class BookstoreController implements Serializable {
   private static final long serialVersionUID = 1L;
   private static final Logger logger = Logger.getLogger("BookstoreController");

   @EJB
   private Bookstore bookstore;
   private Book newBook = new Book();
   private List<Book> bookList = new ArrayList();
   
   /**
    * Add the book submitted via the form to the Bookstore.
    *
    * @return success, failure, or forbidden depending on the EJB request
    */
   public String doCreateBook() {
      String result = "failure";

      try {
         newBook = bookstore.createBook(newBook);
         if (newBook != null) {
            bookList = bookstore.findBooks();
            result = "success";  // return books.list instead?
         }
      } catch (javax.ejb.EJBAccessException ejbae) {
         result = "forbidden";
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Only Bookstore managers can add books."));
      }
      return result;
   }

   // ======================================
   // =          Getters & Setters         =
   // ======================================
   /**
    * The book that's currently being edited.
    *
    * @return the Book that's to be created
    */
   public Book getNewBook() {
      return newBook;
   }

   /**
    * Setter for the book that is to be submitted via a form.
    *
    * @param book the Book to be assigned as the new book
    */
   public void setNewBook(Book book) {
      this.newBook = book;
   }

   /**
    * The collection of Books in the bookstore, requested from the EJB if
    * needed.
    *
    * @return a list of Book currently in the bookstore
    */
   public List<Book> getBookList() {
      if (bookList.isEmpty()) {
         bookList = bookstore.findBooks();
      }
      return bookList;
   }

   public void setBookList(List<Book> bookList) {
      this.bookList = bookList;
   }
}
