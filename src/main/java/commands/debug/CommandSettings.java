package commands.debug;

import java.util.Map.Entry;

import console.Console;
import console.Debug;
import console.DebugList;

public class CommandSettings implements CommandDebug {

    @Override
    public void execute(String[] args, Console console) {

        if (args.length == 1) {

            for (Entry<String, Integer> entry : Debug.getDebugTypes().entrySet()) {
                String key = entry.getKey();
                Integer debugType = entry.getValue();
                boolean isEnable = Debug.getDebugTypeStatus().get(debugType).booleanValue();
                String isEnableString = isEnable ? "activé" : "désactivé";
                Debug.print(DebugList.SETTINGS, "Débogage " + key + " : " + isEnableString);
            }
            return;
        }

        try {
            if (args[1].equals("GENERAL")) {
                Debug.print(DebugList.SETTINGS, "le type de debogage GENERAL ne peut pas être modifié");
                return;
            }
            boolean value = !args[2].equals("0");
            int type = Debug.getDebugTypes().get(args[1]);
            Debug.getDebugTypeStatus().put(type, value);
            Debug.print(DebugList.GENERAL, "Débogage " + args[1] + (args[2].equals("0") ? " désactivé" : " activé"));
        } catch (ArrayIndexOutOfBoundsException e) {
            Debug.print(DebugList.SETTINGS, "[ERREUR/CommandSettings] gestion debogage, out of bounds exception");
        } catch (NullPointerException e) {
            Debug.print(DebugList.SETTINGS, "[ERREUR/CommandSettings] gestion debogage, null pointeur exception");
        }

    }
    
}
