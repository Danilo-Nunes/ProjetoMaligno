package classes;
import java.lang.reflect.*; // encontramos as classes que usamos, invoke e method

/**
	Classe genérica criada com o objetivo de empilhar objetos de forma que se comportem como uma pilha, sendo adicionados no final do vetor, e sendo retirados do
	começo. Possui 2 construtores, o de cópia e o normal; variáveis que armazenam a pilha e a quantidade de dados na pilha; possui o getter do final da pilha e um
	método que o exclui, fazendo a pilha reduzir; possui também os métodos obrigatórios que têm como função auxiliar e facilitar na utilização 	da classe em diversos
	outros programas.

	 @since 2018

     @author Danilo de Oliveira Nunes 18203 e João Vitor Javitti Alves 18207

 */

public class Pilha<X> implements Cloneable
{
	protected Object[] vetor;
	protected int qtd = 0;

	/**
		Construtor principal da classe criado com a função de passar o valor que será atrinuido a variável capacidade.

		@param capacidade define o tamanho limite da fila

		@throws Exception lança uma excessão caso a capacidade da pilha seja negativa

	 */

	public Pilha(int capacidade) throws Exception
	{
		if(capacidade < 0)
			throw new Exception("Capacidade inv�lida");

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
			Method metodo = classe.getMethod("Clone", tiposDosParametrosFormais);
			Object[] parametrosReais = null;
			ret = (X)metodo.invoke(x, parametrosReais);
	    }
	    catch(Exception erro) // pega todas as exce
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
		   throw new Exception("Informa��o ausente!");

		if(isCheia())
			throw new Exception("N�mero m�ximo de capacidade atingido!");

		if(s instanceof Cloneable)
			this.vetor[this.qtd] = meuCloneDeX(s);
		else
			this.vetor[this.qtd] = s;

		this.qtd++;
	}

	/**
		Método criado para retornar o último dado da pilha.

		@throws Exception lança uma exceção se a pilha estiver sem nenhum dado

		@return retorna o último valor da classe clonada, para evitar mal uso das informações e erros futuros

	 */

	public X getUmItem() throws Exception
	{
		if(isVazia())
			throw new Exception("Nada a recuperar");

		if(this.vetor[this.qtd-1] instanceof Cloneable)
			return meuCloneDeX((X)this.vetor[this.qtd-1]);

			return (X)this.vetor[this.qtd-1];
	}

	/**
		Método criado para remover o último dado da fila.

		@throws Exception lança uma exceção caso a fila esteja sem nenhum dado

	 */

	public void jogueForaUmItem() throws Exception
	{
		if(isVazia())
		   throw new Exception ("N�o h� nada a se apagar!");

		this.qtd--;
		this.vetor[this.qtd] = null;
	}

	/**
		Método criado para verificar se a pilha atingiu a sua capacidade máxima.

		@return retorna valor booleano true ou false

	 */

	public boolean isCheia()
	{
		return this.qtd == this.vetor.length;
	}

	/**
		Método criado para verificar se a pilha não possui dados.

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

	    return this.qtd + " elementos, sendo o �ltimo " + this.vetor[this.qtd-1];
    }

	/**
		Método obrigatório que tem como função comparar um objeto da classe Pilha com outro da
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

		Pilha<X> pil = (Pilha<X>)obj;

		if(this.qtd != pil.qtd)
			return false;

		for(int i=0; i < this.qtd; i++)
			if (!this.vetor[i].equals(pil.vetor[i]))
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

		for(int i = 0; i < this.qtd; i++)
		      ret = ret*2 + this.vetor[i].hashCode();

		return ret;
	}

	/**
        Construtor de cópia obrigatório criado para ser usado pelo método clone em clonar a classe, para evitar mal uso das informações e erros futuros.

		@param modelo define o objeto da Pilha a ser clonado

		@throws Exception lança uma exceção caso o parâmetro passado seja nulo

     */

	public Pilha(Pilha modelo) throws Exception
	{
		if(modelo==null)
			throw new Exception("Modelo ausente");

		this.qtd = modelo.qtd;
		this.vetor = new Object[modelo.vetor.length];

		for(int i = 0; i <= modelo.qtd; i++)
		{
			this.vetor[i] = modelo.vetor[i];
		}
	}

	/**
		Método obrigatório criado para utilizar o contrutor de cópia para clonar a classe.

		@return retorna a classe clonada

		@throws Exception lança uma excessão caso o objeto paramenizado da classe seja nulo
	 */

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