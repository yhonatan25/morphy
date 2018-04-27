package com.goltqup.morphy.config;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.i18n.LocaleContextResolver;

import java.util.Locale;

import static java.util.Locale.getDefault;
import static org.springframework.util.StringUtils.isEmpty;

public class QueryParameterLocaleContextResolver implements LocaleContextResolver {
    @Override
    public LocaleContext resolveLocaleContext(final ServerWebExchange exchange) {
        final String languageValue = exchange.getRequest()
                .getQueryParams()
                .getFirst("language");
        return new SimpleLocaleContext(getLocale(languageValue));
    }

    private Locale getLocale(final String languageValue) {
        return isEmpty(languageValue) ? getDefault() : new Locale(languageValue);
    }

    @Override
    public void setLocaleContext(final ServerWebExchange exchange, final LocaleContext localeContext) {
        throw new UnsupportedOperationException(
                "Cannot change HTTP language query parameter - use a different locale context resolution strategy");
    }
}
