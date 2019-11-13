package com.mobiliya.workshop.dataflow.pipeline.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {

	int item_number;
	int price_per_unit;
	int quantity;

}
