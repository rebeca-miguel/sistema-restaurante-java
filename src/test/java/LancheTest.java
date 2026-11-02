import org.example.cardapio.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LancheTest {

    //testar recusa de preço negativo
    @Test
    void  deveRecusarPrecoNegativo(){
        assertThrows(IllegalArgumentException.class,
                () -> new Lanche("X-Teste", -1.00));
    }

    //testar verificação do preço

    @Test
    void xBaconComCheddarCusta26Reais() {
        ItemCardapio lanche = new ComCheddar(LancheFactory.xBacon());

        assertEquals(26.00, lanche.getPreco(), 0.001);
        assertEquals("X-Bacon + cheddar", lanche.getDescricao());
    }

    //testar o valor total do lanche
    @Test
    void xBaconComCheddarEBaconExtraCusta32Reais() {
        ItemCardapio lanche = new ComBaconExtra(
                new ComCheddar(LancheFactory.xBacon())
        );

        assertEquals(32.00, lanche.getPreco(), 0.001);
    }

    //testar nome vazio
    @Test
    void deveRecusarNomeVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Lanche("", 10.00));
    }
    // Testar nome nullo
    @Test
    void deveRecusarNomeNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Lanche(null, 10.00));
    }
}
