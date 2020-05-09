# Käyttöohje

Lataa tiedosto [tictactoe.jar](https://github.com/gitjms/TicTacToe-Ristinolla/releases/tag/3.3)

## Konfigurointi

Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto config.properties, joka määrittelee pelipisteet tallentavan tietokannan nimen, polun, käyttäjän ja salasanan. Tiedoston muoto on seuraava

```
scoreData=scoreData
url=jdbc:h2:
user=sa
password=
```

Mikäli tiedosto katoaa, luo ohjelma sen oletussisältöineen uudestaan sovelluksen käynnistyttyä. Käyttäjä voi vaihtaa tietokantaa pelin käyttöjärjestelmässä ilman konfiguraatiotiedoston muuttamista.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla

```
java -jar tictactoe.jar
```

Windowsissa voi myös tuplaklikata jar-tiedostoa ohjelman käynnistääkseen.

## Ohjelman rakentaminen itse lähdekoodista

Jos jar-tiedosto ei toimi, voi ladata lähdekoodin (source code) ja purkaa zip-tiedoston esim komennolla

```
unzip ot-harjoitustyo-1.zip
```

tai jollain Windowsin työkalulla.

Tar-tiedoston voi purkaa Linuxissa komennolla

```
tar -zxvf ot-harjoitustyo-1.tar.gz
```

Sitten voi siirtyä puretun kansion sisään ja eteenpäin kansioon TicTacToe/, jossa voi antaa komennon

```
mvn package
```

minkä jälkeen voi mennä kansioon target/, jossa voi antaa käynnistyskomennon

```
java -jar tictactoe.jar
```

# Ohjelman käyttäminen

Sovellus käynnistyy pelinäkymään:

<img src="https://user-images.githubusercontent.com/46410240/81398114-45e0b200-9131-11ea-83c1-9cfc09d28d4a.png" alt="Pelinäkymä" width="400" >

Peliä pelataan klikkaamalla vaaleansinisiä neliöitä. Uuden pelin saa klikkaamalla New Game -nappia. Ylimpänä näkyvän pelitilaston saa nollattua Reset-nappulalla. Ohjelma suljetaan napista Quit Game.

Peliruudukon kokoa voidaan vaihdella 3x3, 4x4 ja 5x5 ruudukoiden välillä klikkaamalla alhaalla oikealla olevia nappuloita.

Tietokannan voi vaihtaa alahaalla vasemmalla kirjoittamalla uuden tietokannan nimen ja klikkaamalla GO. Näin kukin pelidata tallentuu omaan tietokantaansa. Tietokannan nimeksi voi kukin eri pelaaja laittaa vaikka oman nimensä, jolloin tietokanta on käytännössä henkilökohtainen pelitili.

## Pelin ratkeaminen

Peli päättyy joko toisen merkkisymbolin voittoon tai tasapeliin. Ohjelma ilmoittaa tilanteen ruudukon yläpuolella. Pistetilanne päivittyy luomalla uuden pelin napista New Game.

<img src="https://user-images.githubusercontent.com/46410240/81398184-63ae1700-9131-11ea-9ea0-83f5df944a39.png" alt="Pelinäkymä: voitto" width="400" >

## Tietokannan vaihtaminen

Peli-ikkunan alhaalla vasemmalla olevaan tekstilaatikkoon voi syöttää uuden tietokannan nimen. Oletus on yllä mainittu scoreData, mikäli tekstilaatikko on tyhjä tai käyttäjä syöttää virheellisen tiedostonimen/-polun.
Uuden tietokannan nimi voi olla sellaisenaan, esim. "uusitietokanta", jolloin ohjelma luo sellaisen tietokannan työkansioon, mikäli sellaista ei jo ole olemassa. Jos tietokanta on jo olemassa, avaa ohjelma siihen yhteyden.
Uusi tietokanta syntyy työkansioon myös kirjoittamalla "./uusitietokanta". Mikäli halutaan luoda tietokanta muualle, tulee kohde kirjoittaa esim. muodossa

```
../../kansio/tietokannat/uusitietokanta
```
jolloin uusi tietokanta "uusitietokanta" sijoittuu kaksi kansiota ylös kansion "kansio" alikansioon "tietokannat" kuten alla olevasta kuvasta käy ilmi.

<img src="https://user-images.githubusercontent.com/46410240/78976584-200ac200-7b1f-11ea-9c0f-7811514d8176.png" alt="Kansiot: uusi tietokanta" width="450" >

Jokainen "../" vie polulla yhden kansion ylöspäin. Seuraavien kuvien osoittama tietokannan luominen on identtinen: uusi tietokanta syntyy samaan paikkaan eli nykyiseen työkansioon.

<img src="https://user-images.githubusercontent.com/46410240/78976933-e2f2ff80-7b1f-11ea-9d1a-9a792ded2b56.png" alt="Pelinäkymä: uusi tietokanta" width="450" >

<img src="https://user-images.githubusercontent.com/46410240/78976938-e4bcc300-7b1f-11ea-8a6e-434bf396ef52.png" alt="Pelinäkymä: uusi tietokanta" width="450" >

Mikäli käyttäjä kirjoittaa erehdyksessä esimerkiksi ".uusitietokanta", luo ohjelma tietokannan juuri tällä nimellä (".uusitietokanta") työkansioon. Mikäli käyttäjä taas kirjoittaa esimerkiksi "/uusitietokanta", luo ohjelma oikeaoppisesti tietokannan nimellä "uusitietokanta" työkansioon samoin kuin pelkällä nimellä. Ellei käyttäjä nimenomaan halua luoda tietokantaa eri paikkaan kuin työkansioon, kannattaa käyttää pelkkää nimeä ilman pisteitä ja kauttaviivoja.
