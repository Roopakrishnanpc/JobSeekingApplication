package com.jobseeking;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;








@SpringBootApplication(scanBasePackages = "com.jobseeking")
///@EnableSwagger2
//@OpenAPIDefinition(info=@Info(title="Swagger api", version = "2.0", description = "Documentation for Swagger API"))
public class JobSeekingApplication {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
//                .build().apiInfo(apiInfo()).useDefaultResponseMessages(false);
//    }
//    @Bean
//	private ApiInfo apiInfo() {
//		// TODO Auto-generated method stub
//		final ApiInfoBuilder builder =new ApiInfoBuilder();
//		return builder.build();
//	}
	

	public static void main(String[] args) {
		SpringApplication.run(JobSeekingApplication.class, args);
	}

}
