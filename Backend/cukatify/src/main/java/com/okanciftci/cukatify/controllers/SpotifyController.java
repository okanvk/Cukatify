package com.okanciftci.cukatify.controllers;

import com.okanciftci.cukatify.common.enums.RoleNames;
import com.okanciftci.cukatify.entities.mongo.Role;
import com.okanciftci.cukatify.security.jwt.JwtTokenProvider;
import com.okanciftci.cukatify.services.abstr.RoleService;
import com.okanciftci.cukatify.services.impl.UserService;
import com.okanciftci.cukatify.spotify.abstr.SpotifyAPIService;
import com.okanciftci.cukatify.spotify.SpotifyLoginHelper;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.model_objects.specification.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Date;

import static com.okanciftci.cukatify.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/spotify")
@CrossOrigin
public class SpotifyController {

    @Autowired
    private SpotifyLoginHelper spotifyLoginHelper;

    @Autowired
    private SpotifyAPIService spotifyAPIService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/loginURI")
    public ResponseEntity<?> loginWithSpotifyUser(){

        URI uri = spotifyLoginHelper.getSpotifyURI();

        return new ResponseEntity<>(uri, HttpStatus.OK);

    }

    @GetMapping("/callback")
    public ResponseEntity<?> loginWithSpotifyUser2(@RequestParam("code") String code, HttpServletResponse response) {

        String token = spotifyLoginHelper.getSpotifyAccessToken(code);

        User spotifyUser = spotifyAPIService.getUserCredentials(token);

        com.okanciftci.cukatify.entities.mongo.User systemUser = userService.getUser(spotifyUser.getEmail());

        if(systemUser == null){

            com.okanciftci.cukatify.entities.mongo.User newSystemUser = new com.okanciftci.cukatify.entities.mongo.User();

            newSystemUser.setUsername(spotifyUser.getEmail());

            newSystemUser.setSpotifyUser(true);

            newSystemUser.setAccessToken(token);

            newSystemUser.setFullName(spotifyUser.getDisplayName());

            newSystemUser.setPassword("123");

            newSystemUser.setSpotifyId(spotifyUser.getId());

            Role role = roleService.getRoleByName(RoleNames.ADMIN.name());

            newSystemUser.addRole(role);

            userService.saveUser(newSystemUser);

            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                newSystemUser.getUsername(),
                                newSystemUser.getPassword()
                        )
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

                String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);

                return new ResponseEntity(jwt, HttpStatus.FOUND);
            }catch (Exception e){
                return new ResponseEntity(e.getMessage(), HttpStatus.FOUND);
            }
        }else{

                systemUser.setAccessToken(token);


            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                systemUser.getUsername(),
                                "123"
                        )
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

                String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);

                return new ResponseEntity(jwt, HttpStatus.FOUND);
            }catch (Exception e){
                return new ResponseEntity(e.getMessage(), HttpStatus.FOUND);
            }
        }


       /* HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "localhost:3000/");
        return new ResponseEntity(headers, HttpStatus.FOUND);*/

    }

}
