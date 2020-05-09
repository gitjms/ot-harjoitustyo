# Testausdokumentti

Ohjelmaa on testattu JUnit-yksikkötesteillä ja manuaalisesti.

## Testauskattavuus

Pääosa automaattisista yksikkötesteistä on tehty käyttöjärjestelmän luovan pakkauksen *jms.tictactoe.ui* luokille, sillä kyseisiä luokkia on eniten (6 kpl). Testikattavuus on korkea (98%) kuten alla olevasta kuvasta käy ilmi:

<img src="https://user-images.githubusercontent.com/46410240/81461894-8aaf2c00-91b7-11ea-98d5-fe2a2ec62af6.png" alt="Jacoco Ui -raportti" width="800" >

Käyttöliittymäkerroksesta jätettiin testeistä pois luokka TicTacToeUI.

Sovelluslogiikkapakkauksen *jms.tictactoe.domain* kahdesta luokasta testataan automaattisesti vain luokka ScoreService, mutta testit kattavat silti myös luokan ScoreData ja kattavuus on korkea (100%), kuten alla olevasta kuvasta nähdään:

<img src="https://user-images.githubusercontent.com/46410240/81461736-7cacdb80-91b6-11ea-8187-852ce9bd45f3.png" alt="Jacoco Domain -raportti" width="800" >

Myös DAO-pakkauksen *jms.tictactoe.dao* kahdesta luokasta testataan vain toinen luokka, ScoreDao, ja sen testikattavuus jää pienimmäksi (53%):

<img src="https://user-images.githubusercontent.com/46410240/81461990-32c4f500-91b8-11ea-85d3-729b432c5c06.png" alt="Jacoco Dao -raportti" width="800" >

Suurin syy pienelle kattavuudelle on try-catch -olioiden catch-haaroissa, joita ei kyetty testaamaan:

<img src="https://user-images.githubusercontent.com/46410240/81462040-791a5400-91b8-11ea-8316-8db9279a136a.png" alt="Jacoco Dao try-catch -raportti" width="800" >

Jacoco-raportin kokonaistestitulos rivikattavuuden osalta on 91% ja haarautumakattavuuden osalta 86%:

<img src="https://user-images.githubusercontent.com/46410240/81375081-5f6e0380-9109-11ea-90d5-011e541934fc.png" alt="Jacoco-raportti" width="800" >

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
