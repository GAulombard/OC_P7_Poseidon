package com.nnk.springboot.IT;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

public abstract class AllTestITest {

    @Autowired
    private WebApplicationContext webApplicationContext; //loads all the application beans and controllers into the context

    protected MockMvc mockMvc; // provides support for spring MVC testing. Makes all web application beans available for testing.

    @BeforeEach
    public void SetUpBeforeEach(){ // initialize the mockMvc before each test
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }
}
