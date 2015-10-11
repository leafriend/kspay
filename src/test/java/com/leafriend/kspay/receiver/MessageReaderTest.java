package com.leafriend.kspay.receiver;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class MessageReaderTest {

    public static final String MESSAGE = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

    private ByteArrayInputStream input;

    private MessageReader reader;

    @Before
    public void setUp() {
        input = new ByteArrayInputStream(MESSAGE.getBytes());
        reader = new MessageReader(input);
    }

    @Test
    public void test() throws IOException {

        assertThat(reader.getPreface(), is("".getBytes()));

        assertThat(reader.read(6), is("Lorem ".getBytes()));
        assertThat(reader.getPreface(), is("Lorem ".getBytes()));

        assertThat(reader.read(6), is("ipsum ".getBytes()));
        assertThat(reader.getPreface(), is("Lorem ipsum ".getBytes()));

        assertThat(reader.read(39), is("dolor sit amet, consectetur adipiscing ".getBytes()));
        assertThat(reader.getPreface(), is("Lorem ipsum dolor sit amet, consectetur adipiscing ".getBytes()));

        try {
            reader.read(6);
            fail();
        } catch (EOFException e) {
            assertTrue(true);
        }
        assertThat(reader.getPreface(), is(MESSAGE.getBytes()));

    }

}
