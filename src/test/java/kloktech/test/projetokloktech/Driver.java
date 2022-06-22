package kloktech.test.projetokloktech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import kloktech.test.projetokloktech.PageObjects.Home;
import kloktech.test.projetokloktech.PageObjects.ProductPage;
import kloktech.test.projetokloktech.PageObjects.ResultPage;

/*Estou usando Page Object para mapear e reutilizar elementos, e todas as boas praticas que conheço,
 * nenhum teste depende do outro.
 */
@TestMethodOrder(OrderAnnotation.class)
public class Driver {

	static WebDriver driver;
	
	//Preparação de ambiente, estanciar propriedades e localização do ChromeDriver
	@BeforeAll
	static void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//5 segundos para achar o elemento
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

		//maximizar navegador
		driver.manage().window().maximize();
	}

	//teste principal: Pesquisa de produto por texto
	@Test
	@Order(1)
	public void automationWebMainFlowTest(){
		//acessando página e buscando elemento de pesquisa
		driver.get("https://www.amazon.com.br");

		//Utilizando os elementos mapeados e metodo de pesquisa
		Home home = new Home(driver);
		home.seachMethod("placa de video RTX");

		//confirmação se o titulo bate com o que foi pesquisado utilizando elementos mapeados da ResultPage
		ResultPage resultPage = new ResultPage(driver);
		String result = resultPage.textVerifiElement();

		assertTrue(result.contains("placa de video"));

		//caso que falhe
		assertFalse(result.contains("copo"));
		
		//clicando no produto
		resultPage.clickElement();
		
		//verificação final do produto, testar se está mesmo relacionado
		ProductPage productPage = new ProductPage(driver);
		String resultVerifi = productPage.verifiText();

		assertTrue(resultVerifi.contains("placa de video"));
	}

	//teste para conferir que ao menos um produto adjacente seja relacionado
	@Test
	@Order(2)
	public void automationWebAlternateFlowTest(){
		//refazendo a navegação e a busca de elemento, para que um teste não dependa de outro
		driver.get("https://www.amazon.com.br");
		Home home = new Home(driver);
		home.seachMethod("placa de video RTX");

		//Encontrando elemento específico pelo cssSelector
		ResultPage resultPage = new ResultPage(driver);
		String result = resultPage.listWebElement(1);

		//Verificar relação
		assertAll(()-> assertEquals(true,result.contains("rtx")),
				()-> assertEquals(true, result.contains("placa")));
	}

	/*teste alternativo de busca de produto relacionado a placa de video,
	 * e utilizando outra forma de busca, por outros elementos
	*/
	@Test
	@Order(3)
	public void automationWebAlternateFlowSecundaryTest(){
		driver.get("https://www.amazon.com.br");
		Home home = new Home(driver);

		/*aqui tem a parte da navegação atraves dos navbar, até a parte de placas de video,
		 adicionei thread sleep para que fique melhor de acompanhar
		*/

		//clica em todos
		home.clickAll();
		timeSleep();

		//clica em ver todos
		home.clickSeeAll();
		timeSleep();

		//clica em uma das opções
		home.clickOption(14);
		timeSleep();

		//clica no componente de computador
		home.clickPcComponent();
		timeSleep();

		//clical no filtro de placa de video
		home.filterSelector(10);
		timeSleep();

		//reutilização de resultPage
		ResultPage resultPage = new ResultPage(driver);
		String result = resultPage.textVerifiElement();

		//verificão de produto relacionado
		assertTrue(result.contains("placa de vídeo"));
	}

	public void timeSleep(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//encerramento, finaliza a execução do driver
	@AfterAll
	static void tearDown(){
		driver.close();
		driver.quit();
	}
}
