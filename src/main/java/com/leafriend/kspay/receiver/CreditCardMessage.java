package com.leafriend.kspay.receiver;

import java.io.EOFException;
import java.io.IOException;

public class CreditCardMessage implements Message {
    /**
     *
     */
    private String rApprovalType;

    /**
     * 거래번호
     */
    private String rTransactionNo;

    /**
     * 상태 O : 승인, X : 거절
     */
    private String rStatus;

    /**
     * 거래일자
     */
    private String rTradeDate;

    /**
     * 거래시간
     */
    private String rTradeTime;

    /**
     * 발급사코드
     */
    private String rIssCode;

    /**
     * 매입사코드
     */
    private String rAquCode;

    /**
     * 승인번호 or 거절시 오류코드
     */
    private String rAuthNo;

    /**
     * 메시지1
     */
    private String rMessage1;

    /**
     * 메시지2
     */
    private String rMessage2;

    /**
     * 카드번호
     */
    private String rCardNo;

    /**
     * 유효기간
     */
    private String rExpDate;

    /**
     * 할부
     */
    private String rInstallment;

    /**
     * 금액
     */
    private String rAmount;

    /**
     * 가맹점번호
     */
    private String rMerchantNo;

    /**
     * 전송구분
     */
    private String rAuthSendType;

    /**
     * 전송구분(0 : 거절, 1 : 승인, 2: 원카드)
     */
    private String rApprovalSendType;

    /**
     * Point1
     */
    private String rPoint1;

    /**
     * Point2
     */
    private String rPoint2;

    /**
     * Point3
     */
    private String rPoint3;

    /**
     * Point4
     */
    private String rPoint4;

    /**
     * Van거래번호
     */
    private String rVanTransactionNo;

    /**
     * 예비
     */
    private String rFiller;

    /**
     * ISP : ISP거래, MP1, MP2 : MPI거래, SPACE : 일반거래
     */
    private String rAuthType;

    /**
     * K : KSNET, R : Remote, C : 제3기관, SPACE : 일반거래
     */
    private String rMPIPositionType;

    /**
     * Y : 재사용, N : 재사용아님
     */
    private String rMPIReUseType;

    /**
     * 예비
     */
    private String rFiller_1;

    public static CreditCardMessage parse(Header header, MessageReader reader) throws EOFException, IOException {
        CreditCardMessage instance = new CreditCardMessage();
        instance.rApprovalType = new String(reader.read(4));
        instance.rTransactionNo = new String(reader.read(12));
        instance.rStatus = new String(reader.read(1));
        instance.rTradeDate = new String(reader.read(8));
        instance.rTradeTime = new String(reader.read(6));
        instance.rIssCode = new String(reader.read(6));
        instance.rAquCode = new String(reader.read(6));
        instance.rAuthNo = new String(reader.read(12));
        instance.rMessage1 = new String(reader.read(16));
        instance.rMessage2 = new String(reader.read(16));
        instance.rCardNo = new String(reader.read(16));
        instance.rExpDate = new String(reader.read(4));
        instance.rInstallment = new String(reader.read(2));
        instance.rAmount = new String(reader.read(9));
        instance.rMerchantNo = new String(reader.read(15));
        instance.rAuthSendType = new String(reader.read(1));
        instance.rApprovalSendType = new String(reader.read(1));
        instance.rPoint1 = new String(reader.read(12));
        instance.rPoint2 = new String(reader.read(12));
        instance.rPoint3 = new String(reader.read(12));
        instance.rPoint4 = new String(reader.read(12));
        instance.rVanTransactionNo = new String(reader.read(12));
        instance.rFiller = new String(reader.read(82));
        instance.rAuthType = new String(reader.read(1));
        instance.rMPIPositionType = new String(reader.read(1));
        instance.rMPIReUseType = new String(reader.read(1));
        instance.rFiller_1 = new String(reader.read(header.getLength() - reader.getPreface().length));
        return instance;
    }

}
