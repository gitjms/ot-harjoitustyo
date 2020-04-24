# Vaatimusmäärittely

## Sovelluksen tarkoitus

Ristinollasovellus on pieni pelilauta, jossa käyttäjä/käyttäjät pelaavat ristinollaa.
Käyttäjää ei identifioida, eli ei ole sisäänkirjautumista.

## Käyttöliittymäluonnos

Kuvassa näkyvät komponentit, joista uloin (BorderPane) asettuu Sceneen joka puolestaan asettuu Stageen, joka näkyy sovellusikkunana.

<img src="https://user-images.githubusercontent.com/46410240/78979104-d2448880-7b23-11ea-8fd7-2558aae97708.png" alt="Käyttöliittymäluonnos" width="700" >

Vasen kuva on vanhempi versio. Oikeaan on lisätty mm. tekstinsyöttökenttä ja nappi alhaalle.

## Käyttäjät

Peliä voi pelata yksin tai kaverin kanssa, mutta pelaajadataa ei yksilöidä.
Peli näkee pelaajat vain ristien ja nollien edustajina.

## Suunnitellut toiminnallisuudet

- [X] Peli tallentaa pelattujen pelien määrän sekä ristien ja nollien voitot tietokantaan.
- [X] Käyttäjä voi vaihtaa tietokantaa käyttöliittymästä käsin (alimpana vasemmalla).
- [X] Ristien ja nollien voittostatistiikka ja tasapelit näkyvät pelilaudan yläosassa.
- [x] Seuraavaksi ylimpänä on teksti, joka ilmoittaa kulloisenkin pelin numeron.
- [x] Tämän alla on teksti, jossa ilmoitetaan pelattavan symbolin vuoro sekä pelin loputulos.
- [x] Keskellä ikkunaa on isoin laatikko, jossa on itse peliruudukko.
- [x] Peliruudukon alla vasemmalla on uuden pelin avaava nappula.
- [X] Peliruudukon alla keskellä on nappula joka nollaa pelin ja tallennetun datan.
- [x] Peliruudukon alla oikealla on nappula jolla suljetaan peli.
- [X] Alimpana oikealla on nappulat, joilla voi valita pelin koon: 3x3, 4x4 ja 5x5.

## Jatkokehitysideat

* Pelaajien identifiointi, eli sisäänkirjautuminen tai uuden pelaajatilin luominen.
Tällöin pelistatistiikka (pelit, voitot, häviöt) tallentuu kunkin pelaajan osalta.
  * Tavallaan jo tehty, sillä tietokannan vaihto toimii kuin uusi tietokanta olisi uusi (tai palaava) pelaaja.
* Vaihtoehtona pelata tietokonetta vastaan.
* Jonkinlainen vapaa kenttä.
