package com.okanciftci.cukatify.unit_integration;

import com.okanciftci.cukatify.entities.mongo.User;
import com.okanciftci.cukatify.security.jwt.JwtTokenProvider;
import com.okanciftci.cukatify.services.impl.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.okanciftci.cukatify.security.SecurityConstants.TOKEN_PREFIX;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Test
    void findUser(){

        // given
        String username = "TobyReinold@gmail.com";

        // when
        User user = userService.getUser(username);

        // then
        Assertions.assertEquals(user.getRole_ids().size(),2);
        Assertions.assertEquals(user.getUsername(),username);


    }
    @Test
    void loginUser(){
        //Given
        String username = "TobyReinold@gmail.com";
        String password = "123";

        //When
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);

        //Then
        Assertions.assertEquals(authentication.getAuthorities().size(),2);
        Assertions.assertEquals(authentication.getName(),username);

    }
}
