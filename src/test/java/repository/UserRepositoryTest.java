package repository;

import com.d1.WebApplication;
import com.d1.domain.User;
import com.d1.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = WebApplication.class)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        userRepository.deleteAll();
    }

    @After
    public void reset() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void whenGetUsers_thenReturnUsers() {
        User user = new User("Vardas", "Pavarde", LocalDate.of(1993, 10, 3), "vardas@gmail.com", "+37062588954");
        testEntityManager.persist(user);
        testEntityManager.flush();

        List<User> allUsers = userRepository.findAll();
        assertThat(allUsers).hasSize(1).extracting(User::getName).containsOnly(user.getName());
    }

}
