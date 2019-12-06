package comandacatraca;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class SystemTrayComanda {

    public void initSystemTray(FrmConfiguraCaminho frmConfigura) {
        try {
            final TrayIcon trayIcon;
            if (!java.awt.SystemTray.isSupported()) {
                JOptionPane.showMessageDialog(null, "Sistema não suporta SystemTray.");
                return;
            }
            ActionListener configurar = evt -> {
                frmConfigura.setSystemTrayInital(true);
                frmConfigura.setVisible(true);
            };
            ActionListener sair = evt -> System.exit(0);

            PopupMenu popu = new PopupMenu();

            MenuItem itemSair = new MenuItem("Sair");
            MenuItem itemConfigurar = new MenuItem("Configurar Banco");

            itemSair.addActionListener(sair);

            itemConfigurar.addActionListener(configurar);

            popu.add(itemConfigurar);
            popu.add(itemSair);

            java.awt.SystemTray systemTray = java.awt.SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage(this.getImagem());
            trayIcon = new TrayIcon(image, "Verificado de comandas", popu);
            trayIcon.setImageAutoSize(true);
            systemTray.add(trayIcon);
            Runnable runnable = () -> {
                while (true) {
                    try {
                        Thread.sleep(900);
                    } catch (InterruptedException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao pausa 900 milliseconds");
                    }
                    GeradoDeArquivoXMLCatraca geradoDeArquivoXMLCatraca = GeradoDeArquivoXMLCatraca.initGeradoDeArquivoXMLCatraca();
                    ControleComandaExcluida controleComandaExcluida = ControleComandaExcluida.getControleComandaExcluida();
                    try {
                        List<String> comandas = controleComandaExcluida.listarComandasExcluidas();
                        comandas.forEach(comanda -> {
                            boolean criarArquivoXML = geradoDeArquivoXMLCatraca.criarArquivoXML(comanda, "L");
                            if(criarArquivoXML){
                                controleComandaExcluida.excluirComanda(comanda);
                            }
                        });
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Não conseguiu listar comandas excluidas");
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        } catch (AWTException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private URL getImagem() {
        return getClass().getResource("verificar-comanda.png");
    }

}
