package com.ContactVault.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactForm {

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid Email Address")
    private String email;

    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^[0-9]{10}$" , message = "Invalid Phone Number")
    private String phoneNumber;

    @NotBlank(message = "Address is required.")
    private String address;
    private String description;
    private boolean favorite=false;
    private String websiteLink;
    private String linkedInLink;

    // will create annotation to validate file size and validation
    private MultipartFile contactImage;



}
