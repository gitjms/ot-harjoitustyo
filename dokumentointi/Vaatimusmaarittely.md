# Vaatimusmäärittely

## Sovelluksen tarkoitus

Ristinollasovellus on pieni 3x3 -kokoinen pelilauta, jossa käyttäjä/käyttäjät pelaavat ristinollaa.
Käyttäjää ei identifioida, eli ei ole sisäänkirjautumista.

## Käyttöliittymäluonnoss

Kuvassa näkyvät komponentit, joista uloin (BorderPane) asettuu Sceneen joka puolestaan asettuu Stageen, joka näkyy sovellusikkunana.

<img src="https://user-images.githubusercontent.com/46410240/76624792-9f1bd300-653e-11ea-8de6-c35aadddc987.png" alt="Käyttöliittymäluonnos" width="450" >

## Käyttäjät

Peliä voi pelata yksin tai kaverin kanssa, mutta pelaajadataa ei yksilöidä.
Peli näkee pelaajat vain ristien ja nollien edustajina.

## Suunnitellut toiminnallisuudet

- [ ] Peli tallentaa pelattujen pelien määrän sekä ristien ja nollien voitot tietokoneelle (käytännossä resurssitiedostoon tai tietokantaan).
- [ ] Ristien ja nollien voittostatistiikka näkyy ylimpänä pelilaudan kulmissa.
  - [x] Aluksi vain kulloisenkin pelin tilanne.
  - [ ] Lopulta myös tallennetut datat mukana.
- [x] Seuraavaksi ylimpänä on teksti, joka ilmoittaa kulloisenkin pelin numeron.
- [x] Tämän alla on teksti, jossa ilmoitetaan pelattavan symbolin vuoro sekä pelin loputulos.
- [x] Keskellä ikkunaa on isoin laatikko, jossa on itse peliruudukko.
- [x] Alhaalla vasemmalla on uuden pelin avaava nappula, ja oikealla nappula jolla suljetaan peli.
- [ ] Alhaalla keskellä on pelitilaston nollaava nappula.

## Jatkokehitysideat

* Pelaajien identifiointi, eli sisäänkirjautuminen tai uuden pelaajatilin luominen.
Tällöin pelistatistiikka (pelit, voitot, häviöt) tallentuu kunkin pelaajan osalta.
* Vaihtoehtona pelata tietokonetta vastaan.
* Isompia pelilautoja kuin 3x3, vapaasti pelaajan valittavissa.