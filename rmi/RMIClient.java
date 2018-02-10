/*
 * Created on 01-Mar-2016
 */
package rmi;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import common.MessageInfo;

public class RMIClient {

	public static void main(String[] args) {

		RMIServerI iRMIServer = null;
		
		String strMsg;

		// Check arguments for Server host and number of messages
		if (args.length < 2){
			System.out.println("Needs 2 arguments: ServerHostName/IPAddress, TotalMessageCount");
			System.exit(-1);
		}

		String urlServer = new String("rmi://" + args[0] + "/RMIServer");
		int numMessages = Integer.parseInt(args[1]);

		// DONE: Initialise Security Manager
		if (System.getSecurityManager() == null){
		    System.setSecurityManager(new SecurityManager());
		}

		// DONE: Bind to RMIServer
        System.out.println("Client: Looking up " + urlServer + "...");

        try {
            iRMIServer = (RMIServerI)Naming.lookup(urlServer);
        }
        catch (Exception e)
        {
        System.out.println("Client: Exception thrown looking up " + urlServer);
        System.exit(1);
        }

		// DONE: Attempt to send messages the specified number of times

		try {
			 for(int i = 0; i < numMessages; i++) {
				MessageInfo msg = new MessageInfo(numMessages,i);
				iRMIServer.receiveMessage(msg);
			 }
		} 
		catch (Exception e)
        {
        System.out.println("Client: Exception thrown sending " + urlServer);
        System.exit(1);
        } 
    }
}
