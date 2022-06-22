package kloktech.test.projetokloktech.PageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    
    private WebDriver driver;
    
    @FindBy(how = How.CSS, using = ".a-size-base-plus.a-color-base.a-text-normal")
    private WebElement textElement;

    @FindBy(how = How.CSS, using = ".s-image")
    private WebElement imageBnt;

    @FindBy(how = How.CSS, using = ".a-size-base-plus.a-color-base.a-text-normal")
    private List<WebElement> textList;

    //construtor
    public ResultPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //método de retorno do primeiro do texto do primeiro elemento
    public String textVerifiElement(){
        return this.textElement.getText().toLowerCase();
    }

    //método de clicar no produto
    public void clickElement(){
        this.imageBnt.click();
    }

    //método para busca de produto relacionado
    public String listWebElement(int value){
        WebElement webElement = this.textList.get(value);
		return webElement.getText().toLowerCase();
    }


}
