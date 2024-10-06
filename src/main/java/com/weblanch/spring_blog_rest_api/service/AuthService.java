package com.weblanch.spring_blog_rest_api.service;

import com.weblanch.spring_blog_rest_api.payload.LoginDto;
import com.weblanch.spring_blog_rest_api.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
