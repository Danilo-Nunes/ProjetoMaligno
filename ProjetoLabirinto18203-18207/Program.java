import Pilha.*;
import Fila.*;
import Coordenadas.*;
import java.io.*;

public class Program
{
    //ver se é número
    static void main(String[] args)
    {
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        // corrigir convenções do java para simbolos
        System.out.println("Digite o caminho do arquivo onde o labirinto está: ");
        String diretorio = teclado.readLine();

        BufferedReader file = new BufferedReader(new FileReader(arquivo));

        int linhas = 0;
        int colunas = 0;

        linhas = file.readLine();
        colunas = file.readLine();


        char labirinto[][] = new char[linhas][colunas];

        for(int i = 0; i < linhas; i++)
            for(int j = 0; j < colunas; j++)
                labirinto[i][j] = file.read();

        file.close();

        Pilha<Coordenadas> caminho = new Pilha<Coordenadas>(linhas*colunas);

        
        Pilha<Fila<Cordenadas>> possibilidades = new Pilha<Fila<Cordenadas>>(linhas*colunas);

        int x = 0;
        int y = 0;

        Coordenadas atual = new Coordenadas(x, y);

        boolean terminou = false;
        boolean prosseguir = false;

       if(checarLabirinto())
        {
            while(!terminou)
            {
              Movimentar(atual);  
            }

            Pilha<Coordenadas> inverso = new Pilha<Coordenadas>(linhas*colunas);

            system.out.println("O Labirinto é resolvido nas seguintes coordenadas: ")

            while(!caminho.isVazia())
            {
                inverso.guarde(caminho.getUmItem());
                caminho.jogueForaUmItem();
                println(inverso.getUmItem());
            }



        }
        else
        {
            throw new Exception("Entrada ou saída não encontradas");
        }


        private boolean checarLabirinto()
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

        private void Movimentar(Coordenadas atual)
        {
            Fila<Coordenadas> fila = new Fila<Coordenadas>(3);

            //Se da pra mover na direção Y:

            if(atual.getY + 1 < linhas)
            {
                if(labirinto[atual.getX][atual.getY+1] == " ")
                {
                    Coordenadas cord1 = new Coordenadas(atual.getX, atual.getY + 1);
                    fila.guarde(cord1);              
                }
            }

            if(atual.gety - 1 < -1)
            {
                if(labirinto[atual.getX][atual.gety-1])
                {
                Cordenadas cord2 = new Coordenadas(atual.getX, atual.getY - 1);
                fila.guarde(cord2)
                }
            }
            //Se pode se mover na direção X
            if(atual.getX + 1 < colunas)
            {
                if(labirinto[atual.getX+1][atual.getY] == " ")
                {
                    Coordenadas cord3 = new Coordenadas(atual.getX+1, atual.getY);
                    fila.guarde(cord3);
                }
            }

            if(atual.getX - 1 > -1)
            {
                if(labirinto[atual.getX+1][atual.getY] == " ")
                {
                    Coordenadas cord4 = new Coordenadas(atual.getX-1, atual.getY);
                    fila.guarde(cord4);
                }
            }
            if(!fila.isVazia())
                {
                    atual = fila.getUmItem();
                    fila.jogueForaUmItem();

                    if(labirinto[atual.getX][atual.getY] != 'S')
                        labirinto[atual.getX][atual.getY] = '*';
                    else
                        terminou = true;

                    caminho.guarde(atual);

                    possibilidades.guarde(fila);
                }            
            else
                {
                   atual = caminho.getUmItem();
                   caminho.jogueForaUmItem();
                   labirinto[atual.getX][atual.getY] = ' ';
                   fila = possibilidades.getUmItem();
                   possibilidades.jogueForaUmItem();

                }


        }
    }
}