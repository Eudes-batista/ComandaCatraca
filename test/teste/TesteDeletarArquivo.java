package teste;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TesteDeletarArquivo {

    public static void main(String[] args) throws InterruptedException, IOException {

        while (true) {
            Thread.sleep(5000);
            Files.deleteIfExists(Paths.get("C:\\Acesso\\Sigma\\IMPORTESECULLUM.xml"));
        }
    }

}
