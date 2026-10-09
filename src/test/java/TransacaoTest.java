
import com.bianca.fintrack.model.Transacao;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author bianca
 */
public class TransacaoTest {

    /*criação de transações e verifica resultado esperado*/
    @Test
    void verificaTransacao() {
        Transacao transacao = new Transacao(true, 100, "salário", LocalDate.of(2026, 10, 8), 1);
        Assertions.assertTrue(transacao.isEhReceita());
        Assertions.assertEquals(100, transacao.getValor());
        Assertions.assertEquals("salário", transacao.getDescricao());
        Assertions.assertEquals(LocalDate.of(2026, 10, 8), transacao.getData());
        Assertions.assertEquals(1, transacao.getUsuarioId());
    }

    @Test
    void alterarTransacao() {
        Transacao transacao = new Transacao(true, 100, "salário", LocalDate.of(2026, 10, 8), 1);
        transacao.setEhReceita(false);
        transacao.setValor(20);
        transacao.setDescricao("limpeza");
        transacao.setData(LocalDate.of(2026, 10, 9));
        transacao.setUsuarioId(2);

        Assertions.assertFalse(transacao.isEhReceita());
        Assertions.assertEquals(20, transacao.getValor());
        Assertions.assertEquals("limpeza", transacao.getDescricao());
        Assertions.assertEquals(LocalDate.of(2026, 10, 9), transacao.getData());
        Assertions.assertEquals(2, transacao.getUsuarioId());
    }
}
