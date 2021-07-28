package com.bgpark.atdd.domain.user.repository;

import com.bgpark.atdd.domain.user.domain.User;
import com.bgpark.atdd.domain.user.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save_사용자를_저장한다() {
        // given
        User user = new User("박병길", "123");

        // when
        userRepository.save(user);

        // then
        assertThat(userRepository.findById(1L).get().getUsername()).isEqualTo("박병길");
        assertThat(userRepository.findById(1L).get().getPassword()).isEqualTo("123");
    }
}