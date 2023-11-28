import Interviewer.Interviewer;
import Interviewer.Calculator;
import Interviewer.Menu;
//import org.junit.*;
import org.junit.Assume;
import org.junit.jupiter.api.DisplayName;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

//import static org.junit.Assert.*;
import static org.testng.Assert.*;

public class test {

  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;

  private ByteArrayInputStream testIn;
  private ByteArrayOutputStream testOut;

  public Calculator calculator;

  @BeforeGroups (groups = {"grupo1"})
  public void setUpOutput() {
    testOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(testOut));
  }

  private void provideInput(String data) {
    testIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(testIn);
  }

  private String getOutput() {
      return testOut.toString();
  }

  @BeforeGroups (groups = {"grupo3","grupo4","EnvDev"})
  public void setUp(){
    calculator = new Calculator();
  }

  @AfterSuite
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }


  @Test(groups = "grupo1")
  public class Grupo1 {


    public void addNewInterviewer() { //Agrega un entrevistador y valida si contiene los datos
      final String interviewerName = "Nombre";
      final String interviewerLastName = "Apellido";
      final String interviewerEmail = "mail@mail.com";
      final String addNewInterviewerCommand = "1 \n " + interviewerName + " \n " + interviewerLastName + " \n " + interviewerEmail + " \n 1 \n 3 \n";
      provideInput(addNewInterviewerCommand);

      Menu.main(new String[0]);
      final String output = getOutput();

      assertTrue(output.contains(interviewerName));
      assertTrue(output.contains(interviewerLastName));
      assertTrue(output.contains(interviewerEmail));
    }


    public void getInterviewer() { //Validar que el entrevistador se registró correctamente
      final String interviewerName = "Jose";
      final String interviewerLastName = "Sosa";
      final String interviewerEmail = "jose@mail.com";
      final String addNewInterviewerCommand = "1 \n " + interviewerName + " \n " + interviewerLastName + " \n " + interviewerEmail + " \n 1 \n";
      final String getInterviewerCommand = "2 \n " + interviewerEmail + "\n ";
      final String exitCommand = "3 \n";
      provideInput(addNewInterviewerCommand + getInterviewerCommand + exitCommand);


      Menu.main(new String[0]);
      final String output = getOutput();
      //System.out.println(""+output);
      assertTrue(output.contains(interviewerName));
      assertTrue(output.contains(interviewerLastName));
      assertTrue(output.contains(interviewerEmail));
      assertTrue(output.contains("Jose"), "Fallo: No Existe el nombre");

    }

  public void testAdd() { //Verifica que  el entrevistador agregado este presente en la lista de datos
    Interviewer.data = new ArrayList<>();
    Interviewer interviewer = new Interviewer("Juan", "lopez", "juan@mail.com", true);
    Interviewer addedInterviewer = interviewer.add();

    assertTrue(Interviewer.data.contains(addedInterviewer), "Fallo:El entrevistador no se agrego correctamente");
  }


  public void testSave() { //Validar que los campos se hayan actualizado correctamente
    Interviewer.data = new ArrayList<>();
    Interviewer interviewer = new Interviewer("Juan", "lopez", "juan@mail.com", true);

    //Guardar los cambios del entrevistador
    interviewer.save("", "Diaz", "ana.diaz@mail.com", false);


    assertEquals(interviewer.name, "Juan", "Fallo: El nombre no se actualizo correctamente");

  }


  public void testEmail() { //Buscar correo existente
    Interviewer.data = new ArrayList<>();
    Interviewer interviewer1 = new Interviewer("Juan", "lopez", "juan@mail.com", true);
    interviewer1.save("Juan", "lopez", "juan@mail.com", true);

    Interviewer foundInterviewer = Interviewer.getByEmail("juan@mail.com");
    assertEquals(interviewer1, foundInterviewer, "Fallo: Entrevistador Encontrado no es el esperado");
  }


  public void testEmailNull() { //Buscar correo existente
    Interviewer.data = new ArrayList<>();
    Interviewer interviewer1 = new Interviewer("Juan", "lopez", "juan@mail.com", false);
    interviewer1.save("Juan", "lopez", "juan@mail.com", true);

    Interviewer foundInterviewer = Interviewer.getByEmail("nonexitente@mail.com");
    assertNull(foundInterviewer, "Fallo: Correo no existente");
  }

}


  @Test(groups = "grupo3")
  public class Grupo3 {
    public void testOne() {
      assertEquals(2, 1 + 1, "La suma de los numeros dados no es igual");
    }


    public void testSuma() {

      int result = calculator.add(3, 5);

      assertEquals(8, result, "EL resultado actual es diferente de 8");
    }


    public void testEquality() {
      String expected = "Hello";
      String actual = "Hello";

      assertEquals(expected, actual, "Cadenas diferentes");
    }


    public void testTrueOrFalse() {
      assertTrue(5 > 2, "La expresion deberia ser verdadera");
      assertFalse(1 > 3, "La expresion deveria ser falsa");
    }


    public void testNullAndNotNull() {
      String objTest = "holla";
      //assertNull("El objeto deberia ser nulo", objTest);
      assertNotNull(objTest, "El objeto de prueba no debe ser nulo");

    }

    /**
     * Pasos para Escribir Pruebas Unitarias
     * 1. Identificar un Método para Probar
     * 2. Crear un clase de prueba para la clase o el metodo a probar
     * 3. Escribir la Prueba: Usar aserciones para verificar el comportamiento del método
     * assertEquals
     * asserrTrue
     * asserNull
     * etc.
     * 4. Ejecutar la prueba
     **/


    public void testArraysEquality() {
      int[] expectedArray = {1, 2, 3};
      int[] actualityArray = {1, 2, 3};
      Assert.assertEquals(expectedArray, actualityArray, "Los arreglos no son iguales");
    }


    public void testException() {
      assertThrows(ArithmeticException.class, () -> {
        int result = 1 / 0;
      });
    }


    public void testObjectReference() {

      String fisrtString = new String("Hello");
      String secondstring = fisrtString;

      assertSame(fisrtString, secondstring);
    }


  }


  @Test (groups = "grupo4")
  public class Grupo4 {
    public void testSO() {
      String sistemaOperativo = System.getProperty("os.name");
      Assume.assumeTrue(sistemaOperativo.contains("Windows"));
      int resultado = calculator.add(2, 3);
      Assert.assertEquals(resultado, 5);
    }

    public void testSOOther() {
      String sistemaOperativo = System.getProperty("os.name");
      Assume.assumeFalse(sistemaOperativo.contains("Linux"));
      int resultado = calculator.add(2, 3);
      Assert.assertEquals(resultado, 5);
    }
  }

  @Test(groups = "EnvDev", enabled = false)
  public class Grupo5 {
    public void testEnviromentDev() {
      System.setProperty("ambientes", "desarrollo");

      int resultado = calculator.add(2, 3);
      Assert.assertEquals(resultado, 5);
    }
  }
  @Test(groups = "sumaParametrizada", dataProvider = "datosSuma")
  public void testSumaParametrizada(int a, int b, int resultadoEsperado) {
    int resultado = calculator.add(a, b);
    Assert.assertEquals(resultado, resultadoEsperado,"El resultado no coincide con el resultado esperado");
  }

  @Test(groups = "multipParametrizada", dataProvider = "datosMultiplicacion")
  public void testMultiParametrizada(int a, int b, int resultadoEsperado){
    int resultado = calculator.multiply(a, b);
    Assert.assertEquals(resultado, resultadoEsperado,"El resultado no coincide con el resultado esperado");
  }

  @DisplayName("Prueba de la funcion Suma")
  @Test(groups = "testDisplay")
  public void testSuma(){
    int resultado = calculator.add(2,3);
    Assert.assertEquals(resultado, 5,"El resultado no coincide con el resultado esperado");
  }






  //Métodos para proporcionar los datos de las pruebas aparametrizadas

  @DataProvider(name ="datosSuma")
    public Object[][] datosSuma(){
      return new Object[][] {
              {2,3,5},
              {0,0,0},
              {-1,1,0},
              {100,8,108}
      };
    }

  @DataProvider(name ="datosMultiplicacion")
  public Object[][] datosMultiplicacion(){
    return new Object[][] {
            {2,3,6},
            {0,5,0},
            {50,30,1500},
            {-20,-50,1000}
    };
  }

  }

