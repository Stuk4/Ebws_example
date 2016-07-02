package edu.cibertec.esb.ebws;

import javax.xml.namespace.QName;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.actions.ActionProcessingDetailFaultException;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

/**
 * 
 * @author Stuk4
 *
 */
public class ESBWSListenerAction extends AbstractActionLifecycle{
	
	
	protected ConfigTree configTree;
	
	/**
	 * ctor
	 * @param configTree
	 */
	public ESBWSListenerAction(ConfigTree configTree) {
		this.configTree = configTree;		
	}
	
	public Message displayMessage(Message message) throws ActionProcessingDetailFaultException{
		final String request;
		final String detail;
		final String response;
		
		request = (String) message.getBody().get();
		System.out.println("Request recibido : " + request);
		
		if(request.contains("Error")){
			System.out.println("On error: -> ");
			detail = "<res:holaFault xmlns:res=\"http://www.example.org/Faul\">" +
						"<res:code>00001</res:code>" +
						"<res:desc></res:desc>"+
					"</res:holaFault>";
			throw new ActionProcessingDetailFaultException(
					new QName("http://www.example.org/Faul","Error code"), "Desc Error", detail);
		}else{
			System.out.println("On response");
			//XML de reponse
			response = "<res:HolaResponse xmlns:res=\"http://www.example.org/Response\">" +
					"<res:HolaResp>Hola mundo</res:HolaResp>" +					
					"</res:HolaResponse>";
			System.out.println("mensaje "+response);
			message.getBody().add(response);
		}
		
		return message;
		
	}
	
	
	

}
