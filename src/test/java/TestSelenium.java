
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestSelenium {

  private WebDriver driver;


  @BeforeClass
  public void getUp(){
    System.setProperty("webdriver.chrome.driver","src/test/Drivers/Chrome/chromedriver.exe");
    driver = new ChromeDriver();
    driver.get("https://stackoverflow.com/questions/57455436/using-object-to-embed-svg-but-doesnt-show-anything");
    driver.manage().window().maximize();
  }

  @Test
  public void TestSeleniumText()
  {
    WebElement question = driver.findElement(By.id("question-header"));
    String actualText = question.getText();
    System.out.println("Texto Actual: "+actualText);

   Assert.assertFalse(actualText.isEmpty(),"El elemento esta vacio");

  }

  @Test
  public void TestSeleniumVotos()
  {
    WebElement question = driver.findElement(By.xpath("//*[@id='question']/div[2]/div[1]/div/div[2]"));
    String actualText = question.getText();
    System.out.println("Texto Actual: "+actualText);

    Assert.assertFalse(actualText.isEmpty(),"El elemento esta vacio");

  }

  ////div/div/div[@class='answer js-answer'][2]

  @Test
  public void TestSeleniumRespuestas() {
    int numeroResp= driver.findElements(By.xpath("//div/div/div[@class='answer js-answer']")).size();
    System.out.println("contenido "+ numeroResp);

  }
  @Test
  public void TestSeleniumVotosMayor()
  {
    int numeroElementos = driver.findElements(By.xpath("//div/div/div[@class='answer js-answer']")).size();;
    //int votos=0;
    String votos="";
    int votosMax =0;
    int respuesta=0;
    for(int i=1; i<=numeroElementos;i++) {
      WebElement answer = driver.findElement(By.xpath("//div/div/div[@class='answer js-answer']["+i+"]"));

      votos = answer.getText();
      char caracter= votos.charAt(0);
      int num = Character.getNumericValue(caracter);
      //System.out.println("contenido "+ num);
      if(num>votosMax) {
        votosMax = num;
       respuesta++;
      }


      Assert.assertFalse(votos.isEmpty(), "El elemento esta vacio");
    }
    System.out.println("La respuesta con mas votos es la numero : " + respuesta +" con un total de "+votosMax +" votos");
  }

////*[@id="question"]/div[2]/div[2]/div[3]/div/div[2]/div

  @Test
  public void TestSeleniumUsuario()
  {
    WebElement question = driver.findElement(By.xpath(" //*[@id='answers']/div/div/div[2]/div[2]/div/div[2]/div/div[3]/a"));
    String actualText = question.getText();
    System.out.println("Texto Actual: "+actualText);

    Assert.assertFalse(actualText.isEmpty(),"El elemento esta vacio");

  }

  @AfterClass
  public void tearDown(){
    if(driver!=null){
      driver.quit();
    }
  }




}
