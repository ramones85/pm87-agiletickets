package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class CalculaPrecoEventosVIP implements CalculaPreco {
	public BigDecimal calculaPreco(Sessao sessao) {
		BigDecimal preco;
		if(sessao.percentualIngressosDisponiveis() <= 0.50) { 
			preco = sessao.aumentaPreco(0.20);
		} else {
			preco = sessao.getPreco();
		}			
		if(sessao.getDuracaoEmMinutos() > 60){
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		} 
		return preco;
	}

}
