# Arkkitehtuurikuvaus
## Rakenne
Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava

![pakkausrakenne](https://github.com/kafenoir/ot-harjoitustyo/blob/master/dokumentointi/kuvat/pakkausrakenne.png)

Pakkaus hanzipractice.ui sisältää JavaFX:llä toteutetun käyttöliittymä, hanzipractice.domain sovelluslogiikan ja hanzipractice.dao tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä
Käyttöliittymä sisältää 8 erilaista näkymää
* kirjautuminen
* uuden käyttäjän luominen
* päävalikko
* harjoituksen valinta
* oma lista
* sanakirja
* harjoitus

jokainen näistä on toteutettu omana Scene-oliona. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen stageen. Käyttöliittymä on rakennettu ohjelmallisesti luokassa hanzipractice.ui.GUI.

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta, se ainoastaan kutsuu sopivin parametrein sovelluslogiikan toteuttavan olion HPServicen metodeja.

## Sovelluslogiikka
Sovelluksen loogisen datamallin muodostavat luokat User, MyList, Word ja Practice.

* User kuvaa käyttäjiä
* MyList kuvaa käyttäjän omaa sanalistaa
* Word kuvaa sanoja
* Practice kuvaa harjoituspeliä

Ohjelman osien suhdetta kuvaava luokka/pakkauskaavio:

![luokkakaavio](https://github.com/kafenoir/ot-harjoitustyo/blob/master/dokumentointi/kuvat/luokkakaavio.png)

## Tietojen pysyväistallennus

Sovelluksen juureen sijoitettu konfiguraatiotiedosto config.properties määrittelee tiedostojen nimet.

Sovellus tallettaa käyttäjät seuraavassa formaatissa
>kafenoir;Antti O

>dogood;Benjamin Franklin

Sovellus tallettaa omat listat seuraavassa formaatissa
>1;一;yi1;one

>2;二;er4;two

Kentät on eroteltu puolipistein. Ensimmäisenä sanan tunniste id, toisena hanzi-merkki, kolmantena pinyin(tooni merkitty numerolla) ja viimeisenä englanninkielinen käännös.

## Päätoiminnallisuudet

![kirjautuminen](https://github.com/kafenoir/ot-harjoitustyo/blob/master/dokumentointi/kuvat/kirjautuminen.png)

![uudenkayttajanluominen](https://github.com/kafenoir/ot-harjoitustyo/blob/master/dokumentointi/kuvat/kayttajanluominen.png)

