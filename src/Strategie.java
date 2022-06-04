import java.util.ArrayList;

public class Strategie extends Comportement {

    private final String nom;
    private final int numeroStrategie;
    ArrayList<Integer> comportement = new ArrayList<>();
    int scoreAller;
    int scoreRetour;
    int scoreTotal;
    private static int cle = 0;

    public Strategie(String nom, int numeroStrategie) {
        this.nom = nom;
        this.numeroStrategie = numeroStrategie;
        this.scoreAller = 0;
        this.scoreRetour = 0;
        this.scoreTotal = 0;
        setComportementDepart(this.numeroStrategie); // Initialise le premier comportement d'une strategie
    }


    public void setScoreAller(int score) {
        this.scoreAller += score;
    }

    public int getScoreAller() {
        return scoreAller;
    }

    public int getScoreRetour() {
        return scoreRetour;
    }
    public void setScoreRetour(int score) {
        this.scoreRetour += score;
    }

    public void setScoreTotal() {
        scoreTotal = scoreAller + scoreRetour;
    }

    public String getNom() {
        return nom;
    }

    public int getNumeroStrategie() {
        return numeroStrategie;
    }

    public void viderComportement() {
        comportement.clear();
    }

    @Override
    public void setComportementDepart(int strategie) {
        if (strategie == 2 || strategie == 5 || strategie == 7 || strategie == 10 || strategie == 11)
            comportement.add(0,Comportement.trahir);
        else
            comportement.add(0,Comportement.cooperer);
    }

    @Override
    public int getDernierComportement(int tour) {
        if (tour < comportement.size())
            return comportement.get(tour);
        else
            return 0;
    }

    @Override
    public void setComportement(int numStrategie, ArrayList<Integer> comportementAdversaire, int tour, int score) {
        switch (numStrategie) {
            case 1:
                comportement.add(Comportement.cooperer);
                break;

            case 2:
                comportement.add(Comportement.trahir);
                break;

            case 3:
            case 5:
                if (comportementAdversaire.get(tour) == Comportement.trahir)
                    comportement.add(Comportement.trahir);
                else
                    comportement.add(Comportement.cooperer);
                break;

            case 4:
                if (tour >= 0) {
                    if (tour >=1){
                        if (comportementAdversaire.get(tour) == Comportement.trahir || comportementAdversaire.get(tour - 1) == Comportement.trahir)
                            comportement.add(Comportement.trahir);
                        else
                            comportement.add(Comportement.cooperer);
                    }
                    else {
                        if (comportementAdversaire.get(tour) == Comportement.trahir) {
                            comportement.add(Comportement.trahir);
                        }
                        else
                            comportement.add(Comportement.cooperer);
                    }

                } else
                    comportement.add(Comportement.cooperer);
                break;

            case 6:
                if (comportementAdversaire.get(tour) == Comportement.trahir || cle == 1) {
                    if (cle == 0)
                        System.out.println("Mefiante: Je me suis fait duper donc je change definitivement de comportement");
                    comportement.add(Comportement.trahir);
                    cle = 1;
                }
                if (cle == 0)
                    comportement.add(Comportement.cooperer);
                break;

            case 7:
                if (tour > 0 && tour % 3 == 0){
                   comportement.add(Comportement.cooperer);
                }
                else
                    comportement.add(Comportement.trahir);
                break;

            case 8:
                if (tour % 3 == 0)
                    comportement.add(Comportement.trahir);
                else
                    comportement.add(Comportement.cooperer);
                break;

            case 9:
                int c = 0, t = 0;
                for (int i = 0; i < tour; i++) { // Calculer comportements adversaire
                    if (comportementAdversaire.get(i) == Comportement.cooperer)
                        c++;

                    else if (comportementAdversaire.get(i) == Comportement.trahir)
                        t++;
                }
                if (t > c)
                    comportement.add(Comportement.trahir);
                else
                    comportement.add(Comportement.cooperer);
                break;

            case 10:
                int c2 = 0, t2 = 0;
                for (int i = 0; i < tour; i++) { // Calculer Comportements adversaire
                    if (comportementAdversaire.get(i) == Comportement.cooperer)
                        c2++;
                    else if (comportementAdversaire.get(i) == Comportement.trahir)
                        t2++;
                }
                if (c2 > t2)
                    comportement.add(Comportement.cooperer);
                else
                    comportement.add(Comportement.trahir);
                break;

            case 11:
                if (comportementAdversaire.get(tour) == Comportement.trahir)
                    comportement.add(Comportement.renoncer);
                else
                    comportement.add(Comportement.trahir);
                break;

            case 12:
                int t3 = 0;
                if (tour <= 3*(tour+1)) {
                    if (tour <= 1)
                        comportement.add(Comportement.cooperer);
                    else {
                        comportement.add(Comportement.trahir);
                        System.out.println("marche pas");
                    }
                }else {
                    if (tour == 4) {
                        for (int i = 0; i <= tour; i++) { // Calculer Comportements adversaire
                            if (comportementAdversaire.get(i) == Comportement.trahir)
                                t3++;
                        }
                    }
                    if (t3 < 3)
                        comportement.add(Comportement.cooperer);
                    else
                        comportement.add(Comportement.renoncer);
                }
                break;

            case 13:
                float nbtours = 5;
                float moyenne;
                if (tour % 5 == 0 && tour>0) {
                    moyenne = score / nbtours;
                    System.out.println("Moyenne : "+moyenne);
                    if (moyenne < 2) {
                        comportement.add(Comportement.renoncer);
                    }
                    else {
                        nbtours = nbtours * 2;
                        if (comportementAdversaire.get(tour) == Comportement.trahir)
                            comportement.add(Comportement.trahir);
                        else
                            comportement.add(Comportement.cooperer);
                    }
                }
                else {
                    if (comportementAdversaire.get(tour) == Comportement.trahir)
                        comportement.add(Comportement.trahir);
                    else
                        comportement.add(Comportement.cooperer);
                    }
                break;

            case 14:
                float nbtours2 = 20;
                float moyenne2;
                int nbrTrahisons = 0;
                int punition = 0;

                if (tour != 0 && tour % 20 == 0) {
                    System.out.println("Je suis entrée");

                    moyenne2 = score / nbtours2;
                    if (moyenne2 < 1.5) {
                        System.out.println("Je suis entrée");

                        comportement.add(Comportement.renoncer);
                    }
                    else {
                            nbtours2 *= 2;
                            if (comportementAdversaire.get(tour) == Comportement.trahir){
                                nbrTrahisons ++;
                                punition = nbrTrahisons*(nbrTrahisons+1)/2;
                            }

                            if (punition > 0) {
                                comportement.add(Comportement.trahir);
                                punition--;
                            }
                            else {
                                comportement.add(Comportement.cooperer);
                            }
                    }
                }
                else {
                    if (comportementAdversaire.get(tour) == Comportement.trahir){
                        nbrTrahisons ++;
                        punition = nbrTrahisons*(nbrTrahisons+1)/2;
                    }

                    if (punition > 0) {
                        comportement.add(Comportement.trahir);
                        punition--;
                    }
                    else {
                        comportement.add(Comportement.cooperer);
                    }
                }

                break;
        }
    }
}

