package org.my.clear_solutions_task.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.my.clear_solutions_task.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImplTest {
    @Autowired
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
    public void testCreateUser() {
        User testUser = createTestUser();
        User createdUser = userService.createUser(testUser);

        Assertions.assertNotNull(createdUser.getId());
        Assertions.assertEquals(testUser.getEmail(), createdUser.getEmail());
        Assertions.assertEquals(testUser.getFirstName(), createdUser.getFirstName());
        Assertions.assertEquals(testUser.getLastName(), createdUser.getLastName());
        Assertions.assertEquals(testUser.getBirthDate(), createdUser.getBirthDate());
        Assertions.assertEquals(testUser.getAddress(), createdUser.getAddress());
        Assertions.assertEquals(testUser.getPhoneNumber(), createdUser.getPhoneNumber());
    }


    @Test
    public void testUpdateUserFields() {
        User testUser = createTestUser();
        userService.createUser(testUser);

        User updatedUser = new User();
        updatedUser.setFirstName("Updated");
        updatedUser.setLastName("User");

        User result = userService.updateUserFields(testUser.getId(), updatedUser);

        Assertions.assertEquals(updatedUser.getFirstName(), result.getFirstName());
        Assertions.assertEquals(updatedUser.getLastName(), result.getLastName());
    }


    @Test
    public void testUpdateAllUserFields() {
        User testUser = createTestUser();
        userService.createUser(testUser);

        User updatedUser = new User();
        updatedUser.setFirstName("Updated");
        updatedUser.setLastName("User");
        updatedUser.setBirthDate(LocalDate.of(2001, 1, 1));
        updatedUser.setAddress("456 Updated Street");
        updatedUser.setPhoneNumber("0987654321");

        User result = userService.updateAllUserFields(testUser.getId(), updatedUser);

        Assertions.assertEquals(updatedUser.getFirstName(), result.getFirstName());
        Assertions.assertEquals(updatedUser.getLastName(), result.getLastName());
        Assertions.assertEquals(updatedUser.getBirthDate(), result.getBirthDate());
        Assertions.assertEquals(updatedUser.getAddress(), result.getAddress());
        Assertions.assertEquals(updatedUser.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    public void testDeleteUser() {
        User testUser = createTestUser();
        userService.createUser(testUser);
        userService.deleteUser(testUser.getId());

        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.updateUserFields(testUser.getId(), new User()));
    }

    @Test
    public void testGetUsersByBirthDateRange() {
        User testUser = createTestUser();
        userService.createUser(testUser);
        List<User> result = userService.getUsersByBirthDateRange(LocalDate.of(1999, 1, 1), LocalDate.of(2001, 1, 1));

        Assertions.assertTrue(result.contains(testUser));
    }

}