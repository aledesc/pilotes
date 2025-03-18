package com.tui.pilotes.order.search;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderSearchAccuracy {

    NON_EXACT("non-exact"),
    EXACT("exact");

    private final String accuracy;
}
