package com.epam.autobasematsiuk;

import com.epam.autobasematsiuk.encoder.Coder;
import com.epam.autobasematsiuk.exception.ServiceException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DoHashTest {
    private Coder coder;

    @Before
    public void init() {
        coder = new Coder();
    }

    @After
    public void clear() {
        coder = null;
    }

    @Test
    public void doHashTest() throws ServiceException {
        String login = coder.doHash("client");
        Assert.assertNotNull(login);
    }
}
