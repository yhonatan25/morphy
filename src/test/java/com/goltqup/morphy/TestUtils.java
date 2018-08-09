package com.goltqup.morphy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.time.ZoneId;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class TestUtils {

    private TestUtils() {
        throw new UnsupportedOperationException();
    }

    public static String getResourceAsString(final String filePath) throws IOException {
        System.out.println(ZoneId.SHORT_IDS);
        System.out.println(ZoneId.getAvailableZoneIds());
        final ClassPathResource classPathResource = new ClassPathResource(filePath);
        return copyToString(classPathResource.getInputStream(), defaultCharset());
    }

    public static <T> T getObjectFromJson(final String filePath, final Class<T> resourceClass) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final ClassPathResource classPathResource = new ClassPathResource(filePath);
        return objectMapper.readValue(classPathResource.getInputStream(), resourceClass);
    }

}
