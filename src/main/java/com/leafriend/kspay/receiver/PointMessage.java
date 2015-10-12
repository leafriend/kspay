package com.leafriend.kspay.receiver;

import java.io.EOFException;
import java.io.IOException;

public class PointMessage implements Message {

    /**
     * 0~: 적립, 1~: 사용 / ~0: 승인, ~1: 취소
     */
    private String _rApprovalType;

    /**
     * 거래번호
     */
    private String _rPTransactionNo;

    /**
     * 상태 O : 승인 , X : 거절
     */
    private String _rPStatus;

    /**
     * 거래일자
     */
    private String _rPTradeDate;

    /**
     * 거래시간
     */
    private String _rPTradeTime;

    /**
     * 발급사코드
     */
    private String _rPIssCode;

    /**
     * 승인번호 or 거절시 오류코드
     */
    private String _rPAuthNo;

    /**
     * 메시지1
     */
    private String _rPMessage1;

    /**
     * 메시지2
     */
    private String _rPMessage2;

    /**
     * 거래포인트
     */
    private String _rPPoint1;

    /**
     * 가용포인트
     */
    private String _rPPoint2;

    /**
     * 누적포인트
     */
    private String _rPPoint3;

    /**
     * 가맹점포인트
     */
    private String _rPPoint4;

    /**
     * 포인트가맹점번호
     */
    private String _rPMerchantNo;

    /**
     * 전표출력1
     */
    private String _rPNotice1;

    /**
     * 전표출력2
     */
    private String _rPNotice2;

    /**
     * 전표출력3
     */
    private String _rPNotice3;

    /**
     * 전표출력4
     */
    private String _rPNotice4;

    /**
     * 예비
     */
    private String _rPFiller;

    public static PointMessage parse(Header header, MessageReader reader) throws EOFException, IOException {
        PointMessage instance = new PointMessage();
        instance._rApprovalType = reader.read(4);
        instance._rPTransactionNo = reader.read(12);
        instance._rPStatus = reader.read(1);
        instance._rPTradeDate = reader.read(8);
        instance._rPTradeTime = reader.read(6);
        instance._rPIssCode = reader.read(6);
        instance._rPAuthNo = reader.read(12);
        instance._rPMessage1 = reader.read(16);
        instance._rPMessage2 = reader.read(16);
        instance._rPPoint1 = reader.read(9);
        instance._rPPoint2 = reader.read(9);
        instance._rPPoint3 = reader.read(9);
        instance._rPPoint4 = reader.read(9);
        instance._rPMerchantNo = reader.read(15);
        instance._rPNotice1 = reader.read(40);
        instance._rPNotice2 = reader.read(40);
        instance._rPNotice3 = reader.read(40);
        instance._rPNotice4 = reader.read(40);
        instance._rPFiller = reader.read(header.getLength() - reader.getPrefaceBytes().length);
        return instance;
    }

}
