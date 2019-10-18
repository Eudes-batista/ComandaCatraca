package comandacatraca;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ComandaCatraca {

    public static void main(String[] args) {
        ConectaBanco conectaBanco = new ConectaBanco();
        FrmConfiguraCaminho frmConfiguracao = new FrmConfiguraCaminho();
        boolean conexao = conectaBanco.conexao();
        if (!conexao) {
            frmConfiguracao.setVisible(true);
            return;
        }
        if (validarSeArquivoDeConfiguraCaminhoExiste(frmConfiguracao)) {
            return;
        }
        SystemTrayComanda systemTrayComanda = new SystemTrayComanda();
        systemTrayComanda.initSystemTray(frmConfiguracao);
    }

    private static boolean validarSeArquivoDeConfiguraCaminhoExiste(FrmConfiguraCaminho frmConfiguracao) {
        File file = new File("config.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
                frmConfiguracao.setVisible(true);
                return true;
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return false;
    }

}
