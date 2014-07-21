package com.sungard.marketmap.common.vertx.verticles;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;

public class MarketMapClientBusOnly extends Verticle {
                
    Integer randomNumber = 1, numMessages = 0;
    Long start;
    
    public void start() {
    	start = System.currentTimeMillis();
       for(Integer i=0;i<100;i++){
    	   registerSymbol(i.toString());
       }
    }
    
    public void registerSymbol(String symbol)
    {
       EventBus eb = vertx.eventBus();
       eb.publish("data.request", symbol);

       eb.registerHandler("data"+symbol, new Handler<Message>()
                                       {
      @Override
      public void handle(Message arg0) {
    	  numMessages++;
    	  if (numMessages%10000 == 0)
    	  {
    		  System.out.println("Received: "+numMessages+".. last 10000 in "+(System.currentTimeMillis()-start)+"ms");
    		  start = System.currentTimeMillis();
    	  }
      }
      });
    }
                
}

