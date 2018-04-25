package com.goltqup.morphy.controller;

import com.goltqup.morphy.domain.Tournament;
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

import static com.goltqup.morphy.TestUtils.getResourceAsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebFluxTest
@Import({ThymeleafAutoConfiguration.class})
public class TournamentControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private TournamentClient tournamentClient;


    @Test
    public void testGetTournaments() throws Exception {
        given(tournamentClient.getTournaments()).willReturn(Flux.just(new Tournament("RklGQVJ1c3NpYTIwMTg=", "FIFA", "Russia", 2018)));

        final EntityExchangeResult<String> stringEntityExchangeResult = webClient.get().uri("/tournaments")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult();

        final String tournamensResponse = getResourceAsString("response_bodies/tournaments.html");

        assertThat(stringEntityExchangeResult.getResponseBody(), is(tournamensResponse));

    }

}
