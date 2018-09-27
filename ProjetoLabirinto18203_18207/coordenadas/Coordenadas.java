package coordenadas;

 /**
         Classe criada com função de guardar as coordenadas de um plano cartesiano. Possui construtor,
         variáveis que armazenam as coordenadas abicssas e as ordenadas com seus getters, possui também
         os métodos obrigatórios que têm como função auxiliar e facilitar na utilização da classe em 
         diversos outros programas.

         @since 2018

         @author Danilo de Oliveira Nunes 18203 e João Vitor Javitti Alves 18207

     */

public class Coordenadas
{
    protected int x = 0; // abicssas
    protected int y = 0; // ordenadas

    /**
        Construtor da classe criado com a função de passar os valores que serão atribuidos respectiva
        mente a x e y para assim criarmos um par ordenado

        @param x valor que define a coordenada abicssa
        @param y valor que define a coordenada ordenada

     */

    public Coordenadas(int x, int y) throws Exception
    {
        if(x < 0 || y < 0 || new Integer(x).equals(null) || new Integer(y).equals(null))
            throw new Exception("Coordenadas inválidas! Digite-as corretamente!");

        this.x = x;
        this.y = y;
    }

    /**
        Método obrigatorio que tem como função retornar o valor das coordenadas

        @return retorna par ordenado
     */

    public String toString()
    {
        return "(" + this.x + ", " + this.y + ")"; 
    }

    /**
        Método que tem como função retornar o valor da variável x

        @return retorna coordenada abicssa
     */

    public int getX()
    {
        return this.x;
    }

    /**
        Método que tem como função retornar o valor da variável y

        @return retorna coordenada ordenada
     */

    public int getY()
    {
        return this.y;
    }

    /**
        Método obrigatório que tem como função comparar um objeto da classe Coordenada com outro da
        mesma classe e verificar se eles são iguais ou não

        @return retorna valor booleano true ou false
     */

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if(obj == null)
            return false;

        if(this.getClass() != obj.getClass())
            return false;

        Coordenadas coord = (Coordenadas)obj;

        if(this.x != coord.x || this.y != coord.y)
            return false;

        return true;
    }

    /**
        Método obrigatório que tem como função retornar um número inteiro que será usado para definir
        o hash code da classe para ser usado caso nescessário usar hash em algum programa que 
        reutilizará esta classe

        @return retorna o hash code da classe
     */

    public int hashCode()
    {
        int ret = 1;

        ret = ret*2 + new Integer(this.x).hashCode();
        ret = ret*2 + new Integer(this.y).hashCode();

        return ret;
    }
}