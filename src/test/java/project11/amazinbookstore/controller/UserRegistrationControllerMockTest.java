package project11.amazinbookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import project11.amazinbookstore.services.UserService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserRegistrationController.class)
class UserRegistrationControllerMockTest {

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private ObjectWriter ow = new ObjectMapper().writer();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void testHomepageAsSignedInUser() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testHomepageAsNotSignedIn() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testRegister() throws Exception {
        // FIXME: wtf... why does this need @WithMockUser????
        mockMvc.perform(get("/register"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testRegisterNewUser() throws Exception {
        // FIXME: again, why does this need @WithMockUser???
        mockMvc.perform(get("/processing-registration"))
                .andDo(print())
                .andExpect(status().isFound());
    }





}