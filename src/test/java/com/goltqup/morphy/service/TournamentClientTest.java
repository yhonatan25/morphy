package com.goltqup.morphy.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.goltqup.morphy.domain.Tournament;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.Options.DYNAMIC_PORT;
import static com.goltqup.morphy.TestUtils.getResourceAsString;
import static com.goltqup.morphy.TournamentAssert.getExpectedTournament;
import static com.goltqup.morphy.TournamentAssert.tournamentMatchesExpected;
import static reactor.test.StepVerifier.create;

public class TournamentClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(DYNAMIC_PORT);

    private TournamentClient tournamentClient;

    @Before
    public void setup() {
        final WebClient webClient = WebClient.create("http://localhost:" + wireMockRule.port());
        tournamentClient = new TournamentClient(webClient);
    }

    @Test
    public void testGetTournaments() throws IOException {
        final String tournamentJson = getResourceAsString("response_bodies/tournaments.json");
        stubFor(get(urlEqualTo("/tournaments"))
                .willReturn(okJson(tournamentJson)));

        final Flux<Tournament> tournamentFlux = tournamentClient.getTournaments();

        create(tournamentFlux)
                .expectNextMatches(tournament -> tournamentMatchesExpected(tournament, getExpectedTournament()))
                .expectComplete()
                .verify();

        verify(getRequestedFor(urlEqualTo("/tournaments")));
    }

    @Test
    public void testGetTournamentById() throws IOException {
        final String tournamentJson = getResourceAsString("response_bodies/tournament.json");
        stubFor(get(urlEqualTo("/tournament/RklGQVJ1c3NpYTIwMTg="))
                .willReturn(okJson(tournamentJson)));

        final Mono<Tournament> tournamentFlux = tournamentClient.getTournament("RklGQVJ1c3NpYTIwMTg=");

        create(tournamentFlux)
                .expectNextMatches(tournament -> tournamentMatchesExpected(tournament, getExpectedTournament()))
                .expectComplete()
                .verify();

        verify(getRequestedFor(urlEqualTo("/tournament/RklGQVJ1c3NpYTIwMTg=")));
    }
}