package controller;

import com.d1.WebApplication;
import com.d1.domain.User;
import com.d1.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import util.JsonUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = WebApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository repository;

    @Before
    public void clear() {
        repository.deleteAll();
    }

    @After
    public void reset() {
        repository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateEmployee() throws IOException, Exception {
        User user = new User("Vardas", "Pavarde", LocalDate.of(1993, 10, 3), "vardas@gmail.com", "+37062588954");
        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(user)));
        List<User> found = repository.findAll();
        assertThat(found).extracting(User::getName).containsOnly("Vardas");
    }

    @Test
    public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {
        User user = new User("Vardas", "Pavarde", LocalDate.of(1993, 10, 3), "vardas@gmail.com", "+37062588954");
        repository.saveAndFlush(user);
        mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].name", is("Vardas")));
    }

}