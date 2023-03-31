package commands.tcp.out;

import commands.tcp.RequestTcp;
import commands.tcp.RequestIndexesList;

public class RequestTcpRoute implements RequestTcp {

    public RequestTcpRoute() {}

    @Override
    public String commandBuilder(String[] args) throws ArrayIndexOutOfBoundsException {
        return RequestIndexesList.ROUTE+";" + args[1] + ";" + args[2];
    }
    
}
