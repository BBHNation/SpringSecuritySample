package Thoughtworks.UserAuth.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private String id;

    private String name;

    private String passCode;

    public User(String name, String passCode) {
        this.name = name;
        this.passCode = passCode;
    }
}
