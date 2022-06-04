import java.util.ArrayList;

public class Tournoi {
    static int nombreTours = 0;
    static ArrayList<Integer> listeVainqueurAller = new ArrayList<>();
    static ArrayList<Integer> listeVainqueurRetour = new ArrayList<>();
    static Strategie vainqueurTournoi;

    public Tournoi(int nombreTours){
        Tournoi.nombreTours = nombreTours;
    }

    public static void setListeVainqueurAller(int numeroStrategie){
        Tournoi.listeVainqueurAller.add(numeroStrategie+1);
    }

    public static void setListeVainqueurRetour(int numeroStrategie){
        Tournoi.listeVainqueurRetour.add(numeroStrategie+1);
    }

    public static void setVainqueurTournoi(Strategie strategie){
        Tournoi.vainqueurTournoi = strategie;
    }
}


