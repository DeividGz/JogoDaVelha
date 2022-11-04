/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogodavelha;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author aluno
 */
public class FuncoesJogo {
    
    // constroi o tabuleiro att as informações de cada posição
    public static void ConstroiTabuleiro(String espacos[][]){
        
        String tabela =
                "    " + "A" + "   " + "B" + "   " + "C\n"+
                "1" + "   " + espacos[0][0] + " | " + espacos[0][1] + " | " + espacos[0][2] + "\n"
                + "    --------- \n" +
                "2" + "   " + espacos[1][0] + " | " + espacos[1][1] + " | " + espacos[1][2] + "\n"
                + "    --------- \n" +
                "3" + "   " + espacos[2][0] + " | " + espacos[2][1] + " | " + espacos[2][2] + "\n\n"
                ;
        
        System.out.println("\n* JOGO DA VELHA *\n");
        System.out.print(tabela);
        
    }
    
    //constrói o tabuleiro de inicio de jogo
    public static void TabuleiroInicioJogo(String[][] Jogo){
        
        for(int l = 0; l < 3; l++){
            for(int c = 0; c < 3; c++){
                Jogo[l][c] = " ";
            }
        }
        
    }
    
    //coleta os nomes dos jogadores
    public static void ColetaNomes(String[] nomes){
        
        Scanner leia = new Scanner(System.in);
        
        for(int i = 0; i < 2; i++){
            System.out.println("Qual o nome do jogador "+ (i+1) +" ?");
            nomes[i] = leia.nextLine();
        }
        
    }
    
    //sorteia qual jogador começa no inicio do jogo
    public static boolean SorteiaVez(){
        
        boolean DeQuem = false;
            
            Random sorteia = new Random();
            int numero = sorteia.nextInt(2);
        
            if(numero == 0){
                DeQuem = true;
            } else if(numero == 1){
                DeQuem = false;
            }
            
            return DeQuem;
            
        }
    
    //mostra os nomes e um asterísco do lado de quem tem a vez
    public static String MostraVezeJogador(String jdres[], boolean VeZ){
        
        boolean VEZ = VeZ;
        
        String Jogadores = "";
        
        //vez == true -> j1 / vez == false -> j2
        
        if(VEZ == true){
            Jogadores = jdres[0] + " *       " + jdres[1]; 
        } else if(VEZ == false){
            Jogadores = jdres[0] + "         " + jdres[1] + " *";
        }
        
        return Jogadores;
        
    }
    
    //troca a vez do jogador
    public static boolean TrocaVez(boolean VezDeQuem){
        
        boolean VezDoJogador = VezDeQuem;
        VezDoJogador = !VezDoJogador;
        
        return VezDoJogador;
        
    }
    
    public static String[] EntradaLinhaColuna() throws Exception{
        Scanner teclado = new Scanner (System.in);
        
        int linha = 0;
        String coluna = "";
        boolean erro = false;
        
        do{
            try {
                linha = EntradaInteiro("Escolha uma linha. (1, 2 ou 3)");
                erro = false;
                if(linha < 1 || linha > 3){
                    erro = true;
                    System.out.println("Essa linha nao e valida!");
                }
            } catch (Exception e) {  
                erro = true;
                System.out.println(e.getMessage()); 
            }  
        } while (erro);
        
        do{
            try {
                coluna = EntradaTexto("Escolha uma coluna. (A, B ou C)");
                erro = false;
                if("a".equals(coluna) || "A".equals(coluna) || "b".equals(coluna) || "B".equals(coluna) || "c".equals(coluna) || "C".equals(coluna)){
                } else {
                    erro = true;
                    System.out.println("Essa coluna nao e valida!");
                }
            } catch (Exception e) {  
                erro = true;
                System.out.println("Essa coluna nao e valida!"); 
            }  
        } while (erro);
        
        String Linha = Integer.toString(linha);
        
        String[] jogada = {Linha, coluna};
        
        return jogada;
        
    }
    
    public static String EntradaTexto(String mensagem){
        Scanner teclado = new Scanner (System.in);
        System.out.print(mensagem);
        return teclado.nextLine();
    }
    
    public static int EntradaInteiro(String mensagem) throws Exception{
        
        int num = 0;
            
            String aux = EntradaTexto(mensagem);
            
            try {
                
                num = Integer.parseInt(aux);
                
            } catch (Exception e) {
                
                throw new Exception ("Nao recebeu um numero inteiro");
                
            }   
        
        return num;
        
    }
    
    public static int AcertaLinha(String Linha){
        int linha = 0;
        
        if("1".equals(Linha)){
            linha = 0;
        }
        if("2".equals(Linha)){
            linha = 1;
        }
        if("3".equals(Linha)){
            linha = 2;
        }
        return linha;
    }
    
    public static int AcertaColuna(String Coluna){
        int coluna = 0;
                
        if("a".equals(Coluna) || "A".equals(Coluna)){
            coluna = 0;
        }
        if("b".equals(Coluna) || "B".equals(Coluna)){
            coluna = 1;
        }
        if("c".equals(Coluna) || "C".equals(Coluna)){
            coluna = 2;
        }
        return coluna;
    }
    
    public static void ExibeJogada(String[] jogada, String[][] tabuleiro, boolean Vez) throws Exception{
        
        int linha = 0, coluna = 0;
        
        linha = AcertaLinha(jogada[0]);
        coluna = AcertaColuna(jogada[1]);
        
        String valor;
        
        if(Vez == true){
            valor = "X";
        } else {
            valor = "O";
        }
        
        String[] NovaJogada;
        boolean erro = false;
        
        do{
        
            if(" ".equals(tabuleiro[linha][coluna])){
                tabuleiro[linha][coluna] = valor;
                erro = false;
            } else {
                System.out.println("Essa posicao ja esta ocupada!");
                erro = true;
            }

            if(erro == true){
                NovaJogada = PegaTestaOutraJogada(tabuleiro);
                erro = false;
                linha = AcertaLinha(NovaJogada[0]);
                coluna = AcertaColuna(NovaJogada[1]);
                tabuleiro[linha][coluna] = valor;
            }
        } while (erro);
        
    }
    
    public static String[] PegaTestaOutraJogada(String[][] tabela) throws Exception{
        String[] OutraJogada;
        boolean erro = false;
        do{
            OutraJogada = EntradaLinhaColuna();

            int linha = 0, coluna = 0;

            linha = AcertaLinha(OutraJogada[0]);
            coluna = AcertaColuna(OutraJogada[1]);

            erro = !" ".equals(tabela[linha][coluna]);
            
        } while (erro);
        
        return OutraJogada;
    }
    
    public static boolean CondicaoResultado(String[][] jogo, int Jogadas, boolean VezDoFim){
        boolean vitoria = false , empate = false, aconteceu = false;
        
        if(
                jogo[1][1].equals(jogo[0][0]) && !" ".equals(jogo[1][1]) && jogo[2][2].equals(jogo[1][1]) || // diag esq sup
                jogo[1][1].equals(jogo[0][2]) && !" ".equals(jogo[1][1]) && jogo[2][0].equals(jogo[1][1]) || // diag dir sup
                jogo[1][0].equals(jogo[0][0]) && !" ".equals(jogo[1][0]) && jogo[2][0].equals(jogo[1][0]) || // vert esq
                jogo[1][1].equals(jogo[1][0]) && !" ".equals(jogo[1][1]) && jogo[1][2].equals(jogo[1][1]) || // vert meio
                jogo[1][2].equals(jogo[0][2]) && !" ".equals(jogo[1][2]) && jogo[2][2].equals(jogo[1][2]) || // vert dir
                jogo[0][1].equals(jogo[0][0]) && !" ".equals(jogo[0][1]) && jogo[0][2].equals(jogo[0][1]) || // hor sup
                jogo[1][1].equals(jogo[1][0]) && !" ".equals(jogo[1][1]) && jogo[1][2].equals(jogo[1][1]) || // hor meio
                jogo[2][1].equals(jogo[2][0]) && !" ".equals(jogo[2][1]) && jogo[2][2].equals(jogo[2][1]) // hor inf
           ){
            vitoria = true;
        }
        
        if(Jogadas == 8 && vitoria == false){
            empate = true;
        }
        
        if(vitoria == true){
            if(VezDoFim == true){
                System.out.println("O jogador 1 (X) VENCEU!");
            }else{
                System.out.println("O jogador 2 (O) VENCEU!");
            }
        }
        
        if(empate == true){
            System.out.println("Deu véia!");
        }
        
        if(vitoria == true || empate == true){
            aconteceu = true;
        }
        
        return aconteceu;
        
    }
    
}
