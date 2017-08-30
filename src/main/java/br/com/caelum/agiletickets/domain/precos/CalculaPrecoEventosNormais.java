package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class CalculaPrecoEventosNormais implements CalculaPreco {
	public BigDecimal calculaPreco(Sessao sessao) {
		BigDecimal preco;
		if(sessao.percentualIngressosDisponiveis() <= 0.05) { 
			preco = sessao.aumentaPreco(0.10);
		} else {
			preco = sessao.getPreco();
		} 
		return preco;
	}
}
