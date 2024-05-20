package org.acme;

import jakarta.enterprise.context.Dependent;

@Dependent
public class ServiceTest {

	
	public void print() {
		System.out.println("Print test");
	}
	
}
