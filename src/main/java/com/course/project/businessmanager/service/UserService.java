package com.course.project.businessmanager.service;

import com.course.project.businessmanager.entity.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Optional;
import java.util.UUID;

public interface UserService extends BasicService<User, UUID>{
    User findByEmail(String email);
    User findByToken(String token);
    User registration(User user);
    void resetPassword(String email);
    User createSocialUser(OAuth2User oAuth2User);
    Optional<User> findSocialUser(String email);
    public User createManager(String email);
}
