package com.goltqup.morphy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class TestUtils {

    private TestUtils() {
        throw new UnsupportedOperationException();
    }

    public static String getResourceAsString(final String filePath) throws IOException {
        final ClassPathResource classPathResource = new ClassPathResource(filePath);
        return copyToString(classPathResource.getInputStream(), defaultCharset());
    }

    public static <T> T getObjectFromJson(final String filePath, final Class<T> resourceClass) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        final ClassPathResource classPathResource = new ClassPathResource(filePath);
        return objectMapper.readValue(classPathResource.getInputStream(), resourceClass);
    }

}
