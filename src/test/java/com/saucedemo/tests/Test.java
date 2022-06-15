package com.saucedemo.tests;

import com.github.javafaker.Faker;
import com.saucedemo.User;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;

@Log4j2
public class Test {

    Faker faker = new Faker();
    String name = faker.name().fullName(); // Miss Samanta Schmidt
    String firstName = faker.name().firstName(); // Emory
    String lastName = faker.name().lastName(); // Barton



    @org.testng.annotations.Test
    public void testUser() {

        log.fatal("Fatal");
        log.error("Error");
        log.warn("warn");
        log.info("info");
        log.debug("debug");
        log.trace("trace");


        User volodya = User.builder()
                .username(name)
                .password(firstName)
                .Sex(lastName)
                .build();

        User Dmitri = new User(name, firstName, lastName);

        Assert.assertEquals(volodya, Dmitri);
    }
}
