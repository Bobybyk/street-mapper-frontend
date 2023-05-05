package vue.utils;

public class Props {

    private static String getFont(String font) {
        return new StringBuilder("/font/").append(font).toString();
    }

    private static String getIcon(String font) {
        return new StringBuilder("/icon/").append(font).toString();
    }

    private Props(){}

    /**
     * Path
     */
    public static final String FONT_PATH_NORMAL = getFont("Roboto-Regular.ttf");
    public static final String FONT_PATH_LIGHT = getFont("Roboto-Light.ttf");

    public static final String ICON_PATH_SEARCH = getIcon("recherche_icon.png");
    public static final String ICON_PATH_HISTORY = getIcon("history_icon.png");
    public static final String ICON_PATH_TIME = getIcon("temps_icon.png");
    /**
     * String
     */
    public static final String HISTORY = "Historique";
    public static final String RECHERCHE = "Rechercher";
    public static final String HORAIRE = "Horaire";
    public static final String CURSOR_IMAGE = getIcon("pointeurMap.png");
    public static final String LIST_TRAJETS = "La liste des vos derniers trajets recherchées";
    public static final String DEPART = "Depart";
    public static final String ARRIVE = "Arrive";
    public static final String VALIDER = "Valider";

    public static final String TYPE_TRAJET = "Type de trajet :";
    public static final String SECTION_A_PIED = "Section à pied : ";
    public static final String DEPART_A = "Depart à :";
    public static final String DISTANCE = "Distance";
    public static final String TEMPS = "Temps ";

    public static final String CHAMPS_INCORRECT = "Erreur champs incorrect";
    public static final String RECHERCHE_EN_COURS = "Recherche en attente ...";
    public static final String OPTION_RECHERCHE = "Option de recherche";
    public static final String NON = "Non ";
    public static final String OUI = "Oui ";

    public static final String POSE_A = "Position A";
    public static final String POSE_B = "Position B";
    public static final String PLACER_MARQUER = "Placez marqueur";
    public static final String NETTOYER_MAP = "Nettoyez la map";

    public static final String STATION = "Station";
    public static final String HORAIRE_A = "Horaire à :";

    public static final String FILE_SERIALIZE_ROUTE = "history.ser";
    public static final String DESTINATION = "Vous êtes déjà à destination.";
    public static final String BUTTON_VOIR_MAP = "Voir sur la map toutes les stations";
    public static final String UNIQUEMENT_SECTIONS = "Uniquement les sections sont affichées ici";
    public static final String MON_TRAJET = "Mon trajet:";

    public static final String CLIENT_INVALID = "Impossible de se connecter au serveur";
}
