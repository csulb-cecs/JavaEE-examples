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

/**
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Group> getGroups() {
        return groups;
    }

    public void setGroups(Collection<Group> groups) {
        this.groups = groups;
    }
    
    /**
     * Add a group to the user's set of groups
     * @param group to be added
     */
    public void addGroup(Group group) {
        if (this.groups == null)
            this.groups = new HashSet<Group>();
        this.groups.add(group);
    }


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
