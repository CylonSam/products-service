package com.americanas;

import org.apache.commons.io.IOUtils;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ContextConfiguration
// @AutoConfigureWireMock(port = 9000)

public abstract class AbstractTest {

    @Autowired
    protected MockMvc mockMvc;

    // @Autowired
    // protected WebTestClient webTestClient;

    private static final Configuration configuration = Configuration.builder()
            .jsonProvider(new JacksonJsonNodeJsonProvider())
            .mappingProvider(new JacksonMappingProvider())
            .build();

    public static String getJsonAsString(final String path) throws IOException {
        final URL resource = AbstractTest.class.getResource("/jsons/" + path);
        return IOUtils.toString(resource, StandardCharsets.UTF_8);

    }

    /**
     * This method changes the json values for test.
     *
     * @param field   field to be changed, you can use . for inner field
     * @param value   value to to be replaced
     * @param payload json to be modify
     * @return json modified
     */
    protected static String modifyPayload(final String field,
            final Object value,
            final String payload) {
        return JsonPath
                .using(configuration)
                .parse(payload)
                .set(field, value)
                .json()
                .toString();
    }

}
