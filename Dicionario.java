import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Dicionario {
    private String idiomaAtual;
    private ArrayList<String> palavrasIdioma = new ArrayList<>();
    private ArrayList<String> palavrasPortugues = new ArrayList<>();
    private ArrayList<String> idiomasDisponiveis;

    
    public Dicionario(String idioma) throws Exception {
        idiomasDisponiveis = new ArrayList<>();
        idiomasDisponiveis.add("ingles");
        idiomasDisponiveis.add("espanhol");
        idiomasDisponiveis.add("alemao");

        
        if (!idiomasDisponiveis.contains(idioma)) {
            throw new Exception("Idioma não suportado.");
        }

        this.idiomaAtual = idioma;
        carregarDicionario(idioma);
    }

    
    private void carregarDicionario(String idioma) throws Exception {
        String nomeArquivo = idioma + ".csv";
        File arquivo = new File(nomeArquivo);

        try (Scanner scanner = new Scanner(arquivo)) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(";");
                String palavraIdioma = partes[0].toLowerCase();
                String palavraPortugues = partes[1].toLowerCase();

                palavrasIdioma.add(palavraIdioma);
                palavrasPortugues.add(palavraPortugues);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao carregar o dicionário: " + e.getMessage());
        }
    }

    
    public ArrayList<String> traduzirParaPortugues(String termo) {
        termo = termo.toLowerCase();
        ArrayList<String> traducoes = new ArrayList<>();

        for (int i = 0; i < palavrasIdioma.size(); i++) {
            if (palavrasIdioma.get(i).equals(termo)) {
                traducoes.add(palavrasPortugues.get(i));
            }
        }
        return traducoes;
    }

    public ArrayList<String> traduzirParaIdioma(String termo) {
        termo = termo.toLowerCase();
        ArrayList<String> traducoes = new ArrayList<>();

        for (int i = 0; i < palavrasPortugues.size(); i++) {
            if (palavrasPortugues.get(i).equals(termo)) {
                traducoes.add(palavrasIdioma.get(i));
            }
        }
        return traducoes;
    }

    public ArrayList<String> localizarPalavraIdioma(String termo) {
        termo = termo.toLowerCase();
        ArrayList<String> palavrasexistentes = new ArrayList<>();

        for (String palavra : palavrasIdioma) {
            if (palavra.contains(termo)) {
                palavrasexistentes.add(palavra);
            }
        }
        return palavrasexistentes;
    }

    public ArrayList<String> localizarPalavraPortugues(String termo) {
        termo = termo.toLowerCase();
        ArrayList<String> palavrasexistentes = new ArrayList<>();

        for (String palavra : palavrasPortugues) {
            if (palavra.contains(termo)) {
                palavrasexistentes.add(palavra);
            }
        }
        return palavrasexistentes;
    }

    public void setIdioma(String idioma) throws Exception {
        if (!idiomasDisponiveis.contains(idioma)) {
            throw new Exception("Idioma não suportado.");
        }
        this.idiomaAtual = idioma;
        this.palavrasIdioma.clear();
        this.palavrasPortugues.clear();
        carregarDicionario(idioma);
    }
}