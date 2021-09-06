package by.myproject.news.servise.validation;

import static org.junit.Assert.*;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ValidationInformationTest {
	
	String email;
	String password;

	@Parameters
	public static Collection<Object[]> method() {
			return Arrays.asList(new Object[][] {
				{"alex@mail.com","123qweASD$"},
				{"alex@mail.com","123qweASDZXC$"},
				{"alex@mail.com","123qweASD$"}
					});
	}

	public ValidationInformationTest(String email, String password) {
			this.email = email;
			this.password = password;
	}

	@Test
	public void checkEmailTest() {
		boolean actual = ValidationInformation.checkEmail(this.email);
		assertTrue(actual);
	}
	
	@Test
	public void checkPasswordTest() {
		boolean actual = ValidationInformation.checkPassword(this.password);
		assertTrue(actual);
	}

	
	
	

}
