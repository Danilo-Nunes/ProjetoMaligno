/*
TIPOS ESCALARES
(v�o dobrando a capacidade 1x2x2x2..., podem guardar 2 elevado ao n�mero, byte = 8 bits --> 2^8 = 256 --> 256/2=128 --> -128 a 127 tirando o 0)
byte
short
int
long

float (equivalente ao tamanho do int)
double (equivalente ao tamanho do long)

char (ocupa 2 bytes, mas somente em java, pois ele usa mais caracteres, devido a internacionaliza��o,
      porem os 256 primeiros s�o os da tabela ASCII j� as outras usam somente 1 byte)

boolean

* String � uma classe.

CONVEN��ES DE NOMENCLATURA
1) Pacotes(biblioteca)
   tem seus nomes escritos totalmente com letras min�sculas e as palavras s�o separadas por(.).
2) Classes e Projetos
   palavras justapostas com iniciais mai�sculas e as demais letras min�sculas
3) Vari�veis, atributos, vari�veis locais e par�metros
   Segue a regra 2 com a seguinte adapta��o:
   a primeira palavra que forma o nome ser� totalmente min�scula
4) Constantes (final float PI = 3.14f;)
   tem seus nomes escritos totalmente com letras mai�sculas e as palavras s�o separadas por(_).

Obs: palavras reservadas pela linguagem e tipos escalares s�o totalente min�sculos

string str = "BANANA";
char chr = str.charAt(2);

o código acima é bem simples, suponhamos agora que queiramos SOFRER... o que fazer? como tornar DEMONIACo o código acima?
Class<?> classe = str.getClass();
Class<?>[] tiposDosParametosFormais = new Class<?>[2]; // 1 pq charAt tem 1 parâmetro
Integer parametroReal = 2; // 2 pq quero usar 2 como parâmetro do charAt
tiposDosParametosFormais[0] = parametroReal.getClass();
Method metodo = classe.getMethod("charAt", tiposDosParametosFormais);
Object[]parametrosReais = new Object[1]; // 1 pq charAt tem 1 parâmetro
parametrosReais[0] = parametroReal;
char chr = ((character)metodo.invoke(parametrosReais)).charValue();
jeito alternativo de se chamar um método, bem do capeta.
parametro formal = parametro que declaro quando estou declarando um método
quando passamos um vetor sem posição indexada, vai todas as posições no parâmetro
a ordem também conta para sobrecarga dos métodos, além da quantidade e dos tipos de parâmetro
vetores e objetos são variáveis que guardam endereços de memórias, são ponteiros
*/
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