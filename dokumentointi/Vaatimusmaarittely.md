# Vaatimusmäärittely

## Sovelluksen tarkoitus

Ristinollasovellus on pieni pelilauta, jossa käyttäjä/käyttäjät pelaavat ristinollaa.
Käyttäjää ei identifioida, eli ei ole sisäänkirjautumista.

## Käyttöliittymäluonnoss

Kuvassa näkyvät komponentit, joista uloin (BorderPane) asettuu Sceneen joka puolestaan asettuu Stageen, joka näkyy sovellusikkunana.

<img src="https://user-images.githubusercontent.com/46410240/78068470-21125580-73a1-11ea-8125-6cb33209f14c.png" alt="Käyttöliittymäluonnos" width="450" >

## Käyttäjät

Peliä voi pelata yksin tai kaverin kanssa, mutta pelaajadataa ei yksilöidä.
Peli näkee pelaajat vain ristien ja nollien edustajina.

## Suunnitellut toiminnallisuudet

- [X] Peli tallentaa pelattujen pelien määrän sekä ristien ja nollien voitot tietokoneelle (käytännossä resurssitiedostoon ~tai tietokantaan~).
- [X] pelilaudan yläosassa näkyy pelattujen pelien kokonaismäärä ja tasapelien määrä.
- [X] Ristien ja nollien voittostatistiikka näkyy pelilaudan yläosassa.
  - [x] Aluksi vain kulloisenkin pelin tilanne.
  - [X] Lopulta myös tallennetut datat mukana.
- [x] Seuraavaksi ylimpänä on teksti, joka ilmoittaa kulloisenkin pelin numeron.
- [x] Tämän alla on teksti, jossa ilmoitetaan pelattavan symbolin vuoro sekä pelin loputulos.
- [x] Keskellä ikkunaa on isoin laatikko, jossa on itse peliruudukko.
- [x] Peliruudukon alla vasemmalla on uuden pelin avaava nappula.
- [X] Peliruudukon alla keskellä on nappula joka resetoi pelin ja tallennetun datan.
- [x] Peliruudukon alla oikealla on nappula jolla suljetaan peli.
- [X] Alimpana on nappulat, joilla voi valita pelin koon: 3x3, 4x4 ja 5x5.

## Jatkokehitysideat

* Pelaajien identifiointi, eli sisäänkirjautuminen tai uuden pelaajatilin luominen.
Tällöin pelistatistiikka (pelit, voitot, häviöt) tallentuu kunkin pelaajan osalta.
* Vaihtoehtona pelata tietokonetta vastaan.
