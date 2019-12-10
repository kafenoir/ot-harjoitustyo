# Käyttöohje
Lataa tiedosto hanzipractice-0.2.jar
## Konfigurointi
Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto config.properties, joka määrittelee käyttäjät ja omat listat tallentavien tiedostojen
sekä sanakirja sisältävän tiedoston nimet. Tiedoston muoto on seuraava
>userFile=users.txt

>dictionaryFile=dictionary.txt

>myListFile=mylist.txt

## Ohjelman käynnistäminen
Ohjelma käynnistetään komennolla
>java -jar hanzipractice-0.2.jar

## Aloitus
Ohjelma käynnistyy aloitusnäkymään, jossa on mahdollisuus kirjautua, luoda uusi käyttäjä tai poistua sovelluksesta.

## Kirjautuminen
Valitessa "Login", ohjelma kysyy käyttäjänimeä. Jos käyttäjänimi löytyy, ohjelma siirtyy päävalikkonäkymään.

## Uuden käyttäjän luominen
Valitessa "Create New User", ohjelma kysyy ensin käyttäjänimeä ja sitten käyttäjän oikeaa nimeä. Kun nämä on syötetty onnistuneesti, ohjelma siirtyy kirjautumisnäkymään. 

## Päävalikko

Päävalikossa on valittavissa seuraavat vaihtoehdot
* Practice!
* My Word List
* Dictionary
* Logout

## Sanojen lisääminen ja poistaminen omasta listasta
Valitsemalla "My Word List" pääsee tarkastelemaan oman listan sisältöä. Valitessa "add words to the dictionary", ohjelma siirtyy sanakirjaan, josta voi lisätä sanojan omaan listaan. Valitessa, "remove a word", ohjelma kysyy poistettavaa sanaa, jonka tunnuksen syöttämällä kyseinen sana poistuu listasta.

## Sanakirjan tarkastelu 
Sanakirjaa pääsee tarkastelemaan suoraan valitsemalla päävalikossa "Dictionary". Sanakirjasta voi lisätä sanoja omaan listaan syöttämällä sanan tunnuksen.

## Sanojen harjoittelu
Tämä osio on vielä työn alla.

## Uloskirjautuminen
Ohjelmasta voi kirjautua ulos valitsemalla päävalikosta "Logout". Ohjelma kirjaa käyttäjän ulos ja siirtyy takaisin aloitusnäkymään.
