package org.acme;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.acme.dto.ItemBuff;
import org.apache.camel.util.CollectionHelper;

import jakarta.enterprise.context.Dependent;

@Dependent
public class BuffService {

	public String toBuff(Object o, Class<?> clazz) {
		// Aqui vai ler os campos com a anotação @Campo, pegar os indices, fazer um for
		Field[] declaredFields = clazz.getDeclaredFields();

		HashMap<Integer, String> campos = new HashMap<Integer, String>();

		String fluxo = "";

		for (Field field : declaredFields) {

			field.setAccessible(true);
			if (field.getName().equals("fluxo")) {
				Object object;
				try {
					object = field.get(o);
					fluxo = object.toString();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (field.getAnnotation(Campo.class) != null) {
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

		return fluxo + resultado;
	}

	public <T> T fromBuff(String buff, Class<?> clazz) throws InstantiationException, IllegalAccessException {
		Field[] declaredFields = clazz.getDeclaredFields();

		HashMap<Integer, Object> campos = new HashMap<Integer, Object>();

		Object newInstance = clazz.newInstance();

		int skip = 0;

		for (Field field : declaredFields) {

			field.setAccessible(true);
			if (field.getName().equals("fluxo")) {
				try {
					field.set(newInstance, buff.substring(0, 5));
					skip = 5;
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (field.getAnnotation(Campo.class) != null) {
				Campo annotation = field.getAnnotation(Campo.class);
				Object valorDoCampo = null;
				try {
					String trim = buff.substring(skip, skip + annotation.tamanhoMaximo()).trim();

					if (field.getType().equals(Double.class)) {
						field.set(newInstance, Double.valueOf(trim));
					} else if (field.getType().equals(String.class)) {
						field.set(newInstance, trim);
					} else if (field.getType().equals(Integer.class)) {
						field.set(newInstance, Integer.valueOf(trim));
					}

					skip = skip + annotation.tamanhoMaximo();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // Obtém o valor do campo para o objeto fornecido

			}
		}

		List<Map.Entry<Integer, Object>> listaDeEntradas = new ArrayList<>(campos.entrySet());

		// Ordenar a lista de entradas com base nas chaves (índices dos campos)
		Collections.sort(listaDeEntradas, Comparator.comparing(Map.Entry::getKey));

		return (T) newInstance;
	}

}
