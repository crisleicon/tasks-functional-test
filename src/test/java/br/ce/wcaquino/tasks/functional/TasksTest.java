package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	public WebDriver acessarAplicacao() {
		System.setProperty("webdriver.chrome.driver", "/Users/crisleicon/workspaces/seleniumDrivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
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
	public void naoDeveSalvarTarefaSemDescricao() {
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
	public void naoDeveSalvarTarefaSemData() {
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
	public void naoDeveSalvarTarefaComDataPassada() {
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
