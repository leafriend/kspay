package com.leafriend.kspay.receiver;

import java.io.EOFException;
import java.io.IOException;

public class PointMessage implements Message {

    /**
     * 0~: 적립, 1~: 사용 / ~0: 승인, ~1: 취소
     */
    private String rApprovalType;

    /**
     * 거래번호
     */
    private String rPTransactionNo;

    /**
     * 상태 O : 승인 , X : 거절
     */
    private String rPStatus;

    /**
     * 거래일자
     */
    private String rPTradeDate;

    /**
     * 거래시간
     */
    private String rPTradeTime;

    /**
     * 발급사코드
     */
    private String rPIssCode;

    /**
     * 승인번호 or 거절시 오류코드
     */
    private String rPAuthNo;

    /**
     * 메시지1
     */
    private String rPMessage1;

    /**
     * 메시지2
     */
    private String rPMessage2;

    /**
     * 거래포인트
     */
    private String rPPoint1;

    /**
     * 가용포인트
     */
    private String rPPoint2;

    /**
     * 누적포인트
     */
    private String rPPoint3;

    /**
     * 가맹점포인트
     */
    private String rPPoint4;

    /**
     * 포인트가맹점번호
     */
    private String rPMerchantNo;

    /**
     * 전표출력1
     */
    private String rPNotice1;

    /**
     * 전표출력2
     */
    private String rPNotice2;

    /**
     * 전표출력3
     */
    private String rPNotice3;

    /**
     * 전표출력4
     */
    private String rPNotice4;

    /**
     * 예비
     */
    private String rPFiller;

    public static PointMessage parse(Header header, MessageReader reader) throws EOFException, IOException {
        PointMessage instance = new PointMessage();
        instance.rApprovalType = new String(reader.read(4));
        instance.rPTransactionNo = new String(reader.read(12));
        instance.rPStatus = new String(reader.read(1));
        instance.rPTradeDate = new String(reader.read(8));
        instance.rPTradeTime = new String(reader.read(6));
        instance.rPIssCode = new String(reader.read(6));
        instance.rPAuthNo = new String(reader.read(12));
        instance.rPMessage1 = new String(reader.read(16));
        instance.rPMessage2 = new String(reader.read(16));
        instance.rPPoint1 = new String(reader.read(9));
        instance.rPPoint2 = new String(reader.read(9));
        instance.rPPoint3 = new String(reader.read(9));
        instance.rPPoint4 = new String(reader.read(9));
        instance.rPMerchantNo = new String(reader.read(15));
        instance.rPNotice1 = new String(reader.read(40));
        instance.rPNotice2 = new String(reader.read(40));
        instance.rPNotice3 = new String(reader.read(40));
        instance.rPNotice4 = new String(reader.read(40));
        instance.rPFiller = new String(reader.read(header.getLength() - reader.getPreface().length));
        return instance;
    }

}
