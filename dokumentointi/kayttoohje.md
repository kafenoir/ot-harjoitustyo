# Käyttöohje
Lataa tiedosto todoapp.jar
##Konfigurointi
Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto config.properties, joka määrittelee käyttäjät ja omat listat tallentavien tiedostojen
sekä sanakirja sisältävän tiedoston nimet. Tiedoston muoto on seuraava
>userFile=users.txt
dictionaryFile=dictionary.txt
myListFile=mylist.txt

##Ohjelman käynnistäminen
Ohjelma käynnistetään komennolla
>java -jar hanzipractice-0.2.jar

##Aloitus
Sovellus käynnistyy aloitusnäkymään, jossa on mahdollisuus kirjautua, luoda uusi käyttäjä tai poistua sovelluksesta.

##Kirjautuminen
Valitessa login, ohjelma kysyy käyttäjänimeä. Jos käyttäjänimi löytyy, ohjelma siirtyy päävalikkonäkymään.

##Uuden käyttäjän luominen
Valitessa Create New User, ohjelma kysyy ensin käyttäjänimeä ja sitten käyttäjän oikeaa nimeä. Kun nämä on syötetty onnistuneesti, ohjelma siirtyy kirjautumisnäkymään. 

