# Exchange
W.UP feladat - valutaváltó

+ Elindítás
Az alkalmazás PostgreSQL adatbázist használ, tehát szükséges egyet létrehozni.
Létrehozás után az application.properties-ben az alábbi property-ket kell megfelelően beállítani:

spring.datasource.url=database url
spring.datasource.username=username
spring.datasource.password=password

Továbbá a docs mappában található egy init.sql fájl, aminek a tartalmát le kell futtatni az adatbázison.
Az alkalmazást most már el lehet indítani.

+ Kipróbálás
A projektben a docs mappában található egy postman példa kérés lista.
