package com.weblanch.spring_blog_rest_api.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
