package tests;

import java.util.Arrays;
import java.util.Collection;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RoverFleetTesting {
	private String input;
    private Boolean expected;
	private RoverFleetTestParameters roverFleetChecker;
	
	@Before
	   public void initialize() {
			roverFleetChecker = new RoverFleetTestParameters();
			expected = true;
	   }
	
	 public RoverFleetTesting(String inputToken, Boolean expected) {
	      this.input = inputToken;
	      this.expected = expected;
	   }


	@Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                 { "5 5 N", true }, { "10 8 S", true }, { "4 12 G", false}
           });
    }
    
    @Test
    public void testFleet() {        
        System.out.println("roverFleetChecker is : " + roverFleetChecker);
        assertEquals(expected, roverFleetChecker.validate(input));
    }
}
