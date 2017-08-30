package br.com.caelum.agiletickets.models;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import junit.framework.Assert;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}
	
	@Test
	public void CriaSessaoComDataInicialIgualAFinal () {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate();
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.DIARIA;
		List<Sessao> sessoes = new ArrayList<Sessao>();
		sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		
		Assert.assertEquals(1, sessoes.size());
	}
	
	@Test
	public void CriaSessaoDiariaComDataFinalMaiorDoQueDataInicial () {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(3);
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.DIARIA;
		List<Sessao> sessoes = new ArrayList<Sessao>();
		sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		
		Assert.assertEquals(4, sessoes.size());
	}
		
	@Test(expected = Exception.class)  
	public void CriaSessaoDiariaDataFinalMenorQueDataInicial () {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(-3);
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.DIARIA;
		List<Sessao> sessoes = new ArrayList<Sessao>();
		sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		
		Assert.assertEquals(0, sessoes.size());
	}
	
	@Test
	public void CriaSessaoSemanalComDataInicialMenorDoQueDataFinal() {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(14);
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		List<Sessao> sessoes = new ArrayList<Sessao>();
		sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		
		Assert.assertEquals(9, sessoes.size());
	}
	
	@Test(expected = Exception.class)  
	public void CriaSessaoSemanalComDataInicialMaiorDoQueDataFinal() {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(-14);
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		List<Sessao> sessoes = new ArrayList<Sessao>();
		sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		
		Assert.assertEquals(0, sessoes.size());
	}
	
	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	
	
}
