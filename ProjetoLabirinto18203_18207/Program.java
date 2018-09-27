import pilha.*;
import fila.*;
import coordenadas.*;
import labirinto.*;
import java.io.*;
import java.util.*; // string builder

public class Program
{
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

            int linhas = Integer.parseInt(file.readLine()); // valueOf(); -- não nescessariamente é um ineteiro
            int colunas = Integer.parseInt(file.readLine());

            char labirinto[][] = new char[linhas][colunas];

            for(int i = 0; i < linhas; i++)
            {
                String str = file.readLine();
                for(int j = 0; j < colunas; j++)
                    labirinto[i][j] = str.charAt(j); // precisa ser lido como caracter
            }
            file.close();

            Labirinto-+ lab = new Labirinto(labirinto, linhas, colunas);

            terminou = false;
            if(lab.checarLabirinto())
            {
                while(!terminou)
                {
                    //Movimentar(atual);
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

            PrintStream resultado = new PrintWriter(new FileWriter(diretorio + ".res.txt"));
            resultado.println(lab.toString());
            resultado.close();
	    }
        catch(Exception erro)
        {
            System.out.println(erro);
        }

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


       /* public static String escreverLabirinto() // static não pode dar override
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
        }*/
    }