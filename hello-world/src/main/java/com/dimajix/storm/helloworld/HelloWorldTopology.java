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

public class HelloWorldTopology {
	public static final String NIMBUS_HOST = "10.110.55.40";
	public static final int NIMBUS_THRIFT_PORT = 6627;
	public static final String ZOOKEEPER_SERVERS = "10.110.55.46";
	public static final int ZOOKEEPER_PORT = 2181;

	/**
	 * @param args
	 * @throws Exception 
	 * @throws  
	 */
	public static void main(String[] args) throws Exception {
		TopologyBuilder builder = new TopologyBuilder();
        
        builder.setSpout("randomHelloWorld", new HelloWorldSpout(), 2);        
        builder.setBolt("HelloWorldBolt", new HelloWorldBolt(), 10)
                .shuffleGrouping("randomHelloWorld");
                
        Config conf = new Config();
        
        if(args!=null && args.length > 0) {
        	conf.setNumWorkers(20);
            StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
        } 
        else {
            conf.setDebug(true);
        
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("test", conf, builder.createTopology());
            Utils.sleep(10000);
            cluster.killTopology("test");
            cluster.shutdown();    
        }

	}

}
