package io.speedy.poc.core.usecase.client;

import com.google.gson.Gson;
import io.speedy.poc.core.ports.in.client.transferobject.ClientResponseTO;
import io.speedy.poc.core.ports.out.sender.RestSenderClient;
import io.speedy.poc.core.ports.out.sender.transferobject.ResponseTO;
import io.speedy.poc.core.usecase.client.transferobject.ClientResponse;
import io.speedy.poc.infra.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class ClientUseCaseImpl implements ClientUseCase {
    @Value("${api.speedy.client.path}")
    private String path;

    @Autowired
    RestSenderClient restSenderClient;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ClientResponseTO postByTransactionId(String transactionId, String authorization) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("transactionId", transactionId);

        ResponseTO response = restSenderClient.post(parameters, authorization, path);

        Gson gson = new Gson();
        Optional<ClientResponse> clientResponse =
                Optional.ofNullable(gson.fromJson(response.getBody(), ClientResponse.class));

        if (clientResponse.isPresent() && clientResponse.get().getCustomerInfo() != null)
            return modelMapper.map(clientResponse.get(), ClientResponseTO.class);
        throw new ResourceNotFoundException("Resource no found transaction-id: " + transactionId);
    }
}
