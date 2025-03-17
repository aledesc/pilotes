package com.tui.pilotes.order.search;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderSearchField {

    FIRST_NAME("first_name","c."),
    LAST_NAME("last_name","c."),
    TELEPHONE("telephone","c."),

    STREET("street","a."),
    DOOR("door","a."),
    CITY("city","a."),
    POSTAL_CODE("postal_code","a."),

    QUANTITY("quantity","");

    private final String fieldName;
    private final String tablePrefix;

}
