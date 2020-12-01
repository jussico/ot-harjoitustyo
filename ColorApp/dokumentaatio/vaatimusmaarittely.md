# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjä voi ladata kuvan koneeltaan tai netistä ja yrittää arvioida mikä on kuvan keskiarvoinen RGB-väri. Myös yksittäisen värin arvausmahdollisuus ilman kuvan lataamista. Kuvien URLit / värikoodit tallennetaan listaan josta sitten arvuutellaan joko järjestyksessä tai satunnaisjärjestyksessä. Sovelluksella on vain yksi käyttäjä. Listoja ei ole mahdollista tallentaa erikseen muuten kuin että ne voidaan copy-pastata ohjelmaan/ohjelmasta.

## Käyttäjät

Alkuvaiheessa vain yksi käyttäjä. Mahdollisuus myöhemmin lisätä useampiä käyttäjärooleja pelin kirjanpidon ylläpitämiseksi.


## Käyttöliittymäluonnos

Sovellus koostuu listan muokkausnäkymästä ja arvuuttelutilasta. Arvuuttelutilan looppi keskeytyy siirryttäessä listan editointitilaan.

* Listaa pystyy muokkaamaan. TEHTY.
* Listassa olevia kuvia haetaan urlin perusteella. TEHTY. (https:// -alkuiset.)
* Näytetään haettu kuva käyttöliittymässä. TEHTY.

## Jatkokehitysideoita

- kuvan alueen tai yhden pixelin värin arviointi.
- useiden pelaajien ja nimien lisääminen.
- äänien sävelkorkeuden arviointi.
