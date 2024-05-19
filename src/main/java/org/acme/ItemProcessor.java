package org.acme;

import org.acme.dto.Item;
import org.acme.dto.ItemBuff;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ItemProcessor implements Processor {

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
		String buff = new BuffService().toBuff("FluxoITEM", itemBuff, ItemBuff.class);
		
		System.out.println(buff);
	}

}
