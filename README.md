# ColorApp

Sovelluksella käyttäjä voi arvoida mikä on kuvan väri. Ladataan kuvia jostain ja koitetaan arvioida pystyykö käyttäjä sanomaan mikä kuvan RGB-värikoodi.

## Releases

[Release Final](https://github.com/jussico/ot-harjoitustyo/releases/tag/1.0)

[Release Viikko6](https://github.com/jussico/ot-harjoitustyo/releases/tag/viikko6)

[Release Viikko5](https://github.com/jussico/ot-harjoitustyo/releases/tag/viikko5)

## Dokumentaatio

[Käyttöohje](https://github.com/jussico/ot-harjoitustyo/blob/master/ColorApp/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/jussico/ot-harjoitustyo/blob/master/ColorApp/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/jussico/ot-harjoitustyo/blob/master/ColorApp/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/jussico/ot-harjoitustyo/blob/master/ColorApp/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/jussico/ot-harjoitustyo/blob/master/ColorApp/dokumentaatio/tuntikirjanpito.md)

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

Tiedostoon [checkstyle.xml](https://github.com/jussico/ot-harjoitustyo/blob/master/ColorApp/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
