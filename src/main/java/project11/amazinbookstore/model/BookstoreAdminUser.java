package project11.amazinbookstore.model;

import jakarta.persistence.Entity;

@Entity
public class BookstoreAdminUser extends RegisteredUser{
    private Role role;
    public BookstoreAdminUser(){
        super();
        this.role = Role.ADMIN;
    }

    @Override
    public Role getRole() {
        return role;
    }

    @Override
    public void setRole(Role role) {
        this.role = role;
    }
}
