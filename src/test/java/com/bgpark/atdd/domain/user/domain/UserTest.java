package com.bgpark.atdd.domain.user.domain;

import com.bgpark.atdd.domain.exception.InvalidPasswordException;
import org.junit.Test;

public class UserTest {

    @Test(expected = InvalidPasswordException.class)
    public void checkPassword_비밀번호는_5자리_초과한다() {
        // given
        User user = new User("박병길", "123");

        // when
        user.checkPassword();
    }
}
