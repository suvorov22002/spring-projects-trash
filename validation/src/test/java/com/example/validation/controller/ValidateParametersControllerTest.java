package com.example.validation.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ValidateParametersController.class)
class ValidateParametersControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void whenPathVariableIsInvalid_thenReturnsStatus400() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/validatePathVariable/3"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenRequestParameterIsInvalid_thenReturnsStatus400() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/validRequestParam")
                        .param("id", "3"))
                .andExpect(status().isBadRequest());
    }
}