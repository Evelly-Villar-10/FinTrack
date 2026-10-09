
import com.bianca.fintrack.model.RepositorioGenerico;
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
public class RepositorioGenericoTest {

    /*criação de repositorios e de transações, depois adiciona/remove ela no repositorio e verifica o resultado esperado */
    @Test
    void verificarAdicionarRepositorio() {
        RepositorioGenerico<Transacao> repositoriotransacoes = new RepositorioGenerico<>();
        Transacao transacao1 = new Transacao(true, 500, "bico", LocalDate.of(2026, 10, 8), 1);
        repositoriotransacoes.adicionarRegistros(transacao1);
        Assertions.assertEquals(1, repositoriotransacoes.getRegistros().size());
    }

    @Test
    void verificarListarRepositorio() {
        RepositorioGenerico<Transacao> repositoriotransacoes = new RepositorioGenerico<>();
        Transacao transacao1 = new Transacao(true, 500, "bico", LocalDate.of(2026, 10, 8), 1);
        Transacao transacao2 = new Transacao(false, 30, "bebida", LocalDate.of(2026, 10, 8), 1);
        repositoriotransacoes.adicionarRegistros(transacao1);
        repositoriotransacoes.adicionarRegistros(transacao2);
        Assertions.assertEquals(2, repositoriotransacoes.getRegistros().size());
    }

    @Test
    void verificarRemoverRepositorio() {
        RepositorioGenerico<Transacao> repositoriotransacoes = new RepositorioGenerico<>();
        Transacao transacao1 = new Transacao(true, 500, "bico", LocalDate.of(2026, 10, 8), 1);
        repositoriotransacoes.adicionarRegistros(transacao1);
        repositoriotransacoes.removerRegistros(transacao1);
        Assertions.assertEquals(0, repositoriotransacoes.getRegistros().size());
    }
}
