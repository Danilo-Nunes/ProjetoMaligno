import pilha.*;
import fila.*;
import coordenadas.*;
import java.io.*;
import java.util.*; // string builder

public class Program // fazer to string para mostrar o labirinto
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
    public static void main(String[] args) // throws Exception buga, n fununcia nd
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

            toString(); // this não é statico
	    }
        catch(Exception erro)
        {
            System.out.println(erro);
        }

    }


        public static boolean checarLabirinto()
        {
            boolean temE = false;
            boolean temS = false;
            for(int i = 0; i < colunas; i++)
                if(labirinto[0][i] == 'E')
                {
                    x = 0;
                    y = i;
                    temE = true;
                }
                else if(labirinto[0][i] == 'S')
                {
                    temS = true;
                }

            if(temE && temS)
                return true;

            for(int i = 0; i < linhas-1; i++)
                if(labirinto[i][0] == 'E' && !temE)
                {
                    x = i;
                    y = 0;
                    temE = true;
                }
                else if(labirinto[i][0] == 'S' && !temS)
                {
                    temS = true;
                }

                if(temE && temS)
                    return true;

            for(int i = 0; i < colunas; i++)
                if(labirinto[linhas - 1][i] == 'E' && !temE)
                {
                    x = linhas - 1;
                    y = i;
                    temE = true;
                }
                else if(labirinto[linhas - 1][i] == 'S' && !temS)
                {
                    temS = true;
                }

                if(temE && temS)
                    return true;

            for(int i = 0; i < linhas; i++)
                if(labirinto[i][colunas - 1] == 'E' && !temE)
                {
                    x = i;
                    y = colunas - 1;
                    temE = true;
                }
                else if(labirinto[i][colunas - 1] == 'S' && !temS)
                {
                    temS = true;
                }

                if(temE && temS)
                    return true;

            return false;
        }

        public static void Movimentar(Coordenadas atual) throws Exception
        {//empilhar
            Fila<Coordenadas> fila = new Fila<Coordenadas>(3);

            //Se da pra mover na direção Y:

            if(atual.getY() + 1 < linhas)
            {
                if(labirinto[atual.getX()][atual.getY()+1] == ' ')
                {
                    Coordenadas cord1 = new Coordenadas(atual.getX(), atual.getY() + 1);
                    fila.guarde(cord1);
                }
            }

            if(atual.getY() - 1 < -1)
            {
                if(labirinto[atual.getX()][atual.getY()-1] == ' ')
                {
                Coordenadas cord2 = new Coordenadas(atual.getX(), atual.getY() - 1);
                fila.guarde(cord2);
                }
            }
            //Se pode se mover na direção X
            if(atual.getX() + 1 < colunas)
            {
                if(labirinto[atual.getX()+1][atual.getY()] == ' ')
                {
                    Coordenadas cord3 = new Coordenadas(atual.getX()+1, atual.getY());
                    fila.guarde(cord3);
                }
            }

            if(atual.getX() - 1 > -1)
            {
                if(labirinto[atual.getX()+1][atual.getY()] == ' ')
                {
                    Coordenadas cord4 = new Coordenadas(atual.getX()-1, atual.getY());
                    fila.guarde(cord4);
                }
            }//resolva
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

        public static String toString() // verificar esse e o try catch
        {
            StringBuilder ret = new StringBuilder();

            for ( int i = 0; i <= this.labirinto.length-1; i++) {
                for ( int j = 0; j <= this.labirinto[i].length-1; j++) {
                    ret.append(this.labirinto[i][j]);
                }
                ret.append('\n');
            }

            ret.append(this.caminho.toString());//a Plha<Coordenada> inverso estava mostrando o caminho invertido (da saida para a entrada),
            return ret.toString();              //entao, para exibir na ordem certa, nao precisou cria-la.
        }
    }