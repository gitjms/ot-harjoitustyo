# Arkkitehtuurikuvaus

## Rakenne

Ohjelman kolmitasoinen kerrosarkkitehtuuri on havainnollistettu alla olevassa kuvassa.

<img src="https://user-images.githubusercontent.com/46410240/81364750-1b6e0500-90ef-11ea-9d5f-673884193c35.png" alt="Pakkausrakenne" width="200" >

JavaFX:llä toteutetusta ohjelman käyttöliittymästä vastaa pakkaus [*jms.tictactoe.ui*](https://github.com/gitjms/TicTacToe-Ristinolla/tree/master/TicTacToe/src/main/java/jms/tictactoe/ui). Sovelluslogiikka on sijoitettu pakkaukseen [*jms.tictactoe.domain*](https://github.com/gitjms/TicTacToe-Ristinolla/tree/master/TicTacToe/src/main/java/jms/tictactoe/domain). Pysyväistallennukseen liittyvä koodi on pakkauksessa [*jms.tictactoe.dao*](https://github.com/gitjms/TicTacToe-Ristinolla/tree/master/TicTacToe/src/main/java/jms/tictactoe/dao).

## Käyttöliittymä

Käyttöliittymä sisältää kolme erillistä näkymää, yksi kullekin pelikoolle: 3x3-kokoisen pelin näkymä, 4x4-näkymä ja 5x5-näkymä. Kukin näkymä on oma Scene-olionsa, joka on sijoitettu sovelluksen Stage-olioon. Käyttöliittymän rakentava koodi on luokassa [TicTacToeUI](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/ui/TicTacToeUI.java), mutta osa käyttöliittymävastuusta on jaettu luokkiin [GameArea](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/ui/GameArea.java), [GameComponents](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/ui/GameComponents.java) ja [GameSquare](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/ui/GameSquare.java). Tähän pakkaukseen on myös sijoitettu kaksi enum-oliota, jotka vastaavat pelin koosta ja voittoluokasta: [GameSize](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/ui/GameSize.java) ja [WinRow](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/ui/WinRow.java).

Käyttöliittymä on eristetty sovelluslogiikasta, johon se pääsee käsiksi sovelluslogiikan olion *scoreService* metodien kautta.

Kun luodaan uusi peli tai eri kokoinen peli, kutsutaan luokan [GameArea](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/ui/GameArea.java) metodia *createArea*, joka luo uuteen näkymään uudet komponentit ja asettaa ne esille Stage-olioon.

## Sovelluslogiikka

Sovelluksen logiikasta vastaa luokka [ScoreData](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/domain/ScoreData.java), joka huolehtii peli- ja pistepäivityksistä, tietokannan tauluista, päivityksistä ja kyselyistä, sekä tietokannan sulkemisesta. Luokka ScoreData tarjoaa metodit tietokantatoiminnoille:

* void createScore(String id, int points)
* void setScore(String player, int points)
* void resetPoints()
* int getPoints(String id)
* int getAmount()
* int void setAmount(int amount)
* int getDraws()
* void setDraws(int draws)
* void closeStatement(Statement statement)
* void closeConnection(Connection connection)

Tietokantaoperaatioihin pääsee käsiksi luokan [ScoreService](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/domain/ScoreService.java) avulla. ScoreService hyödyntää [*ScoreDao*](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/dao/ScoreDao.java)-rajapintaa, joka sijaitsee tallennuksesta vastaavassa *jms.tictactoe.dao*-pakkauksessa.

Ohjelman luokkien suhdetta kuvaa seuraava luokka/pakkauskaavio: 

<img src="https://user-images.githubusercontent.com/46410240/81461269-5fc2d900-91b3-11ea-8dca-608a58c4abf6.png" alt="Pakkauskaavio" width="600" >

## Tietojen pysyväistallennus

Pakkauksessa *jms.tictactoe.dao* oleva luokka [*ScoreDataDao*](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/dao/ScoreDataDao.java) huolehtii tietojen tietojen tallettamisesta tietokantaan sekä itse tietokantataulujen alustamisesta ja olemassaolon tarkistamisesta.

Luokka on rakennettu noudattaen Data Access Object -suunnittelumallia, joloin se on eristetty rajapinnan *ScoreDao* taakse eikä sovelluslogiikka käytä sitä suoraan.

## Tietokanta

Sovellus tallettaa kaksi pelaajaa (*X* ja *O*) sekä näiden pisteet tietokantatauluun *Scores*, sekä pelatut pelit ja tasapelit tietokantatauluun *Games*. Tietokannasta huolehtii *H2*-niminen palvelu. Se luo oletuksena sovelluksen juureen kaksi tietokantatiedostoa: *scoreData.mv.db* ja *scoreData.trace.db*. Oletustietokannan nimi ja muut tiedot on tallennettu myös sovelluksen juureen tiedostoon [config.properties](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/config.properties). Alla kuva tietokantatauluista:

<img src="https://user-images.githubusercontent.com/46410240/81461068-0908cf80-91b2-11ea-8bf8-c6f0159d25d8.png" alt="Tietokantataulut" width="300" >

Käyttäjän vaihtaessa tietokantataulua, luo sovellus uuden tietokannan sovelluksen juureen annetulla nimellä. Uuden tietokannan nimeä ja muita tietoa ei tallenneta config.properties-tiedostoon, jossa on vain oletustietokannan tiedot.


## Päätoiminnallisuudet

Joitakin sovelluksen toimintalogiikan ominaisuuksia on kuvattu seuraavilla sekvenssikaavioilla:

### Uuden pelin luominen

<img src="https://user-images.githubusercontent.com/46410240/81370302-832b4c80-90fd-11ea-8138-7b280962b9da.png" alt="Sekvenssikaavio: uusi peli" width="600" >

### Pelin nollaaminen

<img src="https://user-images.githubusercontent.com/46410240/81370337-94745900-90fd-11ea-8db8-90871242f90c.png" alt="Sekvenssikaavio: pelin nollaus" width="600" >

### Pelin lopettaminen

<img src="https://user-images.githubusercontent.com/46410240/81370370-a6ee9280-90fd-11ea-8cd8-356a47438534.png" alt="Sekvenssikaavio: pelin lopetus" width="600" >

### Pelaajan *X* voitto

<img src="https://user-images.githubusercontent.com/46410240/81370394-b4a41800-90fd-11ea-846c-9beb07e57fa6.png" alt="Sekvenssikaavio: X voittaa" width="600" >



