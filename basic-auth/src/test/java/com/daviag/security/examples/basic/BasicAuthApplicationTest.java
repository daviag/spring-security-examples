package com.daviag.security.examples.basic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BasicAuthApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void givenUnauthenticated_whenRequestPublicResource_thenSucceededWith200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/greeting/public"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenUnauthenticated_whenRequestRestrictedResource_thenFailWith401() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/greeting/restricted"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void givenAuthenticated_whenRequestRestrictedResource_thenSucceededWith200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/greeting/restricted"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenAuthenticatedRealUser_whenRequestRestrictedResource_thenSucceededWith200() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/greeting/restricted")
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + HttpHeaders.encodeBasicAuth("admin", "admin", Charset.defaultCharset())))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
