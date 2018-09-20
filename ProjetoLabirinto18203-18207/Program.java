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


        char[][] labirinto;

        Pilha<Coordenadas> caminho;

        Fila<Coordenadas> fila = new Fila<Coordenadas>(3);
    }
}