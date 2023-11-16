import Interviewer.Interviewer;
import Interviewer.Menu;
import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class test {

  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;

  private ByteArrayInputStream testIn;
  private ByteArrayOutputStream testOut;


  @Before
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




  @After
    public void restoreSystemInputOutput() {
      System.setIn(systemIn);
      System.setOut(systemOut);
    }


  @Test
  public void addNewInterviewer() { //Agrega un entrevistador y valida si contiene los datos
   final String interviewerName = "Nombre";
   final String interviewerLastName ="Apellido";
   final String interviewerEmail = "mail@mail.com";
   final String addNewInterviewerCommand= "1 \n "+ interviewerName + " \n " + interviewerLastName + " \n " + interviewerEmail + " \n 1 \n 3 \n";
    provideInput(addNewInterviewerCommand);

    Menu.main(new String[0]);
    final String output = getOutput();

    assertTrue(output.contains(interviewerName));
    assertTrue(output.contains(interviewerLastName));
    assertTrue(output.contains(interviewerEmail));
  }

  @Test
  public void getInterviewer () { //Validar que el entrevistador se registró correctamente
    final String interviewerName = "Jose";
    final String interviewerLastName = "Sosa";
    final String interviewerEmail = "jose@mail.com";
    final String addNewInterviewerCommand = "1 \n "+ interviewerName + " \n " + interviewerLastName + " \n " + interviewerEmail + " \n 1 \n";
    final String getInterviewerCommand = "2 \n " + interviewerEmail + "\n ";
    final String exitCommand = "3 \n";
    provideInput(addNewInterviewerCommand + getInterviewerCommand + exitCommand);


    Menu.main(new String[0]);
    final String output = getOutput();
    //System.out.println(""+output);
    assertTrue(output.contains(interviewerName));
    assertTrue(output.contains(interviewerLastName));
    assertTrue(output.contains(interviewerEmail));
    assertTrue("Fallo: No Existe el nombre", output.contains("Jose"));

  }

  @Test
  public void testAdd(){ //Verifica que  el entrevistador agregado este presente en la lista de datos
    Interviewer.data = new ArrayList<>();
    Interviewer interviewer = new Interviewer("Juan","lopez", "juan@mail.com", true);
    Interviewer addedInterviewer = interviewer.add();

    assertTrue("Fallo:El entrevistador no se agrego correctamente",Interviewer.data.contains(addedInterviewer));
  }

  @Test
  public void testSave(){ //Validar que los campos se hayan actualizado correctamente
    Interviewer.data = new ArrayList<>();
    Interviewer interviewer = new Interviewer("Juan","lopez", "juan@mail.com", true);

    //Guardar los cambios del entrevistador
    interviewer.save("","Diaz", "ana.diaz@mail.com", false);


    assertEquals("Fallo: El nombre no se actualizo correctamente", interviewer.name,"Juan");

  }
  @Test
  public void testEmail(){ //Buscar correo existente
    Interviewer.data = new ArrayList<>();
    Interviewer interviewer1 = new Interviewer("Juan","lopez", "juan@mail.com", true);
    interviewer1.save("Juan","lopez", "juan@mail.com", true);

   Interviewer foundInterviewer = Interviewer.getByEmail("juan@mail.com");
    assertEquals("Fallo: Entrevistador Encontrado no es el esperado",interviewer1,foundInterviewer);
  }

  @Test
  public void testEmailNull(){ //Buscar correo existente
    Interviewer.data = new ArrayList<>();
    Interviewer interviewer1 = new Interviewer("Juan","lopez", "juan@mail.com", false);
    interviewer1.save("Juan","lopez", "juan@mail.com", true);

    Interviewer foundInterviewer = Interviewer.getByEmail("nonexitente@mail.com");
    assertNull("Fallo: Correo no existente", foundInterviewer);
  }



}
