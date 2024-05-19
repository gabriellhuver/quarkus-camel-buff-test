package org.acme;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuffService {

	public String toBuff(String fluxo, Object o, Class<?> clazz) {
		// Aqui vai ler os campos com a anotação @Campo, pegar os indices, fazer um for
		Field[] declaredFields = clazz.getDeclaredFields();

		HashMap<Integer, String> campos = new HashMap<Integer, String>();

		for (Field field : declaredFields) {
			if (field.getAnnotation(Campo.class) != null) {
				field.setAccessible(true);
				Campo annotation = field.getAnnotation(Campo.class);
				Object valorDoCampo;
				try {
					valorDoCampo = field.get(o);
					// Como acessar o valor do campo ?
					String string = valorDoCampo.toString();
					if (string.length() < annotation.tamanhoMaximo()) {
						for (int i = string.length(); i < annotation.tamanhoMaximo(); i++) {
							string = string + " ";
						}
					}

					campos.put(annotation.indiceDoCampo(), string);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // Obtém o valor do campo para o objeto fornecido

			}
		}

		List<Map.Entry<Integer, String>> listaDeEntradas = new ArrayList<>(campos.entrySet());

		// Ordenar a lista de entradas com base nas chaves (índices dos campos)
		Collections.sort(listaDeEntradas, Comparator.comparing(Map.Entry::getKey));

		StringBuilder retorno = new StringBuilder();

		listaDeEntradas.forEach((entry) -> {
		    retorno.append(entry.getValue());
		});
		
		
		String resultado = retorno.toString();
		
		return fluxo + resultado ;
	}

}
