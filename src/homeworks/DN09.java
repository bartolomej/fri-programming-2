package homeworks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class Planet {

  public String ime;
  public int radij;

  public Planet(String ime, int radij) {
    this.ime = ime;
    this.radij = radij;
  }

  public double povrsina() {
    return 4 * Math.PI * Math.pow(radij, 2);
  }
}

public class DN09 {

  public static void main(String[] args) throws FileNotFoundException {
    String inputFile = args[0];
    String query = args[1];
    Planet[] planeti = readPlanets(inputFile);
    String[] planetNames = query.split("\\+");
    double povrsina = 0;
    for (String name : planetNames) {
      for (Planet planet : planeti) {
        if (name.equalsIgnoreCase(planet.ime)) {
          povrsina += planet.povrsina();
        }
      }
    }
    System.out.println(
      String.format(
        "Povrsina planetov \"%s\" je %d milijonov km2", 
        query, 
        (int)(povrsina / Math.pow(10, 6))
      )
    );
  }

  public static Planet[] readPlanets(String filePath) throws FileNotFoundException {
    ArrayList<Planet> planeti = new ArrayList();
    Scanner scanner = new Scanner(new File(filePath));
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] parts = line.split(":");
      Planet planet = new Planet(parts[0], Integer.parseInt(parts[1]));
      planeti.add(planet);
    }
    return planeti.toArray(new Planet[planeti.size()]);
  }
}