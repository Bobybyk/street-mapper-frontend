package requests.tcp.out;

import requests.tcp.RequestTcp;

public class RequestTcpRoute implements RequestTcp {

    @Override
    public String commandBuilder(String[] args) {
        if (args.length < 3) {
            return "undefined";
        }
        return "ROUTE;" + args[1] + args[2];
    }
    
}
