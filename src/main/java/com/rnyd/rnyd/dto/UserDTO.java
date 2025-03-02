package com.rnyd.rnyd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

public class UserDTO {
    public UserDTO(String name, String surname, String email, String keyword, LocalDate birth_date) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.keyword = keyword;
        this.birth_date = birth_date;
    }

    private String name, surname, email, keyword;
    private LocalDate birth_date;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getKeyword() {
        return keyword;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }
}
