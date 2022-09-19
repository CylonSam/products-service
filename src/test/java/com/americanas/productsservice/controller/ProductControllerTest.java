package com.americanas.productsservice.controller;

import com.americanas.AbstractTest;
import com.americanas.productsservice.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductControllerTest extends AbstractTest {

    @Autowired
    private ProductController controller;

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

    @Test
    public void shouldReturnBadRequestWhenRequiredFieldIsMissingInCreateRequest() throws Exception {
        // GIVEN
        final String request = getJsonAsString("bad_request_product_missing_field.json");

        // WHEN
        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                // THEN
                .andExpect(status().is(400));
    }

}
