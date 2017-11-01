package com.agoda.ignite;


import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCluster;
import org.apache.ignite.IgniteServices;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterGroup;
import org.apache.ignite.cluster.ClusterMetrics;
import org.apache.ignite.cluster.ClusterNode;

public class IgniteServiceRunner {

	public static void main(String[] args) {
		Ignition.start();
		Ignite ignite = Ignition.ignite();

		
		
		//ClusterGroup remoteGroup = ignite.cluster().forRemotes();

		// Limit service deployment only to remote nodes (exclude the local node).
		//IgniteServices svcs = ignite.services(remoteGroup);
		
		
		// Cluster group which includes all caching nodes.
		//ClusterGroup cacheGrp = ignite.cluster().forClientNodes("myCounterCache");

		// Get an instance of IgniteServices for the cluster group.
		//IgniteServices svcs = ignite.services(cacheGrp);
		
		
		IgniteServices svcs = ignite.services();
		
		// Deploy per-node singleton. An instance of the service
		// will be deployed on every node within the cluster group.
		//svcs.deployNodeSingleton("myCounterService", new MyCounterServiceImpl());
		//svcs.deployClusterSingleton("myCounterService", new MyCounterServiceImpl());
		svcs.deployMultiple("myCounterService", new MyCounterServiceImpl(), 5, 3);
		IgniteCluster cluster = ignite.cluster();
		System.out.println("nodes: "+cluster.nodes().size());
		ClusterNode localNode = cluster.localNode();

		// Node metrics.
		ClusterMetrics metrics = localNode.metrics();
		
		System.out.println("jobs: "+metrics.getCurrentActiveJobs());
		System.out.println("deployed services : "+svcs.services("myCounterService").size());
	}

}
