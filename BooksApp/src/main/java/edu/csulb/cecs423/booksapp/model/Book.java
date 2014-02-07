/*
 * Copyright 2014 California State University Long Beach (CSULB) ALL RIGHTS RESERVED
 * Use of this software is authorized for CSULB students in Dr. Alvaro Monge's classes, so long
 * as this copyright notice remains intact. Students must request permission from Dr. Monge
 * if the code is to be used in other venues outside of Dr. Monge's classes.
 */

package edu.csulb.cecs423.booksapp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Book encapsulates the information representing a book. Each book is 
 * automatically assigned an identifier, though, each book must have
 * a unique isbn.
 * 
 * @author Alvaro Monge <alvaro.monge@csulb.edu>
 */
@Entity
@NamedQueries ({
    @NamedQuery(name = Book.FIND_ALL_BOOKS, query = "SELECT b FROM Book b")
})
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * JPQL query to retrieve all Book entities
     */
    public static final String FIND_ALL_BOOKS = "Book.findAllBooks";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private Float price;
    @Size(max = 2000)
    @Column(length = 2000)
    private String description;
    @Column(unique = true)
    private String isbn;
    private Integer nbOfPage;
    private boolean illustrations;

    /**
     * gets the unique identifier of this book
     * @return the id of this book
     */
    public Long getId() {
        return id;
    }

    /**
     * sets the unique identifier of this book
     * @param id   the unique identifier of this book
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * gets the string holding the title of this book
     * @return the title of this book
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets the title of this book
     * @param title  the string to use for the title of this book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets the price of this book
     * @return the price of this book price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * sets the price of this book
     * @param price the amount to be used as the title of this book
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * gets the description of this book
     * @return the string giving a description of this book
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description of this book
     * @param description the string that has the description of this book
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * gets the isbn of this book
     * @return the string that is the isbn of this book
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * sets the isbn of this book, it's required to be unique
     * @param isbn is the string with the isbn of this book
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * gets the number of pages of this book
     * @return the number of pages of this book
     */
    public Integer getNbOfPage() {
        return nbOfPage;
    }

    /**
     * sets the number of pages this book has
     * @param nbOfPage the number of page of this book
     */
    public void setNbOfPage(Integer nbOfPage) {
        this.nbOfPage = nbOfPage;
    }

    /**
     * tests to determine if the book as illustrations
     * @return <code>true</code> if the book has illustrations; 
     *         <code>false</code> otherwise
     */
    public boolean isIllustrations() {
        return illustrations;
    }

    /**
     * sets whether or not this book has illustrations
     * @param illustrations is the boolean representing whether or not this book has illustrations
     */
    public void setIllustrations(boolean illustrations) {
        this.illustrations = illustrations;
    }
    
    /**
     * tests to see if this book is valid
     * @return <code>true</code> if this book has valid values (it has a title
     *              and an isbn;
     *         <code>false</code> otherwise
     */
    public boolean isValid() {
        return (title != null && title.length() > 0 &&
                isbn != null && isbn.length() > 0);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Book");
        sb.append("{id=").append(id);
        sb.append(", ibsn='").append(isbn).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append(", nbOfPage=").append(nbOfPage);
        sb.append(", illustrations=").append(illustrations);
        sb.append('}');
        return sb.toString();
    }

}
