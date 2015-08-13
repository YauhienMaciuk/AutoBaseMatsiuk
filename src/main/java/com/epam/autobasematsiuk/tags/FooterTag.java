package com.epam.autobasematsiuk.tags;


import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * The class FooterTag is the customer tag.
 * Shows email of developer at the bottom of the page.
 */
public class FooterTag extends TagSupport {

    public static final Logger LOGGER = Logger.getLogger(FooterTag.class);
    private static final long serialVersionUID = 4262217135537465097L;

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
     */
    @Override
    public int doStartTag() throws JspException {
        try {
            String email = "matsiuk89@gmail.com";
            JspWriter out = pageContext.getOut();
            String footer = "<p>" + email + "</p>";
            out.write(footer);
        } catch (IOException e) {
            LOGGER.error("IOException in method FooterTag.doStartTag", e);
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
