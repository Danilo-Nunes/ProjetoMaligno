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

        Fila<Coordenadas> fila = new Fila<Coordenadas>(3);
        Pilha<Fila<Cordenadas>> possibilidades = new Pilha<Fila<Cordenadas>>(linhas*colunas);

        int x = 0;
        int y = 0;

        Coordenadas atual = new Coordenadas(x, y);

        boolean terminou = false;
        boolean prosseguir = false;

       if(checarLabirinto())
        {


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
    }
}