package console;

import java.util.HashMap;

public class Debug {
    /**
     * Liste des types de debug disponibles et leur état (activé ou non)
     */
    public static HashMap<Integer, Boolean> debugTypeStatus;
    /**
     * Liste des types de debug disponibles et leur index
     */
    public static HashMap<String, Integer> debugTypes;
   
    /**
     * Initialisation des types de debug
     */
    static {
        debugTypeStatus = new HashMap<Integer, Boolean>();
        debugTypes = new HashMap<String, Integer>();

        debugTypeStatus.put(DebugList.ERROR, true);
        debugTypes.put("ERROR", DebugList.ERROR);
        debugTypeStatus.put(DebugList.INFO, true);
        debugTypes.put("INFO", DebugList.INFO);
        debugTypeStatus.put(DebugList.WARNING, true);
        debugTypes.put("WARNING", DebugList.WARNING);
        debugTypeStatus.put(DebugList.GENERAL, true);
        debugTypes.put("GENERAL", DebugList.GENERAL);
        debugTypeStatus.put(DebugList.SETTINGS, true);
        debugTypes.put("SETTINGS", DebugList.SETTINGS);
        debugTypeStatus.put(DebugList.NETWORK, true);
        debugTypes.put("NETWORK", DebugList.NETWORK);
    }

    /**
     * Affiche le message de debug si son type est activé
     * @param type type de debug
     * @param message message à afficher
     */
    public static void print(int type, String message) {
        try {
            if (debugTypeStatus.get(type) || type == DebugList.GENERAL) {
                System.out.println(message);
            }
        } catch (NullPointerException e) {
            System.out.println("[ERREUR/Debug] type de debug inconnu");
        }
    }
}
