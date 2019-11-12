package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

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
        kortti.lataaRahaa(250);
        assertEquals("saldo: 2.60", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikeinJosRahaaTarpeeksi() {
        kortti.lataaRahaa(250);
        kortti.otaRahaa(210);
        //rahaa kortilla 50 senttiä
        kortti.otaRahaa(50);
        assertEquals("saldo: 0.0", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi() {
        kortti.lataaRahaa(250);
        kortti.otaRahaa(210);
        //rahaa kortilla 50 senttiä
        kortti.otaRahaa(60);
        assertEquals("saldo: 0.50", kortti.toString());
    }
    
    @Test
    public void otaRahaaPalauttaaOikeanTotuusarvon() {
        kortti.lataaRahaa(250);
        kortti.otaRahaa(210);
        //rahaa kortilla 50 senttiä
        assertEquals(kortti.otaRahaa(60), false);
        assertEquals(kortti.otaRahaa(50), true);
        
    }
    
    @Test
    public void toimiikoMuuttujanSaldoArvonPalautus() {
        assertEquals(10, kortti.saldo());
        kortti.lataaRahaa(15);
        assertEquals(25, kortti.saldo());
    }
}
