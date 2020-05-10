# Testausdokumentti

Ohjelmaa on testattu JUnit-yksikkötesteillä ja manuaalisesti.

## Jacoco-testauskattavuus

Kokonaistestikattavuus on korkea (92%) kuten alla olevasta kuvasta käy ilmi:

<img src="https://user-images.githubusercontent.com/46410240/81502789-c1389400-92e8-11ea-9713-9fb95393e03f.png" alt="Jacoco-raportti" width="800" >

Pääosa automaattisista yksikkötesteistä on tehty käyttöjärjestelmän rakentavan pakkauksen (*jms.tictactoe.ui*) luokille, jossa myös on korkea rivikattavuus (98%): 

<img src="https://user-images.githubusercontent.com/46410240/81461894-8aaf2c00-91b7-11ea-98d5-fe2a2ec62af6.png" alt="Jacoco Ui -raportti" width="800" >

Käyttöliittymäkerroksen testeistä jätettiin pois luokka TicTacToeUI.

Sovelluslogiikkapakkauksen (*jms.tictactoe.domain*) testataan molemmat luokat, ScoreService ja ScoreData. Kattavuus on korkea (100%), kuten alla olevasta kuvasta nähdään:

<img src="https://user-images.githubusercontent.com/46410240/81462417-f8108c00-91ba-11ea-8775-542262aa7768.png" alt="Jacoco Domain -raportti" width="800" >

DAO-pakkauksen (*jms.tictactoe.dao*) kahdesta luokasta testataan vain toinen luokka, ScoreDataDao, ja sen testikattavuus jää pienimmäksi (53%):

<img src="https://user-images.githubusercontent.com/46410240/81461990-32c4f500-91b8-11ea-85d3-729b432c5c06.png" alt="Jacoco Dao -raportti" width="800" >

Suurin syy pienelle kattavuudelle on try-catch -olioiden catch-haaroissa, joita ei kyetty testaamaan, kuten alla oleva kuva havainnollistaa:

<img src="https://user-images.githubusercontent.com/46410240/81462040-791a5400-91b8-11ea-8316-8db9279a136a.png" alt="Jacoco Dao try-catch -raportti" width="500" >

## PIT-testauskattavuus

[PIT](http://pitest.org/) on automaattinen mutaatiotestausväline, joka suorittaa pieniä muutoksia koodiin ja tarkistaa kattavatko yksikkötestit myös nämä tilanteet.

Koodimutaation tulee aiheuttaa se, että testit eivät onnistu, tällöin mutaatiot tulevat tapetuksi (*killed*). Jos testit eivät kaadu mutaatioihin, eli menevät yhä läpi, mutaatiot elävät (*lived*). Testien laatua voidaan mitata tapettujen mutaatioiden osuudella.

Perinteinen testauskattavuus (kuten Jacoco) mittaa, miten paljon koodia ei ole testattu. PIT tutkii, ovatko koodit testattu merkityksellisesti. Pit ilmoittaa tuloksena rivikattavuutena ja mutaatiokattavuutena prosenteissa samaan tapaan kuin Jacoco. Alla esimerkki ristinollapelin PIT-raportista:

<img src="https://user-images.githubusercontent.com/46410240/81497112-04810b80-92c5-11ea-9f77-4b474632da5d.png" alt="PIT-raportti" width="600" >

Yllä olevan tuloksen mukaan PIT generoi 370 mutaatiota, joista 103 tapettiin.

Seuraavaksi voimme avata tarkemman raportin esimerkiksi domain-paketista:

<img src="https://user-images.githubusercontent.com/46410240/81496701-275df080-92c2-11ea-912f-6907f25a1445.png" alt="PIT-domainraportti" width="500" >

Nähdään, että tämän pakkauksen rivikattavuus on hyvä (100%) ja että tähän pakkaukseen luotiin 28 mutaatiota, joista 25 tapettiin. Kolme mutaatiota selvisi testeistä. Katsotaan tarkemmin, mistä on kyse. Avataan Luokan ScoreData tulos, jossa kaksi mutaatiota selvisi testeistä:

<img src="https://user-images.githubusercontent.com/46410240/81496520-d4d00480-92c0-11ea-8223-46451f5d079e.png" alt="PIT-tarkennus" width="600" >

Kyseessä on molempien tietokantataulujen luomismetodit (*createScoreTable* ja *createGameTable*) ja niissä olevien olioiden *preparedStatement* sulkemisesta. Alimmasta kuvasta nähdään, että mutaatio poisti rivien 50 ja 71 sulkemiskäskyt, eivätkä testit siis kattaneet tällaista bugia.

Tällä kurssilla ei ole vaadittu mutaatioiden testausta, joten en korjaa testien mutaatio-ongelmia.

## Järjestelmätestaus

Sovellus on testattu manuaalisesti sekä Windowsilla että Linuxilla, eikä ole esiintynyt mitään ongelmia.

### Asennus ja konfigurointi

Sovellus on ladattu GitHubista ja otettu käyttöön kuten käyttöohje neuvoo. Ohjelman olettama config.properties on ollut mukana ja myös poistettuna.

Sovellusta on testattu mm. syöttämällä virheellisiä tietokantapolkuja tietokantatekstilaatikkoon.

### Toiminnallisuudet

[Määrittelydokumentin](https://github.com/gitjms/ot-harjoitustyo/blob/master/dokumentointi/Vaatimusmaarittely.md) ilmoittamat toiminnot on testattu.

## Sovellukseen jääneet laatuongelmat

Ongelmana on lähinnä järjestelemätön ja paikoin ylipitkä koodi. Koodia on varmasti mahdollista supistaa ja tehostaa.

Ongelma voi myös olla luokan GameComponents pituus. Halusin kuitenkin sijoittaa lähes kaikkien vähemmän tärkeiden komponenttien luontikoodit sinne, jotta järjestely pysyisi itselleni selkeänä.
Tärkeimmät sovelluksen käyttämät komponentit ovat omissa luokissaan: GameArea ja GameSquare.
