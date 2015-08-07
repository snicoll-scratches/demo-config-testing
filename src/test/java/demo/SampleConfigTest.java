package demo;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class SampleConfigTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	private ConfigurableApplicationContext context;

	@After
	public void closeContext() {
		if (this.context != null) {
			this.context.close();
		}
	}

	@Test
	public void simple() {
		load("sample.name:foo");
		SampleService service = this.context.getBean(SampleService.class);
		assertThat(service.getName(), is("foo"));
	}

	@Test
	public void validationWorks() {
		thrown.expect(BeanCreationException.class);
		thrown.expectMessage("sample.name");
		thrown.expectMessage("may not be null");
		load();
	}

	private void load(String... environment) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(SampleConfig.class);
		EnvironmentTestUtils.addEnvironment(applicationContext, environment);
		applicationContext.refresh();
		this.context = applicationContext;
	}

}
