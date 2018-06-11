package com.ecms;



import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.IOException;

import javax.jcr.Repository;
import javax.jcr.RepositoryFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ecms.util.LoggingInterceptor;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class MicroserviceEcmsTranscodingApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEcmsTranscodingApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
	}
	
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName())).paths(PathSelectors.any())
				.build().apiInfo(generateApiInfo());
	}

	private ApiInfo generateApiInfo() {
		return new ApiInfo("Transcoding Service", "This service is to.", "Version 1.0 - mw", "urn:tos",
				"transcoding@hcl.com", "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	}

	@Bean
	public FFmpeg ffmpeg() throws IOException {

		return new FFmpeg(
				"D:\\Users\\kandalakar.r\\Documents\\Softwares\\ffmpeg-20180529-ea0010b-win64-static\\ffmpeg-20180529-ea0010b-win64-static\\bin\\ffmpeg");
	}

	@Bean
	public FFprobe ffprobe() throws IOException {

		return new FFprobe(
				"D:\\Users\\kandalakar.r\\Documents\\Softwares\\ffmpeg-20180529-ea0010b-win64-static\\ffmpeg-20180529-ea0010b-win64-static\\bin\\ffprobe");
	}

	@Bean
	public FFmpegBuilder builder() throws IOException {

		return new FFmpegBuilder();
	}
	
		
	
		
}
