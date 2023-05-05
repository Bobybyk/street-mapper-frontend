package commands.tcp.out;

import commands.tcp.RequestIndexesList;
import commands.tcp.RequestTcp;

public class RequestTcpSearchStation implements RequestTcp {

    @Override
    public String commandBuilder(String[] args) throws ArrayIndexOutOfBoundsException {
        if(args.length > 3) throw new ArrayIndexOutOfBoundsException();
        return RequestIndexesList.SEARCH + ";" + args[1] + ";" + args[2];
    }

}
