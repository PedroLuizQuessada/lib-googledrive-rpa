package exceptions;

public class GerarPlanilhaException extends Exception {
    public GerarPlanilhaException(String nomePlanilha) {
        super(String.format("Falha ao gerar planilha %s", nomePlanilha));
    }
}
