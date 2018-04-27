package com.goltqup.morphy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
//@Import(WebFluxAutoConfiguration.class)
public class MorphyConfiguration /*extends DelegatingWebFluxConfiguration */ {

    @Value("${capablanca.port:8080}")
    private int capablancaPort;

    @Value("${capablanca.host:localhost}")
    private String capablancaHost;

    /*@Autowired
    private ISpringWebFluxTemplateEngine templateEngine;*/

    @Autowired
    private HttpHandler httpWebHandlerAdapter;

    @Bean
    public WebClient webClient() {
        return WebClient.create("http://" + capablancaHost + ":" + capablancaPort);
    }

    /*@Bean
    @Order(LOWEST_PRECEDENCE)
    public WebFilter languageQueryParameterWebFilter() {
        return new LanguageQueryParameterWebFilter((HttpWebHandlerAdapter) httpWebHandlerAdapter);
    }*/

    /*@Bean
    public LocaleContextResolver queryParameterLocaleContextResolver() {
        return new QueryParameterLocaleContextResolver();
    }

    @Bean
    public ViewResolver thymeleafReactiveViewResolver() {
        final ThymeleafReactiveViewResolver thymeleafReactiveViewResolver = new ThymeleafReactiveViewResolver();
        thymeleafReactiveViewResolver.setTemplateEngine(templateEngine);
        return thymeleafReactiveViewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(5);
        return messageSource;
    }

    @Override
    protected LocaleContextResolver createLocaleContextResolver() {
        return queryParameterLocaleContextResolver();
    }

    @Override
    protected void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(thymeleafReactiveViewResolver());
        super.configureViewResolvers(registry);
    }*/
}