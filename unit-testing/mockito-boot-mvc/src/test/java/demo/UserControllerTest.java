package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.controller.UserController;
import demo.entity.User;
import demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getUserByIdTest() throws Exception {

        // given -> mock the data returned by the UserService
        Integer testId = 12;
        User user = new User(testId, "John", "john@mail.com", "12345", "M");


        // when -> create a mock HTTP request to verify the expected result
        when(userService.getUserById(anyInt())).thenReturn(user);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + testId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john@mail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("12345"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("M"));
    }

    @Test
    void saveUserTest() throws Exception {
        User user = new User(1, "John", "john@mail.com", "12345", "M");
        when(userService.saveUser(any(User.class))).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(new ObjectMapper().writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john@mail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("12345"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("M"));

    }
}
