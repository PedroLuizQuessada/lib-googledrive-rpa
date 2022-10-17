package exceptions;

import java.io.File;

public class MoverPendenciaException extends Exception {
    public MoverPendenciaException(File arquivo) {
        super(String.format("Falha ao mover pendência do arquivo %s", arquivo.getAbsolutePath()));
    }
}
