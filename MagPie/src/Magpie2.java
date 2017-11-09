
public class Magpie2 {

	//Get a default greeting and return a greeting	
	public String getGreeting() {
		return "Hello, let's talk.";
	}

	/**
	 * Gives a response to a user statement
	 * takes in a user statement
	 * returns a response based on given rules
	 */
	public String getResponse(String statement) {
		String response = "";
		statement.trim();
		if (statement.indexOf("no") >= 0) {
			response = "Why so negative?";
		} else if (statement.indexOf("mother") >= 0
				|| statement.indexOf("father") >= 0
				|| statement.indexOf("sister") >= 0
				|| statement.indexOf("brother") >= 0) {
			response = "Tell me more about your family.";
		} else if(statement.indexOf("Mr.") >=0 || statement.indexOf("Mrs.") >=0 || statement.indexOf("Ms.") >=0 ) {
			response="He sounds like a good teacher.";
		}else if(statement.indexOf("your day") >= 0) {
			response="Good! Thank you.";
		} else if(statement.indexOf("How old") >= 0) {
			response="I am the samge age as you";
		}else if(statement.indexOf("name") >= 0) {
			response="My name's Magpie";
		}else if(statement.equals("")) {
			response="Type something";
		} else {
			response = getRandomResponse();
		}
		return response;
	}

	/**
	 * Pick a default response to use if nothing else fits.
	 * returns a non-committal string
	 */
	private String getRandomResponse() {
		final int NUMBER_OF_RESPONSES = 6;
		double r = Math.random();
		int whichResponse = (int) (r * NUMBER_OF_RESPONSES);
		String response = "";

		if (whichResponse == 0) {
			response = "Interesting, tell me more.";
		} else if (whichResponse == 1) {
			response = "Hmmm.";
		} else if (whichResponse == 2) {
			response = "Do you really think so?";
		} else if (whichResponse == 3) {
			response = "You don't say.";
		} else if (whichResponse==4) {
			response = "That's cool";
		}else if(whichResponse==5) {
			response="I don't really care";
		}

		return response;
	}
}
