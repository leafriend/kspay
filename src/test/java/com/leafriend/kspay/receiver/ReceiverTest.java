package com.leafriend.kspay.receiver;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ReceiverTest {

    private static final byte[] MESSAGE = "VR620212000001KSNETVR  2000001011020030011000012005051918032000000000                                             01817011660    01202000000000079670000000000000000000홍길동                  00000000079670000000000000000000000000010181764188990  20050519180220      2999199999                                      "
            .getBytes(MessageReader.DEFAULT_CHARSET);;

    private InputStream input;

    private ByteArrayOutputStream output;

    private MessageHandler handler;

    private Message message;

    private Receiver receiver;

    @Before
    public void setUp() throws Exception {
        input = new ByteArrayInputStream(MESSAGE);
        output = new ByteArrayOutputStream();
        handler = new MessageHandler() {

            @Override
            public boolean isHandleable(Message message) {
                return true;
            }

            @Override
            public void handle(Message message) {
                ReceiverTest.this.message = message;
            }

        };
        receiver = new Receiver(input, output, handler);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRun() {
        receiver.run();
        assertThat(message, is(instanceOf(OldVirtualAccountMessage.class)));
        assertThat(output.toByteArray()[0], is(Receiver.ACK));
    }

    @Test
    public void testParseAndHandle() throws EOFException, IOException {
        receiver.parseAndHandle();
        assertThat(message, is(instanceOf(OldVirtualAccountMessage.class)));
        assertThat(output.toByteArray().length, is(0));
    }

    @Ignore
    @Test
    public void testRespond() {
        fail("Not yet implemented");
    }

}
