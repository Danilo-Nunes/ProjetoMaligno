public class Coordenadas
{
    protected int x = 0; // abicssas
    protected int y = 0; // ordenadas

    public Coordenadas(int x, int y) throws Exception
    {
        if(x < 0 || y < 0 || new Integer(x).equals(null) || new Integer(y).equals(null))
            throw new Exception("Coordenadas inválidas! Digite-as corretamente!");

        this.x = x;
        this.y = y;
    }

    public String toString()
    {
        return "(" + this.x + ", " + this.y + ")"; // retorna par ordenado
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

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

    public int hashCode()
    {
        int ret = 1;

        ret = ret*2 + new Integer(this.x).hashCode();
        ret = ret*2 + new Integer(this.y).hashCode();

        return ret;
    }
}