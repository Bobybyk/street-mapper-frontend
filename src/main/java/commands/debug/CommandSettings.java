package commands.debug;

import console.Console;
import console.Debug;
import console.DebugList;

public class CommandSettings implements CommandDebug {

    @Override
    public void execute(String[] args, Console console) {

        try {
            boolean value = args[2].equals("0") ? false : true;
            Debug.debugTypeStatus.put(Debug.debugTypes.get(args[1]), value);
            Debug.print(DebugList.SETTINGS, "Débogage " + args[1] + (args[2].equals("0") ? "désactivé" : "activé"));
        } catch (ArrayIndexOutOfBoundsException e) {
            Debug.print(DebugList.SETTINGS, "[ERREUR/CommandSettings] gestion debogage, out of bounds exception");
        } catch (NullPointerException e) {
            Debug.print(DebugList.SETTINGS, "[ERREUR/CommandSettings] gestion debogage, null pointeur exception");
        }

    }
    
}
