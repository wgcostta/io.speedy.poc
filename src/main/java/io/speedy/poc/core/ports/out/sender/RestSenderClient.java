package io.speedy.poc.core.ports.out.sender;

import java.util.Map;

public interface RestSenderClient {
    String post(Map<String, String> parameters, String authorization, String path);
}
