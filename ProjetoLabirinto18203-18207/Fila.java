import java.lang.reflect.*;

public class Fila<X> implements Cloneable
{
	protected Object[] vetor;
	protected int qtd = 0;
	protected int inicio = 0;
    protected int fim = 0;

	public Fila(int capacidade) throws Exception
	{
		if(capacidade < 0)
			throw new Exception("Capacidade invalida");

		this.vetor = new Object[capacidade];
	}

	protected X meuCloneDeX(X x)
	{
		X ret = null;
		try
		{
		// agora o que quero fazer de um jeito DEMONIACO é return x.clone();
		Class<?> classe = x.getClass();
		Class<?>[] tiposDosParametrosFormais = null; // null pq clone tem 0 parâmetros, ou (Class<?>[])null
		Method metodo = classe.getMethod("clone", tiposDosParametrosFormais);
		Object[] parametrosReais = null; // null pq clone tem 0 parâmetros
		ret = (X)metodo.invoke(x, parametrosReais);
	    }
	    catch(NoSuchMethodException erro) // sei que não vai acontecer essa excessão então não vou tratar, mas tem que saber hein. aconteceria se escrevesse outra coisa no lugar de clone, clone não existisse
	    {}
	    catch(IllegalAccessException erro) // aconteceria se clone não existisse
	    {}
		catch(InvocationTargetException erro)
		{}

	    return ret;
    }

	public void guarde (X s) throws Exception
	{
		if(s==null) // s.equals antes n�o daria certo, pois se ele for null vai dar errado j� que n�o se pode chamar m�todo para objeto null
			   throw new Exception("Informa��o ausente");

			if(this.isCheia())
			   throw new Exception("Fila3 cheia");

			if(s instanceof Cloneable)
			{
				if(fim == this.vetor.length-1)
				{
				   fim = 0;
				   this.vetor[this.fim] = meuCloneDeX(s);
				}
				//this.vetor[this.qtd] = (Horario)s.clone();
				else
					this.vetor[this.fim++] = meuCloneDeX(s);
			}
			else
			{
				if(fim == this.vetor.length-1)
				{
				   fim = 0;
				   this.vetor[this.fim] = s;
				}
				//this.vetor[this.qtd] = (Horario)s.clone();
				else
					this.vetor[this.fim++] = s;
			}
			this.qtd++;
	}

	public X getUmItem() throws Exception
	{
		if(this.isVazia())
			   throw new Exception("Nada a recuperar");

	        if(this.vetor[this.inicio] instanceof Cloneable)
	        	return meuCloneDeX((X)this.vetor[this.inicio]);

	        return (X)this.vetor[this.inicio];
	}

	public void jogueForaUmItem() throws Exception
	{
		if(isVazia())
		   throw new Exception ("Nao ha nada a se apagar!");

        this.vetor[this.inicio] = null;

			if(this.inicio == this.vetor.length-1)
			   inicio = 0;
			else
				inicio++;

	        qtd--;
	}

	public boolean isCheia()
	{
		return this.qtd == this.vetor.length;
	}

	public boolean isVazia()
	{
		return this.qtd == 0;
	}

	public String toString()
	{
		if(this.qtd == 0)
		   return "vazia";

	    return this.qtd + " elementos, sendo o ultimo " + this.vetor[this.qtd-1];
    }

    public boolean equals(Object obj)
    {
		if(this==obj)
			return true;

		if(obj==null)
			return false;

		if(this.getClass()!= obj.getClass())
			return false;

		Fila<X> fila = (Fila<X>)obj; // java enxerga que existe uma Fila2 chamada fila (que � o mesmo obj)

		if(this.qtd != fila.qtd)
			return false;

		for(int i = 0,
	                posThis=this.inicio,
	                posFila=fila.inicio;

	            i < this.qtd;

	            i++,
	            posThis=(posThis<this.vetor.length-1?posThis+1:0),
	            posFila=(posFila<fila.vetor.length-1?posFila+1:0))

	           if(!this.vetor[posThis].equals(fila.vetor[posFila]))
	              return false;

		return true;
    }

    public int hashCode()
    {
		int ret = 666;

		ret = ret*2 + new Integer(this.qtd).hashCode();

		for(int i = 0, pos=inicio; i < this.qtd; i++, pos=(qtd<vetor.length?pos+1:0)) //  this foi omitido e pode ser omitido
		      ret = ret*2 + this.vetor[pos].hashCode();
		return ret;
	}

	    public Fila(Fila modelo) throws Exception
	    {
			if(modelo == null)
		    	throw new Exception("Modelo ausente");

			this.qtd = modelo.qtd;

			this.inicio = modelo.inicio;

			this.fim = modelo.fim;

			this.vetor = new Object[modelo.vetor.length];

			for(int i=0; i<modelo.vetor.length-1; i++)
		    	this.vetor[i] = modelo.vetor[i];
	    }

	    public Object clone()
	    {
			Fila<X> ret = null;
			try
			{
				ret = new Fila<X>(this);
			}
			catch(Exception erro)
			{}

			return ret;
        }
}