# Ohjelmistotekniikka, harjoitustyö

## TicTacToe eli ristinolla

Yksinkertainen pelisovellus, joka kerää X:n ja O:n voitot talteen.

## Java-versio

Sovellus on luotu Javan versiolla 11 ja käyttäen JavaFX:n versiota 14.

## Dokumentaatio

* [Vaatimusmäärittely](https://github.com/gitjms/ot-harjoitustyo/blob/master/dokumentointi/Vaatimusmaarittely.md)
* [Työaikakirjanpito](https://github.com/gitjms/ot-harjoitustyo/blob/master/dokumentointi/Tyoaikakirjanpito.md)
* [Arkkitehtuuri](https://github.com/gitjms/ot-harjoitustyo/blob/master/dokumentointi/Arkkitehtuuri.md)

## Komentorivitoiminnot

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html


Projektin koodin pystyy suorittamaan NetBeansin vihreällä napilla tai/ja komennolla

```
mvn compile exec:java -Dexec.mainClass=jms.tictactoe.Main
```

Projektin koodin tyylipisteet voi tarkistaa suorittamalla komento

```
mvn jxr:jxr checkstyle:checkstyle
```
