En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas. A continuación se muestra un ejemplo de la tabla con los campos relevantes:
 
# PILOTES

**Data model:**

The data model provided has three Entities, Address, Client and Order. 

To implement the solution of the test we are using in memory H2. In the /main/resources dir there are a couple of sql
script files, schema.sql and data.sql. Both are launching at application bootstrapping as is indicated in the 
application properties file.

Our data model includes a four Entity, Product, nobody knows how will the great Mr. Montoro feel in a couple of months 
about including his tasteful recipe of SourSweet Chicken, so better be ready. Besides this inclusion allows for a more
intuitive and coherent data model, having the product features like the price in his own entity. Product has 1 - many 
relationship with the Order entity.

There is also a table for Client, it has 1 - many relationship with the Order entity.

There is also a table for Address, it has many - 1 relationship with the Client entity, given a Client can get hungry 
in his mom's house, in his girlfriend's house or in his own's.


 
* **BRAND_ID:** foreign key de la cadena del grupo (1 = ZARA).
* **START_DATE , END_DATE:** rango de fechas en el que aplica el precio tarifa indicado.
* **PRICE_LIST:** Identificador de la tarifa de precios aplicable.
* **PRODUCT_ID:** Identificador código de producto.
* **PRIORITY:** Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
* **PRICE:** precio final de venta.
* **CURR:** ISO de la moneda.
 
**Se pide:**
 
Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:
 
- Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
- Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.
 
Se debe utilizar una base de datos en memoria (tipo h2) e inicializar con los datos del ejemplo, (se pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato que se considere adecuado para los mismos).
              
Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:
                                                                                       
- Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
- Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
- Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
- Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
- Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
 
 
**Se valorará:**
 
* Diseño y construcción del servicio.
* Calidad de Código.
* Resultados correctos en los test.