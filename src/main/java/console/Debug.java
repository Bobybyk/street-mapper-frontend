package console;

import java.util.HashMap;

public class Debug {
    public static HashMap<Integer, Boolean> debugTypeStatus;
    public static HashMap<Integer, String> debugTypes;
   
    static {
        debugTypeStatus = new HashMap<Integer, Boolean>();
        debugTypes = new HashMap<Integer, String>();

        debugTypeStatus.put(DebugList.ERROR, true);
        debugTypes.put(DebugList.ERROR, "ERROR");
        debugTypeStatus.put(DebugList.INFO, true);
        debugTypes.put(DebugList.INFO, "INFO");
        debugTypeStatus.put(DebugList.GENERAL, true);
        debugTypes.put(DebugList.GENERAL, "GENERAL");
    }

    public static void print(int type, String message) {
        if (debugTypeStatus.get(type) || debugTypeStatus.get(DebugList.GENERAL)) {
            System.out.println(message);
            Console.layout();
        }
    }
}
