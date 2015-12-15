package util;

import static java.lang.System.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class LogTest {
	private static final Log logger = LogFactory.getLog(LogTest.class);

	@Test
	public void testDebug() {
		out.println("trace = " + logger.isTraceEnabled());
		out.println("debug = " + logger.isDebugEnabled());
		out.println("info = " + logger.isInfoEnabled());
		out.println("warn = " + logger.isWarnEnabled());
		out.println("error = " + logger.isErrorEnabled());
		out.println("fatal = " + logger.isFatalEnabled());

		logger.trace("trace message");
		logger.debug("debug message");
		logger.info("info message");
		logger.warn("warn message");
		logger.error("error message");
		logger.fatal("fatal message");
	}
}
