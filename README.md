# PILOTES

**Data model & database** 

The data model provided has three entities, Address, Client and Order. 

We are are going to use an in memory H2 database. In the /main/resources directory there are a couple of sql
script files, _schema.sql_ and _data.sql_. Both are ran at application bootstrapping as is indicated in the 
application properties file.

Our data model includes a table for all those aforementioned entities plus a four for the Product entity, nobody 
knows how will the great Mr. Montoro feel in a couple of months about including his tasteful recipe of _*SourSweet 
Pulled Pork*_, so better be ready. Aside from that, this inclusion allows for a more intuitive and coherent data model,
having the product features like the price in his own entity. Product has 1 - many relationship with the Order entity.

The table for Client has 1 - many relationship with the Order entity.

The table for Address has many - 1 relationship with the Client entity, given a Client can get hungry 
in his mom's house, in his girlfriend's house or in his own's, and may wish to be served at different places. One has 
his mood !

At middleware level there are the ProductRepository, the OrderRepository and the OrderRepositoryImpl. The two first are 
standard interfaces with defined user queries, the RepositoryImpl is used for the search, given it requires a more 
complex and specific logic and data treatment.  

**A P I**

The solution is implemented using Spring Webflux with functional endpoints. To document the API, the routing 
configuration method has all needed annotations. The swagger interface show the created endpoints that  can be accessed
and tested at http://localhost:8080/swagger-ui/swagger-ui/index.html

Instead of grouping the functionalities around a technical categories, e.g. controller, model, service et. al, domain
entities drive the structuring, so below _**com.tui.pilotes**_ there are the folders for Address, Client, Product and Order 
entities, also the SpringBoot's  main class and the SpringSecurity configuration class.   

**Testing**

There are some test in place:

- main entities are construed only if there are valid data provided, if not, sensible defaults are in place, that is tested
- the code responsible for creating the search conditions is tested the most, to gran the search condition is properly generated
- as there is a secured endpoint, it is tested the authorization mechanism works well

Some end point accesses :
- curl http://localhost:8080/v1/search/first_name/non-exact/dre -H "Accept: application/json" -H "Authorization: Basic dXNlcjpwYXNzd29yZA=="
- curl http://localhost:8080/v1/search/city/exact/Bruzzone -H "Accept: application/json" -H "Authorization: Basic dXNlcjpwYXNzd29yZA=="
- curl http://localhost:8080/v1/search/street/non-exact/mar -H "Accept: application/json"
- curl http://localhost:8080/v1/order/1 -H "Accept: application/json"

**Missings**

- validators for validation at handler level
- a more thorough and comprehensive unit / integration  testing 
- exceptionals condition representation and  treatment


