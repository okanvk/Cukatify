package com.adu.dataGen;

import me.xdrop.jrand.JRand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
@CrossOrigin


public class MainController {



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @GetMapping("/")
    public ResponseEntity<?> saveUsers(){
        for (int i = 0; i < 225; i++) {

            User user = new User();

            Role user_role = roleRepository.findById("5e779b3fc32700c2096bff33").orElse(null);

            String name = JRand.firstname().gender("m").gen();
            String surname = JRand.lastname().gen();
            String fullname = name + " " + surname;
            String email = name + surname + "@gmail.com";
            LocalDateTime dateTime = LocalDateTime.now();
            String password = "123";

            user.setFullName(fullname);
            user.setUsername(email);
            user.setCreated_At(dateTime);
            user.setPassword(passwordEncoder.encode(password));

            user.addRole(user_role);

            if(i < 75){
                user.setType(0);
            }
            else if(i < 150){
                user.setType(1);
            }else{
                user.setType(2);
            }

            userRepository.save(user);
        }
        return new ResponseEntity(null, HttpStatus.OK);
    }


    @GetMapping("/2")
    public ResponseEntity<?> saveUsers2(){

            User user = userRepository.findById("5e7931473f61107fe53cb237").orElse(null);

            Role user_role = roleRepository.findById("5e779b64c32700c2096bff34").orElse(null);



            user.addRole(user_role);


            userRepository.save(user);

        return new ResponseEntity(null, HttpStatus.OK);
    }

}
