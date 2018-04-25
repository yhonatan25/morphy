package com.goltqup.morphy.service;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.goltqup.morphy.domain.Tournament;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.goltqup.morphy.TestUtils.getResourceAsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.web.reactive.function.client.WebClient.create;

public class TournamentClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.DYNAMIC_PORT);

    private WebClient webClient;
    private TournamentClient tournamentClient;

    @Before
    public void setup() {
        webClient = create("http://localhost:" + wireMockRule.port());
        tournamentClient = new TournamentClient(webClient);
    }

    @Test
    public void testGetTournaments() throws IOException {
        final String tournamentJson = getResourceAsString("response_bodies/tournaments.json");
        stubFor(get(urlEqualTo("/tournaments"))
                .willReturn(okJson(tournamentJson)));

        final Flux<Tournament> tournamentFlux = tournamentClient.getTournaments();
        final Tournament tournament = tournamentFlux.blockFirst();

        final Tournament expectedTournament = new Tournament("RklGQVJ1c3NpYTIwMTg=", "FIFA", "Russia", 2018);
        assertThat(tournament, is(expectedTournament));
        verify(getRequestedFor(urlEqualTo("/tournaments")));
    }
}