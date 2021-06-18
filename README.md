# Examen Mercado Libre

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men.

Te ha contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN.

Para eso te ha pedido crear un programa con un método o función con la siguiente firma (En
alguno de los siguiente lenguajes: Java / Golang / C-C++ / Javascript (node) / Python / Ruby):

**boolean isMutant(String[] dna); // Ejemplo Java**

En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las
cuales representa cada base nitrogenada del ADN.

## Consumo

La api **/mutant/** detecta si un ADN de un humano es mutante o no mediante un HTTP POST con un Json el cual tenga el siguiente formato:

POST → https://springgcp-317004.rj.r.appspot.com/mutant/

POST → localhost:8080/mutant/

{
    "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

En caso de verificar un mutante, devuelve un **HTTP 200-OK**, en caso contrario un **403-Forbidden**.

Adicionalmente la api **/mutant/** contiene dos métodos adiciones, el poder obtener el listado de todos los ADN examinados y el poder consultar por parametro isMutant con true o false la lista de ADN validada anteriormente por el api:

**Todos los análisis**

GET → https://springgcp-317004.rj.r.appspot.com/mutant/

GET → localhost:8080/mutant/

**Análisis filtrados por isMutant, Mutantes o Humanos**

GET → https://springgcp-317004.rj.r.appspot.com/mutant/query?isMutant=true

GET → localhost:8080/mutant/query?isMutant=true

## Desarollo

### Código fuente

La api fue desarrollada en **Spring Boot 2.5.1** con el gestor de dependencias **Maven** y el **SDK 11 de JAVA**, se hizo usó de **MySQL** como gestor de base de datos para almacenar los análisis de ADN, tanto la base de datos como la api están desplegadas en **Google App Engine**.

### Lógica de negocio

Se desarrollo un algoritmo que dada 4 letras detecta si se da la condición que hace que una secuencia de ADN sea de un ser Humano o Mutante.

Luego de hacer la verificación de la secuencia de ADN prosigue hacer el guardado en la base de dato donde se almacena por registro la secuencia de ADN, identificador auto incrementable, fecha y hora del registro y la validación de si es Humano o Mutante.
