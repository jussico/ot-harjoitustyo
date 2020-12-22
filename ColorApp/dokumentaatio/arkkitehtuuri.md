# Arkkitehtuurikuvaus

## Luokkarakenne
Luokkien pakkausrakenne on seuraava

![Kaavio](pakkaus_luokkakaavio.png?raw=true "Pakkaus/Luokkakaavio")


### colorapp
``Main`` sisältää ainoastaan ohjelman entrypointin jossa käynnisteteen varsinaine pääluokka ColorApp.

### colorapp.ui
``ColorApp`` on ohjelman varsinain JavaFX -pääluokka jossa luodaan instassit kaikista tarvittavista apuluokista, luokaan päänäkymä ja käynnistetään ohjelma.  

``SceneBuilder`` on yliluokka MainSceneBuilder ja QuestLoopSceneBuilder-luokille.

``MainSceneBuilder`` sisältää pääkäyttöliittymän rakentamiseen liittyvät koodit.

``QuestLoopSceneBuilder`` sisältää Seikkailunäkymän käyttöliittymän rakentamiseen liittyvät koodit.

``QuizLoop`` sisältää seikkailuloopin koodit

### colorapp.audio

``AudioPlayer`` on rajapinta luokka musiikin ja ääniefektien soittamiseen.

``MusicPlyer`` on taustalla ajattava musiikkisoitin joka vaihtelee musiikkia asetetun tyypin mukaan satunnaisesti.

``MusicType`` on enumeraatio eri tilojen musiikkityypeistä.

``SoundType`` on enumeraatio eri ääniefektityypeistä.

### colorapp.domain

``ColorAnalysis`` osaa analysoida kuvan ja palauttaa keskiarvo RGB-arvon.  

``RGB`` yhtä RGB-arvoa kuvaava luokka.


### colorapp.service

``FileService`` sisältää tekstitiedoston tallennuksen ja avaamisen.  

``ImageService`` osaa tarkistaa että kuvat ovat haettavissa ja hakea kuvat.

## Ohjelman rakenteeseen jääneet heikkoudet

Suhteettoman ison listan hallitsemista ei ole tehty vaan käyttäjän on vaan ymmärrettävä että liian iso lista ei varmaankaan toimi.

Värin syöttäminen on tehty hyvin alkeellisesti yhdellä input-boxilla. Tarkoitus oli käyttää jotain valmista komponenttia, millainen  
JavaFX-kirjastosta kyllä löytyykin mutta sen käyttö ei onnistunut suoraan vaan olisi ollut todella kömpelöä toisen värivalintadialogin takana.  Tämä hienostuneempi toiminnallisuus siis odottaa sitä että asiaan sopiva kirjasto ilmestyy jostain ehkä hamaan maailman loppuun asti.
