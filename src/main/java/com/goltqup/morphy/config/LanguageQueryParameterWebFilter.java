package com.goltqup.morphy.config;

import org.springframework.context.ApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.server.adapter.DefaultServerWebExchange;
import org.springframework.web.server.adapter.HttpWebHandlerAdapter;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

import static org.springframework.util.StringUtils.isEmpty;

@Component
public class LanguageQueryParameterWebFilter implements WebFilter {

    private final ApplicationContext applicationContext;

    private HttpWebHandlerAdapter httpWebHandlerAdapter;

    public LanguageQueryParameterWebFilter(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();
        final MultiValueMap<String, String> queryParams = request.getQueryParams();
        final String languageValue = queryParams.getFirst("language");

        final ServerWebExchange localizedExchange = getServerWebExchange(languageValue, exchange);
        return chain.filter(localizedExchange);
    }

    private ServerWebExchange getServerWebExchange(final String languageValue, final ServerWebExchange exchange) {
        return isEmpty(languageValue)
                ? exchange
                : getLocalizedServeWebExchange(languageValue, exchange);
    }

    private ServerWebExchange getLocalizedServeWebExchange(final String languageValue, final ServerWebExchange exchange) {
        final ServerHttpRequest httpRequest = exchange.getRequest()
                .mutate()
                .headers(httpHeaders -> httpHeaders.set("Accept-Language", languageValue))
                .build();

     gi   return new DefaultServerWebExchange(httpRequest, exchange.getResponse(), httpWebHandlerAdapter.getSessionManager(),
                httpWebHandlerAdapter.getCodecConfigurer(), httpWebHandlerAdapter.getLocaleContextResolver());
    }

    @PostConstruct
    public void setHttpWebHandlerAdapter() {
        this.httpWebHandlerAdapter = (HttpWebHandlerAdapter) applicationContext.getBean(HttpHandler.class);
    }
}
