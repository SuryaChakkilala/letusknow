package com.utils;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

@Stateless
public class QueueHandler {
	@Resource(mappedName = "java:/jms/queue/DLQ")
	Queue jmsQ;
	
	@Inject
	JMSContext context;
	
	public static void sendMessage(String message) {
		try {
			JMSProducer producer = context.
		}
	}
}
