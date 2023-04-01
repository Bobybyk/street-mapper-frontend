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
        System.out.println("## ROUTE <addr1> <addr2> : demande trajet ##");
        System.out.println("##                                        ##");
        System.out.println("############################################");
    }
    
}
