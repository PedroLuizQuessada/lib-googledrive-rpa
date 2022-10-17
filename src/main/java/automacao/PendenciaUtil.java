package automacao;

import java.util.List;

public abstract class PendenciaUtil {
    public abstract <T extends Pendencia> List<T> planilhaToPendencias(Planilha planilha);
}
