package normalizacao.dados;

/**
* Entity class for card.
* 
* 
* @author Vilmar Santos Nepomuceno
* @version 1.0
*/
public class Carta {
	String nome;
	int posicao;
	boolean isBranca;

	public Carta() {
		this.isBranca = true;
		this.nome = "";
		this.posicao = 0;
	}

	public Carta(String nome, int posicao) {
		this.isBranca = false;
		this.nome = nome;
		this.posicao = posicao;
	}
}
