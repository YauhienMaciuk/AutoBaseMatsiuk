package com.epam.autobasematsiuk;

import com.epam.autobasematsiuk.encoder.Coder;
import com.epam.autobasematsiuk.entity.User;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FindUserTest {
    private Coder coder;
    private ServiceUser serviceUser;

    @Before
    public void init() {
        coder = new Coder();
        serviceUser = new ServiceUser();
    }

    @After
    public void clear() {
        coder = null;
    }

    @Test
    public void findUserTest() throws ServiceException {
        String login = coder.doHash("client");
        String password = coder.doHash("client");
        User user = serviceUser.findUser(login, password);
        Assert.assertNotNull(user);
    }
}