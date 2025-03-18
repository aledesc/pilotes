package com.tui.pilotes.order.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderSearch {

    public OrderSearchField field;
    public OrderSearchAccuracy accuracy;
    public String text;

    public OrderSearch(String field, String accuracy, String text) {

        for (OrderSearchField v : OrderSearchField.values()) {
            if (v.getFieldName().equals(field)) {
                this.field= v;
                break;
            }
        }

        for(OrderSearchAccuracy v : OrderSearchAccuracy.values()) {
            if( v.getAccuracy().equals(accuracy)) {
                this.accuracy= v;
                break;
            }
        }

        this.text = text;
    }

    public String getWhereClause() {

        if (accuracy == null) {
            this.accuracy= OrderSearchAccuracy.NON_EXACT;
        }

        if (field==null) {
            this.field= OrderSearchField.FIRST_NAME;
        }

        if (text==null) {
            this.text= "Juan";
        }

        final String EQUALS = " = '";
        final String EQUALS_NO_QTE = " = ";
        final String EQUALS_CLOSE = "'";
        final String LIKE = " LIKE '%";
        final String LIKE_CLOSE= "%'";

        StringBuilder whereClause = new StringBuilder(field.getTablePrefix() + field.getFieldName());

        if( field.equals( OrderSearchField.QUANTITY) ) {
            whereClause.append(EQUALS_NO_QTE).append(text);

        } else if( accuracy.equals( OrderSearchAccuracy.EXACT) ) {
            whereClause.append(EQUALS).append(text).append(EQUALS_CLOSE);

        } else {
            whereClause.append(LIKE).append(text).append(LIKE_CLOSE);
        }

        final String SPACE= " ";
        final String REGEX_SPACES= "\\s{2,}";
        return whereClause.toString().replaceAll(REGEX_SPACES, SPACE).trim();
    }
}
