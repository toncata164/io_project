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

    @Column(name="pin")
    private String pin;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="t_people_id")
    private List<Address> address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="t_people_id")
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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
