package org.io.people.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "t_addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "id")
    private Person person;
    @Column(name = "addr_type")
    private String type;
    @Column(name = "addr_info")
    private String info;

    public Address(int id, Person person, String type, String info){
        this.id = id;
        this.person = person;
        this.type = type;
        this.info = info;
    }
    public Address(){

    }
    public int getId() {
        return id;
    }

    public int getPersonId() {
        return person.getId();
    }

    public String getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }

    public void setType(String type){
        this.type = type;
    }
    public void setInfo(String info){
        this.info = info;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
