
package teste;

import comandacatraca.GeradoDeArquivoXMLCatraca;


public class TesteConexaoBancoFirebird {
    public static void main(String[] args) {
                     
        GeradoDeArquivoXMLCatraca initGeradoDeArquivoXMLCatraca = GeradoDeArquivoXMLCatraca.initGeradoDeArquivoXMLCatraca();
        initGeradoDeArquivoXMLCatraca.criarArquivoXML("0185", "C");
    }
}
