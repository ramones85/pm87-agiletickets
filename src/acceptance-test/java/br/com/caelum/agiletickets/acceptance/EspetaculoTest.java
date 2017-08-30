package br.com.caelum.agiletickets.acceptance;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.caelum.agiletickets.acceptance.page.EstabelecimentosPage;
import junit.framework.Assert;

public class EspetaculoTest {

	public static String BASE_URL = "http://localhost:8080";
	private static WebDriver browser;
	private EstabelecimentosPage estabelecimentos;

	@BeforeClass
	public static void abreBrowser() {
		System.setProperty("webdriver.gecko.driver", "geckodriver");
		browser = new FirefoxDriver();
	}

	@Before
	public void setUp() throws Exception {
		estabelecimentos = new EstabelecimentosPage(browser);
	}

	@AfterClass
	public static void teardown() {
		browser.close();
	}
/*
	@Test
	public void aoAdicionarUmEstabelecimentoDeveMostraLoNaTabela() throws Exception {
		estabelecimentos.abreListagem();

		estabelecimentos.adicioneEstabelecimento("Caelum", "R. Vergueiro, 3185");

		estabelecimentos.ultimaLinhaDeveConter("Caelum", "R. Vergueiro, 3185");
	}

	@Test
	public void aoAdicionarUmEstabelecimentoSemNomeDeveMostrarErro() throws Exception {
		estabelecimentos.abreListagem();

		estabelecimentos.adicioneEstabelecimento("", "R. Vergueiro, 3185");

		estabelecimentos.deveMostrarErro("O nome não pode ser vazio");
	}

	@Test
	public void aoAdicionarUmEstabelecimentoSemEnderecoDeveMostrarErro() throws Exception {
		estabelecimentos.abreListagem();

		estabelecimentos.adicioneEstabelecimento("Caelum", "");

		estabelecimentos.deveMostrarErro("O endereco não pode ser vazio");
	}

	@Test
	public void mostraQueHaEstacionamentoQuandoCadastramosQueSim() throws Exception {
		estabelecimentos.abreListagem();

		estabelecimentos.adicioneEstabelecimentoComEstacionamento(true);

		estabelecimentos.ultimaLinhaDeveTerEstacionamento(true);
	}

	@Test
	public void mostraQueNaoHaEstacionamentoQuandoCadastramosQueNao() throws Exception {
		estabelecimentos.abreListagem();

		estabelecimentos.adicioneEstabelecimentoComEstacionamento(false);

		estabelecimentos.ultimaLinhaDeveTerEstacionamento(false);
	}*/
	
	@Test
	public void testaInclusaoDeEstabelecimento () throws Exception {
		System.setProperty("webdriver.gecko.driver", "geckodriver");
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8080/espetaculos");
		WebElement form = driver.findElement(By.id("addForm"));
		form.findElement(By.name("espetaculo.nome")).sendKeys("Xou da Xuxa");
		form.findElement(By.name("espetaculo.descricao")).sendKeys("Esse é o Xou da Xuxa");
		form.findElement(By.name("espetaculo.tipo")).sendKeys("Teatro");
		form.findElement(By.name("espetaculo.estabelecimento.id")).sendKeys("Casa de Shows da Xuxa");
		form.submit();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementValue(By.name("espetaculo.nome"), "Xou da Xuxa")));
		WebElement linha = driver.findElement(By.cssSelector("table tbody tr:last-child"));
		List<WebElement> colunas  = new ArrayList<WebElement>();
		colunas = linha.findElements(By.cssSelector("td"));
		Assert.assertEquals("Xou da Xuxa", colunas.get(1).getText());
		Assert.assertEquals("Esse é o Xou da Xuxa", colunas.get(2).getText());
		Assert.assertEquals("TEATRO", colunas.get(3).getText());
		driver.close();
		EntityManagerFactory efm = Persistence.createEntityManagerFactory("default");
		EntityManager manager = efm.createEntityManager();
		manager.getTransaction().begin();
		manager.createNativeQuery("delete from Espetaculo where nome='Xou da Xuxa'").executeUpdate();
		manager.getTransaction().commit();
		
	}
	@Test
	public void testaInclusaoDeEstabelecimentoSemDescricao () throws Exception {
		System.setProperty("webdriver.gecko.driver", "geckodriver");
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8080/espetaculos");
		WebElement form = driver.findElement(By.id("addForm"));
		form.findElement(By.name("espetaculo.nome")).sendKeys("Xou da Xuxa");
		form.findElement(By.name("espetaculo.tipo")).sendKeys("Teatro");
		form.findElement(By.name("espetaculo.estabelecimento.id")).sendKeys("Casa de Shows da Xuxa");
		form.submit();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementValue(By.name("espetaculo.nome"), "Xou da Xuxa")));
		
		WebElement linha = driver.findElement(By.cssSelector("#errors>li"));
		System.out.println(linha.getText());
		
		Assert.assertEquals("Descrição do espetáculo não pode estar em branco",linha.getText());
		
		
		
		driver.close();
		
		EntityManagerFactory efm = Persistence.createEntityManagerFactory("default");
		EntityManager manager = efm.createEntityManager();
		manager.getTransaction().begin();
		manager.createNativeQuery("delete from Espetaculo where nome='Xou da Xuxa'").executeUpdate();
		manager.getTransaction().commit();
		
	}
}
