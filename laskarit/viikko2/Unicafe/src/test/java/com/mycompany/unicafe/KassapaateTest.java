package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKassaOlemassa() {
        assertTrue(kassa!=null);      
    }

    @Test
    public void kortinSaldoAlussaOikein() {
        int rahat = kassa.kassassaRahaa();
        int maukkaat = kassa.maukkaitaLounaitaMyyty();
        int edulliset = kassa.edullisiaLounaitaMyyty();
        assertEquals(100000, rahat);
        assertEquals(0, maukkaat+edulliset);
    }

    @Test
    public void kateisostoToimiiEdullisissaLounaissa() {
        assertEquals(60, kassa.syoEdullisesti(300));
        assertEquals(100240, kassa.kassassaRahaa());
        int edulliset = kassa.edullisiaLounaitaMyyty();
        assertEquals(1, edulliset);
    }

    @Test
    public void kateisostoToimiiMaukkaissaLounaissa() {
        assertEquals(100, kassa.syoMaukkaasti(500));
        assertEquals(100400, kassa.kassassaRahaa());
        int maukkaat = kassa.maukkaitaLounaitaMyyty();
        assertEquals(1, maukkaat);
    }

    @Test
    public void kateisostoEiToimiEdullisissaJosRahaaEiOleTarpeeksi() {
        int edulliset = kassa.edullisiaLounaitaMyyty();
        assertEquals(200, kassa.syoEdullisesti(200));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, edulliset);
    }

    @Test
    public void kateisostoEiToimiMaukkaissaJosRahaaEiOleTarpeeksi() {
        int maukkaat = kassa.maukkaitaLounaitaMyyty();
        assertEquals(200, kassa.syoMaukkaasti(200));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, maukkaat);
    }

    @Test
    public void korttiostoToimiiEdullisissaLounaissa() {
        kortti.lataaRahaa(2500);
        assertEquals(true, kassa.syoEdullisesti(kortti));
        assertEquals("saldo: 22.70", kortti.toString());
        int edulliset = kassa.edullisiaLounaitaMyyty();
        assertEquals(1, edulliset);
    }

    @Test
    public void korttiostoToimiiMaukkaissaLounaissa() {
        kortti.lataaRahaa(2500);
        assertEquals(true, kassa.syoMaukkaasti(kortti));
        assertEquals("saldo: 21.10", kortti.toString());
        int maukkaat = kassa.maukkaitaLounaitaMyyty();
        assertEquals(1, maukkaat);
    }

    @Test
    public void korttiostoEiToimiEdullisissaJosRahaaEiOleTarpeeksi() {
        assertEquals(false, kassa.syoEdullisesti(kortti));
        assertEquals("saldo: 0.10", kortti.toString());
        int edulliset = kassa.edullisiaLounaitaMyyty();
        assertEquals(0, edulliset);
    }

    @Test
    public void korttiostoEiToimiMaukkaissaJosRahaaEiOleTarpeeksi() {
        assertEquals(false, kassa.syoMaukkaasti(kortti));
        assertEquals("saldo: 0.10", kortti.toString());
        int maukkaat = kassa.maukkaitaLounaitaMyyty();
        assertEquals(0, maukkaat);
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kassa.lataaRahaaKortille(kortti, 2500);
        assertEquals("saldo: 25.10", kortti.toString());
        assertEquals(102500, kassa.kassassaRahaa());
    }

    @Test
    public void negatiivisenRahanLataaminenEiKasvataSaldoaOikein() {
        kassa.lataaRahaaKortille(kortti, -2500);
        assertEquals("saldo: 0.10", kortti.toString());
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
