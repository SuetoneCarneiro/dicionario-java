public class Main {
    public static void main(String[] args) {
        try {
            Dicionario dicionario = new Dicionario("espanhol");

            System.out.println("Tradução de 'ordenador': " + dicionario.traduzirParaPortugues("ordenador"));
        

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}