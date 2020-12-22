# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjä voi ladata kuvia koneeltaan tai netistä ja yrittää arvioida mikä on kuvan keskiarvoinen RGB-väri. Kuvien Web-urlit tai paikallistiedoston polut tallennetaan listaan josta sitten arvuutellaan järjestyksessä värikoodeja. Sovelluksella on vain yksi käyttäjä. Lista on mahdollista ja ladata ja tallentaa paikalliselle kiintolevylle. 

## Käyttäjät

Sovelluksella on vain yksi käyttäjä.

## Käyttöliittymä

Sovellus koostuu listan muokkausnäkymästä ja arvuuttelutilasta jossa käydään läpi kaikki listan kuvat. Arvuuttelutilan looppi voidaan keskeyttää ja 
siirtyä takaisin listan editointitilaan.

### Listanäkymä / Päänäkymä

Päänäkymässä on ylhäällä valikko josta voi ladata / tallentaa listan ja poistua seikkailusta.  

Listaa voi muotata ja lisätä ja poistaa kuvien polkuja.
* Listaa pystyy muokkaamaan.
* Listassa olevia kuvia haetaan http/https/file -urlin perusteella.

'Tarkista ja hae kuvat' -napista varmistetaan että kaikki kuvat saadaan ladattua. Jos näin on alunperin disabloitu 'Aloita väriseikkailu'-nappi enabloidaan.

'Aloita väriseikkailu'-napista aloitetaan uusi seikkailu.

### Seikkailunäkymä

* Arvuutteluloopissa näytetään haetut kuvat ja koitetaan arvat niiden RGB-koodeja.
* Joka kuvan jälkeen tulee ilmoitus onnistumisprosentista.
* Kaikki kuvien jälkeen tule Game-over näkymä jossa kerrotaan lopullinen onnistumisprosentti.
* Pelissä on vaihtuva taustamusiikki erikseen alkumenuun, pelitilaan ja game-over tilaan.
* Onnistuneesta, kohtalaisesta ja huonosta arvauksesta tulee erilaiset ääniefektit.
* Hyvin onnistuneesta (yli 90%) kokonaistuloksesta saa kuulla voittomusiikin. Huonosta kokonaistuloksesta kuuluu häviäjän musiikki.

## Jatkokehitysideoita

- kuvan rajatun alueen tai yhden pixelin värin arviointi.
- useiden pelaajien ja nimien lisääminen.
- pelaaminen webissa muita vastaan.
- äänien soittaminen ja sävelkorkeuden arviointi.
