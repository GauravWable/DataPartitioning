package com.mobiliya.workshop.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class TestSuite {

    public static final String EVENT_PAYLOAD_JSON = "src/test/resources/order_payload.txt";

    public static final String EVENT_PAYLOAD_JSON_WITHOUT_CONTENT =
            "src/test/resources/mock/cim_event_payload_without_content.json";

    public static final String EVENT_PAYLOAD_MALFORMED_JSON =
            "src/test/resources/mock/order_payload_invalid.txt";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String getEventPayloadJson(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
