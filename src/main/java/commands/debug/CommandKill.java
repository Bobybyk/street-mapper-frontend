package commands.debug;

public class CommandKill implements CommandDebug {

    @Override
    public void execute(String[] args) {
        // TODO ajouter un système pour déconnecter et fermer le programme de manière propre
        System.exit(0);
    }
    
}
