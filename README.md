# Hanzi Practice

Sovelluksen avulla käyttäjillä on mahdollista harjoitella kiinan kielen alkeita. Käyttäjän on mahdollista luoda valmiista vaihtoehdoista harjoittelua varten sanalista, jonka harjoittelu tapahtuu ohjelmassa toteutetulla flashcard-pelillä. Pelissä on kolme eri pelityyppiä: Ensimmäisessä käyttäjä syöttää merkin pinyin-asun, toisessa englanninkielisen käännöksen ja kolmannessa molemmat.

Sovellus on Helsingin Yliopiston Tietojenkäsittelytieteen kurssin Ohjelmistotekniikan menetelmät kurssityö. 

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/kafenoir/ot-harjoitustyo/blob/master/dokumentointi/maarittelydokumentti.md)

[Työaikakirjanpito](https://github.com/kafenoir/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

[Arkkitehtuuri](https://github.com/kafenoir/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[Käyttöohje](https://github.com/kafenoir/ot-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

>mvn test

Testikattavuusraportti luodaan komennolla

>mvn jacoco:report

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

### Checkstyle

Tiedoston [checkstyle.xml](https://github.com/kafenoir/ot-harjoitustyo/blob/master/hanzipractice/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

>mvn jxr:jxr checkstyle:checkstyle

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html

### Javadoc

Javadocin HTML-versio luodaan komennolla

>mvn javadoc:javadoc

### Suoritettavan JARin generointi

JAR luodaan komennolla

>mvn package

## Viikon 5 release

[Release](https://github.com/kafenoir/ot-harjoitustyo/releases/tag/viikko5)

## Viikon 6 release

[Release](https://github.com/kafenoir/ot-harjoitustyo/releases/tag/viikko6)

## Loppupalautus

[Release](https://github.com/kafenoir/ot-harjoitustyo/releases/tag/final)









