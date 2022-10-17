package automacao;

import java.util.ArrayList;
import java.util.List;

public class Planilha {
    private String nome;
    private final List<List<String>> dados = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<List<String>> getDados() {
        return dados;
    }

    public void addDado(List<String> linha) {
        this.dados.add(linha);
    }
}
