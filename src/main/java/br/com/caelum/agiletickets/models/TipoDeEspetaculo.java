package br.com.caelum.agiletickets.models;

import br.com.caelum.agiletickets.domain.precos.CalculaPreco;
import br.com.caelum.agiletickets.domain.precos.CalculaPrecoEventos;
import br.com.caelum.agiletickets.domain.precos.CalculaPrecoEventosNormais;
import br.com.caelum.agiletickets.domain.precos.CalculaPrecoEventosVIP;

public enum TipoDeEspetaculo {
	
	CINEMA(new CalculaPrecoEventosNormais()), 
	SHOW(new CalculaPrecoEventosNormais()),	 
	BALLET(new CalculaPrecoEventosVIP()), 
	ORQUESTRA(new CalculaPrecoEventosVIP()),
	TEATRO(new CalculaPrecoEventos());
	
	private CalculaPreco eventos;
	private TipoDeEspetaculo (CalculaPreco eventos) {
		this.eventos = eventos;
	}
	
	public CalculaPreco getEventos() {
		return eventos;
	}
}
