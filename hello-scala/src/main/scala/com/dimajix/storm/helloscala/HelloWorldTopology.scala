package com.dimajix.storm.helloworld;

import java.util.Map;

import org.json.simple.JSONValue;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.Nimbus.Client;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.NimbusClient;
import backtype.storm.utils.Utils;

object HelloWorldTopology {
	final val NIMBUS_HOST = "10.110.55.40";
	final val NIMBUS_THRIFT_PORT = 6627;
	final val ZOOKEEPER_SERVERS = "10.110.55.46";
	final val ZOOKEEPER_PORT = 2181;

	/**
	 * @param args
	 * @throws Exception 
	 * @throws  
	 */
	def main(args:Array[String]) = {
		  var builder = new TopologyBuilder();
      builder.setSpout("randomHelloWorld", new HelloWorldSpout(), 2);        
      builder.setBolt("HelloWorldBolt", new HelloWorldBolt(), 10)
              .shuffleGrouping("randomHelloWorld");
                
      var conf = new Config();
        
      if(args!=null && args.length > 0) {
        	conf.setNumWorkers(20);
            StormSubmitter.submitTopology(args(0), conf, builder.createTopology());
      } 
      else {
            conf.setDebug(true);
        
            var cluster = new LocalCluster();
            cluster.submitTopology("test", conf, builder.createTopology());
            Utils.sleep(10000);
            cluster.killTopology("test");
            cluster.shutdown();    
      }
	}

}
