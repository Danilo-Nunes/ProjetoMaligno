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

        linhas = file.readLine();
        colunas = file.readLine();


        char labirinto[][] = new char[linhas][colunas];

        for(int i = 0; i < linhas; i++)
            for(int j = 0; j < colunas; j++)
                labirinto[i][j] = file.read();

        Pilha<Coordenadas> caminho = new Pilha<Coordenadas>(linhas*colunas);

        Fila<Coordenadas> fila = new Fila<Coordenadas>(3);
        Pilha<Fila<Cordenadas>> possibilidades = new Pilha<Fila<Cordenadas>>(linhas*colunas);
    }
}