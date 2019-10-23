package comandacatraca;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ControleComandaExcluida {

    private final ConectaBanco conecta;
    private final List<String> comandas;
    private static ControleComandaExcluida controleComandaExcluida = null;

    private ControleComandaExcluida() {
        this.conecta = new ConectaBanco();
        this.comandas = new ArrayList<>();
    }

    public List<String> listarComandasExcluidas() throws SQLException {
        this.comandas.clear();
        if (!this.conecta.conexao()) {
            return comandas;
        }
        if (!this.conecta.executaSQL("select * from COMANDAS_EXCLUIDAS order by comanda")) {
            return this.comandas;
        }
        if (!this.conecta.getRs().first()) {
            return this.comandas;
        }
        do {
            this.comandas.add(this.conecta.getRs().getString(1));
        } while (this.conecta.getRs().next());
        this.conecta.desconecta();
        return this.comandas;
    }

    public void excluirComanda(String comanda) {
        if (!this.conecta.conexao()) {
            JOptionPane.showMessageDialog(null, "Erro ao inciar a comunicação com o banco de dados do servidor.\n Verifique se tem comunicação de rede ou se o servidor está ligado.");
            return;
        }
        try (PreparedStatement pst = this.conecta.getConn().prepareStatement("delete from COMANDAS_EXCLUIDAS where comanda ='" + comanda + "'")) {
             pst.execute();
            this.conecta.getConn().commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar comanda no banco de dados do servidor.\n Verifique se tem comunicação de rede ou se o servidor está ligado.");
        }
        this.conecta.desconecta();
    }

    public static ControleComandaExcluida getControleComandaExcluida() {
        if (controleComandaExcluida == null) {
            controleComandaExcluida = new ControleComandaExcluida();
        }
        return controleComandaExcluida;
    }

}
