package com.okanciftci.cukatify.unit_integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.okanciftci.cukatify.models.LoginRequest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;




@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class UserRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void userLoginScenario() throws Exception {

        // Given
        String password = "123";
        String username = "TobyReinold@gmail.com";
        LoginRequest request = new LoginRequest();
        request.setPassword(password);
        request.setUsername(username);

        //When - Then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/login").
                contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isOk());

    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
