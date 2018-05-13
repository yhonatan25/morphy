package com.goltqup.morphy.controller;

import com.goltqup.morphy.domain.Tournament;
import com.goltqup.morphy.service.TournamentClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class TournamentController {

    private final TournamentClient tournamentClient;

    public TournamentController(TournamentClient tournamentClient) {
        this.tournamentClient = tournamentClient;
    }

    @RequestMapping("/tournaments")
    public Mono<String> getTournaments(final Model model) {
        final Flux<Tournament> tournamentFlux = tournamentClient.getTournaments();
        model.addAttribute("tournamentFlux", tournamentFlux);
        return Mono.just("tournaments");
    }

    @RequestMapping("/tournament/{tournamentId}")
    public Mono<String> getTournamentById(@PathVariable("tournamentId") final String tournamentId, final Model model) {
        final Mono<Tournament> tournamentMono = tournamentClient.getTournament(tournamentId);
        model.addAttribute("tournamentMono", tournamentMono);
        return Mono.just("tournament");
    }

}
