package com.mobiliya.workshop.dataflow.pipeline.fn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiliya.workshop.dataflow.pipeline.entities.Item;
import com.mobiliya.workshop.dataflow.pipeline.entities.Order;
import com.mobiliya.workshop.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.beam.sdk.transforms.Partition;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class DataPartitionFn implements Partition.PartitionFn<String> {

	private int valueThreshold;

	public DataPartitionFn(int valueThreshold) {
		this.valueThreshold = valueThreshold;
	}

	@Override
	public int partitionFor(String element, int numPartitions) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Order order = mapper.readValue(element, Order.class);
			int totalPrice = 0;
			ArrayList<Item> items = order.getItems();
			for (Item item: items) {
				totalPrice += item.getPrice_per_unit() * item.getQuantity();
			}
			if (totalPrice >= valueThreshold) {
				return Constants.PARTITION_HIGH_VALUE;
			} else {
				return Constants.PARTITION_REMAINING;
			}
		} catch (IOException e) {
			//log.error(e.getMessage(), e);
			return Constants.PARTITION_INVALID_DATA;
		}
	}
}
