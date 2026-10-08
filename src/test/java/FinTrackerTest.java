
import com.bianca.fintrack.controller.FinTracker;
import com.bianca.fintrack.dao.TransacaoDAO;
import com.bianca.fintrack.database.Conexao;
import com.bianca.fintrack.exceptions.EntradaInvalidaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class FinTrackerTest {

    @Test
    void verificarCalcularSaldoTotal() throws SQLException, EntradaInvalidaException {
        Connection conectar = Conexao.conectarMemoria();

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

        TransacaoDAO dao = new TransacaoDAO(conectar);
        FinTracker finTracker = new FinTracker(dao);

        finTracker.adicionarTransacao(true, 800, "bolsa alimentação", LocalDate.of(2026, 10, 8), 1);

        finTracker.adicionarTransacao(false, 200, "aluguel", LocalDate.of(2026, 10, 8), 1);

        double saldo = finTracker.calcularSaldoTotal(1);

        Assertions.assertEquals(600, saldo);

        conectar.close();
    }
}
