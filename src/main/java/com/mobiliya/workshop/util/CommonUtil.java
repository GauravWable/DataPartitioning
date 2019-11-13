package com.mobiliya.workshop.util;

import com.mobiliya.workshop.dataflow.pipeline.steps.FailureMetaData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.beam.vendor.guava.v20_0.com.google.common.base.Throwables;

import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class CommonUtil {

    public static String getTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSXXX");
        Date date = new Date();
        return sdf.format(date.getTime());
    }

    public static FailureMetaData getDataValidationFailureResponse(
            String failedClassName, String errorDescription, String data) {
        return FailureMetaData.builder()
                .failedClass(failedClassName)
                .description(errorDescription)
                .precursorDataString(data)
                .timestamp(getTimeStamp())
                .build();
    }

    public static FailureMetaData getExceptionFailureResponse(
            String className, String data, Exception e) {
        return FailureMetaData.builder()
                .failedClass(className)
                .description(e.getMessage())
                .precursorDataString(data)
                .stackTrace(Throwables.getStackTraceAsString(e))
                .timestamp(getTimeStamp())
                .build();
    }
}
