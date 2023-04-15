package commands.debug;

import console.Console;

public class CommandHelp implements CommandDebug {

    @Override
    public void execute(String[] args, Console console) {
        System.out.println("############################################");
        System.out.println("##                                        ##");
        System.out.println("##          Console de debogage           ##");
        System.out.println("##                                        ##");
        System.out.println("##----------------------------------------##");
        System.out.println("##                                        ##");
        System.out.println("##           COMMANDES LOCALES            ##");
        System.out.println("##                                        ##");
        System.out.println("## kill : ferme le programme              ##");
        System.out.println("## help : affiche ce menu de commandes    ##");
        System.out.println("##                                        ##");
        System.out.println("##           COMMANDES RESEAU             ##");
        System.out.println("##                                        ##");
        System.out.println("## ROUTE <addr1> <addr2>                  ##");
        System.out.println("##      -> demande le chemin              ##");
        System.out.println("## TIME <station> <hour> <minutes>        ##");
        System.out.println("##      -> demande temps d'attente        ##");
        System.out.println("##                                        ##");
        System.out.println("############################################");
    }

}
