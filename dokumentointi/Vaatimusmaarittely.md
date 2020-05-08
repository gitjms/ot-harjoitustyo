# Vaatimusmäärittely

## Sovelluksen tarkoitus

Ristinollasovellus on pieni pelilauta, jossa käyttäjä/käyttäjät pelaavat ristinollaa.
Käyttäjää ei identifioida, eli ei ole sisäänkirjautumista.

## Käyttöliittymäluonnos

Kuvassa näkyvät komponentit, joista uloin (BorderPane) asettuu Sceneen joka puolestaan asettuu Stageen, joka näkyy sovellusikkunana.

<img src="https://user-images.githubusercontent.com/46410240/81379832-7b29d780-9112-11ea-8509-782e37186d51.png" alt="Käyttöliittymäluonnos" width="550" >

Alleviivatut komponentit (yksi HBox ja kaksi VBoxia) asettuvat suoraan BorderPane-komponenttiin.

## Käyttäjät

Peliä voi pelata yksin tai kaverin kanssa, mutta pelaajadataa ei yksilöidä.
Peli näkee pelaajat vain ristien ja nollien edustajina.

## Ominaisuudet

* Peli tallentaa pelattujen pelien määrän sekä ristien ja nollien voitot haluttuun tai oletustietokantaan.
* Ristien ja nollien voittostatistiikka ja tasapelit näkyvät pelilaudan yläosassa.
* Seuraavaksi ylimpänä on teksti, joka ilmoittaa kulloisenkin pelin numeron.
* Tämän alla on teksti, jossa ilmoitetaan pelattavan symbolin vuoro sekä pelin loputulos.
* Keskellä ikkunaa on isoin laatikko, jossa on itse peliruudukko.
* Peliruudukon alla vasemmalla on uuden pelin avaava nappula.
* Peliruudukon alla keskellä on nappula joka nollaa pelin ja tallennetun datan.
* Peliruudukon alla oikealla on nappula jolla suljetaan peli.
* Alimpana vasemmalla on tekstilaatikko ja nappula, joilla voi vaihtaa tietokantaa.
* Alimpana oikealla on nappulat, joilla voi valita pelin koon: 3x3, 4x4 ja 5x5.

## Toiminnallisuudet. Käyttäjä voi:

* klikata peliruudun neliöitä
* vaihtaa peliruudukon koon
* luoda uuden pelin
* lopettaa pelin
* vaihtaa tietokannan

## Jatkokehitysideat

* Pelaajien identifiointi, eli sisäänkirjautuminen tai uuden pelaajatilin luominen.
Tällöin pelistatistiikka (pelit, voitot, häviöt) tallentuu kunkin pelaajan osalta.
  * Tavallaan jo tehty, sillä tietokannan vaihto toimii kuin uusi tietokanta olisi uusi (tai palaava) pelaaja.
  * Tällöin tulisi vain lisätä pelaajalle mahdollisuus asettaa oma tilinsä salasanan suojaan.
* Vaihtoehtona pelata tietokonetta vastaan.
* Jonkinlainen vapaa kenttä.
