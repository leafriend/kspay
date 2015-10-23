package com.leafriend.kspay.sendingtester;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.leafriend.kspay.receiver.Daemon;

public class SenderTest {

    // TODO 중복하여 정의하는 상수를 하나로 합치기
    static final byte ACK = 0x06;

    static final byte NAK = 0x15;

    private static final int PORT = 29993;

    private Daemon daemon;

    @Before
    public void setUp() {
        daemon = new Daemon(PORT);
        new Thread(daemon).start();
    }

    @After
    public void tearDown() {
        daemon.stop();
    }

    @Test
    public void testSend() throws IOException {

        // Given
        String transactionNo = "620212000001";
        String bankCode = "11";
        String messageNo = "100001";
        String dealType = "20";
        String bankCode2 = "20";
        int amount2 = 7967;
        String name = "홍길동        ";
        String virtualAccountNo = "10181764188990  ";
        String dealDate = "20050519";
        String dealTime = "180220";
        String storeId = "2999199999                                      ";

        String message;
        message = "VR" // 업무구분: VR
                + transactionNo // 거래번호
                + "KSNETVR  " // 식별코드
                + "20000010" // 업체코드
                + bankCode // 모은행코드
                + "0200" // 메세지코드
                + "300" // 업무구분
                + "1" // 송신횟수
                + messageNo // 전문번호
                + "20050519" // 전송일자
                + "180320" // 전송시간
                + "0000" // 응답코드
                + "0000" // 은행응답코드
                + "        " // 조회일자
                + "      " // 조회번호
                + "                               " // 예비
                + "01817011660    " // 계좌번호
                + "01" // 조립건수
                + dealType // 거래구분
                + bankCode2 // 은행코드
                + String.format("%13d", amount2) // 금액
                + "0000000000000" // 잔액
                + "000000" // 입금지점코드
                + name // 입금인성명
                + "          " // 수표번호
                + "0000000007967" // 현금(현금+당좌수표)
                + "0000000000000" // 타행수표금액
                + "0000000000000" // 가계수표, 기타
                + virtualAccountNo // 가상계좌번호
                + dealDate // 거래일자
                + dealTime // 거래시간
                + "      " // 통장거래 일련번호
                + storeId;
        byte[] bytes = message.getBytes(Charset.forName("KSC5601"));

        String host = "localhost";

        // When
        byte response = Sender.send(host, PORT, bytes);

        // Then
        assertThat(response, is(ACK));

        daemon.stop();

    }

}
