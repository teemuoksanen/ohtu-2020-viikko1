package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void liikaaLisaaminenTayttaaVaraston() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(10);

        // varaston pitäisi olla täynnä
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liikaaOttaminenTyhjentaaVaraston() {
        varasto.lisaaVarastoon(5);
        double saatuMaara = varasto.otaVarastosta(10);

        // varaston pitäisi olla tyhjä ja saatuMaara 5
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(5, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaaminenEiVaikutaVarastoon() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-1);

        // varaston pitäisi pysyä 5:ssä
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttaminenPalauttaaNollan() {
        varasto.lisaaVarastoon(5);
        double saatuMaara = varasto.otaVarastosta(-1);

        // saatuMaara pitää olla 0
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void negatiivinenTilavuusLuoEpakelvonVaraston() {
        varasto = new Varasto(-1);
        // Tilavuus pitää olla 0
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);

        varasto = new Varasto(-1, 1);
        // Tilavuus pitää olla 0
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenAlkusaldoLuoTyhjanVaraston() {
        varasto = new Varasto(10, -1);
        // Saldon pitää olla 0
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuusJaSaldo() {
        varasto = new Varasto(10, 1);

        // Tilavuus pitää olla 0 ja saldo 1
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void merkkijonopalautusToimii() {
        varasto = new Varasto(10, 2);

        // Palauttaa saldon 2 ja tilaa 8
        assertEquals("saldo = 2.0, vielä tilaa 8.0", varasto.toString());
    }

}