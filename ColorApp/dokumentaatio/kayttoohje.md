# Käyttöohje

Lataa tiedosto [colorapp.jar](https://github.com/jussico/ot-harjoitustyo/releases/download/1.0/colorapp.jar)

## Ohjelman käynnistäminen

### valmiin jakelupaketin käynnistys
java -jar colorapp.jar

### kääntäminen ja käynnistys kehitystilassa

script *buildaa_kopioi_uuteen_hakemistoon_ja_aja.sh*
 päähakemistossa paketoi, kopioi uuteen hakemistoon ja ajaa ohjelman:

<code>
#!/bin/bash

kohde="testx_$(date +%s)"

mkdir -p "$kohde" && \
        mvn package && \
        cp target/ColorApp-1.0-SNAPSHOT-shaded.jar "${kohde}/" && \
        cd "$kohde" && \
        java -jar ColorApp-1.0-SNAPSHOT-shaded.jar
</code>

## Listanäkymä / Päänäkymä

### Kuvalista muokkaus

Ohjelman käynnistysnäkymässä on muokattava tekstikenttä johon kirjoitetaan yksi per rivi kuvien polkuja http, https, file -alkuisena. Näitä kuvia käytetään quizzin kuvina.

### Kuvien tarkistus
"Tarkista ja hae kuvat"-nappia painamalla tarkistetataan onnistuuko kuvien haku. Virheilmoituksella ilmoitetaan jos jonkun kuvan haku ei onnistu. Jos kaikkien kuvien haku onnistuu 'Aloita väriseikkailu'-nappi aktivoituu ja seikkailu voi alkaa.

## Seikkailunäkymä

'Aloita väriseikkailu'- nappia painamalla aloitetaan arvuuttelu joilloin näytetään listassa olevia kuvia ja koitetaan arvata mikä on kuvan värin keskiarvo.

* Arvuutteluloopissa näytetään haetut kuvat ja koitetaan arvat niiden RGB-koodeja kirjoittamalla heksadesimaalinen rgb-koodi sille varattuun kenttään.  
* Joka kuvan jälkeen tulee ilmoitus onnistumisprosentista.
* Kaikki kuvien jälkeen tule Game-over näkymä jossa kerrotaan lopullinen onnistumisprosentti.
* Pelissä on vaihtuva taustamusiikki erikseen alkumenuun, pelitilaan ja game-over tilaan.
* Onnistuneesta, kohtalaisesta ja huonosta arvauksesta tulee erilaiset ääniefektit.
* Hyvin onnistuneesta (yli 90%) kokonaistuloksesta saa kuulla voittomusiikin. Huonosta kokonaistuloksesta kuuluu häviäjän musiikki.

![Alkutila](main_screen.png?raw=true "Alkutila")

![Seikkailutila](quiz_screen.png?raw=true "Seikkailutila")
