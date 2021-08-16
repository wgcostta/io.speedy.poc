package io.speedy.poc.core.ports.out.sender;

import io.speedy.poc.core.ports.out.sender.transferobject.ResponseTO;

import java.util.Map;

public interface RestSenderClient {
    ResponseTO post(Map<String, String> parameters, String authorization, String path);
}
