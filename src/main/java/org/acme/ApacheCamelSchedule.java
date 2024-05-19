package org.acme;

import org.apache.camel.builder.RouteBuilder;

public class ApacheCamelSchedule extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("timer:fetchDataTimer?period=60000")
				.setHeader("api-key", constant("123"))
				.to("https://teste/transaction/lucros?bridgeEndpoint=true")
				.unmarshal()
				.json()
				.log("${body}")
				.split()
				.jsonpath("$.log")
				.process(new ItemProcessor());
		
	}
}
