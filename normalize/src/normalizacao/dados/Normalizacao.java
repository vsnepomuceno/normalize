package normalizacao.dados;

import java.util.ArrayList;
import normalizacao.gui.NormalizacaoAlterLabel;


/**
* This class is responsible for normalize
* the cards position.
* 
* 
* @author Vilmar Santos Nepomuceno
* @version 1.0
*/
public class Normalizacao {
	ArrayList<Subconjunto> subconjuntos = new ArrayList<Subconjunto>();

	public int getSomaPosicoes() {
		int soma = 0;
		for (Subconjunto s : this.subconjuntos) {
			soma = (int) (soma + s.getSomaPosicoes());
		}
		return soma;
	}

	public int getT() {
		int t = 0;
		for (Subconjunto s : this.subconjuntos) {
			t += s.getSize();
		}
		return t;
	}

	public int getTotal() {
		int total = 0;
		int somaPosicoes = getSomaPosicoes();
		for (Subconjunto s : this.subconjuntos) {
			total += s.getTotal(somaPosicoes);
		}
		return total;
	}

	public int getE() {
		int e = 0;
		for (int i = 0; i < this.subconjuntos.size() - 1; i++) {
			if (((Subconjunto) this.subconjuntos.get(i)).getWhiteCards() == 0) {
				((Subconjunto) this.subconjuntos.get(i)).er = ((Subconjunto) this.subconjuntos.get(i + 1))
						.getWhiteCards() + 1;
				e += ((Subconjunto) this.subconjuntos.get(i)).er;
			}
		}
		return e;
	}

	public void setKrs() {
		float u = getU();
		((Subconjunto) this.subconjuntos.get(0)).kr = 1.0F;
		for (int i = 1; i < this.subconjuntos.size(); i++) {
			((Subconjunto) this.subconjuntos.get(i)).kr = ((Subconjunto) this.subconjuntos.get(i - 1)).getNextKr(u);
		}
	}

	public void calculaNormalizados() {
		int total = getTotal();
		if (total != 100) {
			float klinha = getKlinha();
			for (Subconjunto s : this.subconjuntos) {
				s.setN(klinha);
			}
		}
	}

	public float getKlinha() {
		setKrs();
		float klinha = 0.0F;
		for (Subconjunto s : this.subconjuntos) {
			if (s.getWhiteCards() == 0)
				klinha += s.getKlinha();
		}
		return klinha;
	}

	public float getU() {
		return (getZ() - 1.0F) / getE();
	}

	public float getZ() {
		int t = getT();
		int p = ((Subconjunto) this.subconjuntos.get(0)).getSize();
		int q = ((Subconjunto) this.subconjuntos.get(this.subconjuntos.size() - 1)).getSize();
		float num = 0.0F;
		float den = 0.0F;
		int i;
		for (i = 0; i < q; i++) {
			num += (t - i);
		}
		num *= p;
		for (i = 0; i < p; i++) {
			den += (1 + i);
		}
		den *= q;
		return num / den;
	}

	public void addSubconjunto(Subconjunto s) {
		this.subconjuntos.add(s);
	}

	public String toString() {
		float soma = 0.0F;
		String resultado = "Subconjunto\tAvalia��o Normalizada\tTotal\n";
		for (Subconjunto s : this.subconjuntos) {
			if (s.getWhiteCards() == 0) {
				float total = s.n * s.getSize();
				String totalStr = String.valueOf(total);
				int iPoint = totalStr.indexOf('.');
				if (iPoint > 0) {
					total = Float.parseFloat(totalStr.substring(0, iPoint + 2));
				}
				soma += total;
				resultado = resultado + s.getNome() + "\t" + s.n + "\t" + total + "\n";
			}
		}
		resultado = resultado + "---\t---\t" + soma;

		return resultado.replace('.', ',');
	}

	public static void normalize(ArrayList<NormalizacaoAlterLabel> alterLabels) {
		double maxU = 0.0D;
		double minU = 0.0D;

		maxU = Integer.parseInt(((NormalizacaoAlterLabel) alterLabels.get(0)).getuValue());
		minU = Integer.parseInt(((NormalizacaoAlterLabel) alterLabels.get(0)).getuValue());

		for (NormalizacaoAlterLabel label : alterLabels) {
			int tmp = Integer.parseInt(label.getuValue());
			if (tmp > maxU) {
				maxU = tmp;
				continue;
			}
			if (tmp < minU) {
				minU = tmp;
			}
		}

		for (NormalizacaoAlterLabel label : alterLabels) {
			double tmp = Integer.parseInt(label.getuValue());
			double uNorm = (maxU - tmp) / (maxU - minU) * 100.0D;
			int uNormTrunc = (int) (uNorm * 100.0D);
			uNorm = uNormTrunc / 100.0D;
			label.setuValueNormalized(Double.valueOf(uNorm));
		}
	}
}
