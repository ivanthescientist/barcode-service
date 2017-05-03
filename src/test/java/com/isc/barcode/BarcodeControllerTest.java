package com.isc.barcode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {TestConfiguration.class, BarcodeApplicationConfiguration.class})
public class BarcodeControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void testBasicInputs() throws Exception {
        mockMvc.perform(get("/api/barcode/130993939AAAA"))
                .andExpect(status().isNotFound());

        mockMvc.perform(post("/api/barcode/130993939AAAA"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/barcode/130993939AAAA"))
                .andExpect(status().isConflict());

        mockMvc.perform(get("/api/barcode/130993939AAAA"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/barcode/130993939BBBB"))
                .andExpect(status().isNotFound());
    }
}
