package org.io.people.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "t_mails")
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_people_id", referencedColumnName = "id")
    private Person person;
    @Column(name = "email_type")
    private String type;
    @Column(name = "email")
    private String email;

    public Mail(int id, Person person, String type, String email){
        this.id = id;
        this.person = person;
        this.type = type;
        this.email = email;
    }
    public Mail(){

    }

    public int getId() {
        return id;
    }

    public int getTPeopleId() {
        return person.getId();
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type = type;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setId(int id){
        this.id = id;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
