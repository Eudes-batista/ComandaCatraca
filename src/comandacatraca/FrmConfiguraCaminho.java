package comandacatraca;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FrmConfiguraCaminho extends javax.swing.JFrame {

    private final File file = new File("config.txt");
    private final ConectaBanco conecta = new ConectaBanco();
    private boolean systemTrayInital = false;

    public FrmConfiguraCaminho() {
        initComponents();
        try {
            String caminho = this.buscarConfiguracao();
            this.jTextFieldCaminho.setText(caminho);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar informação do arquivo de configuração");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldCaminho = new javax.swing.JTextField();
        jButtonConfirma = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCaminhoBanco = new javax.swing.JTextField();
        jButtonBuscarBanco = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButtonBuscarBanco1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuração");

        jButtonConfirma.setText("Confirma");
        jButtonConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmaActionPerformed(evt);
            }
        });

        jLabel1.setText("Digite o caminho a onde irá ficar os arquivos gerados no servidor");

        jButtonBuscarBanco.setText("...");
        jButtonBuscarBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarBancoActionPerformed(evt);
            }
        });

        jLabel2.setText("Caminho do Banco de dados");

        jButtonBuscarBanco1.setText("...");
        jButtonBuscarBanco1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarBanco1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldCaminhoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonBuscarBanco))
                            .addComponent(jLabel2))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jButtonConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(157, 157, 157)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldCaminho, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonBuscarBanco1)))
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCaminhoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarBanco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCaminho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarBanco1))
                .addGap(18, 18, 18)
                .addComponent(jButtonConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(531, 236));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmaActionPerformed
        this.salvarInformacoes();
    }//GEN-LAST:event_jButtonConfirmaActionPerformed

    private void jButtonBuscarBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarBancoActionPerformed
        JFileChooser jFileChooser = new JFileChooser("C:\\OMEGA\\DBA\\");
        jFileChooser.setFileFilter(new FileNameExtensionFilter("*.gdb,*.fdb", "FDB", "GDB"));
        int retorno = jFileChooser.showOpenDialog(new JDialog());
        if (retorno == JFileChooser.APPROVE_OPTION) {
            String caminhoBanco = jFileChooser.getSelectedFile().getAbsolutePath();
            jTextFieldCaminhoBanco.setText("localhost:" + caminhoBanco);
        }
    }//GEN-LAST:event_jButtonBuscarBancoActionPerformed

    private void jButtonBuscarBanco1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarBanco1ActionPerformed
        JFileChooser jFileChooser = new JFileChooser("C:\\OMEGA\\DBA\\");
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int retorno = jFileChooser.showOpenDialog(new JDialog());
        if (retorno == JFileChooser.APPROVE_OPTION) {
            String caminho = jFileChooser.getSelectedFile().getAbsolutePath();
            jTextFieldCaminho.setText(caminho);
        }
    }//GEN-LAST:event_jButtonBuscarBanco1ActionPerformed

    private void salvarInformacoes() {
        File arquivoBanco = new File("banco.txt");
        criarArquivoBancoDeDados(arquivoBanco);
        this.escreverArquivo(arquivoBanco, this.jTextFieldCaminhoBanco.getText(), "Banco de dados");
        if (!this.conecta.conexao()) {
            JOptionPane.showMessageDialog(null, "Erro na comunicação com o banco de dados.");
            this.jTextFieldCaminhoBanco.requestFocus();
            return;
        }
        this.conecta.desconecta();
        File caminhoServidor = new File(this.jTextFieldCaminho.getText());
        if (criarArquivoConfiguracao(caminhoServidor)) {
            return;
        }
        this.escreverArquivo(this.file, this.jTextFieldCaminho.getText(), "Configurações");
        JOptionPane.showMessageDialog(null, "Salvo com sucesso!!");
        if (!this.systemTrayInital) {
            SystemTrayComanda systemTrayComanda = new SystemTrayComanda();
            systemTrayComanda.initSystemTray(this);
        }
        this.dispose();
    }

    private boolean criarArquivoConfiguracao(File caminhoServidor) throws HeadlessException {
        if (!caminhoServidor.exists()) {
            JOptionPane.showMessageDialog(null, "Caminho do servidor não existe.");
            return true;
        }
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao criar arquivo de configuração");
            }
        }
        return false;
    }

    private void criarArquivoBancoDeDados(File arquivoBanco) throws HeadlessException {
        if (!arquivoBanco.exists()) {
            try {
                arquivoBanco.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao criar arquivo de banco");
            }
        }
    }

    private void escreverArquivo(File file, String conteudo, String tipoArquivo) {
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(conteudo);
            pw.flush();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar no arquivo de " + tipoArquivo + " \n verifique se o arquivo foi criado\n ou se você tm permissão para escrever no arquivo");
        }
    }

    private String buscarConfiguracao() throws IOException {
        if (!file.exists()) {
            return "";
        }
        return Files.newBufferedReader(this.file.toPath()).readLine();
    }

    public void setSystemTrayInital(boolean systemTrayInital) {
        this.systemTrayInital = systemTrayInital;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new FrmConfiguraCaminho().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscarBanco;
    private javax.swing.JButton jButtonBuscarBanco1;
    private javax.swing.JButton jButtonConfirma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextFieldCaminho;
    private javax.swing.JTextField jTextFieldCaminhoBanco;
    // End of variables declaration//GEN-END:variables
}
