package me.serendipify.groupdiscoveryapptemplate.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SerendipifyServiceHttpClientTest {

    private SerendipifyServiceHttpClientImpl service;

    @Before
    public void setUp() throws Exception {
        this.service = new SerendipifyServiceHttpClientImpl("http://api-dot-serendipify-me.appspot.com/api/");
    }

    @Test
    public void testThatCreateGroupWorks() throws Exception {
        String newSessionId = service.createGroup("TEST", "test@test.com");
        assertNotNull(newSessionId);
    }
}
