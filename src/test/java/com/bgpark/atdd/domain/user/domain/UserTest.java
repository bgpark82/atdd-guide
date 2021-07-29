package com.bgpark.atdd.domain.user.domain;

import com.bgpark.atdd.domain.exception.InvalidPasswordException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test(expected = InvalidPasswordException.class)
    public void checkPassword_비밀번호는_5자리_초과한다() {
        // given
        User user = new User("박병길", "123");

        // when
        user.checkPassword();
    }

    @Test
    public void addPrefix_성이_박인_사용자는_접두사를_추가한다() {
        // given
        User user = new User("박병길", "123");

        // when
        user.addPrefix();

        // then
        assertThat(user.getUsername()).startsWith("개발자_");
    }
}
