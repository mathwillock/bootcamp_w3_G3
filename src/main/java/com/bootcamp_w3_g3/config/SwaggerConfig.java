package com.bootcamp_w3_g3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
//@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

//    private List<ResponseMessage> responseMessageForGET(){
//        return new ArrayList<>() {{
//            add(new ResponseMessageBuilder()
//                    .code(500)
//                    .message("500 message")
//                    .responseModel(new ModelRef("Error"))
//                    .build());
//            add(new ResponseMessageBuilder()
//                    .code(403)
//                    .message("Forbidden!")
//                    .build());
//        }};
//    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
//                .useDefaultResponseMessages(false)
//                .globalResponseMessage(RequestMethod.GET, responseMessageForGET());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Projeto final Bootcamp Wave 3 - Grupo 3",
                "Grupo 03" + "\n" +
                        "Nomes dos Responsáveis do projeto:" + "\n" +
                        "Alex Cruz" + "\n" +
                        "Joaquim Borges" + "\n" +
                        "Hugo Damm" + "\n" +
                        "Marcelo Santos" + "\n" +
                        "Matheus Willock",
                "2.0",
                "TERMS OF SERVICE URL",
                new Contact("NAME","URL","EMAIL"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }

}



