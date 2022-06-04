import java.util.ArrayList;
import java.util.Scanner;

public class Jeu{

    static String manuel = "Imaginons deux tribus d’indigènes partant à la chasse. Ces deux tribus peuvent choisir entre" +
            "coopérer ou trahir la tribu adverse lors d’une confrontation. Lorsque la situation du dilemme" +
            "est itérée, le jeu devient très intéressant, car la question ne se pose plus sous la forme : trahir" +
            "ou coopérer ?, mais sous la forme : quelle stratégie faut-il adopter en fonction du comportement" +
            "passé de l’entité adverse ?" +
            "Nous supposons que les deux tribus ne peuvent pas passer d’accord. La seule information" +
            "qu’une tribu connaît sur l’autre est son comportement passé lors des coups précédents. Les" +
            "décisions des deux tribus lors de la partie sont prises simultanément. Le nombre de parties n’est" +
            "pas connu à l’avance. Par rapport au simple dilemme des prisonniers nous choisissons de rajouter" +
            "la possibilité de renoncer à jouer, mais ce refus est définitif." +
            "Décrivons la variante adoptée de manière plus abstraite : deux entités peuvent choisir entre" +
            "coopérer (notation c), trahir (notation t) ou renoncer (notation n). Si l’une trahit et l’autre" +
            "coopère (partie [t; c]), celle qui trahit obtient un gain de T unités et celle qui coopère (et s’est" +
            "donc fait duper) obtient un gain de D unités. Lorsque les deux entités coopèrent (partie [c; c])," +
            "elles gagnent chacune C unités en récompense de leur association. Quand elles trahissent toutes" +
            "les deux (partie [t; t]), elles gagnent P unités pour s’être laissées piéger mutuellement. Si une" +
            "partie n’a pas lieu parce que l’une a refusé de jouer les deux entités gagnent N unités. Le choix" +
            "des coefficients T, D, C, P et N n’est pas fortuit. Conformément aux numéro 181 de POUR LA" +
            "SCIENCE nous prenons : T = 5, D = 0, C = 3, P = 1, N = 2.";


    public static void main(String[] args) {
        ArrayList<Integer> listeStrategies = new ArrayList<>();
        // Pas toutes les strategies fonctionnent correctement; le code est encore en construction
        Tournoi.nombreTours = 3;

        listeStrategies.add(1);
        listeStrategies.add(14);
        listeStrategies.add(3);
        listeStrategies.add(2);

        ListeStrategies strategies = new ListeStrategies(listeStrategies);
        Affrontement combat = new Affrontement(strategies.lesStrategies);
        combat.affrontement(false);
        System.out.println("La tribu vainqueuse du tournoi est : " + Tournoi.vainqueurTournoi.getNom() + " ,avec un score totale de : " + Tournoi.vainqueurTournoi.scoreTotal);
    }
}

        ///La version menu du jeu est bugé
        ///Vous pouvez Enlever le commentaires si vous voulez la tester
        ///Les strategies ci-dessous ne fonctionne pas correctement :

        ///Periodique-mechante
        ///Periodique-gentille
        ///Dur
        ///Sondeur-4-Coups

        /*
        int choixmenu = 1;
        int choixmenu2 = 1;
        int nombreTours = 1;
        int choixmenu3 = 1;
        int choixStrategies = 1;
        int numeroAncienneStrategie = 1;
        int numeroNouvelleStrategie = 1;
        int lancerPartie = 1;
        int manuel = 1;
        int type =1;
        boolean partie = false;
        boolean allerRetour = false;

        ArrayList<Integer> listeStrategies = new ArrayList<>();
        ArrayList<Integer> strategies = new ArrayList<>();
        strategies.add(0,1);

        Scanner choixMenu = new Scanner(System.in);

        while (choixmenu != 0) {
            System.out.println("Naviguez dans le menu du jeu. Tapper 0 pour quitter");
            System.out.println("1) Definir/Modifier le nombre de tours | Menu strategies");
            System.out.println("2) Choisir le type du tournoi");
            System.out.println("3) Afficher manuel");
            System.out.println("0) Quitter");

            choixmenu = choixMenu.nextInt();
            switch (choixmenu){
                case 1:
                    while(choixmenu2 != 0){
                        System.out.println("Choisissez : \n1) Definir/Modifiier nombre de tours\n2) Menu strategies");
                        choixmenu2 = choixMenu.nextInt();
                        switch(choixmenu2){
                            case 1:
                                System.out.println("Entrez le nombre de tour du tournoi.\nLe nombre doit etre superieur ou egale 2");
                                while (nombreTours < 2){
                                    nombreTours = choixMenu.nextInt();
                                    if (nombreTours < 2)
                                        System.out.println("Veuillez entrer un nombre superieur a 2");
                                }
                                Tournoi.nombreTours = nombreTours;
                                break;
                            case 2:
                                while (choixmenu3 != 0){
                                    System.out.println("Menu des strategies\n1) Definir la liste des strategies\n2) Modifier la liste de strategies");
                                    choixmenu3 = choixMenu.nextInt();
                                    switch (choixmenu3){
                                        case 1:
                                            System.out.println("Liste des strategies : ");
                                            System.out.println("1)Gentille\n2)Mechante\n3)Donnant-Donnant\n4)Donnant-Donnant-Dur\n5)Mefiante\n6)Rancuniere\n7)Périodique-Méchante\n8)Périodique-Gentille\n9)Majorité-Mou\n10)Majorité-Dur\n11)Dur\n12)Sondeur-4-coups\n13)Donnant-Donnant-Avec-Seuil\n14)Graine-de-champion\n");
                                            System.out.println("Selectionnez les strategies à ajouter au tournoi parmi la liste, en entrant leurs numeros\nTappez 0 pour confirmer votre liste\nIl faut qu'il y ait en moins 2 strategies dans la liste, et une strategie ne peut etre choisie qu'une seule fois");
                                            while (choixStrategies != 0){
                                                int i = 0;
                                                choixStrategies = choixMenu.nextInt();
                                                if (choixStrategies <0 || choixStrategies>14) {
                                                    if (choixStrategies == 0)
                                                        System.out.println("Il faut au moins choisir 2 strategies");
                                                    else
                                                        System.out.println("Veuillez entrer un nombre valide");
                                                }
                                                else {
                                                    if (strategies.size() == 0){
                                                        strategies.add(0,choixStrategies);
                                                    }
                                                    else {
                                                        for (i = 0; i < strategies.size(); i++) {
                                                            if (choixStrategies == strategies.get(i))
                                                                System.out.println("Vous avez deja choisi cette strategie");
                                                        }
                                                        if (i == strategies.size())
                                                            strategies.add(i-1,choixStrategies);
                                                    }
                                                }
                                            }
                                            break;
                                        case 2:
                                            int k = 0;
                                            if (strategies.size() <2)
                                                System.out.println("Rien a modifier, la liste est vide");
                                            else {
                                                System.out.println("Entrez le numero de l'ancienne strategie a remplacer");
                                                numeroAncienneStrategie = choixMenu.nextInt();
                                                for (int i = 0; i < strategies.size(); i++){
                                                    if (numeroAncienneStrategie != strategies.get(i))
                                                        k++;
                                                }
                                                if (k == strategies.size()){
                                                    System.out.println("test");
                                                    k=0;
                                                    System.out.println("Entrez le numero de la nouvelle strategie");
                                                    numeroNouvelleStrategie = choixMenu.nextInt();
                                                    for (int i = 0; i < strategies.size(); i++){
                                                        if (numeroNouvelleStrategie != strategies.get(i))
                                                            k++;
                                                    }
                                                    if (k == strategies.size() && k >0 && k<15){
                                                        strategies.set(numeroAncienneStrategie,numeroNouvelleStrategie);
                                                    }
                                                    else
                                                        System.out.println("Numero incorrecte, retour au menu precedent");
                                                }
                                                else
                                                    System.out.println("Numero incorrecte, retour au menu precedent");
                                            }
                                            break;
                                    }
                                    if (strategies.size() >= 2 && nombreTours >=2){
                                        System.out.println("Liste de strategies choisies : "+strategies.toString());
                                        System.out.println("Nombre de tours : "+Tournoi.nombreTours);
                                        System.out.println("Voulez vous lancer la partie ?\n1) Oui\n0) Non");
                                        while(lancerPartie != 0){
                                            lancerPartie = choixMenu.nextInt();
                                            if (lancerPartie == 1){
                                                System.out.println("Lancement de la partie");
                                                partie = true;
                                                choixmenu = 0;
                                                choixmenu2 = 0;
                                                lancerPartie = 0;
                                                break;
                                            }
                                        }
                                    }
                                    else {
                                        System.out.println("Retour au menu precedent");
                                    }
                                    if (partie)
                                        break;
                                }

                        }
                        if (partie)
                            break;
                    }
                    break;
                case 2:
                    while(type !=1 || type != 2) {
                        System.out.println("Choisissez le type de tournoi\n1) Aller simple\n2) Aller-retour");
                        type = choixMenu.nextInt();
                    }
                    break;

                case 3:
                    System.out.println("Affichage du manuel du jeu");
                    System.out.println(Fin.manuel);
                    System.out.println("");
                    while (manuel != 0) {
                        System.out.println("Tappez 0 pour sortir du manuel");
                        manuel = choixMenu.nextInt();
                    }
                    break;
                case 0:
                    System.out.println("Fermeture du jeu");
                    partie = false;
                    break;
            }
            if (partie)
                break;
        }

        if (partie){
            ListeStrategies strategiesChoisies = new ListeStrategies(listeStrategies);
            Affrontement combat = new Affrontement(strategiesChoisies.lesStrategies);
            combat.affrontement(allerRetour);
            System.out.println("La strategie remportant le tournoi est : " + Tournoi.vainqueurTournoi.getNom() + " ,avec un score totale de : " + Tournoi.vainqueurTournoi.scoreTotal);
        }
}

*/
