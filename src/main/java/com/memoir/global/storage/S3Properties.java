package com.memoir.global.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.cloud.aws.s3")
public class S3Properties {
    private final String bucket;

    public S3Properties(String bucket) {
        this.bucket = bucket;
    }
}
