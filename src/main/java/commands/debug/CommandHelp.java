package commands.debug;

import console.Console;
import console.Debug;

public class CommandHelp implements CommandDebug {

    private static final String PROMPT_LINE = "##                                                     ##";
    private static final String ASK_PATH_LINE = "##      -> demande le chemin de <addr1> à <addr2>      ##";

    @Override
    public void execute(String[] args, Console console) {
        Debug.writeln("#########################################################");
        Debug.writeln(PROMPT_LINE);
        Debug.writeln("##                 Console de debogage                 ##");
        Debug.writeln(PROMPT_LINE);
        Debug.writeln("##-----------------------------------------------------##");
        Debug.writeln(PROMPT_LINE);
        Debug.writeln("##                  COMMANDES LOCALES                  ##");
        Debug.writeln(PROMPT_LINE);
        Debug.writeln("## kill : ferme le programme                           ##");
        Debug.writeln("## help : affiche ce menu de commandes                 ##");
        Debug.writeln("## debug : affiche les commandes de debogage           ##");
        Debug.writeln("## debug <type> <state>                                ##");
        Debug.writeln("##      -> active ou désactive le type de debogage     ##");
        Debug.writeln(PROMPT_LINE);
        Debug.writeln("##                  COMMANDES RESEAU                   ##");
        Debug.writeln(PROMPT_LINE);
        Debug.writeln("## ROUTE <addr1> <addr2>                               ##");
        Debug.writeln(ASK_PATH_LINE);
        Debug.writeln("## ROUTE <addr1> <addr2> <time>                        ##");
        Debug.writeln(ASK_PATH_LINE);
        Debug.writeln("##      -> du temps <time>                             ##");
        Debug.writeln("## ROUTE <addr1> <addr2> <time> DISTANCE               ##");
        Debug.writeln(ASK_PATH_LINE);
        Debug.writeln("##      -> au temps <time> optimisé en distance        ##");
        Debug.writeln("## ROUTE <addr1> <addr2> <time> TIME                   ##");
        Debug.writeln(ASK_PATH_LINE);
        Debug.writeln("##      -> au temps <time> optimisé en temps           ##");
        Debug.writeln("## TIME <station> <hour> <minutes>                     ##");
        Debug.writeln("##      -> demande temps d'attente pour <station>      ##");
        Debug.writeln("## SEARCH <station>                                    ##");
        Debug.writeln("##      -> demande les station contentant <station>    ##");
        Debug.writeln(PROMPT_LINE);
        Debug.writeln("## exemple <time> : 11:06                              ##");
        Debug.writeln("## exemple <hour> : 12                                 ##");
        Debug.writeln("## exemple <minutes> : 30                              ##");
        Debug.writeln("## exemple <station> : châ                             ##");
        Debug.writeln(PROMPT_LINE);
        Debug.writeln("#########################################################");

    }

}
