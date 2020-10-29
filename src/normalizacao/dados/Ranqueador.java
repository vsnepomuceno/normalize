package normalizacao.dados;

import java.util.ArrayList;

/**
* This class is responsible for rank the cards.
* 
* 
* @author Vilmar Santos Nepomuceno
* @version 1.0
*/
public class Ranqueador {
	public ArrayList<CartaPosicionavel> cartas;
	public ArrayList<CartaPosicionavel> cartasOrdenadas;
	public Normalizacao normalizacao;

	public Ranqueador(int quantidade) {
		this.cartas = new ArrayList<CartaPosicionavel>(quantidade);
		for (int i = 0; i < quantidade; i++) {
			this.cartas.add(new CartaPosicionavel());
		}
		this.cartasOrdenadas = new ArrayList<CartaPosicionavel>();
	}

	public void limparOrdem() {
		for (CartaPosicionavel c : this.cartasOrdenadas) {
			c.setDisponivel(true);
			c.setUltimaCarta();
		}
		this.cartasOrdenadas = new ArrayList<CartaPosicionavel>();
	}

	public void setNormalizacao() {
		this.normalizacao = new Normalizacao();
		Subconjunto s = new Subconjunto();

		int posicao = 1;
		for (CartaPosicionavel c : this.cartasOrdenadas) {
			Carta carta = new Carta(c.getNome(), posicao++);
			s.addCarta(carta);
			if (c.getIntensidade() == 0)
				continue;
			if (c.getIntensidade() > 1) {
				this.normalizacao.addSubconjunto(s);
				s = new Subconjunto();
				for (int i = 0; i < c.getIntensidade() - 1; i++) {
					carta = new Carta();
					s.addCarta(carta);
					posicao++;
				}
			}
			this.normalizacao.addSubconjunto(s);
			s = new Subconjunto();
		}
		this.normalizacao.calculaNormalizados();
	}

	public void addCartaOrdenada(CartaPosicionavel c) {
		if (this.cartas.contains(c) && !this.cartasOrdenadas.contains(c)) {

			int tamanhoAtual = this.cartasOrdenadas.size();
			if (tamanhoAtual > 0) {
				CartaPosicionavel ultima = this.cartasOrdenadas.get(tamanhoAtual - 1);
				ultima.setIntensidade(1);
			}
			this.cartasOrdenadas.add(c);
		}
	}

	public void removerCartaOrdenada(CartaPosicionavel c) {
		if (this.cartas.contains(c) && this.cartasOrdenadas.contains(c)) {

			int tamanhoAtual = this.cartasOrdenadas.size();
			if (tamanhoAtual > 1 && this.cartasOrdenadas.indexOf(c) == tamanhoAtual - 1) {
				CartaPosicionavel penultima = this.cartasOrdenadas.get(tamanhoAtual - 2);
				penultima.setUltimaCarta();
			}
			this.cartasOrdenadas.remove(c);
		}
	}

	public String toString() {
		String ordem = "";
		for (CartaPosicionavel c : this.cartasOrdenadas) {
			ordem = ordem + c.toString() + " ";
		}
		return ordem.trim();
	}
}
