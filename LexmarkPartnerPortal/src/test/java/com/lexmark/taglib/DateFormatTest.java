package com.lexmark.taglib;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.jsp.tagext.TagSupport;

import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPageContext;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;

public class DateFormatTest extends EasyMockSupport {
			private DateFormat dateFormat;

			private MockServletContext mockServletContext;
			private MockPageContext mockPageContext;
			private WebApplicationContext mockWebApplicationContext;
 
			@Before
			public void setUp() throws Exception {
				mockServletContext = new MockServletContext();
				
				mockWebApplicationContext = createMock(WebApplicationContext.class);

				mockServletContext.setAttribute(
						WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
						mockWebApplicationContext);
				mockPageContext = new MockPageContext(mockServletContext);
				
				//logger.info(mockPageContext.getRequest().getLocale().getLanguage());//;--en
				
				dateFormat = new DateFormat();
				dateFormat.setPageContext(mockPageContext);
				resetAll();
			}

			@Test
			public void testDateFormatValueNull() throws Exception {
				String expectedOutput = "";
				
				expect(mockWebApplicationContext.getServletContext()).andReturn(
						mockServletContext).anyTimes();
				
				replayAll();
			
				int tagReturnValue = dateFormat.doStartTag();
				
				String output = ((MockHttpServletResponse) mockPageContext
						.getResponse()).getContentAsString();
				assertEquals("Tag should return 'SKIP_BODY'", TagSupport.SKIP_BODY,
						tagReturnValue);
				assertEquals("Output should be ''", expectedOutput, output);
				verifyAll();

			}
			@Test
			public void testDateFormatShowTimeAndSecond() throws Exception {
				String showTime =  "true";
				String showSecond =  "true";
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date value = sdf.parse("11/17/2011 16:13:20");
				
				String expectedOutput = "11/17/2011 16:13:20";
				
				dateFormat.setShowSecond(showSecond);
				dateFormat.setShowTime(showTime);
				dateFormat.setValue(value);
				
				expect(mockWebApplicationContext.getServletContext()).andReturn(
						mockServletContext).anyTimes();
				
				replayAll();
			
				int tagReturnValue = dateFormat.doStartTag();
				
				String output = ((MockHttpServletResponse) mockPageContext
						.getResponse()).getContentAsString();
				assertEquals("Tag should return 'SKIP_BODY'", TagSupport.SKIP_BODY,
						tagReturnValue);
				assertEquals("Output should be '11/17/2011 16:13:20'", expectedOutput, output);
				verifyAll();
			}
			
			@Test
			public void testDateFormatShowTimeAndNoSecond() throws Exception {
				String showTime =  "true";
				String showSecond =  "false";
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date value = sdf.parse("11/17/2011 16:13:20");
				
				String expectedOutput = "11/17/2011 16:13";
				
				dateFormat.setShowSecond(showSecond);
				dateFormat.setShowTime(showTime);
				dateFormat.setValue(value);
				
				expect(mockWebApplicationContext.getServletContext()).andReturn(
						mockServletContext).anyTimes();
				
				replayAll();
			
				int tagReturnValue = dateFormat.doStartTag();
				
				String output = ((MockHttpServletResponse) mockPageContext
						.getResponse()).getContentAsString();
				assertEquals("Tag should return 'SKIP_BODY'", TagSupport.SKIP_BODY,
						tagReturnValue);
				assertEquals("Output should be '11/17/2011 16:13'", expectedOutput, output);
				verifyAll();
			}
			
			@Test
			public void testDateFormatNoShowTime() throws Exception {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date value = sdf.parse("11/17/2011 16:13:20");
				
				String expectedOutput = "11/17/2011";
				
				dateFormat.setValue(value);
				
				expect(mockWebApplicationContext.getServletContext()).andReturn(
						mockServletContext).anyTimes();
				
				replayAll();
			
				int tagReturnValue = dateFormat.doStartTag();
				
				String output = ((MockHttpServletResponse) mockPageContext
						.getResponse()).getContentAsString();
				assertEquals("Tag should return 'SKIP_BODY'", TagSupport.SKIP_BODY,
						tagReturnValue);
				assertEquals("Output should be '11/17/2011'", expectedOutput, output);
				verifyAll();
			}
			
			@Test
			public void testDateFormatOtherLanguage() throws Exception {
				MockHttpServletRequest httpRequest = new MockHttpServletRequest();
				httpRequest.addPreferredLocale(Locale.GERMAN);
				mockPageContext = new MockPageContext(mockServletContext,httpRequest);

				dateFormat = new DateFormat();
				dateFormat.setPageContext(mockPageContext);
				
				mockPageContext.getRequest().getLocale().getLanguage();
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date value = sdf.parse("11/17/2011 16:13:20");
				
				String expectedOutput = "17.11.2011";
				
				dateFormat.setValue(value);
				
				expect(mockWebApplicationContext.getServletContext()).andReturn(
						mockServletContext).anyTimes();
				
				replayAll();
			
				int tagReturnValue = dateFormat.doStartTag();
				
				String output = ((MockHttpServletResponse) mockPageContext
						.getResponse()).getContentAsString();
				assertEquals("Tag should return 'SKIP_BODY'", TagSupport.SKIP_BODY,
						tagReturnValue);
				assertEquals("Output should be '17.11.2011'", expectedOutput, output);
				verifyAll();
			}
		}