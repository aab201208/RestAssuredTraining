package Day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {

	@Test
	void testGenerateDummyData()
	{
		Faker faker = new Faker();
		String fullname = faker.name().fullName();
		System.out.println(fullname);
	}
}
