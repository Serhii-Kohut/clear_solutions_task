package org.my.clear_solutions_task.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email is required")
    String email;

    @NotEmpty(message = "First name is required")
    String firstName;

    @NotEmpty(message = "Last name is required")
    String lastName;

    @Past(message = "Birth date should be in the past")
    LocalDate birthDate;

    String address;

    String phoneNumber;
}
