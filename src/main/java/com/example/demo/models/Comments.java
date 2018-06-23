package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "visions")
public class Comments {
	@Id
	private String _id;
	

}
