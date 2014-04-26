package com.seethayya.shoppingcart.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.net.SMTPAppender;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.TriggeringEventEvaluator;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/26/14
 * Time: 7:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class DynamicSubjectAppender extends SMTPAppender{

    private static final Logger LOGGER = Logger.getLogger(DynamicSubjectAppender.class);

  /*  public DynamicSubjectAppender() throws IOException {
        super();
        LOGGER.debug("-----------------------cond");
        Properties prop = new Properties();

        InputStream input = DynamicSubjectAppender.class.getClassLoader().getResourceAsStream("mail.properties");
        // load a properties file
		prop.load(input);

		// get the property value and print it out
		LOGGER.debug(prop.getProperty("userName"));
		LOGGER.debug(prop.getProperty("password"));
        setSMTPUsername(prop.getProperty("userName"));
        setSMTPPassword(prop.getProperty("password"));

    }*/


    /**
   * Layout body of email message.
   * @since 1.2.16
   */
  protected String formatBody() {


	  // Note: this code already owns the monitor for this
	  // appender. This frees us from needing to synchronize on 'cb'.

      StringBuffer sbuf = new StringBuffer();
      String t = layout.getHeader();
      LoggingEvent event = null;
      if(t != null)
	sbuf.append(t);
      int len =  cb.length();
      for(int i = 0; i < len; i++) {
	//sbuf.append(MimeUtility.encodeText(layout.format(cb.get())));
	event = cb.get();
	sbuf.append(layout.format(event));
	if(layout.ignoresThrowable()) {
	  String[] s = event.getThrowableStrRep();
	  if (s != null) {
	    for(int j = 0; j < s.length; j++) {
	      sbuf.append(s[j]);
	      sbuf.append(Layout.LINE_SEP);
	    }
	  }
	}
      }
      t = layout.getFooter();
      if(t != null) {
	    sbuf.append(t);
      }

      //Dynamic subject change start
      LocationInfo locationInfo = event.getLocationInformation();
      String subject = getSubject();
      subject = StringUtils.replace(subject, "#CLASS", locationInfo.getClassName());
      subject = StringUtils.replace(subject, "#FILE", locationInfo.getFileName());
      subject = StringUtils.replace(subject, "#METHOD", locationInfo.getMethodName());
      subject = StringUtils.replace(subject, "#LINENO", locationInfo.getLineNumber());
      try {
          msg.setSubject(MimeUtility.encodeText(subject, "UTF-8", null));
      } catch (UnsupportedEncodingException ex) {
          LOGGER.error("Unable to encode SMTP subject", ex);
      } catch (MessagingException e) {
          LOGGER.error("Unable to set SMTP subject", e);
      }
      //Dynamic subject change End

      return sbuf.toString();
  }
}
