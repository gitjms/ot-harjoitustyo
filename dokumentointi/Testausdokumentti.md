# Testausdokumentti

Ohjelmaa on testattu JUnit-yksikkötesteillä ja manuaalisesti.

## Testauskattavuus

Jacoco-raportin testitulos rivikattavuudesta on 93% ja haarautumakattavuudesta 86%.

<img src="https://user-images.githubusercontent.com/46410240/78938671-47c83e80-7abb-11ea-9934-8cb7a9c2c71c.png" alt="Jacoco-raportti" width="800" >

Haarautumakattavuuden vaje johtuu pääasiassa try-catch -rakenteiden catch-haaroista, joita en kyennyt testaamaan. Lisäksi jäi kattamatta pari kolme MouseEvent-tapahtumaa ja osa yhdestä if-lauseesta.
Testattavana oli myös käyttöliittymäkerros lukuunottamatta main-luokan sisältävää pääluokkaa TicTacToeUI.

## Järjestelmätestaus

Sovellus on testattu manuaalisesti sekä Windowsilla että Linuxilla, eikä ole esiintynyt mitään ongelmia.

### Asennus ja konfigurointi

Sovellus on ladattu GitHubista ja otettu käyttöön kuten käyttöohje neuvoo. Ohjelman olettama config.properties on ollut mukana ja myös poistettuna.

Sovellusta on testattu mm. syöttämällä virheellisiä tietokantapolkuja tietokantatekstilaatikkoon.

### Toiminnallisuudet

[Määrittelydokumentin](https://github.com/gitjms/ot-harjoitustyo/blob/master/dokumentointi/Vaatimusmaarittely.md) ilmoittamat toiminnot on testattu.

## Sovellukseen jääneet laatuongelmat

Ongelmia ovat lähinnä järjestelemätön ja paikoin ylipitkä koodi. Koodia on varmasti mahdollista supistaa ja tehostaa, mutta ei tämän kurssin aikataulun puitteissa.

Myös ohjelmalogiikkakerroksessa sijaitseva luokka GameSquare on ongelma. Varsinaisesti se kuuluisi käyttöliittymäkerrokseen, koska siinä periaatteessa luodaan joitain komponentteja käyttöliittymää varten. Toisaalta sen luomat komponentit ovat koko sovelluksen tarkoituksen perusta: klikattavat napit, joiden luomia risti- ja nolla-rivejä koodi seuraa. Kun peli on pelattu, ottaa koodi tämän luokan kautta yhteyttä tietokantaan.
Toisin sanoen, tämä luokka on rajatapaus, joka kuuluu sekä käyttöliittymä- että ohjelmalogiikkakerrokseen. Päädyin sijoittamaan sen kuitenkin logiikkapuoleen, sillä käyttöliittymäosastossa on jo niin paljon luokkia ja logiikkakerroksessa on muutenkin lähellä sen tarvitsemat muut luokat.

Ongelma voi olla myös luokan GameComponents pituus. Halusin kuitenkin sijoittaa lähes kaikkien vähemmän tärkeiden komponenttien luontikoodit sinne, jotta järjestely pysyisi itselleni selkeänä.
Tärkeimmät sovelluksen käyttämät komponentit ovat omissa luokissaan: GameArea ja GameSquare, joista jälkimmäinen siis on rajatapaus käyttöliittymän ja sovelluslogiikan välillä.
