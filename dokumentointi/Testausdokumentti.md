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

Ongelmana on lähinnä järjestelemätön ja paikoin ylipitkä koodi. Koodia on varmasti mahdollista supistaa ja tehostaa.

Ongelma joillekin voi myös olla luokan GameComponents pituus. Halusin kuitenkin sijoittaa lähes kaikkien vähemmän tärkeiden komponenttien luontikoodit sinne, jotta järjestely pysyisi itselleni selkeänä.
Tärkeimmät sovelluksen käyttämät komponentit ovat omissa luokissaan: GameArea ja GameSquare.
