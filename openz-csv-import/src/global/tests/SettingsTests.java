package global.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import io.SettingsInstantiation;
import global.SettingsValidator;

public class SettingsTests {
	
	@Test
	public void validateSettingsTest() throws IOException{
		assertTrue(SettingsValidator.validateSettings(SettingsInstantiation.getSettings()));
	}

}
