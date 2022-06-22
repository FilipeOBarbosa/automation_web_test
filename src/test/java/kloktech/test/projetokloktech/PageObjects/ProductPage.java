package kloktech.test.projetokloktech.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

    private WebDriver driver;

    @FindBy(how = How.CSS, using = "#productTitle")
    private WebElement textProduct;

    //construtor
    public ProductPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String verifiText(){
        return this.textProduct.getText().toLowerCase();
    }
}
