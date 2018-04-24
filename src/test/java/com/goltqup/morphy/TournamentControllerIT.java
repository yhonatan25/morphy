package com.goltqup.morphy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = DEFINED_PORT, properties = {"capablanca.port=8888"})
@AutoConfigureWireMock(port = 8888)
@AutoConfigureMockMvc
public class TournamentControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetTournaments() throws Exception {
        stubFor(get(urlEqualTo("/tournaments"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withStatus(200)
                        .withBodyFile("classpath:stubs/tournaments.json")));

        mockMvc.perform(MockMvcRequestBuilders.get("/tournaments"))
                .andExpect(status().isOk())
                .andExpect(view().name("tournaments"));

        verify(getRequestedFor(urlEqualTo("/tournaments")));
    }

}
