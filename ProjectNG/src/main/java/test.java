import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class test {
  @BeforeMethod
  public void configuracionPrevia() {
    // Código para configurar el entorno antes de cada caso de prueba
    System.out.println("Configuración previa a la prueba");
  }

  @Test
  public void casoDePrueba1() {
    // Código de prueba 1
    System.out.println("Caso de prueba 1");
  }

  @Test
  public void casoDePrueba2() {
    // Código de prueba 2
    System.out.println("Caso de prueba 2");
  }

  @AfterMethod
  public void limpiezaPosterior() {
    // Código para limpiar el entorno después de cada caso de prueba
    System.out.println("Limpieza posterior a la prueba");
  }
  }
