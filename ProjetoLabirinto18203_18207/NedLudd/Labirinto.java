package ProjetoLabirinto;

class Labirinto
{
  protected char[][] matrix;
  protected int linhas;
  protected int colunas;

  protected Cordenada atual;

  Pilha<Cordenada> caminho;
  Pilha<Fila<Cordenada>> possibilidades;

  public Labirinto(char[][] m, int l, int c) throws Exception
  {
    if(m == null)
       throw new Exception("valor inadequado");
    if(l <= 2 || c <= 2)
       throw new Exception("labirinto muito pequeno");

    linhas = l;
    colunas = c;

    matrix = m;

    atual = new Cordenada();

    caminho = new Pilha(colunas*linhas);
    possibilidades = new Pilha(colunas*linhas);
  }

  public boolean acharEntrada() throws Exception
  {
    for(int i = 0; i < colunas; i++)
      if(matrix[0][i] == 'E')
      {
        atual.setY(i);
        atual.setX(0);
        return true;
      }
      else if(matrix[linhas - 1][i] == 'E')
      {
        atual.setY(i);
        atual.setX(linhas - 1);
        return true;
      }
    for(int i = 0; i < linhas; i++)
      if(matrix[i][0] == 'E')
      {
        atual.setX(i);
        atual.setY(0);
        return true;
      }
      else if(matrix[i][colunas - 1] == 'E')
      {
        atual.setX(i);
        atual.setY(colunas - 1);
        return true;
      }

    return false;
  }

  public boolean acharSaida() throws Exception
  {
    boolean achouSaida = false;
    String modo = "progressivo";

    while(!achouSaida)
    {
      Fila<Cordenada> fila = new Fila(3);
      if(modo == "progressivo")
      {
        if(atual.getX() < linhas - 1)
          if(matrix[atual.getX() + 1][atual.getY()] == ' ' || matrix[atual.getX() + 1][atual.getY()] == 'S')
            fila.addItem(new Cordenada(atual.getX() + 1, atual.getY()));
        if(atual.getX() > 0)
          if(matrix[atual.getX() - 1][atual.getY()] == ' ' || matrix[atual.getX() - 1][atual.getY()] == 'S')
            fila.addItem(new Cordenada(atual.getX() - 1, atual.getY()));
        if(atual.getY() < colunas - 1)
          if(matrix[atual.getX()][atual.getY() + 1] == ' ' || matrix[atual.getX()][atual.getY() + 1] == 'S')
            fila.addItem(new Cordenada(atual.getX(), atual.getY() + 1));
        if(atual.getY() > 0)
          if(matrix[atual.getX()][atual.getY() - 1] == ' ' || matrix[atual.getX()][atual.getY() - 1] == 'S')
            fila.addItem(new Cordenada(atual.getX(), atual.getY() - 1));

        if(!fila.isVazia())
        {
          atual.setX(fila.getItem().getX());
          atual.setY(fila.getItem().getY());
          fila.removerItem();
 
          if(matrix[atual.getX()][atual.getY()] == ' ')
          {
            matrix[atual.getX()][atual.getY()] = '*';

            possibilidades.addItem(fila);

            caminho.addItem(new Cordenada(atual.getX(), atual.getY()));
          }
          else
            achouSaida = true;
        }
        else
          modo = "regressivo";
      }
      if(modo == "regressivo")
      {
        atual.setX(caminho.getItem().getX());
        atual.setY(caminho.getItem().getY());
        caminho.removerItem();

        matrix[atual.getX()][atual.getY()] = ' ';

        fila = possibilidades.getItem();
        possibilidades.removerItem();

        if(!fila.isVazia())
        {
          atual.setX(fila.getItem().getX());
          atual.setY(fila.getItem().getY());
          fila.removerItem();

          matrix[atual.getX()][atual.getY()] = '*';

          caminho.addItem(new Cordenada(atual.getX(), atual.getY()));

          possibilidades.addItem(fila);
          modo = "progressivo"; 
        }
      }
    }

    return true;
  }
}