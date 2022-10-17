package exceptions;

public class ArquivoException extends Exception {
    public ArquivoException(String path) {
        super(String.format("Falha ao processar o arquivo %s", path));
    }
}
