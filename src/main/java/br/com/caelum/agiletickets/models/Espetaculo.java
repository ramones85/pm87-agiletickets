package br.com.caelum.agiletickets.models;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Weeks;

@Entity
public class Espetaculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String descricao;

	@Enumerated(EnumType.STRING)
	private TipoDeEspetaculo tipo;

	@ManyToOne
	private Estabelecimento estabelecimento;
	
	@OneToMany(mappedBy="espetaculo")
	private List<Sessao> sessoes = newArrayList();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoDeEspetaculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeEspetaculo tipo) {
		this.tipo = tipo;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}
	
	public List<Sessao> getSessoes() {
		return sessoes;
	}
	
	/**
     * Esse metodo eh responsavel por criar sessoes para
     * o respectivo espetaculo, dado o intervalo de inicio e fim,
     * mais a periodicidade.
     * 
     * O algoritmo funciona da seguinte forma:
     * - Caso a data de inicio seja 01/01/2010, a data de fim seja 03/01/2010,
     * e a periodicidade seja DIARIA, o algoritmo cria 3 sessoes, uma 
     * para cada dia: 01/01, 02/01 e 03/01.
     * 
     * - Caso a data de inicio seja 01/01/2010, a data fim seja 31/01/2010,
     * e a periodicidade seja SEMANAL, o algoritmo cria 5 sessoes, uma
     * a cada 7 dias: 01/01, 08/01, 15/01, 22/01 e 29/01.
     * 
     * Repare que a data da primeira sessao é sempre a data inicial.
     */
	public List<Sessao> criaSessoes(LocalDate inicio, LocalDate fim, LocalTime horario, Periodicidade periodicidade)
throws IllegalArgumentException {
		List<Sessao> sessoes = new ArrayList<Sessao>();
		
		int diferenca = 0;
		if (periodicidade.equals(Periodicidade.DIARIA)) {
			diferenca = Days.daysBetween(inicio, fim).getDays();
		} else {
			diferenca = Weeks.weeksBetween(inicio, fim).getWeeks();
		}
		
		if (diferenca < 0) {
			throw new IllegalArgumentException("20 anos de curso, porra");
		}
				
		for(int i=0; i<=diferenca; i++){
			DateTime inicioSessao;
			if (periodicidade.equals(Periodicidade.DIARIA)) {
				inicioSessao = inicio.plusDays(i).toDateTime(horario);
			} else {
				inicioSessao = inicio.plusWeeks(i).toDateTime(horario);
			}
			Sessao sessao = new Sessao(inicioSessao);
			sessoes.add(sessao);
		}		
		return sessoes;
	}
	
	public boolean Vagas(int qtd, int min)
   {
       int totDisp = 0;

       for (Sessao s : sessoes)
       {
           if (s.getIngressosDisponiveis() < min) return false;
           totDisp += s.getIngressosDisponiveis();
       }

       if (totDisp >= qtd) return true;
       else return false;
   }

   public boolean Vagas(int qtd)
   {
       int totDisp = 0;

       for (Sessao s : sessoes)
       {
           totDisp += s.getIngressosDisponiveis();
       }

       if (totDisp >= qtd) return true;
       else return false;
   }

}
