import java.io.*;

class Main
{
  public static void main(String[] args) throws Exception
  {
    BufferedReader leitorTerminal = new BufferedReader(new InputStreamReader(System.in));
      
    System.out.println("Digite o nome do arquivo:");
      
    String arquivo = leitorTerminal.readLine();
   
    leitorTerminal.close();

    FileReader arq = new FileReader(arquivo);
    BufferedReader leitorArquivo = new BufferedReader(arq);

    int linhas = Integer.parseInt(leitorArquivo.readLine());
    int colunas = Integer.parseInt(leitorArquivo.readLine());

    char [][] matrix = new char[linhas][colunas];

    for(int i = 0; i < linhas; i ++)
    {
      String linha = leitorArquivo.readLine();
      for(int j = 0; j < colunas; j++)
        matrix[i][j] = linha.charAt(j);
    }

    leitorArquivo.close();

    Labirinto labirinto = new Labirinto(matrix, linhas, colunas);

    if(!labirinto.acharEntrada())
      throw new Exception("o labirinto nao possui uma entrada");

    if(!labirinto.acharSaida());
      throw new Exception("o labirinto nao possui uma saida");

    System.out.println("\na saida foi encontrada!\n");
    System.out.println("Labirinto:");

    for(int i = 0; i < linhas; i ++)
    {
      for(int j = 0; j < colunas; j++)
        System.out.print(matrix[i][j]);
      System.out.println();
    }
  }
}