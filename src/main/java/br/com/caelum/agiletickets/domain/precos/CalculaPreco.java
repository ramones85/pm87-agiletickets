package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public interface CalculaPreco {
	public BigDecimal calculaPreco (Sessao sessao);
}
