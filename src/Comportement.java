import java.util.ArrayList;

public abstract class Comportement {
    static int cooperer = 1;
    static int trahir = 2;
    static int renoncer = 3;
    public abstract void setComportementDepart(int numStrategie);
    public abstract void setComportement(int numStrategie, ArrayList<Integer> comportementAdversaire,  int tour, int score);
    public abstract int getDernierComportement(int tour);
    public void viderComportement() {

    }
}

