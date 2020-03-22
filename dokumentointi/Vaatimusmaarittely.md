# Vaatimusmäärittely

## Sovelluksen tarkoitus

Ristinollasovellus on pieni 3x3 -kokoinen pelilauta, jossa käyttäjä/käyttäjät pelaavat ristinollaa.
Käyttäjää ei identifioida, eli ei ole sisäänkirjautumista.

## Käyttöliittymäluonnoss

<img src="https://user-images.githubusercontent.com/46410240/76624792-9f1bd300-653e-11ea-8de6-c35aadddc987.png" alt="Käyttöliittymäluonnos" width="450" >

## Käyttäjät

Peliä voi pelata yksin tai kaverin kanssa, mutta pelaajadataa ei yksilöidä.
Peli näkee pelaajat vain ristien ja nollien edustajina.

## Suunnitellut toiminnallisuudet

* Peli tallentaa pelattujen pelien määrän sekä ristien ja nollien voitot tietokoneelle (käytännossä resurssitiedostoon tai tietokantaan)
* Ristien ja nollien voittostatistiikka näkyy ylimpänä pelilaudan kulmissa. Seuraavaksi ylimpänä on teksti, joka ilmoittaa kulloisenkin pelin numeron. Tämän alla on teksti, jossa ilmoitetaan pelattavan symbolin vuoro sekä pelin loputulos.
* Keskellä ikkunaa on isoin laatikko, jossa on itse peliruudukko.
* Alhaalla vasemmalla on uuden pelin avaava nappula, ja oikealla nappula jolla suljetaan peli.

## Jatkokehitysideat

* Pelaajien identifiointi, eli sisäänkirjautuminen tai uuden pelaajatilin luominen.
Tällöin pelistatistiikka (pelit, voitot, häviöt) tallentuu kunkin pelaajan osalta.
* Vaihtoehtona pelata tietokonetta vastaan.
* Isompia pelilautoja kuin 3x3, vapaasti pelaajan valittavissa.