package com.mobiliya.workshop.dataflow.pipeline.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {

	@JsonProperty("item_number")
	int itemNumber;

	@JsonProperty("price_per_unit")
	int pricePerUnit;

	@JsonProperty("quantity")
	int quantity;

}
