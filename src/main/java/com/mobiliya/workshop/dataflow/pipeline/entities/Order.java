package com.mobiliya.workshop.dataflow.pipeline.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

	int order_id;
	String create_ts;
	String update_ts;
	int customer_id;
	String created_by;
	String updated_by;
	ArrayList<Item> items;
}
