package com.mobiliya.workshop.dataflow.pipeline;

import com.mobiliya.workshop.dataflow.pipeline.options.DataPipelineOptions;
import com.mobiliya.workshop.exception.DataPipelineException;
import com.mobiliya.workshop.util.TestConstants;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.testing.TestPipeline;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DataFlowPipelineBuilderTest {

    @Rule
    public final TestPipeline pipeline = TestPipeline.create();

    @Test
    public void testDataFlowPipeline() {

        Map<String, String> arguments = new HashMap<>();
        arguments.put(TestConstants.PROJECT_KEY, TestConstants.PROJECT_ID);
        arguments.put(TestConstants.FILE_PATH_KEY, TestConstants.FILE_PATH);
        arguments.put(TestConstants.VALUE_THRESHOLD_KEY, TestConstants.VALUE_THRESHOLD);
        arguments.put(TestConstants.DATABASE_URL_KEY, TestConstants.DATABASE_URL);
        arguments.put(TestConstants.DATABASE_USER_NAME_KEY, TestConstants.DATABASE_USER_NAME);
        arguments.put(TestConstants.DATABASE_PWD_KEY, TestConstants.DATABASE_PWD);
        arguments.put(TestConstants.JDBC_DRIVER_KEY, TestConstants.JDBC_DRIVER);
        // To logs the failure / exception data

        arguments.put(TestConstants.RUNNER_KEY, TestConstants.RUNNER);
        DataFlowPipelineBuilder builder = new DataFlowPipelineBuilder();

        Pipeline actualPipeline =
                builder.createDataPipeline(
                        arguments.entrySet().stream()
                                .map(e -> String.format(TestConstants.PATTERN, e.getKey(), e.getValue()))
                                .toArray(String[]::new));
        Assert.assertNotNull(actualPipeline);
        DataPipelineOptions options = (DataPipelineOptions) actualPipeline.getOptions();
        Assert.assertEquals(arguments.get(TestConstants.PROJECT_KEY), options.getProject());
        Assert.assertEquals(arguments.get(TestConstants.FILE_PATH_KEY), options.getFilePath());
        Assert.assertEquals(arguments.get(TestConstants.VALUE_THRESHOLD_KEY), options.getValueThreshold());
        Assert.assertEquals(arguments.get(TestConstants.DATABASE_URL_KEY), options.getDatabaseURL());
        Assert.assertEquals(arguments.get(TestConstants.DATABASE_USER_NAME_KEY), options.getDatabaseUserName());
        Assert.assertEquals(arguments.get(TestConstants.DATABASE_PWD_KEY), options.getDatabasePassword());
        Assert.assertEquals(arguments.get(TestConstants.JDBC_DRIVER_KEY), options.getjdbcDriver());
        Assert.assertEquals(arguments.get(TestConstants.RUNNER_KEY), options.getRunner().getSimpleName());
    }

    @Test(expected = DataPipelineException.class)
    public void testDataFlowPipelineWithoutProject() {

        Map<String, String> arguments = new HashMap<>();
        arguments.put(TestConstants.FILE_PATH_KEY, TestConstants.FILE_PATH);
        arguments.put(TestConstants.VALUE_THRESHOLD_KEY, TestConstants.VALUE_THRESHOLD);

        // To logs the failure / exception data
        arguments.put(TestConstants.RUNNER_KEY, TestConstants.RUNNER);

        DataFlowPipelineBuilder sut = new DataFlowPipelineBuilder();
        sut.createDataPipeline(
                arguments.entrySet().stream()
                        .map(e -> String.format(TestConstants.PATTERN, e.getKey(), e.getValue()))
                        .toArray(String[]::new));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDataFlowPipelineWithoutFilePath() {

        Map<String, String> arguments = new HashMap<>();
        arguments.put(TestConstants.PROJECT_KEY, TestConstants.PROJECT_ID);
        arguments.put(TestConstants.VALUE_THRESHOLD_KEY, TestConstants.VALUE_THRESHOLD);

        // To logs the failure / exception data
        arguments.put(TestConstants.RUNNER_KEY, TestConstants.RUNNER);

        DataFlowPipelineBuilder sut = new DataFlowPipelineBuilder();
        sut.createDataPipeline(
                arguments.entrySet().stream()
                        .map(e -> String.format(TestConstants.PATTERN, e.getKey(), e.getValue()))
                        .toArray(String[]::new));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDataFlowPipelineWithoutValueThreshold() {

        Map<String, String> arguments = new HashMap<>();
        arguments.put(TestConstants.PROJECT_KEY, TestConstants.PROJECT_ID);
        arguments.put(TestConstants.FILE_PATH_KEY, TestConstants.FILE_PATH);

        // To logs the failure / exception data
        arguments.put(TestConstants.RUNNER_KEY, TestConstants.RUNNER);

        DataFlowPipelineBuilder sut = new DataFlowPipelineBuilder();
        sut.createDataPipeline(
                arguments.entrySet().stream()
                        .map(e -> String.format(TestConstants.PATTERN, e.getKey(), e.getValue()))
                        .toArray(String[]::new));
    }
}
