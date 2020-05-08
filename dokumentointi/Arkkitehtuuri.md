# Arkkitehtuurikuvaus

## Rakenne

Ohjelman kolmitasoinen kerrosarkkitehtuuri on havainnollistettu alla olevassa kuvassa.

<img src="https://user-images.githubusercontent.com/46410240/81364750-1b6e0500-90ef-11ea-9d5f-673884193c35.png" alt="Pakkausrakenne" width="200" >

JavaFX:llä toteutetusta ohjelman käyttöliittymästä vastaa pakkaus *tictactoe.ui*. Sovelluslogiikka on sijoitettu pakkaukseen *tictactoe.domain*. Pysyväistallennukseen liittyvä koodi on pakkauksessa *tictactoe.dao*.

## Käyttöliittymä

Käyttöliittymä sisältää kolme erillistä näkymää, yksi kullekin pelikoolle: 3x3-kokoisen pelin näkymä, 4x4-näkymä ja 5x5-näkymä. Kukin näkymä on oma Scene-olionsa, joka on sijoitettu sovelluksen Stage-olioon. Käyttöliittymän rakentava koodi on luokassa [TicTacToeUI](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/ui/TicTacToeUI.java), mutta osa käyttöliittymävastuusta on jaettu luokkiin [GameArea](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/ui/GameArea.java), [GameComponents](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/ui/GameComponents.java) ja [GameSquare](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/ui/GameSquare.java). Tähän pakkaukseen on myös sijoitettu kaksi enum-oliota, jotka vastaavat pelin koosta ja voittoluokasta: [GameSize](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/ui/GameSize.java) ja [WinRow](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/ui/WinRow.java).

Käyttöliittymä on eristetty sovelluslogiikasta. Käyttöliittymä kutsuu pääosin sovelluslogiikan olion *scoreService* metodeja, mutta sulkeakseen tietokantayhteyden se kutsuu myös olion *scoreData* metodeja *closeStatement* ja *closeConnection*.

Kun luodaan uusi peli tai eri kokoinen peli, kutsutaan luokan [GameArea](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/ui/GameArea.java) metodia *createArea*, joka luo uuteen näkymään uudet komponentit ja asettaa ne esille Stage-olioon.

## Sovelluslogiikka

Sovelluksen logiikasta vastaa luokka [ScoreData](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/domain/ScoreData.java), joka huolehtii peli- ja pistepäivityksistä, tietokannan tauluista, päivityksistä ja kyselyistä, sekä tietokannan sulkemisesta.

Toiminnallisista kokonaisuuksista vastaa luokan [ScoreService](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/domain/ScoreService.java) ainoa olio. Luokka tarjoaa kaikille käyttöliittymän toiminnoille oman metodin, kuten esimerkiksi

* void createScore(String id, int points)
* void setScore(String player, int points)
* void resetPoints()
* int getPoints(String id)

ScoreService pääsee käsiksi pisteisiin tietojen tallennuksesta vastaavan pakkauksen *tictactoe.dao* rajapinnan [*ScoreDao*](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/dao/ScoreDao.java) toteuttavan luokan kautta. Luokan toteutus injektoidaan sovelluslogiikalle konstruktorikutsun yhteydessä.

ScoreServicen ja ohjelman muiden osien suhdetta kuvaa seuraava luokka/pakkauskaavio: 

<img src="https://user-images.githubusercontent.com/46410240/81365297-650b1f80-90f0-11ea-94a7-636c93d115b4.png" alt="Pakkauskaavio" width="600" >

## Tietojen pysyväistallennus

Pakkauksen *tictactoe.dao* luokka [*ScoreDataDao*](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/src/main/java/jms/tictactoe/dao/ScoreDataDao.java) huolehtii tietojen tietojen tallettamisesta tietokantaan ja muista tietokantatoiminnoista.

Luokka noudattaa Data Access Object -suunnittelumallia, ja se on mahdollista vaihtaa mikäli tallennustavaksi halutaan jokin muu kuin tietokanta. Luokka on eristetty rajapinnan *ScoreDao* taakse, eikä sovelluslogiikka käytä luokkaa suoraan.

Sovelluslogiikan testauksessa hyödynnetään DAO-mallin eristämistä siten, että testeissä käytetään tiedostoon tallentavien DAO-olioiden sijaan keskusmuistiin tallentavia toteutuksia.

## Tietokanta

Sovellus tallettaa kaksi pelaajaa (*X* ja *O*) sekä näiden pisteet tietokantatauluun *Scores*, ja pelatut pelit sekä tasapelit tietokantatauluun *Games*. Tietokannasta huolehtii *H2*-niminen palvelu. Se luo oletuksena sovelluksen juureen kaksi tietokantatiedostoa: *scoreData.mv.db* ja *scoreData.trace.db*. Oletustietokannan nimi ja muut tiedot on tallennettu myös sovelluksen juureen tiedostoon [config.properties](https://github.com/gitjms/TicTacToe-Ristinolla/blob/master/TicTacToe/config.properties). Alla kuva tietokantatauluista:

<img src="https://user-images.githubusercontent.com/46410240/81363641-8407b280-90ec-11ea-9f27-978d94a4e017.png" alt="Tietokantataulut" width="300" >

Käyttäjän vaihtaessa tietokantataulua, luo sovellus uuden tietokannan sovelluksen juureen annetulla nimellä. Uuden tietokannan nimeä ja muita tietoa ei tallenneta config.properties-tiedostoon, jossa on vain oletustietokannan tiedot.


## Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaavioina.

### Uuden pelin luominen

<img src="https://user-images.githubusercontent.com/46410240/81370302-832b4c80-90fd-11ea-8138-7b280962b9da.png" alt="Sekvenssikaavio: uusi peli" width="600" >

### Pelin nollaaminen

<img src="https://user-images.githubusercontent.com/46410240/81370337-94745900-90fd-11ea-8db8-90871242f90c.png" alt="Sekvenssikaavio: pelin nollaus" width="600" >

### Pelin lopettaminen

<img src="https://user-images.githubusercontent.com/46410240/81370370-a6ee9280-90fd-11ea-8cd8-356a47438534.png" alt="Sekvenssikaavio: pelin lopetus" width="600" >

### Pelaajan *X* voitto

<img src="https://user-images.githubusercontent.com/46410240/81370394-b4a41800-90fd-11ea-846c-9beb07e57fa6.png" alt="Sekvenssikaavio: X voittaa" width="600" >



