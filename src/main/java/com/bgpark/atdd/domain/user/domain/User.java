package com.bgpark.atdd.domain.user.domain;

import com.bgpark.atdd.domain.exception.InvalidPasswordException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class User {

    private static final int PASSWORD_LENGTH_THRESHOLD = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void checkPassword() {
        if (this.password.length() <= PASSWORD_LENGTH_THRESHOLD) {
            throw new InvalidPasswordException("비밀번호는 5자리를 초과해야합니다");
        }
    }

    public void addPrefix() {
    }
}
