package comandacatraca;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

public class ConectaBanco {

    private final String driver = "org.firebirdsql.jdbc.FBDriver";
    private Statement stm;
    private ResultSet rs;
    private Connection conn;
    private String caminho;

    public boolean conexao() {
        this.BuscarCaminho();
        String path = "jdbc:firebirdsql:" + this.caminho;
        try {
            Class.forName(driver);
            Properties props = new Properties();
            props.put("user", "SYSDBA");
            props.put("password", "masterkey");
            props.put("charset", "UTF8");
            props.put("lc_ctype", "ISO8859_1");
            conn = DriverManager.getConnection(path, props);
            conn.setAutoCommit(false);
            return true;
        } catch (ClassNotFoundException | SQLException ex) {           
            return false;
        }
    }

    public void desconecta() {

        try {
            if(this.stm != null){
                this.stm.close();
            }
            if(this.rs != null){
                this.rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public boolean executaSQL(String sql) {
        if (this.conn == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO");
            alert.setContentText("a conexão não foi realizada com o banco de dados");
            alert.show();
            return false;
        }
        try {
            this.stm = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            this.rs = this.stm.executeQuery(sql);
            return true;
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("AVISO");
            alert.setContentText("Erro ao consultar " + ex.getMessage());
            alert.show();
        }
        return false;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public ConectaBanco setCaminho(String caminho) {
        this.caminho = caminho;
        return this;
    }

    private void BuscarCaminho() {
        Path path = Paths.get("banco.txt");
        if (Files.exists(path)) {
            try {
                List<String> lista = Files.lines(path).collect(Collectors.toList());
                lista.forEach(string -> this.caminho = string);
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setContentText("Erro arquivo não encontrado" + ex.getMessage());
                alert.show();
            }
        }
    }

}
