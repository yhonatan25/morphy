package com.goltqup.morphy.controller;

import com.goltqup.morphy.service.TournamentClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.goltqup.morphy.TestUtils.getResourceAsString;
import static com.goltqup.morphy.TournamentAssert.getExpectedTournament;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebFluxTest
@Import({ThymeleafAutoConfiguration.class})
public class TournamentControllerTest {

    private static final String TOURNAMENT_ID = "RklGQVJ1c3NpYTIwMTg=";
    @Autowired
    private WebTestClient webClient;

    @MockBean
    private TournamentClient tournamentClient;


    @Test
    public void testGetTournaments() throws Exception {
        given(tournamentClient.getTournaments()).willReturn(Flux.just(getExpectedTournament()));

        final EntityExchangeResult<String> stringEntityExchangeResult = webClient.get().uri("/tournaments")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult();

        final String tournamentResponse = getResourceAsString("response_bodies/tournaments.html");

        assertThat(stringEntityExchangeResult.getResponseBody(), is(tournamentResponse));

    }

    @Test
    public void testGetTournamentById() throws Exception {
        given(tournamentClient.getTournament(TOURNAMENT_ID)).willReturn(Mono.just(getExpectedTournament()));

        final EntityExchangeResult<String> stringEntityExchangeResult = webClient.get().uri("/tournament/" + TOURNAMENT_ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult();

        final String tournamentResponse = getResourceAsString("response_bodies/tournament.html");

        assertThat(stringEntityExchangeResult.getResponseBody(), is(tournamentResponse));

    }

}
