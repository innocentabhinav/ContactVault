package com.ContactVault.forms;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String about;


}
