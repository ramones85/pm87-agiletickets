package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class CalculaPrecoEventos implements CalculaPreco {
	public BigDecimal calculaPreco (Sessao sessao) {
		return sessao.getPreco();
	}

}
