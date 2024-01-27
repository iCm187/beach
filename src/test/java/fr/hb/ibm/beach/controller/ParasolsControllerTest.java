package fr.hb.ibm.beach.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc 
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // A évoquer ce jour
public class ParasolsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @WithMockUser(roles = "USER")
    @Test
    void testerPageParasols() throws Exception {

        // On prépare une requête en HTTP en indiquant la méthode (ds l'ex : get) puis
        // en paramètre l'url
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/parasols");

        mockMvc.perform(requestBuilder).andExpect(view().name("parasols")).andExpect(status().isOk());
    }
    
    
    @Test
    void testerAjoutParasolSucces() throws Exception {
    
        mockMvc.perform(MockMvcRequestBuilders.post("/parasol")
            .accept(MediaType.TEXT_HTML)
            .param("numEmplacement", "12")
            .param("file", "8"))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/parasols"))
            .andExpect(status().isFound());
    } 
}