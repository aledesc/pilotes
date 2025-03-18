package com.tui.pilotes.order;

import com.tui.pilotes.order.search.OrderSearch;
import com.tui.pilotes.order.search.OrderSearchAccuracy;
import com.tui.pilotes.order.search.OrderSearchField;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class OrderSearchTest {

    private static OrderSearch orderSearch;
    private static String text;


    @BeforeAll
    public static void beforeAll() {
        orderSearch= new OrderSearch();
    }

    @AfterAll
    public static void afterAll() {
        orderSearch= null;
        text= null;
    }

    @Test
    public void testConversionFromValues() {

        String field= "first_name";
        String accuracy= "exact";
        String text= "rew";

        OrderSearch search= new OrderSearch(field, accuracy, text);

        assertEquals(OrderSearchField.FIRST_NAME, search.getField() );
        assertEquals(OrderSearchAccuracy.EXACT, search.getAccuracy() );
        assertEquals("rew", search.getText() );


        accuracy= "non-exact";
        search= new OrderSearch(field, accuracy, text);
        assertEquals(OrderSearchAccuracy.NON_EXACT, search.getAccuracy() );

    }

    @Test
    public void testWhereFirstNameClause() {

        text= "Julian";

        // testing like
        String expected= "c.first_name LIKE '%Julian%'";
        orderSearch= new OrderSearch(OrderSearchField.FIRST_NAME, OrderSearchAccuracy.NON_EXACT,text);
        assertEquals(expected, orderSearch.getWhereClause());

        //testing exact
        expected= "c.first_name = 'Julian'";
        orderSearch.setAccuracy(OrderSearchAccuracy.EXACT);
        assertEquals(expected, orderSearch.getWhereClause());

        //testing exact
        //testing city
        expected= "a.city = 'Madrid'";
        text= "Madrid";
        orderSearch.setField(OrderSearchField.CITY);
        orderSearch.setText(text);
        assertEquals(expected, orderSearch.getWhereClause());

    }

    @Test
    public void testWhereLastNameClause() {

        text = "RuiPérez";

        // testing like
        String expected = "c.first_name LIKE '%RuiPérez%'";
        orderSearch = new OrderSearch(OrderSearchField.FIRST_NAME, OrderSearchAccuracy.NON_EXACT, text);
        assertEquals(expected, orderSearch.getWhereClause());

        //testing exact
        expected = "c.first_name = 'RuiPérez'";
        orderSearch.setAccuracy(OrderSearchAccuracy.EXACT);
        assertEquals(expected, orderSearch.getWhereClause());

    }

    @Test
    public void testWhereCityPostalCodeClause() {

        //testing exact
        //testing city
        String expected= "a.city = 'Madrid'";
        text= "Madrid";
        orderSearch.setAccuracy(OrderSearchAccuracy.EXACT);
        orderSearch.setField(OrderSearchField.CITY);
        orderSearch.setText(text);
        assertEquals(expected, orderSearch.getWhereClause());

        //testing parcial
        //testing post code
        expected= "a.postal_code LIKE '%20%'";
        text= "20";
        orderSearch.setField(OrderSearchField.POSTAL_CODE);
        orderSearch.setAccuracy(OrderSearchAccuracy.NON_EXACT);
        orderSearch.setText("Madrid");
        assertNotEquals(expected, orderSearch.getWhereClause());

        orderSearch.setText(text);
        assertEquals(expected, orderSearch.getWhereClause());
    }

    @Test
    public void testWhereQuantityClause() {

        //testing parcial
        //testing post code
        String expected= "quantity = 5";
        text= "5";
        orderSearch.setField(OrderSearchField.QUANTITY);
        orderSearch.setAccuracy(OrderSearchAccuracy.EXACT);
        orderSearch.setText("5");

        assertEquals(expected, orderSearch.getWhereClause());
    }


}
