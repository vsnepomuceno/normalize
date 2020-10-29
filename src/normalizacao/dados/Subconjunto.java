package normalizacao.dados;

import java.util.ArrayList;


/**
* Entity class for a card subset.
* 
* 
* @author Vilmar Santos Nepomuceno
* @version 1.0
*/
public class Subconjunto {
	ArrayList<Carta> cartas = new ArrayList<Carta>();
	int er = 0;
	float kr = 0.0F;
	float ki = 0.0F;
	float kt = 0.0F;
	float di = 0.0F;
	float da = 0.0F;
	float n = 0.0F;

	public void setDs() {
		this.di = (0.1F - this.ki - this.kt) / this.ki;
		this.da = (this.ki - this.kt) / this.ki;
	}

	public void setKs(float klinha) {
		this.ki = 100.0F / klinha * this.kr;
		setKt();
	}

	public void setN(float klinha) {
		setKs(klinha);
		setDs();
		if (this.di > this.da) {
			this.n = this.kt;
		} else {

			this.n = this.kt + 0.1F;
		}
	}

	private void setKt() {
		String kiStr = String.valueOf(this.ki);
		int iPoint = kiStr.indexOf('.');
		this.kt = Float.parseFloat(kiStr.substring(0, iPoint + 2));
	}

	public void addCarta(Carta c) {
		this.cartas.add(c);
	}

	public float getNextKr(float u) {
		float sumEr = (this.kr - 1.0F) / u;
		return (sumEr + this.er) * u + 1.0F;
	}

	public float getKlinha() {
		return this.kr * getSize();
	}

	public int getSize() {
		return this.cartas.size();
	}

	public float getSomaPosicoes() {
		float soma = 0.0F;
		for (Carta c : this.cartas)
			soma += c.posicao;
		return soma;
	}

	public int getWhiteCards() {
		int size = 0;
		for (Carta c : this.cartas) {
			if (c.isBranca)
				size++;
		}
		return size;
	}

	public String getNome() {
		String nome = "";
		for (Carta c : this.cartas) {
			nome = nome + ", " + c.nome;
		}
		return nome.substring(2);
	}

	public float getNonNormalized() {
		return getSomaPosicoes() / getSize();
	}

	public int getNormalized(int somaPosicoes) {
		float value = getNonNormalized() / somaPosicoes * 100.0F;
		this.n = Math.round(value);

		return (int) this.n;
	}

	public int getTotal(int somaPosicoes) {
		return getSize() * getNormalized(somaPosicoes);
	}
}
