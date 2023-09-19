package org.io.people.entities;

import jakarta.persistence.*;

@Entity
@Table (name = "t_people")
public class Person {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "full_name")
    private String fullName;

    @OneToOne(mappedBy = "person")
    private Address address;

    @OneToOne(mappedBy = "person")
    private Mail email;

    public Person(int id, String fullName, Address address, Mail email){
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
    }

    public Person(){

    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Address getAddress() {
        return address;
    }

    public Mail getEmail() {
        return email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmail(Mail email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setId(int id) {
        this.id = id;
    }
}
