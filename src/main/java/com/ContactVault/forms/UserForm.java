package com.ContactVault.forms;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "Username is required")
    @Size(min=3 ,message = "Min 3 character is required")
    private String name;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=6, message = "Min 6 characters are required")
    private String password;

    @Size(min=8 ,max=12 ,message="Invalid phone number")
    private String phoneNumber;

    @NotBlank(message = "About is required")
    private String about;


}
