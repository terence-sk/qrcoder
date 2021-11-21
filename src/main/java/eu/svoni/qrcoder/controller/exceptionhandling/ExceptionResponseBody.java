package eu.svoni.qrcoder.controller.exceptionhandling;

import lombok.*;

@Builder
@Getter
@Setter
public class ExceptionResponseBody {

    private String message;
    private String path;
    private String method;

}
