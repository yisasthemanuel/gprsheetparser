# GPRO SHEET PARSER

## Introducción

Libería que implementa las funcionalidades para extraer información de las hojas excel que se descargan de GPRO con el informe de la carrera.


## Desarrollo

### Prerequisitos

* Un IDE con soporte al proyecto Lombok (<https://projectlombok.org/>): Eclipse, IntelliJ, Visual Studio Code.
* Máquina versión Java instalada. Bien:
** JKD 1.8 de Oracle
** La JVM OpenJ9 instalada (<https://adoptopenjdk.net/installation.html#linux-pkg>, <https://adoptopenjdk.net/releases.html?variant=openjdk8&jvmVariant=openj9>)
* Maven: No necesario, está integrado en el proyecto mediante "maven wrapper" / mvnw (<https://github.com/takari/maven-wrapper>)


## Construcción

Para generar el jar

```sh
$ cd gprsheetparser
$ ./mvnw clean package
```

Para publicar el jar en el repositorio maven local


```sh
$ cd gprsheetparser
$ ./mvnw clean install
```
## TO DOs

* Integrar con Artifactory para publicar las nuevas releases