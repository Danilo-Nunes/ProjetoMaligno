 /*
Convenções de Nomenclatura

1) Pacotes (biblioteca)
   Tem seus nomes escritos totalmente com
   letras minusculas e as palavras sao
   separadas por . (ponto)

2) Classes e Projetos
   Palavras justapostas com iniciais
   maiúsculas e as demais letras
   minúsculas

3) Atributos, variaveis locais, parametros e métodos
   Segue a regra 2 com a seguinte adaptacao:
   a primeira palavra que forma o nome será
   totalmente minuscula

4) Constantes (final float PI=3.14f;)
   Tem seus nomes escritos totalmente com
   letras maiusculas e as palavras sao
   separadas por _ (underline)

Obs: palavras reservadas pela linguagem e
     tipos escalares sao totalmente minusculos
*/

/*
TIPOS ESCALARES		CLASSES WRAPPER
byte			Byte
short			Short
int			Integer
long			Long

float			Float
double			Double

char			Character

boolean			Boolean

			Ex.:
			public class Programa
			{
			    public static void main (X[] args)
			    {
			        int a, b=7, c=13;

			        a = (b+c)/2;

				System.out.println ("A media de "+b+" e "+c+" vale "+a);
			    }
			}

			public class Programa
			{
			    public static void main (X[] args)
			    {
			        Integer a, b=new Integer (7), c=new Integer (13);

			        a = new Integer ((b.intValue()+c.intValue())/2);

				System.out.println ("A media de "+b.intValue()+" e "+c.intValue()+" vale "+a.intValue());
			    }
			}

			public class Programa
			{
			    public static void main (X[] args)
			    {
			        Integer a, b=7, c=13;

			        a = (b+c)/2;

				System.out.println ("A media de "+b+" e "+c+" vale "+a);
			    }
			}

			public class Programa
			{
			    public static void main (X[] args)
			    {
			        X a, b=new X("COTUCA"), c=new X("UNICAMP");

			        a = b.concat(new X("/").concat(c));

				System.out.println (a.toX());
			    }
			}

			public class Programa
			{
			    public static void main (X[] args)
			    {
			        X a, b="COTUCA", c="UNICAMP"; // proprio de wrapper, mas nao é wrapper

			        a = b+"/"+c; // proprio de wrapper, mas nao é wrapper

				System.out.println (a); // proprio de wrapper, mas nao é wrapper
			    }
			}

			public class Programa
			{
			    public static void main (X[] args)
			    {
			        int i=13;

			        X s=""+i; // truque para tranformar em X; estou aplicando a um int, mas poderia ser aplicado a qualquer coisa, objetos ou escalares

			        ...
			    }
			}

			public class Programa
			{
			    public static void main (X[] args)
			    {
			        X s="3.14";

				float piEscalar=Float.parseFloat(s); // nao tem truque; só mesmo usando um método
                                Float piObjeto =new Float (s);

			        ...
			    }
			}

*/
/*
...
String str = "COTUCA";
char   chr = str.charAt(2);
...
// o codigo acima é bem simples; suponham agora
// que queiramos SOFRER... oque fazer? como tornar
// DEMONIACO o codigo acima?
String str = "COTUCA";
Class<?> classe = str.getClass();
Integer parametroReal = 2; // 2 pq quero usar 2 como parametro do charAt
Class<?>[] tiposDosParametrosFormais = new Class<?>[1]; // 1 pq charAt tem 1 parametro
tiposDosParametrosFormais[0]=parametroReal.getClass();
Method metodo=classe.getMethod("charAt",tiposDosParametrosFormais);
Object[] parametrosReais = new Object [1]; // 1 pq charAt tem 1 parametro
parametrosReais[0] = parametroReal;
char chr = ((Character)metodo.invoke(str,parametrosReais)).charValue();
*/
import java.lang.reflect.*;

public class Pilha<X> implements Cloneable
{
    private Object[] vetor;
    private int qtd = 0;

    //versao preventiva
    public Pilha (int capacidade) throws Exception
    {
        if (capacidade<0)
            throw new Exception ("Capacidade invalida");

        this.vetor = new Object [capacidade];
    }

    //versao remediadora
    /*
    public Pilha (int capacidade) throws Exception
    {
        try
        {
            this.vetor = new Object [capacidade];
        }
        catch (NegativeArraySizeException erro)
        {
            throw new Exception ("Capacidade invalida");
        }
    }
    */

    private X meuCloneDeX (X x)
    {
		// agora, o que quero fazer dum jeito DEMONIACO é
		// return x.clone();
		X ret=null;

		try
		{
			Class<?> classe = x.getClass();
			Class<?>[] tiposDosParametrosFormais = null; // null pq clone tem 0 parametros
			Method metodo=classe.getMethod("clone",tiposDosParametrosFormais);
			Object[] parametrosReais = null; // null pq clone tem 0 parametros
			ret=(X)metodo.invoke(x,parametrosReais);
		}
		catch (NoSuchMethodException erro)
		{}
		catch (IllegalAccessException erro)
		{}
		catch (InvocationTargetException erro)
		{}
		return ret;
	}

    public void guarde (X s) throws Exception
    {
		if (s==null)
				throw new Exception ("Informacao ausente");

		if (this.isCheia())
				throw new Exception ("Pilha cheia");

        if (s instanceof Cloneable)
	        this.vetor[this.qtd] = meuCloneDeX(s);  // vai dar pau; tem que contornar
	    else
	        this.vetor[this.qtd] = s;

        this.qtd++;
    }

    public X getUmItem () throws Exception
    {
        if (this.isVazia())
            throw new Exception ("Nada a recuperar");

        if (this.vetor[this.qtd-1] instanceof Cloneable)
            return meuCloneDeX ((X)this.vetor[this.qtd-1]); // vai dar pau; tem que contornar

        return (X)this.vetor[this.qtd-1];

    }

    public void jogueForaUmItem () throws Exception
    {
	if (this.isVazia())
	{    Exception problema;
	     problema = new Exception ("Pilha vazia");
	     throw problema;
	}

        this.qtd--;
        this.vetor[this.qtd]=null;
    }

    public boolean isCheia ()
    {
        return this.qtd==this.vetor.length;
    }

    public boolean isVazia ()
    {
        return this.qtd==0;
    }

    public String toString ()
    {
		if (this.qtd==0)
		    return "Vazia";

		return this.qtd+" elementos, sendo o ultimo "+this.vetor[this.qtd-1];
	}

	//compara this e obj
	public boolean equals (Object obj)
	{
		if (this==obj)
		    return true;

		if (obj==null)
		    return false;

		if (this.getClass()!=obj.getClass())
		    return false;

		Pilha<X> pil = (Pilha<X>)obj;

		if (this.qtd!=pil.qtd)
		    return false;

		for (int i=0; i<this.qtd; i++)
		    if (!this.vetor[i].equals(pil.vetor[i]))
		        return false;

		return true;
	}

	public int hashCode ()
	{
		int ret=666; // so nao pode ser zero

		ret = ret*2 + new Integer(this.qtd).hashCode();

        for (int i=0; i<this.qtd; i++)
          //if (this.vetor[i]!=null)
				ret = ret*2 + this.vetor[i].hashCode();

		return ret;
	}

	//construtor de copia
	public Pilha (Pilha modelo) throws Exception
	{
		if (modelo==null)
			throw new Exception ("Modelo ausente");

		this.qtd = modelo.qtd;

		this.vetor = new Object [modelo.vetor.length];

		for (int i=0; i<=modelo.qtd; i++)
		    this.vetor[i] = modelo.vetor[i];
	}

	public Object clone ()
	{
		Pilha<X> ret=null;

		try
		{
			ret = new Pilha<X> (this);
		}
		catch (Exception erro)
		{}

		return ret;
	}
}