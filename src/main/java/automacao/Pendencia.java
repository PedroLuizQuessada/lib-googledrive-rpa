package automacao;

public abstract class Pendencia {
    private Planilha planilha;

    public Planilha getPlanilha() {
        return planilha;
    }

    public void setPlanilha(Planilha planilha) {
        this.planilha = planilha;
    }
}
