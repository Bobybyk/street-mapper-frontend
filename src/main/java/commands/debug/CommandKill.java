package commands.debug;

import console.Console;

public class CommandKill implements CommandDebug {

    @Override
    public void execute(String[] args, Console console) {
        if (console.getClient() == null) {
            console.setRunning(false);
        } else if (console.getClient().kill()) {
            console.setRunning(false);
        } else {
            System.out.println("Impossible de fermer la connexion...");
        }
    }

}
