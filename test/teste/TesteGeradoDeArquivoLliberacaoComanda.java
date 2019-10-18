package teste;

import comandacatraca.FrmConfiguraCaminho;
import comandacatraca.SystemTrayComanda;

public class TesteGeradoDeArquivoLliberacaoComanda {

    public static void main(String[] args) {

        SystemTrayComanda systemTrayComanda = new SystemTrayComanda();
        systemTrayComanda.initSystemTray(new FrmConfiguraCaminho());

    }
}
