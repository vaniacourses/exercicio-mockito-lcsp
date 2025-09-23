package jogo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class JogoTest {
   @Mock
   private Jogador jogador;
  
   @Mock
   private Dado dado1;
   @Mock
   private Dado dado2;
  
   @InjectMocks
   private Jogo jogo;
   @Test
   void primeiraJogadaSete() {
      
       when(jogador.lancar(dado1, dado2)).thenReturn(7);
      
       boolean resultado = jogo.jogo();
      
       assertTrue(resultado, "O jogador ganha com 7 na primeira jogada.");
      
      
       verify(jogador, times(1)).lancar(dado1, dado2);
   }
   @Test
   void primeiraJogadaForOnze() {
      
       when(jogador.lancar(dado1, dado2)).thenReturn(11);
      
       boolean resultado = jogo.jogo();
       assertTrue(resultado, "O jogador ganha com 11 na primeira jogada.");
   }
   @Test
   void falsoPrimeiraJogadaForDois() {
      
       when(jogador.lancar(dado1, dado2)).thenReturn(2);
      
       boolean resultado = jogo.jogo();
      
       assertFalse(resultado, "O jogador perde com 2 na primeira jogada.");
   }
  
   @Test
   void falsoPrimeiraJogadaForDoze() {
   	when(jogador.lancar(dado1, dado2)).thenReturn(12);
       boolean resultado = jogo.jogo();
       assertFalse(resultado, "O jogador perde com 12 na primeira jogada.");
   }
   @Test
   void ganhaSegundoTurnoPontoRepetido() {
      
       when(jogador.lancar(dado1, dado2)).thenReturn(8, 8);
       boolean resultado = jogo.jogo();
       assertTrue(resultado, "Ganha se repetir o ponto na segunda jogada.");
      
       verify(jogador, times(2)).lancar(dado1, dado2);
   }
  
   @Test
   void perdeSegundoTurnoSeTirarSete() {
      
       when(jogador.lancar(dado1, dado2)).thenReturn(6, 7);
       boolean resultado = jogo.jogo();
       assertFalse(resultado, "Perde se tirar 7 na segunda jogada.");
       verify(jogador, times(2)).lancar(dado1, dado2);
   }
   @Test
   void continuarJogandoVenceChegandoNoPonto() {
       // 1ª : 9 (ponto)
       // 2ª : 5 (continua)
       // 3ª : 10 (continua)
       // 4ª : 9 (ganha)
       when(jogador.lancar(dado1, dado2)).thenReturn(9, 5, 10, 9);
      
       boolean resultado = jogo.jogo();
      
       assertTrue(resultado, "Deveria ganhar após várias jogadas ao atingir o ponto.");
       verify(jogadork, times(4)).lancar(dado1, dado2);
   }
}

