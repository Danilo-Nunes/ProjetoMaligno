package labirinto;
import java.lang.reflect.*;
import labirinto.coordenadas.*;
import labirinto.pilha.*;
import labirinto.fila.*;

/**
*   Classe para ler o labirinto e verificar se segue o padrão correto, depois se tudo estiver correto, resolvê-lo em coordenadas e mostrar a solução.
*
*   @since 2018
*
*   @author João Victor Javitti - Danilo de Oliveira Nunes
*/

public class Labirinto implements Cloneable
{
    protected int linhas;
    protected int colunas;
    protected char labirinto[][];
    protected int xEntrada;
    protected int yEntrada;
    protected Coordenadas atual;
    protected Pilha<Coordenadas> caminho;
    protected Pilha<Fila<Coordenadas>> possibilidades;
    protected boolean terminou;

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

        this.terminou = false;

        this.caminho = new Pilha(lin*column);
        this.possibilidades = new Pilha(lin*column);
    }

    /**
    * 	Método que verifica se o Labirinto fornecido possui uma entrada e uma saída para ser finalizado e delimita a posição inicial isto é,
    *   a posição de entrada do labirinto.
    *
    *   @return true se o Laabirinto atenda as verificações e false se não atende
    *   @see Usa os métodos estaNaLinha e estaNaColuna para procurar a entrada e a saída
    *
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
        * Método que percorre uma linha especifica da matriz e verifica se ela possui os caracteres fornecidos
        *
        *   @param lin A linha onde se deve procurar
        *   @param e o primeiro caracter para procurar
        *   @param s o segundo caracter para procurar
        *   @return Retorna um vetor de boolean com os valores true se encontrou e false se não encontrou
        *
        */

       protected boolean[] estaNaLinha (int lin, char e, char s)
        {
           boolean[] bol = {false, false};
            for (int i = 1; i <= this.labirinto[lin].length-2; i++)
            {
	             if (this.labirinto[lin][i] == e)
                 {
                     xEntrada = i;
                     yEntrada = lin;
                     bol[0] = true;
                 }
                 if(this.labirinto[lin][i] == s)
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
        *
        */

        protected boolean[] estaNaColuna (int col, char e, char s)
        {
            boolean[] bol = {false, false};
            for (int i = 0; i <= this.labirinto.length-1; i++)
	 		{
	     		if(this.labirinto[i][col] == e)
                {
                    xEntrada = col;
                    yEntrada = i;
                    bol[0] = true;
                }
                if(this.labirinto[i][col] == s)
                    bol[1] = true;
	     	}
              return bol;
        }

        /**
		   Método que tem como função chamar outros métodos auxiliares para achar a saida do labirinto.

           @see usa os métodos direcaoY, direcaoX e testaOutra para verificar os espaços possiveis de movimento e armazenar as possibilidades alternativas de movimento
           em possibilidades até achar a saída, caso não reste nenhuma(todas não tenham a saida), o labirinto não terá solução.

		*/

		public void movimentar() throws Exception
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

        /**
            Método criado para verificar possibilidades de movimento no eixo Y, espaços em branco nas linhas da matriz.

         */

		private void direcaoY(Coordenadas[] espacos, int atualX, int atualY)
		{
			if(atualY + 1 < linhas)
		    {
		       if(this.labirinto[atualX][atualY + 1] == ' ')
		       {
		          Coordenadas cord = new Coordenadas(atualX, atualY + 1);
				  espacos[0] = cord;
		       }
		    }

		    if(atualY - 1 < -1)
		    {
		       if(this.labirinto[atualX][atualY - 1] == ' ')
		       {
		          Coordenadas cord = new Coordenadas(atualX, atualY - 1);
			      espacos[1] = cord;
		       }
		    }
		}

        /**
            Método criado para verificar possibilidades de movimento no eixo X, espaços em branco nas Colunas da matriz.

         */

		private void direcaoX(Coordenadas[] espacos, int atualX, int atualY)
		{
			if(atual.getX() + 1 < colunas)
		    {
		       if(this.labirinto[atual.getX()+1][atual.getY()] == ' ')
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

        /**
            Método criado para testar outra possibilidade de movimento no eixo Y, em outra coordenada possível, para caso não tenha nenhum espaço em branco nas linhas
            e colunas da matriz na última coordenada testada.

            @param fila fila que usaremos para pegar as possiveis coordenadas em que é possível nos mover.

         */

		private void testaOutra(Fila<Coordenadas> fila) // se nao tem nenhuma coord possivel, pega outra na possibilidades
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

        /**
            Método criado para verificar se o labirinto foi completado.

            @return retorna o valor de terminou.

         */

        public boolean isCompletado()
        {
            return this.terminou;
        }

        /**
            Método criado para escrever a matriz com os dados referentes ao labirinto resolvido(indicando caminho).

            @return retorna o labirinto resolvido

         */

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


        public int hashCode()
        {
			int ret = 666;

			ret = ret * 2 + new Integer(this.linhas).hashCode();

			for(int i = 0; i < linhas; i++)
				for(int j = 0; j < colunas; j++)
					ret = ret*2 + this.labirinto[i][j].hashCode();

			ret = ret * 2 + new Integer(this.xEntrada).hashCode();
			ret = ret * 2 + new Integer(this.yEntrada).hashCode();

			ret = ret * 2 + this.atual.hashCode();
			ret = ret * 2 + this.caminho.hashCode();
			ret = ret * 2 + this.possibilidades.hashCode();

			ret = ret * 2 + new Boolean(this.terminou).hashCode();

			return ret;

		}

		public Object clone()
		{
			Labirinto ret = null;

			try
			{
				ret = new Labirinto(this);
			}
			catch (Exception erro)
			{

			}

			return ret;
		}
}