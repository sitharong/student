package com.rupp.student.core.jwt;

public interface UserService {
    /** check if username exist */
    boolean hasUsername(String username);

    /** get username */
    String getUsername();

    /** save user */
    void saveUser(UserModel user);

    /** login user */
    String login(UserModel user);
}