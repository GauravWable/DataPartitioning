package com.mobiliya.workshop.dataflow.pipeline;

import com.mobiliya.workshop.dataflow.pipeline.fn.DataPartitionFn;
import com.mobiliya.workshop.dataflow.pipeline.options.DataPipelineOptions;
import com.mobiliya.workshop.exception.DataPipelineException;
import com.mobiliya.workshop.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.io.jdbc.JdbcIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Partition;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionList;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class DataFlowPipelineBuilder implements Serializable {

	public Pipeline createDataPipeline(String[] args) {

		log.info("create data pipeline function is started");

		final DataPipelineOptions options =
				PipelineOptionsFactory.fromArgs(args).withValidation().as(DataPipelineOptions.class);
		final String projectName = options.getProject();
		if (StringUtils.isEmpty(projectName)) {
			throw new DataPipelineException("Project is missing from pipeline options.");
		}

		// Create the Pipeline with the specified options
		final Pipeline pipeline = Pipeline.create(options);

		final int valueThreshold = Integer.parseInt(options.getValueThreshold());
		final String filePath = Paths.get(options.getFilePath()).toString();

		PCollectionList<String> processedOrders =
				pipeline.apply("ReadFile", TextIO.read().from(filePath))
						.apply("Partitioning",Partition.of(3, new DataPartitionFn(valueThreshold)));

		// Partitioned data
		PCollection<String> highValueOrders = processedOrders.get(Constants.PARTITION_HIGH_VALUE);
		PCollection<String> remainingOrders = processedOrders.get(Constants.PARTITION_REMAINING);

		// Publish data
		highValueOrders.apply("PubSub message", PubsubIO.writeStrings().to(options.getHighValueTopic()));

		// Store data
		remainingOrders.apply("Database", JdbcIO.<String>write()
				.withDataSourceConfiguration(JdbcIO.DataSourceConfiguration.create(
						options.getjdbcDriver(), options.getDatabaseURL())
						.withUsername(options.getDatabaseUserName())
						.withPassword(options.getDatabasePassword()))
				.withStatement("insert into pipeline_output(order_json) values(?)")
				.withPreparedStatementSetter(new JdbcIO.PreparedStatementSetter<String>() {
					public void setParameters(String element, PreparedStatement query) {
						try {
							log.info("setParameters");
							query.setString(1, element);
						} catch (SQLException e) {
							log.error(e.getMessage(), e);
						}
					}
				}));
		return pipeline;
	}
}
