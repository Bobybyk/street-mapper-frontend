package commands.tcp.out;

import commands.tcp.RequestIndexesList;
import commands.tcp.RequestTcp;

public class RequestTcpTimeStation implements RequestTcp {

    @Override
    public String commandBuilder(String[] args) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        if (!isTimeFormat(args[2])) throw new NumberFormatException();
        return RequestIndexesList.TIME + ";" + args[1] + ";" + args[2];
    }
    
}
