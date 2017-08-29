package br.com.caelum.agiletickets.models;

import org.junit.Assert;
import org.junit.Test;

public class SessaoTest {

	@Test
	public void deveVender1ingressoSeHa2vagas() throws Exception {
		Sessao sessao = new Sessao();
        sessao.setTotalIngressos(2);

        Assert.assertTrue(sessao.podeReservar(1));
	}
	
	@Test
	public void deveVender5ingressosSeHa1vagas() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(1);
		
		Assert.assertFalse(sessao.podeReservar(5));
	}
	
	@Test
	public void deveVender10IngressosSeHa10Vagas() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(10);
		
		Assert.assertTrue(sessao.podeReservar(10));
	}
	
}
