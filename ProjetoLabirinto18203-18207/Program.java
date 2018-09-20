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
        String arquivo = teclado.readLine();

        BufferedReader file = new BufferedReader(new FileReader(arquivo));

        int linhas = 0;
        int colunas = 0;
        boolean taSerto;

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

        if(checarE() && checarS())
        {

        }
        else
        {
            throw new Exception('Entrada ou saída não encontradas');
        }


        private boolean checarE()
        {
            for(int i = 0; i < colunas; i++)
                if(labirinto[0][i] == 'E')
                    return true;

            for(int i = 0; i < linhas; i++)
                if(labirinto[i][0] == 'E')
                    return true;
            
            for(int i = 0; i < colunas; i++)
                if(labirinto[linhas][i] == 'E')
                    return true;

            for(int i = 0; i < linhas; i++)
                if(labirinto[i][colunas] == 'E')
                    return true;

            return false;

        }

        private boolean checarS()
        {
            for(int i = 0; i < colunas; i++)
                if(labirinto[0][i] == 'S')
                    return true;

            for(int i = 0; i < linhas; i++)
                if(labirinto[i][0] == 'S')
                    return true;
            
            for(int i = 0; i < colunas; i++)
                if(labirinto[linhas][i] == 'S')
                    return true;

            for(int i = 0; i < linhas; i++)
                if(labirinto[i][colunas] == 'S')
                    return true;

            return false;

        }

                
    }
}