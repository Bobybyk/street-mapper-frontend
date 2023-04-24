package commands.tcp.out;

import commands.tcp.RequestIndexesList;
import commands.tcp.RequestTcp;

public class RequestTcpTimeStation implements RequestTcp {

    @Override
    public String commandBuilder(String[] args) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        return RequestIndexesList.TIME + ";" + args[1] + ";" + Integer.parseInt(args[2]) + ";" + Integer.parseInt(args[3]);
    }
    
}
