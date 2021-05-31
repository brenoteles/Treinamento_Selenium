import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Treinamento {
	private WebDriver driver;
	
	//Sempre ser� no inicio do teste
	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.driver", "PATH_WEBDRIVER");
		driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
	}
	
	//Sempre ser� executado no final do teste
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void testeTextField() {		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("teste");
		Assert.assertEquals("teste", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	}
	
	@Test
	public void testeTextArea() {		
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Ol�\nMinhas sugest�es s�o:\n Tome agua\nPratique Exerc�cios");
		Assert.assertEquals("Ol�\nMinhas sugest�es s�o:\n Tome agua\nPratique Exerc�cios", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

	}
	
	@Test
	public void testeRadioButton() {	
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertEquals(true, driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
	}
	
	@Test
	public void testeCheckBox() {
		driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();	
		Assert.assertEquals(true, driver.findElement(By.id("elementosForm:comidaFavorita:1")).isSelected());
	}
	
	@Test
	public void testeCombo() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		combo.selectByVisibleText("2o grau completo");
		Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
	}
	
	@Test
	public void testeComboMultiplo() {	
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Futebol");
		
		List<WebElement> AllSelectedOptions = combo.getAllSelectedOptions();
		
		Assert.assertEquals(2, AllSelectedOptions.size());
	}
	
	@Test
	public void testeButton() {		
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
	}
	
	@Test
	@Ignore
	public void testeLink() {
		driver.findElement(By.linkText("Voltar")).click();
		
	}
	
	@Test
	public void testeTitulo() {
		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText()); 
	}
	
	@Test
	public void testeAlertSimples() {		
		driver.findElement(By.id("alert")).click();
		
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Alert Simples", alert.getText());
}	
	@Test
	public void testeAlertConfirm() {
		driver.findElement(By.id("confirm")).click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
	
		Assert.assertEquals("Confirmado", alert.getText());
	}
	
	@Test
	public void testePrompt() {
		driver.findElement(By.id("prompt")).click();
		
		Alert alert = driver.switchTo().alert();
		alert.sendKeys("12");
		alert.accept();
		
		Assert.assertEquals("Era 12?", alert.getText());
	}
	
	@Test
	public void testeCadastro() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Jo�o");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Silva");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Mestrado");
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Natacao");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Jo�o"));
		Assert.assertEquals("Sobrenome: Silva", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Pizza", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: mestrado", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Natacao", driver.findElement(By.id("descEsportes")).getText());
		
	}
}
