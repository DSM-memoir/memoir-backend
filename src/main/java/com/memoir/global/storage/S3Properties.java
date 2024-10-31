package com.memoir.global.storage;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties("spring.cloud.aws.s3")
public class S3Properties {
    private final String bucket;

    public S3Properties(String bucket) {
        this.bucket = bucket;
    }
}
