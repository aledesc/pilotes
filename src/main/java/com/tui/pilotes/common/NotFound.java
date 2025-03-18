package com.tui.pilotes.common;

import org.springframework.http.HttpStatus;

public class NotFound  extends NoContent{
    public NotFound(HttpStatus code, String msg) {
        super(code, msg);
    }
}
