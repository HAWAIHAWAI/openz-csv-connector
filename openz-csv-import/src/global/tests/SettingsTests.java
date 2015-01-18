package global.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import io.SettingsInstantiation;
import global.SettingsValidator;

/**
 * Simple test class.
 */
public class SettingsTests {

	/**
	 * Test to check if all settings are correct.
	 * @throws IOException Error occurred during execution...
	 */
	@Test
	public void validateSettingsTest() throws IOException {
		assertTrue(SettingsValidator.validateSettings(SettingsInstantiation
				.getSettings()));
	}

}
