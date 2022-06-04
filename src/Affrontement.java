import java.util.ArrayList;
import java.util.List;

public class Affrontement {

    static int gagnant = 0;
    int score = 0;
    int scoreStrategie1;
    int scoreStrategie2;
    List<Strategie> lesStrategies;// Liste des strategies du tournoi

    ArrayList<Integer> dernierComportement1 = new ArrayList<>(); //Liste contenant les comportements de Tribu1
    ArrayList<Integer> dernierComportement2 = new ArrayList<>(); //Liste contenant les comportements de Tribu2

    public Affrontement(List<Strategie> lesStrategies) {
        this.lesStrategies = lesStrategies;
    }

    public void affrontement(boolean type){

        /***********************************************************************************************************************/
        /*****************************************************DEBUT: ALLER******************************************************/
        System.out.println("\n\n\n\n\t\t\t\t\tALLER");
        System.out.println("\n\n\tNombre de strategies : "+lesStrategies.size()+"\t"+"Nombre de tours = "+Tournoi.nombreTours);
        for (int i = 0; i < lesStrategies.size(); i++)
        { // Tribu1 qui commence à chaque tour
            for (int j = i; j < lesStrategies.size(); j++)
            { // Tribu2
                //--------------------------------- debut: réinitialiser le comportement de chaque tribu  ---------------------------------//
                dernierComportement1.clear();
                for (Strategie lesStrategy : lesStrategies) lesStrategy.viderComportement();
                dernierComportement2.clear();
                //--------------------------------- fin: réinitialiser le comportement de chaque tribu  ---------------------------------//

                lesStrategies.get(i).setComportementDepart(lesStrategies.get(i).getNumeroStrategie()); // Premier comportement de Tribu1
                lesStrategies.get(j).setComportementDepart(lesStrategies.get(j).getNumeroStrategie()); // Premier comportement de Tribu2

                System.out.println("\n\t\t\t\t"+lesStrategies.get(i).getNom()+"   VS   "+ lesStrategies.get(j).getNom());
                System.out.println("========================================================");
                scoreStrategie1 = 0;
                scoreStrategie2 = 0;
                System.out.println("\t\t\tScore1    "+scoreStrategie1+"       Score2     "+scoreStrategie2);
                System.out.println("========================================================");


                for (int tour = 1; tour <= Tournoi.nombreTours; tour++)
                {
                    System.out.println("\t\t\t\t\t\tTour: "+tour);
                    if (j == i) { // Une des Tribus joue contre elle meme
                        dernierComportement1.add(lesStrategies.get(i).getDernierComportement(tour - 1));// Ajouter le dernier comportement sur la liste des comportements de Tribu1
                        if (dernierComportement1.get(tour - 1) == Comportement.cooperer) {
                            scoreStrategie1 += 3;
                            scoreStrategie2 += 3;
                            lesStrategies.get(i).setScoreAller(3);
                            System.out.println("\t"+lesStrategies.get(i).getNom()+":  Score aller  : " +scoreStrategie1 +"  Score Total : "+lesStrategies.get(i).getScoreAller());
                            System.out.println("--------------------------------------------------------");
                        }
                        else if (dernierComportement1.get(tour - 1) == Comportement.trahir) {
                            scoreStrategie1 += 1;
                            scoreStrategie2 += 1;
                            lesStrategies.get(i).setScoreAller(1);
                            System.out.println(lesStrategies.get(i).getNom()+":  Score aller  : " +scoreStrategie1 +"  Score Total : "+lesStrategies.get(i).getScoreAller());

                        } else if (dernierComportement1.get(tour - 1) == Comportement.renoncer) {
                            scoreStrategie1 += 2;
                            scoreStrategie2 += 2;
                            lesStrategies.get(i).setScoreAller(2);
                            System.out.println(lesStrategies.get(i).getNom()+":  Score aller  : " +scoreStrategie1 +"  Score Total : "+lesStrategies.get(i).getScoreAller());
                            break;
                        }
                        //System.out.println("");
                        System.out.println("Liste des comportement de la strategie "+lesStrategies.get(i).getNom()+" contre elle meme");
                        System.out.println(" "+dernierComportement1.toString()+"  ");
                        //System.out.println("");
                        lesStrategies.get(i).setComportement(lesStrategies.get(i).getNumeroStrategie(), dernierComportement1, tour-1, lesStrategies.get(i).getScoreAller()); // Tribu1 met à jour son comportement
                    }

                    else {
                        dernierComportement1.add(lesStrategies.get(i).getDernierComportement(tour - 1));// Ajouter le dernier comportement sur la liste des comportements de Tribu1
                        dernierComportement2.add(lesStrategies.get(j).getDernierComportement(tour - 1));// Ajouter le dernier comportement sur la liste des comportements du Tribu2

                        //--------------------------------- debut: Calculer le gagnant de chaque tour ---------------------------------//
                        if (dernierComportement1.get(tour - 1) == Comportement.cooperer && dernierComportement2.get(tour - 1) == Comportement.cooperer) {
                            scoreStrategie1 += 3;
                            scoreStrategie2 += 3;
                            lesStrategies.get(i).setScoreAller(3); // Mettre a jour le score de Tribu1 =
                            lesStrategies.get(j).setScoreAller(3); // Mettre a jour le score de Tribu2 =

                            System.out.println("\t"+lesStrategies.get(i).getNom()+":  Score aller  : " +scoreStrategie1 +"  Score Total : "+lesStrategies.get(i).getScoreAller());
                            System.out.println("\t"+lesStrategies.get(j).getNom()+":  Score aller  : " +scoreStrategie2 +"  Score Total : "+lesStrategies.get(j).getScoreAller());
                            System.out.println("--------------------------------------------------------");

                        } else if (dernierComportement1.get(tour - 1) == Comportement.trahir && dernierComportement2.get(tour - 1) == Comportement.cooperer) {
                            scoreStrategie1 += 5;
                            scoreStrategie2 += 0;
                            lesStrategies.get(i).setScoreAller(5); // >
                            lesStrategies.get(j).setScoreAller(0); // <

                            System.out.println("\t"+lesStrategies.get(i).getNom()+":  Score aller  : " +scoreStrategie1 +"  Score Total : "+lesStrategies.get(i).getScoreAller());
                            System.out.println("\t"+lesStrategies.get(j).getNom()+":  Score aller  : " +scoreStrategie2 +"  Score Total : "+lesStrategies.get(j).getScoreAller());
                            System.out.println("--------------------------------------------------------");

                        }
                        else if (dernierComportement1.get(tour - 1) == Comportement.cooperer && dernierComportement2.get(tour - 1) == Comportement.trahir) {
                            scoreStrategie1 += 0;
                            scoreStrategie2 += 5;
                            lesStrategies.get(i).setScoreAller(0); // >
                            lesStrategies.get(j).setScoreAller(5); // <

                            System.out.println("\t"+lesStrategies.get(i).getNom()+":  Score aller  : " +scoreStrategie1 +"  Score Total : "+lesStrategies.get(i).getScoreAller());
                            System.out.println("\t"+lesStrategies.get(j).getNom()+":  Score aller  : " +scoreStrategie2 +"  Score Total : "+lesStrategies.get(j).getScoreAller());
                            System.out.println("--------------------------------------------------------");

                        }

                        else if (dernierComportement1.get(tour - 1) == Comportement.trahir && dernierComportement2.get(tour - 1) == Comportement.trahir) {
                            scoreStrategie1 += 1;
                            scoreStrategie2 += 1;
                            lesStrategies.get(i).setScoreAller(1); // =
                            lesStrategies.get(j).setScoreAller(1); // =

                            System.out.println("\t"+lesStrategies.get(i).getNom()+":  Score aller  : " +scoreStrategie1 +"  Score Total : "+lesStrategies.get(i).getScoreAller());
                            System.out.println("\t"+lesStrategies.get(j).getNom()+":  Score aller  : " +scoreStrategie2 +"  Score Total : "+lesStrategies.get(j).getScoreAller());
                            System.out.println("--------------------------------------------------------");

                        }
                        else if (dernierComportement1.get(tour - 1) == Comportement.renoncer || dernierComportement2.get(tour - 1) == Comportement.renoncer)
                        {
                            scoreStrategie1 += 2;
                            scoreStrategie2 += 2;
                            lesStrategies.get(i).setScoreAller(2); // =
                            lesStrategies.get(j).setScoreAller(2); // =

                            System.out.println("\t"+lesStrategies.get(i).getNom()+":  Score aller  : " +scoreStrategie1 +"  Score Total : "+lesStrategies.get(i).getScoreAller());
                            System.out.println("\t"+lesStrategies.get(j).getNom()+":  Score aller  : " +scoreStrategie2 +"  Score Total : "+lesStrategies.get(j).getScoreAller());
                            System.out.println("--------------------------------------------------------");

                            break;
                        }
                        System.out.println("");
                        System.out.println("Liste des comportement de la strategie "+lesStrategies.get(i).getNom());
                        System.out.println(" "+dernierComportement1.toString()+"  ");
                        System.out.println("Liste des comportement de la strategie "+lesStrategies.get(j).getNom());
                        System.out.println(" "+dernierComportement2.toString()+"  ");
                        System.out.println("");

                        lesStrategies.get(i).setComportement(lesStrategies.get(i).getNumeroStrategie(), dernierComportement2, tour-1, lesStrategies.get(i).getScoreAller()); // Tribu1 met à jour son comportement
                        lesStrategies.get(j).setComportement(lesStrategies.get(j).getNumeroStrategie(), dernierComportement1, tour-1, lesStrategies.get(j).getScoreAller()); // Tribu2 met à jour son comportement

                        //

                        //
                    }
                }
                //--------------------------------- debut: calculer le chaque gagnant de la manche aller ---------------------------------//
                if (scoreStrategie1 > scoreStrategie2)
                    Tournoi.listeVainqueurAller.add(lesStrategies.get(i).getNumeroStrategie());

                else if (scoreStrategie1 < scoreStrategie2)
                    Tournoi.listeVainqueurAller.add(lesStrategies.get(j).getNumeroStrategie());

                else
                    Tournoi.listeVainqueurAller.add(0);
                System.out.println("\t\t\tVainqueuse de manche : "+Tournoi.listeVainqueurAller.get(j)+"\n");

                //---------------------------------- fin: calculer le chaque gagnant de la manche aller ----------------------------------//
            }
        }


        System.out.println("\t\tVainqueurs Aller : "+Tournoi.listeVainqueurAller.toString());
        System.out.println("========================================================");
        System.out.println("========================================================");
        System.out.println("========================================================\n\n");

        /*****************************************************FIN: ALLER*****************************************************/
        /********************************************************************************************************************/
        //--------------------------------- debut: réinitialiser le comportement de chaque tribu  ---------------------------------//
        dernierComportement1.clear();
        for (Strategie lesStrategy : lesStrategies) lesStrategy.viderComportement();
        dernierComportement2.clear();
        //--------------------------------- fin: réinitialiser le comportement de chaque tribu  ---------------------------------//
        if (type) {
            System.out.println("\t\t\t\t\tRETOUR\n\n");
            /*****************************************************DEBUT: RETOUR*****************************************************/
            /***********************************************************************************************************************/
            for (int i = 0; i < lesStrategies.size(); i++) { // Tribu1 qui commence à chaque tour
                for (int j = i; j < lesStrategies.size(); j++) { // Tribu2
                    //--------------------------------- debut: réinitialiser le comportement de chaque tribu  ---------------------------------//
                    dernierComportement1.clear();
                    for (Strategie lesStrategy : lesStrategies) lesStrategy.viderComportement();
                    dernierComportement2.clear();
                    //--------------------------------- fin: réinitialiser le comportement de chaque tribu  ---------------------------------//

                    lesStrategies.get(i).setComportementDepart(lesStrategies.get(i).getNumeroStrategie()); // Premier comportement de Tribu1
                    lesStrategies.get(j).setComportementDepart(lesStrategies.get(j).getNumeroStrategie()); // Premier comportement de Tribu2

                    System.out.println("\n\t\t\t\t" + lesStrategies.get(j).getNom() + "   VS   " + lesStrategies.get(i).getNom());
                    System.out.println("========================================================");
                    scoreStrategie1 = 0;
                    scoreStrategie2 = 0;
                    System.out.println("\t\t\tScore1    " + scoreStrategie2 + "       Score2     " + scoreStrategie1);
                    System.out.println("========================================================");


                    for (int tour = 1; tour <= Tournoi.nombreTours; tour++) {
                        System.out.println("\t\t\t\t\t\tTour: " + tour);
                        if (j == i) { // Une des Tribus joue contre elle meme
                            dernierComportement1.add(lesStrategies.get(j).getDernierComportement(tour - 1));// Ajouter le dernier comportement sur la liste des comportements de Tribu1
                            if (dernierComportement1.get(tour - 1) == Comportement.cooperer) {
                                scoreStrategie1 += 3;
                                scoreStrategie2 += 3;
                                lesStrategies.get(j).setScoreRetour(3);
                                System.out.println("\t" + lesStrategies.get(i).getNom() + ":  Score retour  : " + scoreStrategie1 + "  Score Total : " + lesStrategies.get(j).getScoreRetour());
                                System.out.println("--------------------------------------------------------");
                            } else if (dernierComportement1.get(tour - 1) == Comportement.trahir) {
                                scoreStrategie1 += 1;
                                scoreStrategie2 += 1;
                                lesStrategies.get(i).setScoreRetour(1);
                                System.out.println(lesStrategies.get(i).getNom() + ":  Score retour  : " + scoreStrategie1 + "  Score Total : " + lesStrategies.get(i).getScoreRetour());

                            } else if (dernierComportement1.get(tour - 1) == Comportement.renoncer) {
                                scoreStrategie1 += 2;
                                scoreStrategie2 += 2;
                                lesStrategies.get(i).setScoreRetour(2);
                                System.out.println(lesStrategies.get(i).getNom() + ":  Score retour  : " + scoreStrategie1 + "  Score Total : " + lesStrategies.get(i).getScoreRetour());
                                break;
                            }
                            //System.out.println("");
                            //System.out.println("Liste des comportement de la strategie "+lesStrategies.get(i).getNom()+" contre elle meme");
                            //System.out.println(" "+dernierComportement1.toString()+"  ");
                            //System.out.println("");
                            lesStrategies.get(j).setComportement(lesStrategies.get(j).getNumeroStrategie(), dernierComportement1, tour - 1, lesStrategies.get(j).getScoreRetour()); // Tribu1 met à jour son comportement
                        } else {
                            dernierComportement1.add(lesStrategies.get(j).getDernierComportement(tour - 1));// Ajouter le dernier comportement sur la liste des comportements de Tribu1
                            dernierComportement2.add(lesStrategies.get(i).getDernierComportement(tour - 1));// Ajouter le dernier comportement sur la liste des comportements du Tribu2

                            //--------------------------------- debut: Calculer le gagnant de chaque tour ---------------------------------//
                            if (dernierComportement1.get(tour - 1) == Comportement.cooperer && dernierComportement2.get(tour - 1) == Comportement.cooperer) {
                                scoreStrategie1 += 3;
                                scoreStrategie2 += 3;
                                lesStrategies.get(j).setScoreRetour(3); // Mettre a jour le score de Tribu1 =
                                lesStrategies.get(i).setScoreRetour(3); // Mettre a jour le score de Tribu2 =

                                System.out.println("\t" + lesStrategies.get(j).getNom() + ":  Score retour  : " + scoreStrategie1 + "  Score Total : " + lesStrategies.get(j).getScoreRetour());
                                System.out.println("\t" + lesStrategies.get(i).getNom() + ":  Score retour  : " + scoreStrategie2 + "  Score Total : " + lesStrategies.get(i).getScoreRetour());
                                System.out.println("--------------------------------------------------------");

                            } else if (dernierComportement1.get(tour - 1) == Comportement.trahir && dernierComportement2.get(tour - 1) == Comportement.cooperer) {
                                scoreStrategie1 += 5;
                                scoreStrategie2 += 0;
                                lesStrategies.get(j).setScoreRetour(5); // >
                                lesStrategies.get(i).setScoreRetour(0); // <

                                System.out.println("\t" + lesStrategies.get(j).getNom() + ":  Score retour  : " + scoreStrategie1 + "  Score Total : " + lesStrategies.get(j).getScoreRetour());
                                System.out.println("\t" + lesStrategies.get(i).getNom() + ":  Score retour  : " + scoreStrategie2 + "  Score Total : " + lesStrategies.get(i).getScoreRetour());
                                System.out.println("--------------------------------------------------------");

                            } else if (dernierComportement1.get(tour - 1) == Comportement.cooperer && dernierComportement2.get(tour - 1) == Comportement.trahir) {
                                scoreStrategie1 += 0;
                                scoreStrategie2 += 5;
                                lesStrategies.get(j).setScoreRetour(0); // >
                                lesStrategies.get(i).setScoreRetour(5); // <

                                System.out.println("\t" + lesStrategies.get(j).getNom() + ":  Score retour  : " + scoreStrategie1 + "  Score Total : " + lesStrategies.get(j).getScoreRetour());
                                System.out.println("\t" + lesStrategies.get(i).getNom() + ":  Score retour  : " + scoreStrategie2 + "  Score Total : " + lesStrategies.get(i).getScoreRetour());
                                System.out.println("--------------------------------------------------------");

                            } else if (dernierComportement1.get(tour - 1) == Comportement.trahir && dernierComportement2.get(tour - 1) == Comportement.trahir) {
                                scoreStrategie1 += 1;
                                scoreStrategie2 += 1;
                                lesStrategies.get(j).setScoreRetour(1); // =
                                lesStrategies.get(i).setScoreRetour(1); // =

                                System.out.println("\t" + lesStrategies.get(j).getNom() + ":  Score retour  : " + scoreStrategie1 + "  Score Total : " + lesStrategies.get(j).getScoreRetour());
                                System.out.println("\t" + lesStrategies.get(i).getNom() + ":  Score retour  : " + scoreStrategie2 + "  Score Total : " + lesStrategies.get(i).getScoreRetour());
                                System.out.println("--------------------------------------------------------");

                            } else if (dernierComportement1.get(tour - 1) == Comportement.renoncer || dernierComportement2.get(tour - 1) == Comportement.renoncer) {
                                scoreStrategie1 += 2;
                                scoreStrategie2 += 2;
                                lesStrategies.get(j).setScoreRetour(2); // =
                                lesStrategies.get(i).setScoreRetour(2); // =

                                System.out.println("\t" + lesStrategies.get(j).getNom() + ":  Score retour  : " + scoreStrategie1 + "  Score Total : " + lesStrategies.get(j).getScoreRetour());
                                System.out.println("\t" + lesStrategies.get(i).getNom() + ":  Score retour  : " + scoreStrategie2 + "  Score Total : " + lesStrategies.get(i).getScoreRetour());
                                System.out.println("--------------------------------------------------------");

                                break;
                            }
                            //--------------------------------- fin: Calculer le gagnant de chaque tour ---------------------------------//
                            lesStrategies.get(j).setComportement(lesStrategies.get(j).getNumeroStrategie(), dernierComportement2, tour - 1, lesStrategies.get(j).getScoreRetour()); // Tribu1 met à jour son comportement
                            lesStrategies.get(i).setComportement(lesStrategies.get(i).getNumeroStrategie(), dernierComportement1, tour - 1, lesStrategies.get(i).getScoreRetour()); // Tribu2 met à jour son comportement


                            System.out.println("");
                            System.out.println("Liste des comportement de la strategie " + lesStrategies.get(j).getNom());
                            System.out.println(" " + dernierComportement1.toString() + "  ");
                            System.out.println("Liste des comportement de la strategie " + lesStrategies.get(i).getNom());
                            System.out.println(" " + dernierComportement2.toString() + "  ");
                            System.out.println("");

                        }
                    }
                    //--------------------------------- debut: calculer le chaque gagnant de la manche aller ---------------------------------//
                    if (scoreStrategie1 > scoreStrategie2)
                        Tournoi.listeVainqueurRetour.add(lesStrategies.get(j).getNumeroStrategie());

                    else if (scoreStrategie1 < scoreStrategie2)
                        Tournoi.listeVainqueurRetour.add(lesStrategies.get(i).getNumeroStrategie());

                    else
                        Tournoi.listeVainqueurRetour.add(0);
                    System.out.println("\t\t\tVainqueuse de manche : " + Tournoi.listeVainqueurRetour.get(j) + "\n");

                    //---------------------------------- fin: calculer le chaque gagnant de la manche aller ----------------------------------//
                }
            }

            /******************************************************FIN: RETOUR******************************************************/
            /***********************************************************************************************************************/

            //for (Integer i : vainqueursRetour)
            //System.out.println(vainqueursRetour.get(i));
            System.out.println("Vainqueurs du Retour : " + Tournoi.listeVainqueurRetour.toString());
        }
        //--------------------------------- debut: calculer le score total de chaque strategie, aller + retour ---------------------------------//
        for(Strategie laStrategie : lesStrategies) laStrategie.setScoreTotal();
        //---------------------------------- fin: calculer le score total de chaque strategie, aller + retour ----------------------------------//

        System.out.println("\t\tLe score total de chaque strategie : ");
        for(Strategie laStrategie : lesStrategies) System.out.println("\t\t\t\t"+laStrategie.getNom() +" : "+laStrategie.scoreTotal);

        //--------------------------------- debut: calculer le gagnant du tournoi ---------------------------------//
        for (int i = 0; i < lesStrategies.size(); i++){
            if (lesStrategies.get(i).scoreTotal > score) {
                score = lesStrategies.get(i).scoreTotal;
                gagnant = i;
            }
        }
        Tournoi.setVainqueurTournoi(lesStrategies.get(gagnant));
    }
}