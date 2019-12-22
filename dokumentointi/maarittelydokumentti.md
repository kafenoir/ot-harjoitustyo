# Vaatimusmäärittely
## Sovelluksen tarkoitus
Sovelluksen avulla käyttäjillä on mahdollista harjoitella kiinan kielen alkeita. Käyttäjän on mahdollista luoda valmiista sanakirjasta harjoittelua varten sanalista, jonka harjoittelu tapahtuu ohjelmassa toteutetulla flashcard-pelillä. Pelissä on kaksi eri pelityyppiä: Ensimmäisessä käyttäjä syöttää merkin pinyin-siirtokirjoitusmuodon ja toisessa englanninkielisen käännöksen. 

## Käyttäjät
Sovelluksella on ainoastaan yksi käyttäjärooli eli normaali käyttäjä.

## Käyttöliittymäluonnos

Sovellus koostuu kymmenestä eri näkymästä.

Se aukeaa kirjautumisnäkymään, josta on mahdollista siirtyä uuden käyttäjän luomisnäkymään tai onnistuneen kirjautumisen kautta käyttäjän päävalikkoon.

Päävalikosta voi siirtyä joko harjoituksen valintaan (Practice!) tai oman sanalistan muokkaukseen (My Word List). Valikosta voi myös kirjautua ulos (Logout").

Harjoituksen valinnasta (Practice Menu) voi siirtyä pinyin-peliin (Hanzi to Pinyin) tai käännöspeliin (Hanzi to English).

Pelin suorittamisen jälkeen näytetään pelaajan tulos ja annetaan mahdollisuus yrittää uudestaan tai siirtyä takaisin harjoitusvalikkoon.

(My Word List) avaa pelaajan oman listan, johon voi lisätä sanoja valitsemalla sanakirjasta sanan ja klikkaamalla (Add>>) tai poistaa sanan valitsemalla sen ja klikkaamalla (Remove).

Kaikista ikkunoista voi myös siirtyä napilla (Exit) edelliseen näkymään tai päävalikkoon. Päävalikosta pääsee aloitusnäkymään valitsemalla (Logout).

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista 

* käyttäjä voi luoda järjestelmään käyttäjätunnuksen :heavy_check_mark:
  * käyttäjätunnus on uniikki ja vähintään 2 merkkiä :heavy_check_mark:

* käyttäjä voi kirjautua järjestelmään
  * kirjautuminen onnistuu syöttäessä olemassaoleva käyttäjätunnus kirjautumiskenttään :heavy_check_mark:
* jos käyttäjää ei ole olemassa, ilmoittaa järjestelmä tästä :heavy_check_mark:

### Kirjautumisen jälkeen

* Päävalikko
  * käyttäjä voi siirtyä harjoitusvalikkoon (Practice!) :heavy_check_mark:
  * käyttäjä voi siirtyä omaan listaan (My List) :heavy_check_mark:
  * käyttäjä voi siirtyä sanakirjaa (Dictionary) :heavy_check_mark:
  * käyttäjä voi kirjautua ulos :heavy_check_mark:

* Harjoitusvalikko
  * käyttäjä voi valita harjoituksen kahdesta vaihtoehdosta :heavy_check_mark:
  * käyttäjä voi palata päävalikkoon :heavy_check_mark:

* Harjoitusnäkymä (Practice!)
  * käyttäjä näkee kysytyn kirjoitusmerkin :heavy_check_mark:
  * käyttäjä voi syöttää merkin pinyin-siirtokirjoitusmuodon (pinyin-harjoitus) :heavy_check_mark:
  * käyttäjä voi syöttää merkin englanninkielisen käännöksen (eng. harjoitus) :heavy_check_mark:
  * käyttäjä saa tietää, menikö vastaus oikein :heavy_check_mark:
    *jos meni, käyttäjä saa pisteen 

* Tulosnäkymä
  * harjoituksen päätyttyä käyttäjä näkee pisteensä :heavy_check_mark:
  * hän voi yrittää harjoitusta uudestaan tai siirtyä päävalikkoon :heavy_check_mark:

* Oma lista-näkymä (My List)
  * käyttäjä näkee sanakirjan :heavy_check_mark:
  * käyttäjä näkee oman sanalistansa, joka on alkuun tyhjä :heavy_check_mark:
  * käyttäjä voi lisätä sanoja sanakirjasta omalle listalle :heavy_check_mark:
  * käyttäjä voi poistaa sanan omalta listalta :heavy_check_mark:

* Käyttäjän oma lista tallennetaan aina muutosten yhteydessä ja se säilyy seuraavaa käyttökertaa varten :heavy_check_mark:

## Jatkokehitysideoita

* Mahdollisuus tallentaa useita sanalistoja saman käyttäjänimen alle ja parhaan saavutetun tuloksen näyttäminen kullekin listalle
* Enemmän statistiikkaa pelaajan suoriutumisesta harjoituksissa (esim. vastaukseen kulunut aika, vaikeimmat merkit harjoitusten perusteella)
* Oman listan merkkien kysymisfrekvenssin säätäminen
* Sanakirjan sanojen merkkaaminen opituiksi
* Käyttäjän mahdollisuus ladata omia sanakirjoja
* Uusia harjoituspelejä




 




