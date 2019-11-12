
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kp;

    @Before
    public void setUp() {
        kp = new Kassapaate();
    }
    
    @Test
    public void uudessaKassassaRahaa1000e() {
        assertEquals(100000, kp.kassassaRahaa());
    }
    
    @Test
    public void edullisiaLounaitaMyytyAlussa0() {
        assertEquals(0, kp.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaitaLounaitaMyytyAlussa0() {
        assertEquals(0, kp.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanKateisostoToimiiKunMaksuRiittava() {
        kp.syoEdullisesti(300);
        assertEquals(100240, kp.kassassaRahaa());
    }
    
    @Test
    public void maukkaanLounaanKateisostoToimiiKunMaksuRiittava() {
        kp.syoMaukkaasti(500);
        assertEquals(100400, kp.kassassaRahaa());
    }
    
    @Test
    public void myytyjenEdullistenLounaidenMaaraKasvaaKunMaksuRiittava() {
        kp.syoEdullisesti(240);
        kp.syoEdullisesti(240);
        assertEquals(2, kp.edullisiaLounaitaMyyty());

    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraKasvaaKunMaksuRiittava() {
        kp.syoMaukkaasti(400);
        kp.syoMaukkaasti(400);
        assertEquals(2, kp.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void toimitaanOikeinJosRahaaEiRiittavastiEdulliseen() {
        kp.syoEdullisesti(235);
        assertEquals(100000, kp.kassassaRahaa());
        assertEquals(0, kp.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void toimitaanOikeinJosRahaaEiRiittavastiMaukkaaseen() {
        kp.syoMaukkaasti(395);
        assertEquals(100000, kp.kassassaRahaa());
        assertEquals(0, kp.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenKorttiostoToimiiKunRahaaRiittavasti() {
        
        Maksukortti kortti = new Maksukortti(240);
        assertEquals(kp.syoEdullisesti(kortti), true);
        assertEquals(0, kortti.saldo());
    }
    
     @Test
    public void maukasKorttiostoToimiiKunRahaaRiittavasti() {
        
        Maksukortti kortti = new Maksukortti(400);
        assertEquals(kp.syoMaukkaasti(kortti), true);
        assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void myytyjenEdullistenMaaraKasvaaKunKortillaRiittavasti() {
        Maksukortti kortti = new Maksukortti(500);
        kp.syoEdullisesti(kortti);
        kp.syoEdullisesti(kortti);
        assertEquals(2, kp.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myytyjenMaukkaidenMaaraKasvaaKunKortillaRiittavasti() {
        Maksukortti kortti = new Maksukortti(1000);
        kp.syoMaukkaasti(kortti);
        kp.syoMaukkaasti(kortti);
        assertEquals(2, kp.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void toimitaanOikeinKunRahaaEiKortillaRiittavastiEdulliseen() {
        Maksukortti kortti = new Maksukortti(235);
        assertEquals(kp.syoEdullisesti(kortti), false);
        assertEquals(235, kortti.saldo());
        assertEquals(0, kp.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void toimitaanOikeinKunRahaaEiKortillaRiittavastiMaukkaaseen() {
        Maksukortti kortti = new Maksukortti(395);
        assertEquals(kp.syoMaukkaasti(kortti), false);
        assertEquals(395, kortti.saldo());
        assertEquals(0, kp.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassanRahamaaraEiMuutuKortillaOstettaessa() {
        Maksukortti kortti = new Maksukortti(1000);
        kp.syoEdullisesti(kortti);
        kp.syoMaukkaasti(kortti);
        assertEquals(100000, kp.kassassaRahaa());
    }
    
    @Test
    public void kortinLataaminenKasvattaaOikeinKortinSaldoaJaKassaa() {
        Maksukortti kortti = new Maksukortti(100);
        kp.lataaRahaaKortille(kortti, 900);
        assertEquals(1000, kortti.saldo());
        assertEquals(100900, kp.kassassaRahaa());
        
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaSummaa() {
        Maksukortti kortti = new Maksukortti(100);
        kp.lataaRahaaKortille(kortti, -50);
        assertEquals(100, kortti.saldo());
        assertEquals(100000, kp.kassassaRahaa());
    }

}
