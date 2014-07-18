package com.sungard.marketmap.common.vertx.data;

import java.util.Map;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.Vertx;
import org.vertx.java.platform.Verticle;

public class MarketLinkDataTest extends Verticle {
                
    private static int sequence = 0;
    private EventBus eb = null;
    
    public void start()
    {
    	init();
    }
    
    public void init() {
       eb = vertx.eventBus();

       // Registering the handler for the specified bus above
       eb.registerHandler("data.request",new Handler<Message>()
       {
           public void handle(Message message)
           {
        	   System.out.println("Message received: "+message);
              long timerID = vertx.setPeriodic(1000, new Handler<Long>() {
                  public void handle(Long timerID) {
                	  int numMsg = 500;
                	  Long start = System.currentTimeMillis();
                     for (int i = 0; i < numMsg; i++){
                                     OnITPriceUpdate(i, null);
                     }
                     System.out.println("Sent "+numMsg+" in "+(System.currentTimeMillis()-start));
                  }
               });
           }
       });
    }

    public void OnITPriceUpdate(int arg0, int[] arg1) {
    	eb.publish("data" + arg0, sequence++);
    }
}
