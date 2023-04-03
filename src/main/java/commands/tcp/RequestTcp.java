package commands.tcp;

public interface RequestTcp {

    /**
     * @param args arguments de la requête
     * @return la requête construite pour le serveur
     * @throws ArrayIndexOutOfBoundsException
     */
    public String commandBuilder(String[] args) throws ArrayIndexOutOfBoundsException;
}
