package requests.tcp;

public interface RequestTcp {
    /**
     * @param args : arguments de la requête
     * @return : la requête construite pour le serveur
     */
    public String commandBuilder(String[] args);
}
