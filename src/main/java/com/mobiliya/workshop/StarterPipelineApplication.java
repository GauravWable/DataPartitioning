package com.mobiliya.workshop;

import com.mobiliya.workshop.dataflow.pipeline.DataFlowPipelineBuilder;

/**
 * Provide description here
 * @version 0.1
 */
public class StarterPipelineApplication {

    public static void main(String[] args) {
        new DataFlowPipelineBuilder().createDataPipeline(args).run().waitUntilFinish();
    }
}
