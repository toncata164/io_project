package org.io.people.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "t_people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "full_name")
    private String fullName;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> address;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mail> email;

    public Person(int id, String fullName, List<Address> address, List<Mail> email){
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

    public List<Address> getAddress() {
        return address;
    }

    public List<Mail> getEmail() {
        return email;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public void setEmail(List<Mail> email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setId(int id) {
        this.id = id;
    }
}
