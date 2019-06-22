package com.expogrow.training;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.expogrow.hot.property.consule.EnableDynamicProperties;

@EnableDynamicProperties
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	private static Log logger = LogFactory.getLog(Application.class);

	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		logger.info("Starting application hot-properties-demo");
		return application.sources(Application.class);
	}

	public static void main(final String[] args) {
		new SpringApplication();
		SpringApplication.run(Application.class, args);
	}

	@Controller
	@RefreshScope
	public class SampleController {

		@Value("${name:World}")
		private String name;

		@Value("${test.msg:}")
		private String msg;

		@GetMapping("/get")
		@ResponseBody
		public String helloWorld() {
			return "{ \"name\": \"" + name + "\", \"test.msg\": \"" + msg + "\" }";
		}

	}

}