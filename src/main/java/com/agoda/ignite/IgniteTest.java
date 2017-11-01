package com.agoda.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

public class IgniteTest {

	public static void main(String[] args) {
		// Get service proxy for the deployed service.
		
		Ignition.start();
		Ignite ignite = Ignition.ignite();
		
		MyCounterService cntrSvc = ignite.services().
		  serviceProxy("myCounterService", MyCounterService.class, /*not-sticky*/false);

		// Ivoke a method on 'MyCounterService' interface.
		cntrSvc.increment();

		// Print latest counter value from our counter service.
		System.out.println("Incremented value : " + cntrSvc.get());

	}

}
