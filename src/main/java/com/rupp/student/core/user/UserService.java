package com.rupp.student.core.user;

public interface UserService {
    /** check if username exist */
    boolean hasUsername(String username);

    /** get username */
    String getUsername();

    /** save user */
    void saveUser(UserEntity user);

    /** login user */
    String login(UserEntity user);
}