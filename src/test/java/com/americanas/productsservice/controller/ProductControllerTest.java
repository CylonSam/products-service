package com.americanas.productsservice.controller;

import org.junit.jupiter.api.Test;

import com.americanas.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Calendar;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ProductControllerTest extends AbstractTest {

    @Autowired
    private ProductController controller;
    @Autowired
    private ProductService service;

    // @Autowired
    // private FindProductId serviceImpl;

//    @Test
//    public void shouldReturnNotFoundWhenGetProductById() throws Exception{
//        //WHEN
//        mockMvc.perform(get("/product/999"))
//                //THEN
//                .andExpect(status().is(404))
//                .andExpect(MockMvcResultMatchers.content().string(getJsonAsString("not_found_product.json")));
//    }

    @Test
    public void shouldReturnNotFoundWhenGetProductById() throws Exception {
        String exceptionParam = "not_found";

        mockMvc.perform(get("/exception/{exception_id}", exceptionParam)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductNotFoundException))
                .andExpect(result -> assertEquals("resource not found", result.getResolvedException().getMessage()));
    }

    @Test
    public void shouldReturnProductJsonWhenProductIsCreated() throws Exception {
        // GIVEN
        final String request = getJsonAsString("product.json");

        // WHEN
        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                // THEN
                .andExpect(status().is(201))
                .andExpect(MockMvcResultMatchers.content().json(getJsonAsString("create_product_response.json")));

    }

}
