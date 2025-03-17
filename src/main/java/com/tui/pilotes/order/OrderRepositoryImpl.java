package com.tui.pilotes.order;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public class OrderRepositoryImpl {

    private final String SQL_SEARCH_BY_FIELD = "SELECT o.number,o.date_time,o.client_id,o.product_id,o.quantity,o.status FROM orders o INNER JOIN client c ON(o.client_id=c.id) WHERE ";

    private final DatabaseClient databaseClient;

    public OrderRepositoryImpl(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    public Flux<OrderModel> searchByQuantity(String quantity)
    {
        final String SEARCH_BY_QUANTITY= "SELECT number,date_time,client_id,product_id,quantity,status FROM orders WHERE quantity=" + quantity;
        return databaseClient.sql( SEARCH_BY_QUANTITY )
                .map((row, metadata) -> new OrderModel(row.get("number", Integer.class)
                        ,row.get("date_time", LocalDateTime.class)
                        ,row.get("client_id", Integer.class)
                        ,row.get("product_id", Integer.class)
                        ,row.get("quantity", Integer.class)
                        ,row.get("status", Integer.class)            ))
                .all();
    }

    public Flux<OrderModel> searchByWhereClause(String whereClause)
    {
        final String SEARCH_BY_WHERE= "SELECT o.number,o.date_time,o.client_id,o.product_id,o.quantity,o.status" +
                " FROM orders o INNER JOIN client c ON (o.client_id=c.id AND " + whereClause + ") INNER JOIN address a ON ( c.id = a.client_id )";

        return databaseClient.sql( SEARCH_BY_WHERE )
                .map((row, metadata) -> new OrderModel(row.get("number", Integer.class)
                        ,row.get("date_time", LocalDateTime.class)
                        ,row.get("client_id", Integer.class)
                        ,row.get("product_id", Integer.class)
                        ,row.get("quantity", Integer.class)
                        ,row.get("status", Integer.class)            ))
                .all();
    }

}
