package com.leafriend.kspay.receiver;

import java.io.EOFException;
import java.io.IOException;

public class WorldPassMessage implements Message {

    /**
     * 승인구분 코드
     */
    private String _rApprovalType;

    /**
     * 거래번호 - rWPTransactionNo.charAt(2) = '0' : 승인, '1' : 취소
     */
    private String _rWPTransactionNo;

    /**
     * 오류구분- O:승인 X:거절
     */
    private String _rWPStatus;

    /**
     * 거래 개시 일자(YYYYMMDD)
     */
    private String _rWPTradeDate;

    /**
     * 거래 개시 시간(HHMMSS)
     */
    private String _rWPTradeTime;

    /**
     * 발급기관코드
     */
    private String _rWPIssCode;

    /**
     * 승인번호 - 오류시 오류코드
     */
    private String _rWPAuthNo;

    /**
     * 잔액
     */
    private String _rWPBalanceAmount;

    /**
     * 한도액
     */
    private String _rWPLimitAmount;

    /**
     * 응답 message1
     */
    private String _rWPMessage1;

    /**
     * 응답 message2
     */
    private String _rWPMessage2;

    /**
     * 카드번호
     */
    private String _rWPCardNo;

    /**
     * 금액(승인요청금액)
     */
    private String _rWPAmount;

    /**
     * 매입사부여 가맹점번호
     */
    private String _rWPMerchantNo;

    /**
     * 예비
     */
    private String _rWPFiller;

    public static WorldPassMessage parse(Header header, MessageReader reader) throws EOFException, IOException {
        WorldPassMessage instance = new WorldPassMessage();
        instance._rApprovalType = reader.read(4);
        instance._rWPTransactionNo = reader.read(12);
        instance._rWPStatus = reader.read(1);
        instance._rWPTradeDate = reader.read(8);
        instance._rWPTradeTime = reader.read(6);
        instance._rWPIssCode = reader.read(6);
        instance._rWPAuthNo = reader.read(12);
        instance._rWPBalanceAmount = reader.read(9);
        instance._rWPLimitAmount = reader.read(9);
        instance._rWPMessage1 = reader.read(16);
        instance._rWPMessage2 = reader.read(16);
        instance._rWPCardNo = reader.read(16);
        instance._rWPAmount = reader.read(9);
        instance._rWPMerchantNo = reader.read(15);
        instance._rWPFiller = reader.read(header.getLength() - reader.getPrefaceBytes().length);
        return instance;
    }

}
