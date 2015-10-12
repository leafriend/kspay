package com.leafriend.kspay.receiver;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.leafriend.kspay.receiver.Header.EncryptionType;
import com.leafriend.kspay.receiver.Header.KeyInType;
import com.leafriend.kspay.receiver.Header.LineType;

public class HeaderTest {

    @Test
    public void test() throws EOFException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Header header = generateHeader(
                "065920311000200505131040032999199999060613006494424                                   홍길동                                                         test@test.kr                                      1event000                                          K1            1   0000000000200505131040030500    ");
        assertThat(header.getLength(), is(659));
        assertThat(header.getEncryptionType(), is(EncryptionType.SEED));
        assertThat(header.getVersion(), is("0311"));
        assertThat(header.getVersionType(), is("00"));
        assertFalse(header.isResend());
        assertThat(sdf.format(header.getRequestDate()), is("2005-05-13 10:40:03"));
        assertThat(header.getStoreId(), is("2999199999"));
        assertThat(header.getOrderNo(), is("060613006494424"));
        assertThat(header.getBuyerName(), is("홍길동"));
        assertThat(header.getBuyerId(), is(""));
        assertThat(header.getBuyerEmail(), is("test@test.kr"));
        assertFalse(header.isPhysicalProduct());
        assertTrue(header.isDigitalProduct());
        assertThat(header.getProductName(), is("event000"));
        assertThat(header.getKeyInType(), is(KeyInType.KEYIN));
        assertThat(header.getLineType(), is(LineType.INTERNET));
        assertThat(header.getBuyerMobile(), is(""));
        assertThat(header.getApprovedCount(), is(1));
        assertThat(header.getRemark(), is("0000000000200505131040030500"));
    }

    private Header generateHeader(String message) throws EOFException, IOException {
        byte[] buf = message.getBytes(MessageReader.DEFAULT_CHARSET);
        InputStream input = new ByteArrayInputStream(buf);
        MessageReader reader = new MessageReader(input, MessageReader.DEFAULT_CHARSET);
        Header header = Header.parse(reader);
        return header;
    }

}
