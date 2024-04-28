package org.my.clear_solutions_task.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.my.clear_solutions_task.entity.User;
import org.my.clear_solutions_task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    User createTestUser() {
        User user = new User();
        user.setEmail(UUID.randomUUID() + "@example.com");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setBirthDate(LocalDate.of(2000, 1, 1));
        user.setAddress("123 Test Street");
        user.setPhoneNumber("1234567890");
        return user;
    }

    @Test
    public void testCreateUser() throws Exception {
        mockMvc.perform(post("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"firstName\":\"Test\",\"lastName\":\"User\",\"birthDate\":\"2000-01-01\",\"address\":\"123 Test Street\",\"phoneNumber\":\"1234567890\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUserFields() throws Exception {
        User testUser = createTestUser();
        User savedUser = createTestUser();
        savedUser.setId(1L);

        when(userService.createUser(testUser)).thenReturn(savedUser);

        mockMvc.perform(patch("/api/users/" + savedUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Updated\",\"lastName\":\"User\"}"))
                .andExpect(status().isOk());
    }


    @Test
    public void testUpdateAllUserFields() throws Exception {
        User testUser = createTestUser();
        User savedUser = createTestUser();
        savedUser.setId(1L);

        when(userService.createUser(testUser)).thenReturn(savedUser);

        mockMvc.perform(put("/api/users/" + savedUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Updated\",\"lastName\":\"User\",\"birthDate\":\"2001-01-01\",\"address\":\"456 Updated Street\",\"phoneNumber\":\"0987654321\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUser() throws Exception {
        User testUser = createTestUser();
        User savedUser = createTestUser();
        savedUser.setId(1L);

        when(userService.createUser(testUser)).thenReturn(savedUser);

        mockMvc.perform(delete("/api/users/" + savedUser.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUsersByBirthDateRange() throws Exception {
        mockMvc.perform(get("/api/users/")
                        .param("from", "1999-01-01")
                        .param("to", "2001-01-01"))
                .andExpect(status().isOk());
    }


}
