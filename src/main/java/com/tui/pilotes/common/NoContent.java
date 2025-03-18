package com.tui.pilotes.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class NoContent {
    private HttpStatus code;
    private String msg;
}
