import pilha.*;
import fila.*;
import coordenadas.*;
import java.io.*;
import java.util.*; // string builder

public class Program
{
	static int linhas;
    static int colunas;
    static char labirinto[][];
    static int x;
    static int y;
    static Coordenadas atual;
    static boolean terminou;
    static Pilha<Coordenadas> caminho;
    static Pilha<Fila<Coordenadas>> possibilidades;
    //ver se é número
    public static void main(String[] args)
    {
		try
		{
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            // corrigir convenções do java para simbolos
            System.out.println("Digite o caminho do arquivo onde o labirinto está: ");
            String diretorio = teclado.readLine();

            BufferedReader file = new BufferedReader(new FileReader(diretorio));

            linhas = 0;
            colunas = 0;

            linhas = Integer.parseInt(file.readLine()); // valueOf(); -- não nescessariamente é um ineteiro
            colunas = Integer.parseInt(file.readLine());


            labirinto = new char[linhas][colunas];

            for(int i = 0; i < linhas; i++)
                for(int j = 0; j < colunas; j++)
                    labirinto[i][j] = (char)(file.read()); // precisa ser lido como caracter

            file.close();

            caminho = new Pilha<Coordenadas>(linhas*colunas);


            possibilidades = new Pilha<Fila<Coordenadas>>(linhas*colunas);

            x = 0;
            y = 0;

            atual = new Coordenadas(x, y);

            terminou = false;

            if(checarLabirinto())
            {
                while(!terminou)
                {
                    Movimentar(atual);
                }

                Pilha<Coordenadas> inverso = new Pilha<Coordenadas>(linhas*colunas);

                System.out.println("O Labirinto é resolvido nas seguintes coordenadas: \n");

                while(!caminho.isVazia())
                {
                    inverso.guarde(caminho.getUmItem());
                    caminho.jogueForaUmItem();
                    System.out.println(inverso.getUmItem());
                }
            }
            else
            {
                throw new Exception("Entrada ou saída não encontradas");
            }

            System.out.println("\nLabirinto Resolvido: \n");

            escreverLabirinto(); // this não é statico
	    }
        catch(Exception erro)
        {
            System.out.println(erro);
        }

    }


        public static boolean checarLabirinto()
        {
 			int saidas = 0;
	 		int entradas = 0;

	 		boolean temUm = false;

	     	//  primeira linha
	     	for (int i = 1; i <= labirinto[0].length-2; i++) // procura apenas 1 vez
	     	{
	             if (labirinto[0][i] == 'E') {
	                     entradas++;

	                     x = i;
	                     y = 0;
	             }
	             if (labirinto[0][i] == 'S') {
	                     saidas++;
	             }
	     	}

	     	//  primeira coluna
	 		for (int i = 0; i <= labirinto.length-1; i++)
	 		{
	     		if (labirinto[i][0] == 'E')
	     		{
	     			entradas++;

	     			x = 0;
	     			y = i;
	     		}
	     		if (labirinto[i][0] == 'S')
	     		{
	 				saidas++;
	     		}
	     	}

	     	// ultima linha
			for (int i = 1; i <= labirinto[0].length-2; i++) // procura apenas 1 vez
			{
				 if(labirinto[labirinto.length-1][i] == 'E')
				 {
				     entradas++;

				     x = i;
				     y = linhas;
				 }
				 if(labirinto[labirinto.length-1][i] == 'S')
				 {
				 	saidas++;
				 }
	     	}

	     	// ultima coluna
	 		for (int i = 0; i <= labirinto.length-1; i++)
	 		{
	     		if(labirinto[i][labirinto[0].length-1] == 'E') {
	     			entradas++;

	     			x = colunas;
	     			y = i;
	     		}
	     		if(labirinto[i][labirinto[0].length-1] == 'S')
	     		{
	 				saidas++;
	     		}
	     	}

	     	if (entradas == 1 && saidas ==1)
	     		temUm = true;

    		return temUm;
        }

        public static void Movimentar(Coordenadas atual) throws Exception
        {//empilhar
            Fila<Coordenadas> fila = new Fila<Coordenadas>(3);

            //Se da pra mover na direção Y:

            if(atual.getY() + 1 < linhas)
            {
                if(labirinto[atual.getX()][atual.getY()+1] == ' ')
                {
                    Coordenadas cord = new Coordenadas(atual.getX(), atual.getY() + 1);
                    fila.guarde(cord);
                }
            }

            if(atual.getY() - 1 < -1)
            {
                if(labirinto[atual.getX()][atual.getY()-1] == ' ')
                {
                Coordenadas cord = new Coordenadas(atual.getX(), atual.getY() - 1);
                fila.guarde(cord);
                }
            }
            //Se pode se mover na direção X
            if(atual.getX() + 1 < colunas)
            {
                if(labirinto[atual.getX()+1][atual.getY()] == ' ')
                {
                    Coordenadas cord = new Coordenadas(atual.getX()+1, atual.getY());
                    fila.guarde(cord);
                }
            }

            if(atual.getX() - 1 > -1)
            {
                if(labirinto[atual.getX()+1][atual.getY()] == ' ')
                {
                    Coordenadas cord = new Coordenadas(atual.getX()-1, atual.getY());
                    fila.guarde(cord);
                }
            }
            if(!fila.isVazia())
                {
                    atual = fila.getUmItem();
                    fila.jogueForaUmItem();

                    if(labirinto[atual.getX()][atual.getY()] != 'S')
                        labirinto[atual.getX()][atual.getY()] = '*';
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


        public static String escreverLabirinto() // static não pode dar override
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