package com.test.interviewer;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
  Scanner sc;

  public Menu() {
    sc = new Scanner(System.in);
    Interviewer.data = new ArrayList<Interviewer>();

    showMainMenu();
  }

  public void showMainMenu() {
    int option = 0;

    while (option != 3) {
      System.out.println("Seleccione la operacion a realizar:");
      System.out.println("1. Dar de alta un entrevistador");
      System.out.println("2. Consultar un entrevistador");
      System.out.println("3. Salir");

      option = sc.nextInt();
      sc.nextLine();

      switch (option) {
        case 1:
          addInterviewer();
          break;
        case 2:
          searchInterviewer();
          break;
      }
    }
    ;

    System.out.println("Programa terminado");
  }

  public void addInterviewer() {
    System.out.println("Ingrese el nombre del entrevistador: ");
    String name = sc.nextLine();
    System.out.println("Ingrese el apellido del entrevistador: ");
    String lastName = sc.nextLine();
    System.out.println("Ingrese el email del entrevistador: ");
    String email = sc.nextLine();
    System.out.println("El entrevistador se encuentra activo? (1=Si/2=No)");
    Boolean isActive = sc.nextInt() == 1;
    sc.nextLine();

    Interviewer interviewer = new Interviewer(name, lastName, email, isActive);
    interviewer.add();

    System.out.println(interviewer.toString());
  }

  public void searchInterviewer() {
    System.out.println("Ingrese el email del entrevistador a consultar:");
    String email = sc.nextLine();

    Interviewer interviewer = Interviewer.getByEmail(email);

    if (interviewer != null) {
      System.out.println("Entrevistador encontrado:");
      System.out.println(interviewer.toString());
    } else {
      System.out.println("Entrevistador no encontrado");
    }
  }

  public static void main(String[] args) {
    new Menu();
  }
}
  Nuestro método save se encarga de guardar el nuevo entrevistador dentro del arreglo de entrevistadores.
        El método getByEmail permite buscar entrevistadores existentes.
        Interviewer.java

        package com.test.interviewer;

        import java.io.*;
        import java.util.ArrayList;

public class Interviewer implements Serializable {
  static ArrayList<Interviewer> data;

  int id;
  String name;
  String lastName;
  String email;
  Boolean isActive;

  public Interviewer(
          String name,
          String lastName,
          String email,
          Boolean isActive
  ) {
    this.id = data.size() + 1;
    this.name = name;
    this.lastName = lastName;
    this.email = email;
    this.isActive = isActive;
  }

  public Interviewer add() {
    data.add(this);
    return this;
  }

  public void save(
          String name,
          String lastName,
          String email,
          Boolean isActive
  ) {
    if (!name.equals(""))
      this.name = name;

    if (!lastName.equals(""))
      this.lastName = lastName;

    if (!email.equals(""))
      this.email = email;

    this.isActive = isActive;

    data.add(this);
  }

  public static Interviewer getByEmail(String email) {
    for (Interviewer interviewer : data) {
      if (interviewer.email.equals(email))
        return interviewer;
    }

    return null;
  }

  @Override
  public String toString() {
    return "\nID: " + this.id +
            "\nName: " + this.name +
            "\nLast Name: " + this.lastName +
            "\nEmail: " + this.email +
            "\nIs Active: " + this.isActive + "\n";
  }
}