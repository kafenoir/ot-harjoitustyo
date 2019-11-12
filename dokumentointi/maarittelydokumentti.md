# Vaatimusmäärittely
## Sovelluksen tarkoitus
Sovelluksen avulla käyttäjillä on mahdollista harjoitella kiinan kielen alkeita. Käyttäjän on mahdollista luoda valmiista vaihtoehdoista harjoittelua varten sanalista, jonka harjoittelu tapahtuu ohjelmassa toteutetulla flashcard-pelillä. Pelissä on kaksi eri pelityyppiä: Ensimmäisessä käyttäjä syöttää merkin pinyin-asun ja toisessa englanninkielisen käännöksen. 

## Käyttäjät
Sovelluksella on ainoastaan yksi käyttäjärooli eli normaali käyttäjä.

## Käyttöliittymäluonnos

Sovellus koostuu kymmenestä eri näkymästä.

Se aukeaa kirjautumisnäkymään, josta on mahdollista siirtyä uuden käyttäjän luomisnäkymään tai onnistuneen kirjautumisen kautta käyttäjän päävalikkoon.

Päävalikosta voi siirtyä joko harjoituksen valintaan (Practice!), omaan sanalistaan (My List) tai sanakirjaan (Dictionary). 

Harjoituksen valinnasta voi siirtyä Pinyin-peliin, käännöspeliin tai näiden yhdistelmään.

Pelin suorittamisen jälkeen näytetään pelaajan tulos ja annetaan mahdollisuus yrittää uudestaan tai siirtyä päävalikkoon.

"My List"-nappi avaa pelaajan oman listan, johon voi lisätä sanoja(Add...) tai poistaa sanan valitsemalla sen ja klikkaamalla "Remove".

"Add..."-nappi avaa sanakirja, josta voi lisätä sanan omaan listaan valitsemalla sen ja klikkaamalla "Add to My List".

Kaikista ikkunoista voi myös siirtyä napilla edelliseen näkymään tai päävalikkoon.

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista 

* käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  * käyttäjätunnus on uniikki ja vähintään 3 merkkiä

* käyttäjä voi kirjautua järjestelmään
  * kirjautuminen onnistuu syöttäessä olemassaoleva käyttäjätunnus kirjautumislomakkeelle
* jos käyttäjää ei ole olemassa, ilmoittaa järjestelmä tästä

### Kirjautumisen jälkeen

* Päävalikko
  * käyttäjä voi siirtyä harjoitusvalikkoon (Practice!)
  * käyttäjä voi siirtyä omaan listaan (My List)
  * käyttäjä voi siirtyä sanakirjaa (Dictionary)
  * käyttäjä voi kirjautua ulos

* Harjoitusvalikko
  * käyttäjä voi valita harjoituksen kolmesta vaihtoehdosta
  * käyttäjä voi palata päävalikkoon

* Harjoitusnäkymä (Practice!)
  * käyttäjä näkee kysytyn kirjoitusmerkin
  * käyttäjä voi syöttää antaa merkin pinyinin (Pinyin-harjoitus, yhdistelmäharjoitus)
  * käyttäjä voi syöttää merkin englanninkielisen käännöksen (eng. harjoitus, yhdistelmäharjoitus)
  * käyttäjä saa tietää, menikö vastaus oikein
    *jos meni, käyttäjä saa pisteen

* Tulosnäkymä
  * harjoituksen päätyttä käyttäjä näkee pisteensä
  * hän voi yrittää harjoitusta uudestaan tai siirtyä päävalikkoon

* Oma lista-näkymä (My List)
  * käyttäjä näkee oman sanalistansa, joka on alkuun tyhjä
  * käyttäjä voi lisätä sanan/sanoja, jolloin siirrytään valitsemaan sana/sanat sanakirjasta
  * käyttäjä voi poistaa sanan omasta listasta

* Sanakirjanäkymä (Dictionary)
  * Käyttäjä voi lisätä sanoja sanakirjasta omaan listaansa

* Käyttäjän uloskirjautuessa käyttäjän oma lista ja listaan liittyvä paras harjoituksen tulos tallennetaan seuraavaa käyttökertaa varten

## Jatkokehitysideoita

* Enemmän statistiikkaa pelaajan suoriutumisesta harjoituksissa (esim. vastaukseen kulunut aika, vaikeimmat merkit harjoitusten perusteella)
* Uusia harjoituspelejä
* Oman listan merkkien kysymisfrekvenssin säätäminen
* Sanakirjan sanojen merkkaaminen opituiksi
* Käyttäjän mahdollisuus ladata omia sanakirjoja




 




