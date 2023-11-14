package project11.amazinbookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import project11.amazinbookstore.model.Role;
import project11.amazinbookstore.services.UserService;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
    @WithMockUser(roles = {"ANONYMOUS"})
    void testLoginAsAnonymousUser() throws Exception {
        mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testLoginError() throws Exception {
        MvcResult result = mockMvc.perform(get("/login?error"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Invalid credentials"));
    }

    @Test
    void testLoginLogout() throws Exception {
        MvcResult result = mockMvc.perform(get("/login?logout"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("You have been signed out"));
    }

    @Test
    @WithMockUser
    void testLoginAsSignedInUser() throws Exception {
        mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(roles = {"ANONYMOUS"})
    void testRegisterAsAnonymousUser() throws Exception {
        // Observation:
        // - @WithMockUser allows the debugger to enter "/register"
        // - @WithAnonymousUser does not allow the debugger to enter "/register"
        mockMvc.perform(get("/register"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testRegisterAsSignedInUser() throws Exception {
        mockMvc.perform(get("/register"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(roles = {"ANONYMOUS"})
    void testRegisterUsernameExistsAsAnonymousUser() throws Exception {
        // FIXME: why do I need @WithMockUser here as well?
        MvcResult result = mockMvc.perform(get("/register?usernameExists"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("An account with that username already exists."));
    }

    @Test
    @WithMockUser
    void testRegisterNewUser() throws Exception {
        // FIXME: again, why does this need @WithMockUser???
        when(userService.addNewUser("testUser","password",Role.USER)).thenReturn(true);
        mockMvc.perform(get("/processing-registration?username=testUser&password=password"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    @WithMockUser
    void testRegisterNewUserDuplicate() throws Exception {
        // FIXME: why does this need @WithMockUser?
        when(userService.addNewUser("testUser", "password", Role.USER)).thenReturn(false);
        mockMvc.perform(get("/processing-registration?username=testUser&password=password"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/register?usernameExists"));
    }

}