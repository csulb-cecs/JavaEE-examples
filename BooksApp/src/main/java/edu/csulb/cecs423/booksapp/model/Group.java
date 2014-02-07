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
 * Group encapsulates a security group and is used for role-based authorization.
 * It maintains a collection of users and thus all users in a group will have
 * the same role and thus share be authorized to have the same permissions.
 * It's possible for a user to be part of many groups.
 * 
 * @author Alvaro Monge <alvaro.monge@csulb.edu>
 */
@Entity(name="UserGroup")
@Table(name="groups")
@NamedQueries ({
    @NamedQuery(name = Group.FIND_GROUP_BY_USERNAME, query = "SELECT g FROM UserGroup g WHERE g.name = :groupname")
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

    /**
     * 
     */
    public Group() { }

    /**
     * creates a group with the given name
     * @param name is the name of the group to be created
     */
    public Group(String name) {
        this.name = name;
    }

    /**
     * gets the name of this group
     * @return the name of this group
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of this group 
     * @param name is the string that is to be the name of this group
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the description of this group
     * @return is the string describing this group
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description of this group
     * @param description is the string to be used to describe this group
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * gets the collection of users that are members of this group
     * @return the collection of users in this group
     */
    public Collection<User> getUsers() {
        return users;
    }

    /**
     * sets the collection of users in this group
     * @param users the collection of users in this group
     */
    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    /**
     * adds a user to this group
     * @param user is the user to be added to this group
     */
    public void addUser(User user) {
        if (this.users == null)
            this.users = new HashSet();
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
        final StringBuilder sb = new StringBuilder();
        sb.append("Group");
        sb.append("{name=").append(name);
        sb.append(", description='").append(description);
        sb.append('}');
        return sb.toString();

    }
    
}
