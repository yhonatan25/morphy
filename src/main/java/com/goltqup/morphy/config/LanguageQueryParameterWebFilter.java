package com.goltqup.morphy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.server.adapter.DefaultServerWebExchange;
import org.springframework.web.server.adapter.HttpWebHandlerAdapter;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class LanguageQueryParameterWebFilter implements WebFilter {
    private static final String LANGUAGE_QUERY_PARAM_KEY = "language";

    @Value("${accepted.language.default:es-GT}")
    private String defaultAcceptedLanguage;
    private final ApplicationContext applicationContext;

    private HttpWebHandlerAdapter httpWebHandlerAdapter;

    public LanguageQueryParameterWebFilter(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadHttpHandler() {
        this.httpWebHandlerAdapter = applicationContext.getBean(HttpWebHandlerAdapter.class);
    }

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final WebFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();
        final MultiValueMap<String, String> queryParams = request.getQueryParams();
        final String languageQueryParamValue = queryParams.getFirst(LANGUAGE_QUERY_PARAM_KEY);

        final ServerWebExchange localizedExchange = getServerWebExchange(languageQueryParamValue, exchange);
        return chain.filter(localizedExchange);
    }

    private ServerWebExchange getServerWebExchange(final String languageQueryParamValue, final ServerWebExchange exchange) {
        return isEmpty(languageQueryParamValue)
                ? getLocalizedServerWebExchange(defaultAcceptedLanguage, exchange)
                : getLocalizedServerWebExchange(languageQueryParamValue, exchange);
    }

    private ServerWebExchange getLocalizedServerWebExchange(final String languageQueryParamValue, final ServerWebExchange exchange) {
        final ServerHttpRequest httpRequest = exchange.getRequest()
                .mutate()
                .headers(httpHeaders -> httpHeaders.set(ACCEPT_LANGUAGE, languageQueryParamValue))
                .build();

        return new DefaultServerWebExchange(httpRequest, exchange.getResponse(),
                httpWebHandlerAdapter.getSessionManager(), httpWebHandlerAdapter.getCodecConfigurer(),
                httpWebHandlerAdapter.getLocaleContextResolver());
    }
}