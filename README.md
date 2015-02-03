# storm-playground
Personal playground for Apache Storm


# You need to setup storm for cluster testing:

```
cd /opt
sudo wget http://psg.mtu.edu/pub/apache/storm/apache-storm-0.9.3/apache-storm-0.9.3.tar.gz
sudo tar xvzf apache-storm-0.9.3.tar.gz
sudo ln -s apache-storm-0.9.3 storm
sudo ln -s /opt/storm/bin/storm /usr/local/bin
```


# Store your Storm cluster information in a file:

You should create a file ~/.storm/storm.yaml

```
nimbus.host: "123.45.678.890"
```

# Run topology:

To run on cluster

    storm jar hello-world-1.0-SNAPSHOT-jar-with-dependencies.jar storm.cookbook.HelloWorldTopology MyTopologyName
    
To run locally

    storm jar hello-world-1.0-SNAPSHOT-jar-with-dependencies.jar storm.cookbook.HelloWorldTopology


# Watch topology in web-ui

    http://nimbus-ip:8080
    
