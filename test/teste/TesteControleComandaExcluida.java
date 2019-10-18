package teste;

import comandacatraca.ControleComandaExcluida;
import java.sql.SQLException;
import java.util.List;

public class TesteControleComandaExcluida {

    public static void main(String[] args) throws SQLException {

        ControleComandaExcluida controleComandaExcluida = ControleComandaExcluida.getControleComandaExcluida();

        List<String> listarComandasExcluidas = controleComandaExcluida.listarComandasExcluidas();
        listarComandasExcluidas.forEach(System.out::println);

    }
}
