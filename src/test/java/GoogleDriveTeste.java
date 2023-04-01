import automacao.Planilha;
import exceptions.ArquivoException;
import org.junit.Assert;
import org.junit.Test;
import util.GoogleDriveUtil;

import java.util.List;

public class GoogleDriveTeste {

    private static final String googleDrivePathPendentes = "G:\\Meu Drive\\Sistemato\\Demo\\AutomacaoWhatsapp\\Pendentes";
    private static final boolean recuperarValoresComoTexto = true;

    @Test
    public void testeRecuperarPendencias() throws ArquivoException {
        List<Planilha> planilhas = GoogleDriveUtil.recuperarPendencias(googleDrivePathPendentes, recuperarValoresComoTexto);
        Assert.assertNotNull(planilhas);
        Assert.assertFalse(planilhas.isEmpty());
    }
}
