package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }    
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(200);
        assertEquals("saldo: 2.10", kortti.toString());
    }    
    
    @Test
    public void saldoVaheneeJosRahaaOnTarpeeksi() {
        kortti.otaRahaa(10);
        assertEquals("saldo: 0.0", kortti.toString());
    }    

    @Test
    public void saldoEiMuutuJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(11);
        assertEquals("saldo: 0.10", kortti.toString());
    }    

    @Test
    public void rahanOttaminenPalauttaaTrueJosRiitti() {
        Boolean totuus = kortti.otaRahaa(1);
        assertEquals("true", totuus.toString());
    }    

    @Test
    public void rahanOttaminenPalauttaaFalseJosEiRiittanyt() {
        Boolean totuus = kortti.otaRahaa(11);
        assertEquals("false", totuus.toString());
    }    
    
}
