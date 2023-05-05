package console;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Debug {
    /**
     * Liste des types de debug disponibles et leur état (activé ou non)
     */
    private static final Map<Integer, Boolean> DEBUG_TYPE_STATUS;
    /**
     * Liste des types de debug disponibles et leur index
     */
    private static final Map<String, Integer> debugTypes;

    private static final Logger LOGGER = Logger.getLogger(Debug.class.getName());

    /**
     * Initialisation des types de debug
     */
    static {
        DEBUG_TYPE_STATUS = new HashMap<>();
        debugTypes = new HashMap<>();

        DEBUG_TYPE_STATUS.put(DebugList.ERROR, true);
        debugTypes.put("ERROR", DebugList.ERROR);
        DEBUG_TYPE_STATUS.put(DebugList.INFO, true);
        debugTypes.put("INFO", DebugList.INFO);
        DEBUG_TYPE_STATUS.put(DebugList.WARNING, true);
        debugTypes.put("WARNING", DebugList.WARNING);
        DEBUG_TYPE_STATUS.put(DebugList.GENERAL, true);
        debugTypes.put("GENERAL", DebugList.GENERAL);
        DEBUG_TYPE_STATUS.put(DebugList.SETTINGS, true);
        debugTypes.put("SETTINGS", DebugList.SETTINGS);
        DEBUG_TYPE_STATUS.put(DebugList.NETWORK, true);
        debugTypes.put("NETWORK", DebugList.NETWORK);
    }

    private Debug(){}

    public static Map<Integer, Boolean> getDebugTypeStatus() {
        return DEBUG_TYPE_STATUS;
    }

    public static Map<String, Integer> getDebugTypes() {
        return debugTypes;
    }

    /**
     * Affiche le message de debug si son type est activé
     * @param type type de debug
     * @param message message à afficher
     */
    public static void print(int type, String message) {
        try {
            if (DEBUG_TYPE_STATUS.get(type) || type == DebugList.GENERAL) {
                System.out.println(message);
                LOGGER.info(message);
            }
        } catch (NullPointerException e) {
            LOGGER.info("[ERREUR/Debug] type de debug inconnu");
        }
    }
}
