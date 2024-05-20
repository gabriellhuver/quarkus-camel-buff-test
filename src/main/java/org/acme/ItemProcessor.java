package org.acme;

import org.acme.dto.Item;
import org.acme.dto.ItemBuff;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;


@Dependent
public class ItemProcessor implements Processor {
	
	@Inject
	private BuffService buffService;

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		Object body = exchange.getIn().getBody();
		Item convertValue = new ObjectMapper().convertValue(body, Item.class);
		ItemBuff itemBuff = new ItemBuff();
		itemBuff.setItem(convertValue.getItem());
		itemBuff.setValorComprado(convertValue.getValorComprado());
		itemBuff.setValorVendido(convertValue.getValorVendido());
		itemBuff.setValorRecebido(convertValue.getValorRecebido());
		System.out.println(convertValue);
		String buff = buffService.toBuff(itemBuff, ItemBuff.class);		
		System.out.println("toBuff -> " + buff);
		ItemBuff fromBuff = buffService.fromBuff(buff, ItemBuff.class);
		System.out.println("from buff -> " + fromBuff);
	}

}
