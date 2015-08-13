package com.epam.autobasematsiuk.tags;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The class TodayDateTag is the customer tag.
 * Shows the current date.
 */
public class TodayDateTag extends TagSupport {

    public static final Logger LOGGER = Logger.getLogger(TodayDateTag.class);
    private static final long serialVersionUID = 8519258576335973884L;

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
     */
    @Override
    public int doStartTag() {
        LocalDate localDate = LocalDate.now();
        String lang = (String) pageContext.getSession()
                .getAttribute("lang");
        Locale locale = new Locale(lang);
        ResourceBundle rb = ResourceBundle.getBundle(
                "pagecontent", locale);
        String time = rb.getString("today.date") + localDate;
        try {
            JspWriter out = pageContext.getOut();
            out.write(time);
        } catch (IOException e) {
            LOGGER.error("IOException in method TodayDate.doStartTag", e);
        }
        return SKIP_BODY;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
     */
    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}