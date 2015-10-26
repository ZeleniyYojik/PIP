// Вариант 1705
public class Lab2 {
  public static void main(String[] args) {
    Nosepass sister = new Probopass();
    Nosepass father = new Nosepass();
    Probopass daughter = new Probopass();

    daughter.sharpen();
    daughter.stoneEdge(father);
    sister.batonPass();
    daughter.magnetPull();
    father.growth();
    sister.sharpen();
    daughter.batonPass();
    daughter.stoneEdge(sister);
    daughter.swordsDance();
    father.stoneEdge(daughter);
    sister.stockpile();
    father.batonPass();
    father.stoneEdge(sister);
    ((Probopass)sister).defenseCurl();
    daughter.unbreakable();
    father.sharpen();
  }
}

class Nosepass {
  public static int fragile;
  int sky;
  protected byte burrow = (byte) 0x84;
  protected String fire = "Fire";
  protected String grass = "Grass";
  protected String grassFire = "GrassFire";
  float depth = 8.2f;

  public Nosepass() {
    fragile = 84;
  }

  {
    sky = 067;
  }


  public void batonPass() {
    System.out.println("Nosepass casts Baton Pass");
  }

  public void stockpile() {
    System.out.println(grassFire.equals(grass+"Fire"));
    System.out.println(grassFire == "Grass"+"Fire");
    System.out.println(grassFire == "Grass"+fire);
    System.out.println(grassFire == grass+fire);
    System.out.println(grassFire.equals(grass+fire));
    System.out.println(grassFire.equals("Grass"+"Fire"));
  }

  public void swordsDance() {
    System.out.println(sky - fragile);
    System.out.println(fragile - burrow);
    System.out.println(burrow + sky);
  }

  public void growth() {
    float defense = 6.1f;

    System.out.println((depth + defense) == 14.3f);
  }

  public void stoneEdge(Nosepass p) {
    System.out.println("Nosepass attacks Nosepass with Stone Edge");
  }

  public static void sharpen() {
    System.out.println("Nosepass casts Sharpen");
  }

  public void stoneEdge(Probopass p) {
    System.out.println("Nosepass attacks Probopass with Stone Edge");
  }
}
//Класс Probopass наследуется от Nosepass
class Probopass extends Nosepass {
  private int stealth;
  private String electricFire = "ElectricFire";
  private String electric = "Electric";
  float height = 3.1f;

  public Probopass() {
    stealth = 84;
  }

  {
    stealth = 23;
  }


  public void defenseCurl() {
    System.out.println(electricFire == new String("Electric"+"Fire"));
    System.out.println(electricFire == electric+fire);
    System.out.println(electricFire == (electric+fire).intern());
    System.out.println(electricFire == new String("ElectricFire"));
  }

//Перегрузка метода stoneEdge
  public void stoneEdge(Probopass p) {
    System.out.println("Probopass attacks Probopass with Stone Edge");
  }

  public void stoneEdge(Nosepass p) {
    System.out.println("Probopass attacks Nosepass with Stone Edge");
  }

  public void unbreakable() {
    System.out.println(stealth + fragile);
    System.out.println(burrow - stealth);
    System.out.println(stealth - sky);
  }

  public void batonPass() {
    System.out.println("Probopass casts Baton Pass");
  }

  public static void sharpen() {
    System.out.println("Probopass casts Sharpen");
  }

  public void magnetPull() {
    float accuracy = 7.7f;

    System.out.println((accuracy + height) == 10.8f);
  }
}


