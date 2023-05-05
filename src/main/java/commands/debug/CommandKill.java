package commands.debug;

import console.Console;
import console.Debug;
import console.DebugList;

public class CommandKill implements CommandDebug {

    @Override
    public void execute(String[] args, Console console) {
        if (console.getClient() == null) {
            console.setRunning(false);
        } else if (console.getClient().kill()) {
            console.setRunning(false);
        } else {
            Debug.print(DebugList.WARNING, "[WARNING/CommandKill] Impossible de fermer la connexion...");
        }
    }

}
