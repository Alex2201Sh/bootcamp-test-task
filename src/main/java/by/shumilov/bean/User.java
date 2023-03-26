package by.shumilov.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;
    @Pattern(regexp = "([A-Za-z0-9-]{1,40})", message = "Invalid surname. Please enter your surname")
    private String surname;
    @Pattern(regexp = "([A-Za-z0-9-]{1,20})", message = "Invalid name. Please enter your name")
    private String name;
    @Pattern(regexp = "([A-Za-z0-9-]{1,40})", message = "Invalid paternity. Please enter your paternity")
    private String paternity;
    @Email(regexp = "(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})", message = "Invalid Email.Please enter proper Email")
    private String email;
    private Role role;

}
