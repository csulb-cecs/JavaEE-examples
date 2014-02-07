/*
 * Copyright 2014 California State University Long Beach (CSULB) ALL RIGHTS RESERVED
 * Use of this software is authorized for CSULB students in Dr. Alvaro Monge's classes, so long
 * as this copyright notice remains intact. Students must request permission from Dr. Monge
 * if the code is to be used in other venues outside of Dr. Monge's classes.
 */

package edu.csulb.cecs423.booksapp.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User encapsulates the users that have registered for this app.
 * 
 * @author Alvaro Monge <alvaro.monge@csulb.edu>
 */
@Entity(name="Users")
@NamedQueries ({
    @NamedQuery(name = User.FIND_USER_BY_EMAIL, query = "SELECT u FROM Users u WHERE u.email = :email")
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * Name of JPQL query to retrieve the Users with a given email address
     */
    public static final String FIND_USER_BY_EMAIL = "User.findUserByEmail";

    @Id
    private String email;
    private String firstName;
    private String lastName;

    @Column(length=32, nullable=false)
    private String password;
    @ManyToMany(mappedBy="users", cascade=CascadeType.ALL)
    private Collection<Group> groups;

    /**
     * gets the email address of this user
     * @return the email address this user has
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the e-mail address for this user
     * @param email the email address to use for this user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * gets the first name of this user
     * @return the first name of this user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * sets the first name of this user 
     * @param firstName the string to user as the first name of this user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * gets the last name of this user
     * @return the last name of this user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * sets the last name of this user 
     * @param lastName the string to user as the last name of this user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * gets the password for this user
     * @return the string that is the password of this user
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets the password for this user
     * @param password is the string to be assigned to this user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets the groups that this user is a member of
     * @return a collection of groups that this user belongs to
     */
    public Collection<Group> getGroups() {
        return groups;
    }

    /**
     * sets the groups that this user belongs to
     * @param groups is the collection of groups that this user is a member of
     */
    public void setGroups(Collection<Group> groups) {
        this.groups = groups;
    }
    
    /**
     * Add a group to the user's set of groups
     * @param group to be added
     */
    public void addGroup(Group group) {
        if (this.groups == null)
            this.groups = new HashSet();
        this.groups.add(group);
    }

    /**
     * determines whether or not the information for this book is valid
     * @param confirmPassword the password to be confirmed
     * @return <code>true</code> if this book has valid information; 
     *         <code>false</code> otherwise
     */
    public boolean isInformationValid(String confirmPassword) {
        return (firstName != null && lastName != null
                 && email != null && password != null
                 && confirmPassword.equals(password));
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the email fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return (this.email != null || other.email == null) && (this.email == null || this.email.equals(other.email));
    }

    @Override
    public String toString() {
        return "User[email=" + email + ", name=" + firstName + " " + lastName + "]";
    }

}
