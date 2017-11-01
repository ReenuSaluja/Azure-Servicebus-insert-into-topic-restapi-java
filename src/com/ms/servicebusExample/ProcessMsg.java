package com.ms.servicebusExample;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.microsoft.windowsazure.Configuration;
import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.services.servicebus.ServiceBusConfiguration;
import com.microsoft.windowsazure.services.servicebus.ServiceBusContract;
import com.microsoft.windowsazure.services.servicebus.ServiceBusService;
import com.microsoft.windowsazure.services.servicebus.models.BrokeredMessage;

@Path("/busservicemsg")
public class ProcessMsg {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String processBlankMsg() {
 
		String result = "Hello Service bus... I don't have anything to send.";
		return result;
	}
 
	@Path("{c}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String processStrMsg(@PathParam("c") String c) {
		
		Configuration config =
			    ServiceBusConfiguration.configureWithSASAuthentication(
			      "----Name of service bus---",
			      "RootManageSharedAccessKey",
			      "------accesskey-----",
			      ".servicebus.windows.net"
			      );


			ServiceBusContract service = ServiceBusService.create(config);
			try  
			{
			   BrokeredMessage message = new BrokeredMessage(c);
			    service.sendTopicMessage("----topic name----", message);
			    System.out.print("Message Sent ");
			}
			catch (ServiceException e) {
			    System.out.print("ServiceException encountered: ");
			    System.out.println(e.getMessage());
			    System.exit(-1);
			}
		return "Successful done: "+c;
	}
}
