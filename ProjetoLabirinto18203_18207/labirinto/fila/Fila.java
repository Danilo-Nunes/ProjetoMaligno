package labirinto.fila;
import java.lang.reflect.*; // encontramos as classes que usamos, invoke e method

/**
	Classe genérica criada com o objetivo de enfileirar objetos de forma que se comportem como uma fila, sendo adicionados no final do vetor, e sendo retirados do 
	começo. Possui 2 construtores, o de cópia e o normal; variáveis que armazenam a fila, o seu começo, o seu fim e a quantidade de dados na fila; possui o getter do 
	inicio da fila e um método que o exclui, fazendo a fila andar; possui também os métodos obrigatórios que têm como função auxiliar e facilitar na utilização 
	da classe em diversos outros programas.

	 @since 2018

     @author Danilo de Oliveira Nunes 18203 e João Vitor Javitti Alves 18207
	
 */

public class Fila<X> implements Cloneable
{
	protected Object[] vetor;
	protected int qtd = 0;
	protected int inicio = 0;
    protected int fim = 0;

	/**
		Construtor principal da classe criado com a função de passar o valor que será atrinuido a variável capacidade.

		@param capacidade define o tamanho limite da fila

		@throws Exception lança uma excessão caso a capacidade da fila seja negativa

	 */

	public Fila(int capacidade) throws Exception
	{
		if(capacidade < 0)
			throw new Exception("Capacidade invalida");

		this.vetor = new Object[capacidade];
	}

	/**
		Método que força a chamada do método clone da classe paramenizada pois não é possível chamar o clone de uma classe que o java não sabe que tem clone,
		mas como sabe-se que possui, forçaremos a sua chamada com este método.

		@exception erro exceção genérica que não é tratada, pois não há a nescessidade

		@return retorna clone de x
		
	 */

	protected X meuCloneDeX(X x)
	{
		X ret = null;
		try
		{
		Class<?> classe = x.getClass();
		Class<?>[] tiposDosParametrosFormais = null; 
		Method metodo = classe.getMethod("clone", tiposDosParametrosFormais);
		Object[] parametrosReais = null;
		ret = (X)metodo.invoke(x, parametrosReais);
	    }	    
		catch(Exception erro)
		{

		}

	    return ret;
    }

	/**
		Método criado para guardar um objeto da classe paramenizada.

		@throws Exception lança exceções se a instancia do objeto for nula ou se a fila não tiver capacidade para guardar mais uma informação

	 */

	public void guarde (X s) throws Exception
	{
		if(s==null) 
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
				else
					this.vetor[this.fim++] = s;
			}
			this.qtd++;
	}

	/**
		Método criado para retornar o 1º dado da fila.

		@throws Exception lança uma exceção se a fila estiver sem nenhum dado

		@return retorna o 1º valor da classe clonada, para evitar mal uso das informações e erros futuros

	 */

	public X getUmItem() throws Exception
	{
		if(this.isVazia())
			   throw new Exception("Nada a recuperar");

	        if(this.vetor[this.inicio] instanceof Cloneable)
	        	return meuCloneDeX((X)this.vetor[this.inicio]);

	        return (X)this.vetor[this.inicio];
	}

	/**
		Método criado para remover o 1º dado da fila.

		@throws Exception lança uma exceção caso a fila esteja sem nenhum dado

	 */

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

	/**
		Método criado para verificar se a fila atingiu a sua capacidade máxima.

		@return retorna valor booleano true ou false

	 */

	public boolean isCheia()
	{
		return this.qtd == this.vetor.length;
	}

	/**
		Método criado para verificar se a fila não possui dados.

		@return retorna valor booleano true ou false

	 */

	public boolean isVazia()
	{
		return this.qtd == 0;
	}

	/**
		Método criado para indicar a quantidade de elementos e o valor do último elemento.

		@return retorna essas informações em uma String

	 */

	public String toString()
	{
		if(this.qtd == 0)
		   return "vazia";

	    return this.qtd + " elementos, sendo o ultimo " + this.vetor[this.qtd-1];
    }

	/**
		Método obrigatório que tem como função comparar um objeto da classe Fila com outro da
        mesma classe e verificar se eles são iguais ou não.

        @return retorna valor booleano true ou false

	 */

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

	/**
        Método obrigatório que tem como função retornar um número inteiro que será usado para definir
        o hash code da classe para ser usado caso nescessário usar hash em algum programa que 
        reutilizará esta classe.

        @return retorna o hash code da classe
        
     */

    public int hashCode()
    {
		int ret = 666;

		ret = ret*2 + new Integer(this.qtd).hashCode();

		for(int i = 0, pos=inicio; i < this.qtd; i++, pos=(qtd<vetor.length?pos+1:0)) 
		      ret = ret*2 + this.vetor[pos].hashCode();
		return ret;
	}

	/**
        Construtor de cópia obrigatório criado para ser usado pelo método clone em clonar a classe, para evitar mal uso das informações e erros futuros.
        
		@param modelo define o objeto da Fila a ser clonado

		@throws Exception lança uma exceção caso o parâmetro passado seja nulo

     */

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

	/**
		Método obrigatório criado para utilizar o contrutor de cópia para clonar a classe.

		@return retorna a classe clonada

		@throws Exception lança uma excessão caso o objeto paramenizado da classe seja nulo
	 */

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