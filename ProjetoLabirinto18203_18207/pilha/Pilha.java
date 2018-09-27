package pilha;
import java.lang.reflect.*; // encontramos as classes que usamos, invoke e method

public class Pilha<X> implements Cloneable
{
	private Object[] vetor;
	private int qtd = 0;

	public Pilha(int capacidade) throws Exception
	{
		if(capacidade < 0)
			throw new Exception("Capacidade inv�lida");

		this.vetor = new Object[capacidade];
	}
	/* vers�o remediadora
	public Pilha(int capacidade) throws Exception
	{
		try
		{
			this.vetor = new String[capacidade];
		}
		catch(NegativeArraySizeException erro)
		{
			throw new Exception("Capacidade inv�lida");
		}
	}
	*/
	private X meuCloneDeX(X x)
	{
		X ret = null;
		try
		{
		// agora o que quero fazer de um jeito DEMONIACO é return x.clone();
		Class<?> classe = x.getClass();
		Class<?>[] tiposDosParametrosFormais = null; // null pq clone tem 0 parâmetros, ou (Class<?>[])null
		Method metodo = classe.getMethod("Clone", tiposDosParametrosFormais);
		Object[] parametrosReais = null; // null pq clone tem 0 parâmetros
		ret = (X)metodo.invoke(x, parametrosReais); // dois parâmeros, o chamante e o vetor(str, parametrosReais)
	    }
	    catch(NoSuchMethodException erro) // sei que não vai acontecer essa excessão então não vou tratar, mas tem que saber hein. aconteceria se escrevesse outra coisa no lugar de clone, clone não existisse
	    {

	    }
	    catch(IllegalAccessException erro) // aconteceria se clone não existisse
	    {

	    }
	    catch(Exception erro) // aconteceria se clone não existisse
		{

	    }
	    return ret;
    }

	public void guarde (X s) throws Exception
	{
		if(s==null) // não precisa desse:  || s.equals("") pois é só em string
		   throw new Exception("Informa��o ausente!");

		if(isCheia())
			throw new Exception("N�mero m�ximo de capacidade atingido!");

		if(s instanceof Cloneable) // é um comando, não método
			this.vetor[this.qtd] = meuCloneDeX(s); // vai dar pau, tem que contornar
		else
			this.vetor[this.qtd] = s;
		// this.vetor[this.qtd] = (Registro)s.clone();
		// this.vetor[this.qtd] = new Object(s);
		this.qtd++;
	}

	public X getUmItem() throws Exception
	{
		if(isVazia())
			throw new Exception("Nada a recuperar");

		// return this.vetor[this.qtd-1];
		if(this.vetor[this.qtd-1] instanceof Cloneable)
			return meuCloneDeX((X)this.vetor[this.qtd-1]);
		// else
			return (X)this.vetor[this.qtd-1];
	}

	public void jogueForaUmItem() throws Exception
	{
		if(isVazia())
		   throw new Exception ("N�o h� nada a se apagar!"); // = Exception problema; problema = new Exception("N�o h� nada a recuperar"); throw problema;

		this.qtd--;
		this.vetor[this.qtd] = null;
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

	    return this.qtd + " elementos, sendo o �ltimo " + this.vetor[this.qtd-1];
    }

    public boolean equals(Object obj)
    {
		if(this==obj)
			return true;

		if(obj==null)
			return false;

		if(this.getClass()!= obj.getClass())
			return false;

		Pilha<X> pil = (Pilha<X>)obj;

		if(this.qtd != pil.qtd) // ou ((Pilha)obj) -- mais econ�mico, economiza 4 bytes
			return false;

		for(int i=0; i < this.qtd; i++)
			if (!this.vetor[i].equals(pil.vetor[i]))
				return false;

		return true;

    }

    public int hashCode()
    {
		int ret = 666; // pode ser qualquer valor, desde que n�o seja 0

		ret = ret*2 + new Integer(this.qtd).hashCode(); // qualquer n�mero primo, pode variar nas outras linhas + hashCode de cada coisa que tenho guardada dentro do meu objeto

		for(int i = 0; i < this.qtd; i++)
		 // if(this.vetor[i] != null, a rigor dever�amos fazer isso, mas n�s codificamos m�todos que n�o permitem isso
		      ret = ret*2 + this.vetor[i].hashCode(); // envolver todos os dados, at� os mais miudinhos, evita repeti��es

		return ret;
	}

	// construtor de copia
	    public Pilha(Pilha modelo) throws Exception
	    {
			if(modelo==null)
			   throw new Exception("Modelo ausente");

			this.qtd = modelo.qtd;
			this.vetor = new Object[modelo.vetor.length]; // se não for assim teremos apenas um espaço ara jogar ponteiros, bagunçando tudo

			for(int i = 0; i <= modelo.qtd; i++)
			{
				this.vetor[i] = modelo.vetor[i];
				// this.vetor[i] = new Horario(modelo.vetor[i]); -- para que todas as coisas sejam de fato clonadas, teria que ser esse, porém gasta mais memória.
			}
	    }

	    public Object clone()
	    {
			Pilha<X> ret = null;

			try
			{
	        ret = new Pilha<X>(this);
		    }
		    catch(Exception erro)
		    {

		    }

		    return ret;
    }
}