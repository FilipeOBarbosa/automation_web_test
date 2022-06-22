package kloktech.test.projetokloktech.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/*mapeamento de elementos de PageObjects */
public class Home {

    private WebDriver driver;

    @FindBy(how = How.CSS, using = "#twotabsearchtextbox")
    private WebElement seachBox;

    @FindBy(how = How.CSS, using = "#nav-search-submit-button")
    private WebElement btn;
    
    @FindBy(how = How.CSS, using = "#nav-hamburger-menu")
    private WebElement bntAll;

    @FindBy(how = How.CSS, using = ".hmenu-item.hmenu-compressed-btn")
    private WebElement bntSeeAll;

    @FindBy(how = How.CSS, using = ".nav-sprite.hmenu-arrow-next")
    private List<WebElement> bntInfo;

    //aqui utilizo xpath porque tinha varias opções aparecendo e não tinha como saber o valor certo com css.selector
    @FindBy(how = How.XPATH, using = ".//*[@id='hmenu-content']/ul[16]/li[10]/a")
    private WebElement bntPcComponent;

    @FindBy(how = How.CSS, using = ".a-color-base.a-link-normal>span")
    private List<WebElement> filter;

    //construtor
    public Home(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    
    //método de busca
    public void seachMethod(String seachText){
        this.seachBox.sendKeys(seachText);
        this.btn.click();
    }

    //método de clicar na opção Todos
    public void clickAll(){
        this.bntAll.click();
    }

    //método de clicar na opção ver todos, apos o barra lateral surgir
    public void clickSeeAll(){
        this.bntSeeAll.click();
    }

    //método para clicar na opão de informatica
    public void clickOption(int value){
        WebElement webElement = this.bntInfo.get(value);
        webElement.click();
    }

    //método para clicar na opção de componentes para computador
    public void clickPcComponent(){;
        this.bntPcComponent.click();
    }

    //filtragem para placa de videos
    public void filterSelector(int value){
        WebElement webElement = this.filter.get(value);
        webElement.click();
    }

}
