# Ohjelmistotekniikka, harjoitustyö

## TicTacToe eli ristinolla

Yksinkertainen pelisovellus, joka kerää X:n ja O:n voitot talteen tietokantaan.

## Java-versio

Sovellus on luotu Javan versiolla 11 ja käyttäen JavaFX:n versiota 14.

## Dokumentaatio

[Käyttöohje](https://github.com/gitjms/ot-harjoitustyo/blob/master/dokumentointi/Kayttoohje.md)

[Vaatimusmäärittely](https://github.com/gitjms/ot-harjoitustyo/blob/master/dokumentointi/Vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/gitjms/ot-harjoitustyo/blob/master/dokumentointi/Tyoaikakirjanpito.md)

[Arkkitehtuuri](https://github.com/gitjms/ot-harjoitustyo/blob/master/dokumentointi/Arkkitehtuuri.md)

[Testausdokumentti](https://github.com/gitjms/ot-harjoitustyo/blob/master/dokumentointi/Testausdokumentti.md)

## Release

[tictactoe.jar / ot-harjoitustyo-1.zip / ot-harjoitustyo-1.tar.gz](https://github.com/gitjms/ot-harjoitustyo/releases)

## Komentorivitoiminnot

Testit suoritetaan komennolla

```
mvn test
```

Joskus voi olla tarpeen kirjoittaa (mikäli tulee ongelmia)

```
mvn clean test
```

***
Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

***
Projektin koodin voi suorittaa NetBeansin vihreällä napilla tai komentorivillä komennolla

```
mvn compile exec:java -Dexec.mainClass=jms.tictactoe.Main
```
***
Projektin koodin tyylivirheet voi tarkistaa suorittamalla komento

```
mvn jxr:jxr checkstyle:checkstyle
```

Tulosta voi tarkastella avaamalla selaimella tiedosto target/site/checkstyle.html
***
Suorituskelpoisen jar-tiedoston voi luoda komennolla

```
mvn package
```

Ajettava tiedosto löytyy target-kansiosta ja se voidaan ajaa komennolla

```
java -jar tictactoe.jar
```
***
JavaDoc voidaan generoida komennolla

```
mvn javadoc:javadoc
```
***