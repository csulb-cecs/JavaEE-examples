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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Alvaro Monge <alvaro.monge@csulb.edu>
 */
@Entity
@Table(name="groups")
@NamedQueries ({
    @NamedQuery(name = Group.FIND_GROUP_BY_USERNAME, query = "SELECT g FROM Group g WHERE g.name = :groupname")
})
public class Group implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * Name of JPQL query to retrieve the Group given its name
     */
    public static final String FIND_GROUP_BY_USERNAME = "Group.findGroupByName";
    
    @Id
    private String name;
    private String description;
    @ManyToMany
    @JoinTable(name="groups_users",
          joinColumns=@JoinColumn(name="groupname"),
          inverseJoinColumns=@JoinColumn(name="email"))
    private Collection<User> users;

    public Group() { }

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        if (this.users == null)
            this.users = new HashSet<User>();
        this.users.add(user);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the name fields are not set
        if (!(object instanceof Group)) {
            return false;
        }
        Group other = (Group) object;
        return (this.name != null || other.name == null) && (this.name == null || this.name.equals(other.name));
    }

    @Override
    public String toString() {
        return "edu.csulb.cecs423.booksapp.model.Group[ id=" + name + " ]";
    }
    
}
