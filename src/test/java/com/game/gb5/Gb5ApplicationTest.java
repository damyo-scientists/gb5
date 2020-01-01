package com.game.gb5;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Gb5ApplicationTest {
	private static ConfigurableApplicationContext configurableApplicationContext;
	private static SpringApplicationBuilder sprab;
	private static final String APPLICATION = "spring.config.location=classpath:/application.properties";
	
	@BeforeAll
	public static void setUp() {
		sprab = new SpringApplicationBuilder(Gb5ApplicationTest.class);
		
		configurableApplicationContext =
				sprab.web(WebApplicationType.NONE)
						.build().run();
	}
	
	@Test
	@Order(1)
	@DisplayName("ActiveProfile")
	public void activeProfileTest() {
		System.out.println(sprab);
		Assertions.assertArrayEquals(new String[]{}, configurableApplicationContext.getEnvironment().getActiveProfiles());
	}
	
	@Test
	@Order(2)
	@DisplayName("ContainsProperty")
	public void containsPropertyTest() {
		
		Assertions.assertTrue(configurableApplicationContext.getEnvironment().containsProperty("spring.application.name"));
		Assertions.assertTrue(configurableApplicationContext.getEnvironment().containsProperty("google.client.clientId"));
	}
}