package test;

import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TestMyVelibSection23 {

	@BeforeAll
	static void initAll() {
		System.out.println("INIT ALL @ time in millisecond: " + Calendar.getInstance().getTimeInMillis());
		System.out.println("\tLoading once << my_velib.ini >> if possible");
	}

	@BeforeEach
	void init() {
		System.out.println("\nINIT @ time in millisecond: " + Calendar.getInstance().getTimeInMillis());
		System.out.println("\tLoading common << my_velib.ini >> for each test (if not possible with INIT ALL !)");
	}
	
	// testScenario_section_23_req_1.txt
	// testScenario_section_23_req_2.txt
	// testScenario_section_23_req_3.txt
	// testScenario_section_23_req_4.txt
	// can, of course, be the same and called testScenario_section23.txt
	// It can be loaded only once with initAll()
	
	// JUnit test for section 23 / Requirement 1
	void requirement_1_loadScenario() {
		System.out.println("\tSCN_1: Optional loading of a textual scenario << testScenario_section_23_req_1.txt >> bringing");
		System.out.println("\t\tthe system into the required configuration to test the requirement ! ");
	}

	@Test
	void requirement_1_Test() {
		System.out.println("REQUIREMENT 1 TEST @ time in millisecond: " + Calendar.getInstance().getTimeInMillis());
		System.out.println("\tREQ_1: To rent a bicycle a user must get to one station, identify herself(either through");
		System.out.println("\t\ta velib-card or through a credit-card) and pick up one of the available bikes.");
		
		requirement_1_loadScenario();
		
		// TEST CODE
	}

	// JUnit test for section X / Requirement 2
	void requirement_2_loadScenario() {
		System.out.println("\tSCN_2: Optional loading of << testScenario_section_23_req_2.txt >>");
	}

	@Test
	void requirement_2_Test() {
		System.out.println("REQUIREMENT 2 TEST @ time in millisecond: " + Calendar.getInstance().getTimeInMillis());
		System.out.println("\tREQ_2: A user can only rent at most one bicycle (i.e. if she has a bicycle");
		System.out.println("\t\tand has not yet returned it, she cannot rent a second one).");
		
		requirement_2_loadScenario();
		
		// TEST CODE
		fail("REQ_2: a failing test");
	}
	
	// JUnit test for section X / Requirement 3
	void requirement_3_loadScenario() {
		System.out.println("\tSCN_3: Optional loading of << testScenario_section_23_req_3.txt >>");
	}

	@Test
	void requirement_3_Test() {
		System.out.println("REQUIREMENT 3 TEST @ time in millisecond: " + Calendar.getInstance().getTimeInMillis());
		System.out.println("\tREQ_3: To return a bicycle a user must park it to a free (and on-duty) parking bay of some station.");
		
		requirement_3_loadScenario();
		
		// TEST CODE
	}
	
	// JUnit test for section X / Requirement 4
	void requirement_4_loadScenario() {
		System.out.println("\tSCN_4: Optional loading of << testScenario_section_23_req_4.txt >>");
	}

	@Test
	void requirement_4_Test() {
		System.out.println("REQUIREMENT 3 TEST @ time in millisecond: " + Calendar.getInstance().getTimeInMillis());
		System.out.println("\tREQ_3: When the bike is returned the cost for the ride is computed and user is");
		System.out.println("\t\tautomatically charged (if a charge applies).");
		
		
		requirement_4_loadScenario();
		
		// TEST CODE
		fail("REQ_4: a failing test");
	}
	
	
//	@Test
//	void failingTest() {
//		System.out.println("FAILING TEST @ time in millisecond: " + Calendar.getInstance().getTimeInMillis());
//		fail("a failing test");
//	}
//
//	@Test
//	@Disabled("for demonstration purposes")
//	void skippedTest() {
//		System.out.println("SKIPPED TEST @ time in millisecond: " + Calendar.getInstance().getTimeInMillis());
//		// not executed
//	}

	@AfterEach
	void tearDown() {
		System.out.println("TEAR DOWN @ time in millisecond: " + Calendar.getInstance().getTimeInMillis());
		System.out.println("\tCommon code to reset the system after each test, if need");
	}

	@AfterAll
	static void tearDownAll() {
		System.out.println("\nTEAR DOWN ALL @ time in millisecond: " + Calendar.getInstance().getTimeInMillis());
		System.out.println("\tReset once the system after all tests, if possible");
	}

}
