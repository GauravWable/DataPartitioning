package com.mobiliya.workshop.dataflow.pipeline.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

	@JsonProperty("order_id")
	int orderId;

	@JsonProperty("create_ts")
	String createTs;

	@JsonProperty("update_ts")
	String updateTs;

	@JsonProperty("customer_id")
	int customerId;
	@JsonProperty("created_by")
	String createdBy;

	@JsonProperty("updated_by")
	String updatedBy;

	@JsonProperty("items")
	private ArrayList<Item> items;
}
