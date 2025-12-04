package com.example.Tozin_Solutions_back_end;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import com.example.Tozin_Solutions_back_end.TestConfig;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestConfig.class)
class TozinSolutionsBackEndApplicationTests {

	@Test
	void contextLoads() {	
	}

}
