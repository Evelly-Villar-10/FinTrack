
import com.bianca.fintrack.dao.TransacaoDAO;
import com.bianca.fintrack.database.Conexao;
import com.bianca.fintrack.model.Transacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author bianca
 */
public class TransacaoDAOTest {

    private Connection conectar;
    private TransacaoDAO dao;

    @BeforeEach
    void configurarBanco() throws SQLException {
        conectar = Conexao.conectarMemoria();

        String sql = """
        CREATE TABLE transacao (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            eh_receita BOOLEAN,
            valor REAL,
            descricao TEXT,
            data DATE,
            usuario_id INTEGER
        )
        """;

        PreparedStatement stmt = conectar.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();

        dao = new TransacaoDAO(conectar);
    }

    @Test
    void adicionarTransacao() throws SQLException {
        Transacao transacao = new Transacao(true, 800, "bolsa", LocalDate.of(2026, 10, 8), 1);
        dao.adicionar(transacao);
        List<Transacao> transacoes = dao.listar(1);
        System.out.println("Quantidade: " + transacoes.size());
        Assertions.assertEquals(1, transacoes.size());
    }

    @Test
    void buscaPorIdTransacao() throws SQLException {
        Transacao transacao = new Transacao(true, 800, "bolsa", LocalDate.of(2026, 10, 8), 1);
        dao.adicionar(transacao);
        List<Transacao> transacoes = dao.listar(1);
        int id = transacoes.get(0).getId();
        Transacao resultado = dao.buscarPorId(id);
        Assertions.assertNotNull(resultado);
    }

    @Test
    void atualizarTransacao() throws SQLException {
        Transacao transacao = new Transacao(true, 800, "bolsa", LocalDate.of(2026, 10, 8), 1);
        dao.adicionar(transacao);

        List<Transacao> transacoes = dao.listar(1);
        int id = transacoes.get(0).getId();

        transacao.setId(id);
        transacao.setEhReceita(false);
        transacao.setValor(80);
        transacao.setDescricao("limpeza");
        transacao.setData(LocalDate.of(2026, 10, 9));
        transacao.setUsuarioId(1);

        dao.atualizar(transacao);

        Transacao resultado = dao.buscarPorId(id);

        Assertions.assertFalse(resultado.isEhReceita());
        Assertions.assertEquals(80, resultado.getValor());
        Assertions.assertEquals("limpeza", resultado.getDescricao());
        Assertions.assertEquals(LocalDate.of(2026, 10, 9), resultado.getData());
        Assertions.assertEquals(1, resultado.getUsuarioId());
    }

    @Test
    void deletarTransacao() throws SQLException {
        Transacao transacao = new Transacao(true, 800, "bolsa", LocalDate.of(2026, 10, 8), 1);
        dao.adicionar(transacao);
        List<Transacao> transacoes = dao.listar(1);
        int id = transacoes.get(0).getId();
        dao.deletar(id, 1);
        Transacao resultado = dao.buscarPorId(id);
        Assertions.assertNull(resultado);
    }

    @AfterEach
    void fecharBanco() throws SQLException {
        conectar.close();
    }
}
