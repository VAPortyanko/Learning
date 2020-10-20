/**********************************************************************************************/
/* Example of the addSuppressed and getSuppressed methods of the throwable class.             */
/* If we have several exceptions, several of which have been suppressed, we can attach        */
/* information about them to the main exception.                                              */
/* We can read the suppressed exceptions later.                                               */
/**********************************************************************************************/

package suppressedExceptions;

public class SuppressedExceptions {
	public static void main(String[] args) throws Exception {
		try {
			callTryFinallyBlock();
		} catch (Exception e) {
			System.err.println("We catched the exception:");
			e.printStackTrace();
			System.err.println("It contains suppresed exceptions:");
			for (Throwable t : e.getSuppressed()) {
				t.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	private static void callTryFinallyBlock() throws Exception {
		Throwable t = null;
		try {
			throw new TryException();
		} catch (Exception e) {
			t = e;
		} finally {
			FinallyException fEx = new FinallyException();
			if (t != null)
				fEx.addSuppressed(t);
			throw fEx;
		}
	}
}

@SuppressWarnings("serial")
class TryException extends Exception {
}

@SuppressWarnings("serial")
class FinallyException extends Exception {
}
