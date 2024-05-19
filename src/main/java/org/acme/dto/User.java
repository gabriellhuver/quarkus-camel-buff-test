package org.acme.dto;

import java.io.Serializable;

import lombok.Data;


@Data
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7884091945740891273L;
	private String user;
	
}
