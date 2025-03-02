package com.rnyd.rnyd.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
// CUIDADO COMILLAS NECESARIAS
@Table(name = "\"user\"")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email",length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "name",length = 20 ,nullable = false)
    private String name;

    @Column(name = "surname",length = 75 ,nullable = false)
    private String surname;

    @Column(name = "birth_date",nullable = false)
    private LocalDate birth_date;

    @Column(name = "keyword",length = 100 , nullable = false)
    private String keyword;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public String getKeyword() {
        return keyword;
    }

    public UserEntity(String email, String name, String surname, LocalDate birth_date, String keyword) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.keyword = keyword;
    }

    public Long getId() {
        return id;
    }
    public UserEntity() {
    }
}
