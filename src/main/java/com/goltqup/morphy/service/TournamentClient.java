package com.goltqup.morphy.service;

import com.goltqup.morphy.domain.Tournament;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TournamentClient {

    private final WebClient webClient;

    TournamentClient(final WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Tournament> getTournaments() {
        return webClient.get()
                .uri("/tournaments")
                .retrieve()
                .bodyToFlux(Tournament.class);
    }

    public Mono<Tournament> getTournament(final String tournamentId) {
        return webClient.get()
                .uri("/tournaments/{tournamentId}", tournamentId)
                .retrieve()
                .bodyToMono(Tournament.class);
    }
}
