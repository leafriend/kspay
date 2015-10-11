package com.leafriend.kspay.receiver;

import java.io.EOFException;
import java.io.IOException;

public class WorldPassMessage implements Message {

    /**
     * 승인구분 코드
     */
    private String rApprovalType;

    /**
     * 거래번호 - rWPTransactionNo.charAt(2) = '0' : 승인, '1' : 취소
     */
    private String rWPTransactionNo;

    /**
     * 오류구분- O:승인 X:거절
     */
    private String rWPStatus;

    /**
     * 거래 개시 일자(YYYYMMDD)
     */
    private String rWPTradeDate;

    /**
     * 거래 개시 시간(HHMMSS)
     */
    private String rWPTradeTime;

    /**
     * 발급기관코드
     */
    private String rWPIssCode;

    /**
     * 승인번호 - 오류시 오류코드
     */
    private String rWPAuthNo;

    /**
     * 잔액
     */
    private String rWPBalanceAmount;

    /**
     * 한도액
     */
    private String rWPLimitAmount;

    /**
     * 응답 message1
     */
    private String rWPMessage1;

    /**
     * 응답 message2
     */
    private String rWPMessage2;

    /**
     * 카드번호
     */
    private String rWPCardNo;

    /**
     * 금액(승인요청금액)
     */
    private String rWPAmount;

    /**
     * 매입사부여 가맹점번호
     */
    private String rWPMerchantNo;

    /**
     * 예비
     */
    private String rWPFiller;

    public static WorldPassMessage parse(Header header, MessageReader reader) throws EOFException, IOException {
        WorldPassMessage instance = new WorldPassMessage();
        instance.rApprovalType = new String(reader.read(4));
        instance.rWPTransactionNo = new String(reader.read(12));
        instance.rWPStatus = new String(reader.read(1));
        instance.rWPTradeDate = new String(reader.read(8));
        instance.rWPTradeTime = new String(reader.read(6));
        instance.rWPIssCode = new String(reader.read(6));
        instance.rWPAuthNo = new String(reader.read(12));
        instance.rWPBalanceAmount = new String(reader.read(9));
        instance.rWPLimitAmount = new String(reader.read(9));
        instance.rWPMessage1 = new String(reader.read(16));
        instance.rWPMessage2 = new String(reader.read(16));
        instance.rWPCardNo = new String(reader.read(16));
        instance.rWPAmount = new String(reader.read(9));
        instance.rWPMerchantNo = new String(reader.read(15));
        instance.rWPFiller = new String(reader.read(header.getLength() - reader.getPreface().length));
        return instance;
    }

}
