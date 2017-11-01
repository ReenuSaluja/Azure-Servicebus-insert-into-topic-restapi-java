package com.ms.servicebusExample;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.servicebus.ConnectionStringBuilder;
import com.microsoft.windowsazure.Configuration;
import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.services.servicebus.ServiceBusConfiguration;
import com.microsoft.windowsazure.services.servicebus.ServiceBusContract;
import com.microsoft.windowsazure.services.servicebus.ServiceBusService;
import com.microsoft.windowsazure.services.servicebus.models.BrokeredMessage;

@Path("/eventHubmsg")
public class Processevent {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String processBlankMsg() {
 
		String result = "Hello Event Hub... I don't have anything to send.";
		return result;
	}
 
	@Path("{c}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String processStrMsg(@PathParam("c") String c) {
		
		final String namespaceName = "---eventhub namespace name------";
        final String eventHubName = "---eventhub  name------";
        final String sasKeyName = "----key name-----";
        final String sasKey = "----key--------";
        ConnectionStringBuilder connStr = new ConnectionStringBuilder(namespaceName, eventHubName, sasKeyName, sasKey);
         byte[] payloadBytes;
        		EventData sendEvent;
        		java.util.Date date = new java.util.Date();
try
{
	

	EventHubClient ehClient = EventHubClient.createFromConnectionStringSync(connStr.toString());
       {
        	String s="TestMsg:";
        	 payloadBytes =  s.getBytes("UTF-8");
             sendEvent = new EventData(payloadBytes);
        //ehClient.sendSync(sendEvent);
             ehClient.send(sendEvent);
        ehClient.close();
        }
}catch(Exception e)
{
	
}
		return "Successful done: "+c;
	}
}
