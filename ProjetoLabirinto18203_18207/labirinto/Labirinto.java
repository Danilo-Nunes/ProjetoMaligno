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
       if(lab == null || lin == null || column == null)
        throw new exception("Valores Nulos");

        if(lin < 0 || column < 0)
            throw new exception("Valores inválidos");


        this.labirinto = lab;
        this.linhas = lin;
        this.colunas = column;


        caminho = new Pilha(lin*column);
        possibilidades = new Pilha(lin*column);
    }
    /**
    * 	Metodo que checa se o Labirinto tem uma entrada e uma saída
    *
    *   @return true se o Laabirinto atenda as verificações e false se não atende
    *   @see Usa os métods estaNaLinha e estaNaColuna para procurar a entrada e a saída
    */

    public boolean checarLabirinto()
        {
 			int saidas = 0;
	 		int entradas = 0;
	     	//  primeira linha
            boolean[] estaNa1Linha = this.estaNaLinha(0, 'E', 'S');
            if(estaNa1Linha[0] == true)
                entradas++;
            if(estaNa1Linha[1] == true)
                saidas++;

	     	//  primeira coluna
            boolean[] estaNa1Coluna = this.estaNaColuna(0, 'E', 'S');
            if(estaNa1Coluna[0] == true)
                entradas++;
            if(estaNa1Coluna[1] == true)
                saidas++;

	     	// ultima linha
            boolean estaNaUltLinha = this.estaNaLinha(linhas, 'E', 'S');
			if(estaNaUltLinha[0] == true)
                entradas++;
            if(estaNaUltLinha[1] == true)
                saidas++;

	     	// ultima coluna
	 		  boolean[] estaNaUltColuna = this.estaNaColuna(colunas, 'E', 'S');
            if(estaNaUltColuna[0] == true)
                entradas++;
            if(estaNaUltColuna[1] == true)
                saidas++;

    		if(entradas == 1)
    			atual = new Coordenada(xEntrada, yEntrada);
    		return (entradas == 1 && saidas == 1);

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

        /**
		   Método criado para
		*/

		public static void Movimentar() throws Exception
		{
		   Fila<Coordenadas> fila = new Fila<Coordenadas>(3);
		   Coordenadas[] espacos = new Coordenada[4];

		   atualX = atual.getX();
		   atualY = atual.getY();

		   direcaoY(espacos, atualX, atualY);
		   direcaoX(espacos, atualX, atualY);

		   for(int i = 0; i < 3; i++)
				if(!espacos[i].equals(null))
				   fila.guarde(espacos[i]);

		   testaOutra(Fila<Coordenadas> fila);
		}

		private void direcaoY(Coordenadas[] espacos, int atualX, int atualY)
		{
			if(atualY + 1 < linhas)
		    {
		       if(labirinto[atualX][atualY + 1] == ' ')
		       {
		          Coordenadas cord = new Coordenadas(atualX, atualY + 1);
				  espacos[0] = cord;
		       }
		    }

		    if(atualY - 1 < -1)
		    {
		       if(labirinto[atualX][atualY - 1] == ' ')
		       {
		          Coordenadas cord = new Coordenadas(atualX, atualY - 1);
			      espacos[1] = cord;
		       }
		    }
		}

		private void direcaoX(Coordenadas[] espacos, int atualX, int atualY)
		{
			if(atual.getX() + 1 < colunas)
		    {
		       if(labirinto[atual.getX()+1][atual.getY()] == ' ')
		       {
		           Coordenadas cord = new Coordenadas(atual.getX() + 1, atual.getY());
				   espacos[2] = cord;
		       }
		    }

		    if(atual.getX() - 1 > -1)
		    {
		        if(labirinto[atual.getX()+1][atual.getY()] == ' ')
		        {
		           Coordenadas cord = new Coordenadas(atual.getX() - 1, atual.getY());
				   espacos[3] = cord;
		        }
		    }
		}

		private void testaOutra(Coordenadas atual, Fila<Coordenadas> fila) // se nao tem nenhuma coord possivel, pega outra na possibilidades
		{
			if(!fila.isVazia())
		    {
		       atual = fila.getUmItem();
		       fila.jogueForaUmItem();

			   atualX = atual.getX();
			   atualY = atual.getY();

		       if(labirinto[atualX][atualY] != 'S')
		          labirinto[atualX][atualY] = '*';
		       else
		          terminou = true;

		          caminho.guarde(atual);

		          possibilidades.guarde(fila);
		      }
		      else
		      {
		      if(!possibilidades.isVazia())
		      {
		         atual = caminho.getUmItem();
		         caminho.jogueForaUmItem();
		         labirinto[atual.getX()][atual.getY()] = ' ';
		         fila = possibilidades.getUmItem();
		         possibilidades.jogueForaUmItem();
		      }
		      else
		         throw new Exception("Labirinto sem resolução!");

		      }
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