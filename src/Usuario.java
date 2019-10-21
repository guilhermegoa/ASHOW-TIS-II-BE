
public abstract class Usuario {
	private static int contador;
	private String senha;
	private String email;
	private String nome;
	private int ID;
	private float mediaAvaliacao;
	private Endereco endereco;
	
	public abstract void addAvaliacao();
	public abstract float calculaMediaAvaliacao();
}
