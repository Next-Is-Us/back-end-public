package com.nextisus.project.util.config;

import com.nextisus.project.util.logging.LoggingInterceptor;
import jakarta.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LogbackConfig implements WebMvcConfigurer {

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
    }


    @PostConstruct
    public void createLogDirectory() {
        // 프로젝트 루트 경로를 기준으로 로그 디렉터리 생성
        String projectRoot = Paths.get("").toAbsolutePath().toString();
        String logDirPath = Paths.get(projectRoot, "log").toString();
        File logDir = new File(logDirPath);

        if (!logDir.exists()) {
            boolean created = logDir.mkdirs();
            if (created) {
                System.out.println("Log directory created: " + logDirPath);
            } else {
                System.err.println("Failed to create log directory: " + logDirPath);
            }
        }
    }
}