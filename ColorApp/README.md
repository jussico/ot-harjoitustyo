# ColorApp

Sovelluksella käyttäjä voi arvoida mikä on kuvan väri. Ladataan kuva jostain ja koitetaan arvioida pystyykö käyttäjä sanomaan mikä on sen RGB-värikoodi.

## Dokumentaatio

TODO: [Käyttöohje](https://github.com/jussico/ot-harjoitustyo/blob/master/ColorApp/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/jussico/ot-harjoitustyo/blob/master/ColorApp/dokumentaatio/vaatimusmaarittely.md)

TODO: [Arkkitehtuurikuvaus](https://github.com/jussico/ot-harjoitustyo/ColorApp/blob/master/dokumentaatio/arkkitehtuuri.md)

TODO: [Testausdokumentti](https://github.com/jussico/ot-harjoitustyo/ColorApp/blob/master/dokumentaatio/testaus.md)

TODO: [Työaikakirjanpito](https://github.com/jussico/ot-harjoitustyo/ColorApp/blob/master/dokumentaatio/tuntikirjanpito.md)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _OtmTodoApp-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/mluukkai/OtmTodoApp/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
