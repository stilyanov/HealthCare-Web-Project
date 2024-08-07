package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.user.UserProfileDTO;
import bg.softuni.healthcare.model.dto.user.UserRegisterDTO;
import bg.softuni.healthcare.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() {
        Mockito.when(userService.getUserProfileById(any(Long.class))).thenReturn(new UserProfileDTO());
    }

    @Test
    public void testGetProfile() throws Exception {
        mockMvc.perform(get("/users/profile/{userId}", 1L))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userProfile"))
                .andExpect(view().name("user-profile"));
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testLoginError() throws Exception {
        mockMvc.perform(post("/users/login-error"))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("wrongCredentials", true))
                .andExpect(redirectedUrl("/users/login"));
    }

    @Test
    public void testRegister() throws Exception {
        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("registerDTO"))
                .andExpect(view().name("register"));
    }

    @Test
    public void testRegisterUser() throws Exception {
        mockMvc.perform(post("/users/register")
                        .flashAttr("registerDTO", new UserRegisterDTO()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));
    }
}