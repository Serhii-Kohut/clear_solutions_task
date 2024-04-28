package org.my.clear_solutions_task.service;

import org.my.clear_solutions_task.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    User createUser(User user);

    User updateUserFields(Long id, User updatedUser);

    User updateAllUserFields(Long id, User updatedUser);

    void deleteUser(Long id);

    List<User> getUsersByBirthDateRange(LocalDate from, LocalDate to);
}
