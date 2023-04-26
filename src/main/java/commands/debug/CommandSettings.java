package commands.debug;

import console.Console;
import console.Debug;
import console.DebugList;

public class CommandSettings implements CommandDebug {

    @Override
    public void execute(String[] args, Console console) {

        if (args.length == 1) {
            for (String key : Debug.debugTypes.keySet()) {
                Debug.print(DebugList.SETTINGS, "Débogage " + key + " : " + (Debug.debugTypeStatus.get(Debug.debugTypes.get(key)) ? "activé" : "désactivé"));
            }
            return;
        }

        try {
            if (args[1].equals("GENERAL")) {
                Debug.print(DebugList.SETTINGS, "le type de debogage GENERAL ne peut pas être modifié");
                return;
            }
            boolean value = args[2].equals("0") ? false : true;
            int type = Debug.debugTypes.get(args[1]);
            Debug.debugTypeStatus.put(type, value);
            Debug.print(DebugList.GENERAL, "Débogage " + args[1] + (args[2].equals("0") ? " désactivé" : " activé"));
        } catch (ArrayIndexOutOfBoundsException e) {
            Debug.print(DebugList.SETTINGS, "[ERREUR/CommandSettings] gestion debogage, out of bounds exception");
        } catch (NullPointerException e) {
            Debug.print(DebugList.SETTINGS, "[ERREUR/CommandSettings] gestion debogage, null pointeur exception");
        }

    }
    
}
