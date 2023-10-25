package com.frandorado.loggingrequestresponsewithbody.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class GreetingResponse implements Serializable {

    private String id;
    private String message;

    public GreetingResponse() {
        // TODO document why this constructor is empty
    }
}
