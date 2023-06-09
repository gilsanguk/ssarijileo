package com.example.ssarijileo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class AwsS3DirectoryName {

    // public final static String DEFAULT_PROFILE = "https://woojin-test-bucket.s3.ap-northeast-2.amazonaws.com/test.png"; //프로필 디폴트 이미지 기본경로
    // public final static String DEFAULT_BANNER = "https://woojin-test-bucket.s3.ap-northeast-2.amazonaws.com/test.png"; //배너 디폴트 이미지 기본경로.
    // public final static String DEFAULT_THUMBNAIL = "https://woojin-test-bucket.s3.ap-northeast-2.amazonaws.com/test.png"; //섬네일 디폴트 이미지 기본경로.

	// 노래 파일 경로
    public final static String SONG_PATH = "song-file/";

	// 녹화 파일 경로
	public final static String RECORDING_PATH = "recording-file/";

    // public final static String THUMBNAIL_IMG = "thumbnail-img/"; //베너이미지 관련
    // public final static String THUMBNAIL_FILE = "thumbnail-file/"; //스터디 관련 파일 기본경로.
}