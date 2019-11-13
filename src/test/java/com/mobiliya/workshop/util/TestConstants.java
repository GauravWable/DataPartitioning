package com.mobiliya.workshop.util;

public class TestConstants {

	private TestConstants() {
		throw new IllegalStateException("Utility Class");
	}
    public static final String FILE_PATH_KEY = "filePath";
    public static final String FILE_PATH = "c:/beam/minimalwordcount_file_data.txt";

    public static final String VALUE_THRESHOLD_KEY = "valueThreshold";
    public static final String VALUE_THRESHOLD = "1000";

	public static final String PUBSUB_TOPIC_HIGH_VALUE_KEY = "highValueTopic";
    public static final String PUBSUB_TOPIC_HIGH_VALUE = "high_value_data";

    public static final String DATABASE_URL_KEY = "databaseURL";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pipeline_db";

	public static final String JDBC_DRIVER_KEY = "jdbcDriver";
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	public static final String DATABASE_USER_NAME_KEY = "databaseUserName";
    public static final String DATABASE_USER_NAME = "root";

    public static final String DATABASE_PWD_KEY = "databasePassword";
    public static final String DATABASE_PWD = "root";

    public static final String PROJECT_KEY = "project";
    public static final String PROJECT_ID = "test-isx-returns-09201971";

    public static final String RUNNER_KEY = "runner";
    public static final String RUNNER = "DirectRunner";

    public static final String PATTERN = "--%s=%s";

}
