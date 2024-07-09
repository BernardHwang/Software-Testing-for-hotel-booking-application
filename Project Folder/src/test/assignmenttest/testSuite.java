package assignmenttest;

import org.junit.runner.RunWith; 
import org.junit.runners.Suite; 
import org.junit.runners.Suite.SuiteClasses; 
@RunWith(value = Suite.class) 
@SuiteClasses(value = {
	TestBookingIntegration.class, 
	TestBookingUnit.class, 
	TestUser.class, 
	TestWaitingList.class, 
}) 
public class testSuite {
	
} 