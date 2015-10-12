package com.leafriend.kspay.receiver;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;

public class MessageReaderTest {

    public static final String MESSAGE = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

    private ByteArrayInputStream input;

    private MessageReader reader;

    @Before
    public void setUp() {
        input = new ByteArrayInputStream(MESSAGE.getBytes());
        reader = new MessageReader(input, MessageReader.DEFAULT_CHARSET);
    }

    @Test
    public void test() throws IOException {

        assertThat(reader.getPreface(), is(""));

        assertThat(reader.read(6), is("Lorem "));
        assertThat(reader.getPreface(), is("Lorem "));

        assertThat(reader.read(6), is("ipsum "));
        assertThat(reader.getPreface(), is("Lorem ipsum "));

        assertThat(reader.read(39), is("dolor sit amet, consectetur adipiscing "));
        assertThat(reader.getPreface(), is("Lorem ipsum dolor sit amet, consectetur adipiscing "));

        try {
            reader.read(6);
            fail();
        } catch (EOFException e) {
            assertTrue(true);
        }
        assertThat(reader.getPreface(), is(MESSAGE));

    }

    @Test
    public void t() {
        Charset charset = Charset.forName("UNKNOWN");
        new String(new byte[0], charset);
    }

}
