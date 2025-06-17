package com.acleda.student.jwt;

public interface UserService {
    UserModel findUserByJwtToken(String jwtToken);

    UserModel findUserByEmail(String email) throws Exception;
}
