package com.rita.publisher.kafka;

import com.rita.publisher.dto.MessageDTO;
import com.rita.publisher.dto.MessageUpdateDTO;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

public record MessageEvent(UUID mapKey, Operation operation, Long byId, MessageUpdateDTO message, List<MessageDTO> response, Boolean isException) {

    public enum Operation{
        FIND_ALL,
        FIND_BY_ID,
        CREATE,
        DELETE_BY_ID,
        UPDATE
    }
}
