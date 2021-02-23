package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	public WebDriver acessarAplicacao() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "/Users/crisleicon/workspaces/seleniumDrivers/chromedriver");
//		WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.15.174:4444/wd/hub"),cap);
		driver.navigate().to("http://192.168.15.174:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			//clica em Add Todo
			driver.findElement(By.id("addTodo")).click();
			//escreve descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			//escreve a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");
			//clica em salvar
			driver.findElement(By.id("saveButton")).click();
			//validar mensagem de sucess
			String msg = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", msg);
		}finally {
			driver.quit();	
		}
		
	}
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			//clica em Add Todo
			driver.findElement(By.id("addTodo")).click();
			//escreve a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");
			//clica em salvar
			driver.findElement(By.id("saveButton")).click();
			//validar mensagem de sucess
			String msg = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", msg);
		}finally {
			driver.quit();	
		}
		
	}
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			//clica em Add Todo
			driver.findElement(By.id("addTodo")).click();
			//escreve descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			//clica em salvar
			driver.findElement(By.id("saveButton")).click();
			//validar mensagem de sucess
			String msg = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", msg);
		}finally {
			driver.quit();	
		}
		
	}
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			//clica em Add Todo
			driver.findElement(By.id("addTodo")).click();
			//escreve descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			//escreve a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2019");
			//clica em salvar
			driver.findElement(By.id("saveButton")).click();
			//validar mensagem de sucess
			String msg = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", msg);
		}finally {
			driver.quit();	
		}
		
	}
	
}
