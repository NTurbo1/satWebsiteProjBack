package com.nturbo1.satWebsiteProjBack.web.security.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.nturbo1.satWebsiteProjBack.web.versioning.ApiVersionRequestMappingHandlerMapping;

@Configuration
public class WebMvcRegistrationsConfig implements WebMvcRegistrations {
	
	@Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        ApiVersionRequestMappingHandlerMapping handlerMapping = new ApiVersionRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);
        handlerMapping.setRemoveSemicolonContent(false);
        return handlerMapping;
    }

}
