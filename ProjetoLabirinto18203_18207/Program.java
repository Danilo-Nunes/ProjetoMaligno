import labirinto.coordenadas.*;
import labirinto.pilha.*;
import labirinto.fila.*;
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

            Labirinto lab = new Labirinto(labirinto, linhas, colunas);

            if(lab.checarLabirinto())
            {
                while(lab.isCompletado())
                {
                    lab.movimentar();
                }
                System.out.println("O Labirinto é resolvido da seguinte forma: \n");
                System.out.println(lab.toString());

            }
            else
            {
                throw new Exception("Entrada ou saída não encontradas");
            }

            PrintStream resultado = new PrintStream(diretorio + ".res.txt");
            resultado.println(lab.toString());
            resultado.close();
	    }
        catch(Exception erro)
        {
            System.out.println(erro);
        }

    }
}