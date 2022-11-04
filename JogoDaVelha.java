/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jogodavelha;

import static jogodavelha.FuncoesJogo.*;
import java.util.Scanner;

/**
 *
 * @author aluno
 */
public class JogoDaVelha {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        //criando scanner
        Scanner leia = new Scanner(System.in);
        
        //variaveis
        String jogadores[] = new String[2];
        String tabuleiro[][] = new String [3][3];
        int NumeroJogadas = 0;
        
        //exibe o tabuleiro de inicio de jogo (tudo em branco)
        TabuleiroInicioJogo(tabuleiro);
        
        //coleta os nomes dos jogadores
        ColetaNomes(jogadores);
        
        //guarda na variável "VezJogador" de quem é a vez no sorteio inicial
        boolean VezJogador = SorteiaVez();
        
        //exibe o tabuleiro com as posições salvas na matriz
        ConstroiTabuleiro(tabuleiro);
        
        //Exibe o nome dos jogadores e de quem é a vez
        System.out.println(MostraVezeJogador(jogadores, VezJogador));
        
        boolean CriterioParada = false;
        
        do{
            ExibeJogada(EntradaLinhaColuna(), tabuleiro, VezJogador);
            ConstroiTabuleiro(tabuleiro);
            CriterioParada = CondicaoResultado(tabuleiro, NumeroJogadas, VezJogador);
            
            if(CriterioParada == false){
                VezJogador = TrocaVez(VezJogador);
                System.out.println(MostraVezeJogador(jogadores, VezJogador));
            }
            
            NumeroJogadas ++;
        } while (CriterioParada == false);
        
    }
    
}
