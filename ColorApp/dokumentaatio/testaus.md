# Testausdokumentti

Ohjelmaa on testattu koodatessa luokkakohtaisten main-metodien avulla sekä lopuksi tärkeimmille luokille tehtyjen Junit-testien avulla.

## Testien suoritus

Testit suoritetaan komennolla

```
mvn test
```

## Testikattavuusraportti

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

## Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/jussico/ot-harjoitustyo/blob/master/ColorApp/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
