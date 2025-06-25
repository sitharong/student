package com.acleda.student.jwt;

public interface UserService {

    /** check if username exist */
    public boolean hasUsername(String username);

    /** get username */
    public String getUsername();

    /** save user */
    public void saveUser(UserModel user);

    /** login user */
    public String login(UserModel user);

}
