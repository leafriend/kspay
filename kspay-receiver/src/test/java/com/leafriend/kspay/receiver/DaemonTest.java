package com.leafriend.kspay.receiver;

import static org.hamcrest.Matchers.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class DaemonTest {

    @Test
    public void testCallingShutdonwCallback() throws InterruptedException {

        // Given
        class Callback implements Runnable {

            boolean isCalled = false;

            @Override
            public void run() {
                isCalled = true;
            }

            @Override
            public String toString() {
                return "TestCallback";
            }

        }
        Daemon daemon = new Daemon(29992);
        Callback callback = new Callback();
        daemon.addShutdownCallback(callback);
        new Thread(daemon).start();

        // When
        Thread.sleep(200);
        daemon.stop();
        Thread.sleep(200);

        // Then
        assertThat("Callback is called", callback.isCalled, is(true));

    }

}
