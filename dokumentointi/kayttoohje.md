# Käyttöohje
Lataa tiedostot hanzipractice-final.jar, config.properties ja dictionary.txt.
## Konfigurointi
Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto config.properties, joka määrittelee käyttäjät ja omat listat tallentavien tiedostojen
sekä sanakirjan sisältävän tiedoston nimet. Tiedoston muoto on seuraava
>userFile=users.txt

>dictionaryFile=dictionary.txt

>myListFile=mylist.txt

Lisäksi ohjelma olettaa, että käynnistyshakemistossa on sanakirjatiedosto dictionary.txt.

## Ohjelman käynnistäminen
Ohjelma käynnistetään komennolla
>java -jar hanzipractice-final.jar

## Aloitus
Ohjelma käynnistyy aloitusnäkymään, jossa on mahdollisuus kirjautua, luoda uusi käyttäjä tai poistua sovelluksesta.

## Kirjautuminen
Kirjautuminen tapahtuu syöttämällä käyttäjänimi ja klikkaamalla "Login". Jos käyttäjänimi löytyy, ohjelma siirtyy päävalikkonäkymään.

## Uuden käyttäjän luominen
Valitsemalla "Create New User", ohjelma kysyy käyttäjänimeä ja käyttäjän oikeaa nimeä. Kummankin pituus tulee olla enemmän kuin yksi merkki. Kun nämä on syötetty onnistuneesti, ohjelma palaa aloitusnäkymään. 

## Päävalikko

Päävalikossa on valittavissa seuraavat vaihtoehdot:
* Practice!
* My Word List
* Logout

## Sanojen lisääminen ja poistaminen omalta listalta
Valitsemalla "My Word List" pääsee tarkastelemaan sanakirjan (vasemmalla) ja oman listan (oikealla) sisältöä. Sanakirjasta voi siirtää sanoja omaan listaan valitsemalla siitä sana ja klikkaamalla nappia "Add>>". Sanojen poistaminen omalta listalta tapahtuu valitsemalla listalta sana ja klikkaamalla nappia "Remove".

## Practice!
Valitsemalla päävalikosta "Practice!" pääse harjoitusvalikkoon, jossa voi valita pelityypin kahdesta vaihtoehdosta: Hanzi to Pinyin tai Hanzi to English. 

### Hanzi to Pinyin
Hanzi to Pinyin-pelimuodossa vastataan syöttämällä kysytty Hanzi-merkki pinyin-siirtokirjoituksella pienillä kirjaimilla. Tooni merkitään numerolla (1-4) pinyin-muodon loppuun. Jos tooni on neutraali, ei lisätä numeroa.

Esimerkki:
>Kysymys: 猫

>Odotettu vastaus: mao1


>Kysymys: 的

>Odotettu vastaus: de


>Kysymys: 没有

>Odotettu vastaus: mei2you3

### Hanzi to English
Hanzi to English-pelimuodossa vastataan syöttämällä kysytyn Hanzi-merkin englanninkielinen käännös ilman artikkelia ja pienillä kirjaimilla. 

Esimerkki:
>Kysymys: 猫

>Odotettu vastaus: cat

### Pelin kulku
Peli kysyy sattumanvaraisessa järjestyksessä kaikki omalle listalle lisätyt merkit. Jokaisen vastauksen jälkeen näytetään, menikö vastaus oikein. Kun kaikki merkit on kysytty, ohjelma näyttää harjoituksesta saadut pisteet.

## Uloskirjautuminen
Ohjelmasta voi kirjautua ulos valitsemalla päävalikosta "Logout". Ohjelma kirjaa käyttäjän ulos ja siirtyy takaisin aloitusnäkymään.
