package commands.tcp;

public interface CommandTcpRcv <T extends Object>{
    public void commandExec(T data);
}
