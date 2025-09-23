package jogo;

public class Jogo {

    // A classe agora tem uma dependência que será injetada
    private final Jogador jogador;
    private final Dado dadinho1;
    private final Dado dadinho2;

    // O construtor recebe as dependências em vez de criá-las
    public Jogo(Jogador jogador, Dado dado1, Dado dado2) {
        this.jogador = jogador;
        this.dadinho1 = dado1;
        this.dadinho2 = dado2;
    }

    /*
     * Se for o primeiro turno e a soma das faces dos dados cair 7 ou 11 você ganha o jogo;
     * Se for o primeiro turno e a soma das faces dos dados cair 2, 3 e 12 você perde o jogo;
     * Se não cair nenhum desses valores, o valor é armazenado e passa para o segundo turno;
     * Se for o segundo turno e a soma das faces dos dados cair 7 novamente, você perde o jogo;
     * Se for o segundo turno, você continuando jogando os dados e só ganha se cair um numero igual ao anterior do primeiro turno;
     */
    public boolean jogo() {

        // As dependências agora são usadas a partir dos atributos da classe
        int resultado = jogador.lancar(dadinho1, dadinho2);

        if (resultado == 7 || resultado == 11) {
            return true; // Ganha na primeira rodada
        } else if (resultado == 2 || resultado == 3 || resultado == 12) {
            return false; // Perde na primeira rodada
        }

        int ponto = resultado;

        // Loop para as próximas rodadas
        while (true) {
            resultado = jogador.lancar(dadinho1, dadinho2);
            if (resultado == ponto) {
                return true; // Ganha ao atingir o "ponto"
            }
            if (resultado == 7) {
                return false; // Perde se tirar 7 antes de atingir o "ponto"
            }
        }
    }
}
