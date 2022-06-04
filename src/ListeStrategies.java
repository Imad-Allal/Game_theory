import java.util.ArrayList;
import java.util.List;

public class ListeStrategies {

    ArrayList<Integer> numeroDansLaListe;
    List<Strategie> lesStrategies = new ArrayList<>();

    ListeStrategies (ArrayList<Integer> numeroDansLaListe) {
        this.numeroDansLaListe = numeroDansLaListe;
        for (int numero : numeroDansLaListe)
            switch (numero) {
                case 1 -> {
                    Strategie gentille = new Strategie("Gentille", 1);
                    lesStrategies.add(gentille);
                }
                case 2 -> {
                    Strategie mechante = new Strategie("Méchante", 2);
                    lesStrategies.add(mechante);
                }
                case 3 -> {
                    Strategie donnant_donnant = new Strategie("Donnant-Donnant", 3);
                    lesStrategies.add(donnant_donnant);
                }
                case 4 -> {
                    Strategie donnant_donnant_dur = new Strategie("Donnant-Donnant-Dur", 4);
                    lesStrategies.add(donnant_donnant_dur);
                }
                case 5 -> {
                    Strategie mefiante = new Strategie("Méfiante", 5);
                    lesStrategies.add(mefiante);
                }
                case 6 -> {
                    Strategie rancuniere = new Strategie("Rancunière", 6);
                    lesStrategies.add(rancuniere);
                }
                case 7 -> {
                    Strategie periodique_mechante = new Strategie("Périodique-Méchante", 7);
                    lesStrategies.add(periodique_mechante);
                }
                case 8 -> {
                    Strategie periodique_gentille = new Strategie("Périodique-Gentille", 8);
                    lesStrategies.add(periodique_gentille);
                }
                case 9 -> {
                    Strategie majorite_mou = new Strategie("Majorité-Mou", 9);
                    lesStrategies.add(majorite_mou);
                }
                case 10 -> {
                    Strategie majorite_dur = new Strategie("Majorité-Dur", 10);
                    lesStrategies.add(majorite_dur);
                }
                case 11 -> {
                    Strategie dur = new Strategie("Dur", 11);
                    lesStrategies.add(dur);
                }
                case 12 -> {
                    Strategie sondeur_quatre_coups = new Strategie("Sondeur-4-coups", 12);
                    lesStrategies.add(sondeur_quatre_coups);
                }
                case 13 -> {
                    Strategie donnant_donnant_avec_seuil = new Strategie("Donnant-Donnant-Avec-Seuil", 13);
                    lesStrategies.add(donnant_donnant_avec_seuil);
                }
                case 14 -> {
                    Strategie graine_de_champion = new Strategie("Graine-de-champion", 14);
                    lesStrategies.add(graine_de_champion);
                }
            }
        }
    }

