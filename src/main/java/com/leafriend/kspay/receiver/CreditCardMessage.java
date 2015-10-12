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
        instance.rApprovalType = reader.read(4);
        instance.rTransactionNo = reader.read(12);
        instance.rStatus = reader.read(1);
        instance.rTradeDate = reader.read(8);
        instance.rTradeTime = reader.read(6);
        instance.rIssCode = reader.read(6);
        instance.rAquCode = reader.read(6);
        instance.rAuthNo = reader.read(12);
        instance.rMessage1 = reader.read(16);
        instance.rMessage2 = reader.read(16);
        instance.rCardNo = reader.read(16);
        instance.rExpDate = reader.read(4);
        instance.rInstallment = reader.read(2);
        instance.rAmount = reader.read(9);
        instance.rMerchantNo = reader.read(15);
        instance.rAuthSendType = reader.read(1);
        instance.rApprovalSendType = reader.read(1);
        instance.rPoint1 = reader.read(12);
        instance.rPoint2 = reader.read(12);
        instance.rPoint3 = reader.read(12);
        instance.rPoint4 = reader.read(12);
        instance.rVanTransactionNo = reader.read(12);
        instance.rFiller = reader.read(82);
        instance.rAuthType = reader.read(1);
        instance.rMPIPositionType = reader.read(1);
        instance.rMPIReUseType = reader.read(1);
        instance.rFiller_1 = reader.read(header.getLength() - reader.getPrefaceBytes().length);
        return instance;
    }

}
