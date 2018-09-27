package labirinto;
import java.lang.reflect.*;
import coordenadas.*;
import pilha.*;
import fila.*;

/**
*   Classe para ler o labirinto e verificar se segue o padrão correto
*
*   @since 2018
*   @author João Victor Javitti - Danilo de Oliveira Nunes
*/

public class Labirinto implements Cloneable
{
    protected int linhas;
    protected int colunas;
    protected char labirinto[][];
    protected int xEntrada;
    protected int yEntrada;
    protected Pilha<Coordenadas> caminho;
    protected Pilha<Fila<Coordenadas>> possibilidades;

    /**
    *   Construtor principal da classe Labirinto
    *
    *   @param lab[][] A matriz com os desenhos do labirinto
    *   @param lin a quantidade de linhas que tem o labirinto
    *   @param colum a quantidade de colunas que tem o labirinto
    *
    */

    public Labirinto(char lab[][], int lin, int column)
    {
        this.labirinto = lab;
        this.linhas = lin;
        this.colunas = column;
    }
    /**
    * Metodo que checa se o Labirinto tem uma entrada e uma saída
    *
    *   @return true se o Laabirinto atenda as verificações e false se não atende
    *   @see Usa os métods estaNaLinha e estaNaColuna para procurar a entrada e a saída
    */

    public boolean checarLabirinto()
        {
 			int saidas = 0;
	 		int entradas = 0;
	     	//  primeira linha
            boolean[] taaki = this.estaNaLinha(0, 'E', 'S');
            if(taaki[0] == true)
                entradas++;
            if(taaki[1] == true)
                saidas++;

	     	//  primeira coluna
            boolean[] taaki2 = this.estaNaColuna(0, 'E', 'S');
            if(taaki2[0] == true)
                entradas++;
            if(taaki2[1] == true)
                saidas++;

	     	// ultima linha
            boolean taaki3 = this.estaNaLinha(linhas, 'E', 'S');
			if(taaki3[0] == true)
                entradas++;
            if(taaki3[1] == true)
                saidas++;

	     	// ultima coluna
	 		  boolean[] taaki4 = this.estaNaColuna(colunas, 'E', 'S');
            if(taaki4[0] == true)
                entradas++;
            if(taaki4[1] == true)
                saidas++;

    		return (entradas == 1 && saidas ==1);
        }
        /**
        * Método que procura dois caracteres em uma linha da matriz e manda as coordenadas da entrada
        *
        *   @param lin A linha onde se deve procurar
        *   @param e o primeiro caracter para procurar
        *   @param s o segundo caracter para procurar
        */

       protected boolean[] estaNaLinha (int lin, char e, char s)
        {
           boolean[] bol = {false, false};
            for (int i = 1; i <= labirinto[lin].length-2; i++)
            {
	             if (labirinto[lin][i] == e)
                 {
                     xEntrada = i;
                     yEntrada = lin;
                     bol[0] = true;
                 }
                 if(labirinto[lin][i] == s)
                    bol[1] = true;
	     	}

             return bol;
        }

        /**
        * Método que procura dois caracteres em uma coluna da matriz e manda as coordenadas da entrada
        *
        *   @param col A coluna onde se deve procurar
        *   @param e o primeiro caracter para procurar
        *   @param s o segundo caracter para procurar
        */

        protected boolean[] estaNaColuna (int col, char e, char s)
        {
            boolean[] bol = {false, false};
            for (int i = 0; i <= labirinto.length-1; i++)
	 		{
	     		if (labirinto[i][col] == e)
                {
                    xEntrada = col;
                    yEntrada = i;
                    bol[0] = true;
                }
                if(labirinto[i][col] == s)
                    bol[1] = true;
	     	}
              return bol;
        }

        public String toString()
        {
            StringBuilder ret = new StringBuilder();

            for ( int i = 0; i <= labirinto.length-1; i++) {
                for ( int j = 0; j <= labirinto[i].length-1; j++) {
                    ret.append(labirinto[i][j]);
                }
                ret.append('\n');
            }

            ret.append(caminho.toString());//a Plha<Coordenada> inverso estava mostrando o caminho invertido (da saida para a entrada),
            return ret.toString();              //entao, para exibir na ordem certa, nao precisou cria-la.
        }
}