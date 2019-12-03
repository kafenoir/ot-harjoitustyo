# Arkkitehtuurikuvaus
## Rakenne
Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:

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

jokainen näistä on toteutettu omana Scene-oliona. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen stageen. Käyttöliittymä on rakennettu ohjelmallisesti luokassa hanzipractice.ui.HPUi.

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta, se ainoastaan kutsuu sopivin parametrein sovelluslogiikan toteuttavan olion HPServicen metodeja.

##Sovelluslogiikka
Sovelluksen loogisen datamallin muodostavat luokat User, MyList, Word ja Game.

* User kuvaa käyttäjiä
* MyList kuvaa käyttäjän omaa sanalistaa
* Word kuvaa sanoja
* Game kuvaa harjoituspeliä


## Tietojen pysyväistallennus

## Päätoiminnallisuudet

