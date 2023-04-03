package commands.tcp.out;

import commands.tcp.RequestIndexesList;
import commands.tcp.RequestTcp;

public class RequestTcpRoute implements RequestTcp {

    public RequestTcpRoute() {
    }

    @Override
    public String commandBuilder(String[] args) throws ArrayIndexOutOfBoundsException {
        return RequestIndexesList.ROUTE + ";" + args[1] + ";" + args[2];
    }

}
