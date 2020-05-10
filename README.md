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

[tictactoe.jar / Source code .zip / Source code .tar.gz](https://github.com/gitjms/TicTacToe-Ristinolla/releases/tag/3.3)

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
Projektin koodin voi suorittaa NetBeansin vihreällä napilla tai komentorivillä komennolla

```
mvn compile exec:java -Dexec.mainClass=jms.tictactoe.Main
```

***
Jacoco-testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

***
PIT-testikattavuusraportti luodaan komennolla

```
mvn org.pitest:pitest-maven:mutationCoverage
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/pit-reports/YYYYMMDDHHMI/index.html

Jos sovellus on käännetty (compile), täytyy sovellus käynnistää ja sulkea kerran, muuten voi käydä niin, ettei PIT osaa tehdä testejä.

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
Tulosta voi tarkastella avaamalla selaimella esimerkiksi jokin seuraavista tiedostoista:

* target/site/apidocs/index.html
* target/site/apidocs/allclasses-index.html
* target/site/apidocs/allpackages-index.html
* target/site/apidocs/index-all.html
* target/site/apidocs/overview-tree.html
***