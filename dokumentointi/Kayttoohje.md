# Käyttöohje

Lataa tiedosto [tictactoe.jar](https://github.com/gitjms/ot-harjoitustyo/blob/master/dokumentointi/Kayttoohje.md)

## Konfigurointi

Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto config.properties, joka määrittelee pelipisteet tallentavan tietokannan nimen, polun, käyttäjän ja salasanan. Tiedoston muoto on seuraava

```
scoreData=scoreData
url=jdbc:h2:
user=sa
password=
```

Mikäli tiedosto katoaa, luo ohjelma sen oletussisältöineen uudestaan sovelluksen käynnistyttyä. Käyttäjä voi vaihtaa tietokantaa pelin käyttöjärjestelmässä ilman konfiguraatiotiedoston muuttamista.

# Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla

```
java -jar tictactoe.jar
```

## Ohjelman käyttäminen

Sovellus käynnistyy pelinäkymään:

<img src="https://user-images.githubusercontent.com/46410240/78933604-3d557700-7ab2-11ea-93c9-b1807f589581.png" alt="Pelinäkymä" width="450" >

Peliä pelataan klikkaamalla vaaleansinisiä neliöitä. Uuden pelin saa klikkaamalla New Game -nappia. Ylimpänä näkyvän pelitilaston saa nollattua Reset-nappulalla. Ohjelma suljetaan napista Quit Game.

Peliruudukon kokoa voidaan vaihdella 3x3, 4x4 ja 5x5 ruudukoiden välillä klikkaamalla alhaalla oikealla olevia nappuloita.

Tietokannan voi vaihtaa alahaalla vasemmalla kirjoittamalla uuden tietokannan nimen ja klikkaamalla GO. Näin kukin pelidata tallentuu omaan tietokantaansa.

## Pelin ratkeaminen

Peli päättyy joko toisen merkkisymbolin voittoon tai tasapeliin. Ohjelma ilmoittaa tilanteen ruudukon yläpuolella. Pistetilanne päivittyy luomalla uuden pelin.

<img src="https://user-images.githubusercontent.com/46410240/78933848-99200000-7ab2-11ea-9309-cb993cccc895.png" alt="Pelinäkymä: voitto" width="450" >

## Tietokannan vaihtaminen

Peli-ikkunan alhaalla vasemmalla olevaan tekstilaatikkoon voi syöttää uuden tietokannan nimen. Oletus on yllä mainittu scoreData, mikäli tekstilaatikko on tyhjä tai käyttäjä syöttää virheellisen tiedostonimen/-polun.
Uuden tietokannan nimi voi olla sellaisenaan, esim. "uusitietokanta", jolloin ohjelma luo sellaisen tietokannan työkansioon, mikäli sellaista ei jo ole olemassa. Jos tietokanta on jo olemassa, avaa ohjelma siihen yhteyden.
Uusi tietokanta syntyy työkansioon myös kirjoittamalla "./uusitietokanta" tai "/uusitietokanta". Mikäli halutaan luoda tietokanta muualle, tulee kohde kirjoittaa esim. muodossa

```
../../kansio/tietokannat/uusitietokanta"
```
jolloin uusi tietokanta "uusitietokanta" sijoittuu kaksi kansiota ylös kansion "kansio" alikansioon "tietokannat" kuten alla olevasta kuvasta käy ilmi.

<img src="https://user-images.githubusercontent.com/46410240/78975736-69f2a880-7b1d-11ea-8ea7-f654c004d877.png" alt="Kansiot: uusi tietokanta" width="400" >

Seuraavien kuvien osoittama tietokannan luominen on identtinen: uusi tietokanta syntyy samaa paikkaan, työkansioon.

<img src="https://user-images.githubusercontent.com/46410240/78934126-10ee2a80-7ab3-11ea-990f-6fc002d673f7.png" alt="Pelinäkymä: uusi tietokanta" width="450" >

<img src="https://user-images.githubusercontent.com/46410240/78934138-1481b180-7ab3-11ea-86f7-dcd437a091fb.png" alt="Pelinäkymä: uusi tietokanta" width="450" >

