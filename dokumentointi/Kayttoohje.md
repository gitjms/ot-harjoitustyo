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

Mikäli tiedosto katoaa, luo ohjelma sen oletussisältöineen. Käyttäjä voi vaihtaa tietokantaa pelin käyttöjärjestelmässä ilman konfiguraatiotiedoston muuttamista.

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

## Pelin ratkeaminen

Peli päättyy joko toisen merkkisymbolin voittoon tai tasapeliin. Ohjelma ilmoittaa tilanteen ruudukon yläpuolella. Pistetilanne päivittyy luomalla uuden pelin.

<img src="https://user-images.githubusercontent.com/46410240/78933848-99200000-7ab2-11ea-9309-cb993cccc895.png" alt="Pelinäkymä: voitto" width="450" >

## Tietokannan vaihtaminen

Peli-ikkunan alhaalla vasemmalla olevaan tekstilaatikkoon voi syöttää uuden tietokannan nimen. Oletus on yllä mainittu scoredata, mikäli tekstilaatikko on tyhjä tai käyttäjä syöttää virheellisen tiedostonimen/-polun.
Uuden tietokannan nimi voi olla sellaisenaan, esim. "uusitietokanta", jolloin ohjelma luo sellaisen tietokannan työkansioon, mikäli sellaista ei jo ole olemassa. Jos tietokanta on jo olemassa, avaa ohjelma siihen yhteyden.
Uusi tietokanta syntyy työkansioon myös kirjoittamalla "./uusitietokanta" tai "/uusitietokanta". Mikäli halutaan luoda tietokanta muualle, tulee kohde kirjoittaa esim. muodossa

```
../../kansio/tietokannat/uusitietokanta"
```
jolloin uusi tietokanta "uusitietokanta" sijoittuu kaksi kansiota ylös kansion "kansio" alikansioon "tietokannat".

<img src="https://user-images.githubusercontent.com/46410240/78934126-10ee2a80-7ab3-11ea-990f-6fc002d673f7.png" alt="Pelinäkymä: uusi tietokanta" width="450" >

<img src="https://user-images.githubusercontent.com/46410240/78934138-1481b180-7ab3-11ea-86f7-dcd437a091fb.png" alt="Pelinäkymä: uusi tietokanta" width="450" >
