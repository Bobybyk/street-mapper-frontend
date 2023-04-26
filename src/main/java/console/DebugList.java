package console;

public class DebugList {
    /**
     * erreur critique menant à la fermeture du programme
     */
    public static final int ERROR = -1;
    /**
     * erreur non critique menant à un comportement non souhaité mais ne menant pas à l'arrêt du programme
     */
    public static final int WARNING = 0;
    /**
     * information sur le déroulement du programme
     */
    public static final int INFO = 1;
    /**
     * information obligatoire, ne peut pas être désactivée 
     */
    public static final int GENERAL = 2;
    /**
     * information sur les paramètres de débogage
     */
    public static final int SETTINGS = 3;
    /**
     * information sur le réseau
     */
    public static final int NETWORK = 4;
}
