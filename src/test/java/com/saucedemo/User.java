package com.saucedemo;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"Sex"})
@Data
@Builder
public class User {

    private  String username;

    private  String password;

    private String Sex;

    public User(String username, String password){
        setUsername(username);
        setPassword(password);
    }

}
