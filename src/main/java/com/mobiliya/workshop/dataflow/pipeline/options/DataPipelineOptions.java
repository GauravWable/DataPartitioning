package com.mobiliya.workshop.dataflow.pipeline.options;

import org.apache.beam.sdk.extensions.gcp.options.GcpOptions;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.Validation;

public interface DataPipelineOptions extends GcpOptions {

    @Validation.Required
    @Description("Path to orders file in JSON format")
    String getFilePath();
    void setFilePath(String filePath);

    @Validation.Required
    @Description("Value threshold value")
    String getValueThreshold();
    void setValueThreshold(String valueThreshold);

    @Validation.Required
    @Description("Database URL command line argument.")
    String getDatabaseURL();
    void setDatabaseURL(String databaseURL);

	@Validation.Required
	@Description("Database URL command line argument.")
	String getJdbcDriver();
	void setJdbcDriver(String jdbcDriver);

    @Validation.Required
    @Description("Database USERNAME command line argument.")
    String getDatabaseUserName();
    void setDatabaseUserName(String databaseUserName);

    @Validation.Required
    @Description("Database PASSWORD command line argument.")
    String getDatabasePassword();
    void setDatabasePassword(String databasePassword);

	@Validation.Required
    @Description("PubSub Topic for high_value data")
    String getHighValueTopic();
    void setHighValueTopic(String highValueTopic);

}
