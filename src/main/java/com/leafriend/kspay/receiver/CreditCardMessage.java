package com.leafriend.kspay.receiver;

import java.io.EOFException;
import java.io.IOException;

public class CreditCardMessage extends AbstractMessage {
    /**
     *
     */
    private String _rApprovalType;

    /**
     * 거래번호
     */
    private String _rTransactionNo;

    /**
     * 상태 O : 승인, X : 거절
     */
    private String _rStatus;

    /**
     * 거래일자
     */
    private String _rTradeDate;

    /**
     * 거래시간
     */
    private String _rTradeTime;

    /**
     * 발급사코드
     */
    private String _rIssCode;

    /**
     * 매입사코드
     */
    private String _rAquCode;

    /**
     * 승인번호 or 거절시 오류코드
     */
    private String _rAuthNo;

    /**
     * 메시지1
     */
    private String _rMessage1;

    /**
     * 메시지2
     */
    private String _rMessage2;

    /**
     * 카드번호
     */
    private String _rCardNo;

    /**
     * 유효기간
     */
    private String _rExpDate;

    /**
     * 할부
     */
    private String _rInstallment;

    /**
     * 금액
     */
    private String _rAmount;

    /**
     * 가맹점번호
     */
    private String _rMerchantNo;

    /**
     * 전송구분
     */
    private String _rAuthSendType;

    /**
     * 전송구분(0 : 거절, 1 : 승인, 2: 원카드)
     */
    private String _rApprovalSendType;

    /**
     * Point1
     */
    private String _rPoint1;

    /**
     * Point2
     */
    private String _rPoint2;

    /**
     * Point3
     */
    private String _rPoint3;

    /**
     * Point4
     */
    private String _rPoint4;

    /**
     * Van거래번호
     */
    private String _rVanTransactionNo;

    /**
     * 예비
     */
    private String _rFiller;

    /**
     * ISP : ISP거래, MP1, MP2 : MPI거래, SPACE : 일반거래
     */
    private String _rAuthType;

    /**
     * K : KSNET, R : Remote, C : 제3기관, SPACE : 일반거래
     */
    private String _rMPIPositionType;

    /**
     * Y : 재사용, N : 재사용아님
     */
    private String _rMPIReUseType;

    /**
     * 예비
     */
    private String _rFiller_1;

    public static CreditCardMessage parse(Header header, MessageReader reader) throws EOFException, IOException {
        CreditCardMessage instance = new CreditCardMessage();
        instance._rApprovalType = reader.read(4);
        instance._rTransactionNo = reader.read(12);
        instance._rStatus = reader.read(1);
        instance._rTradeDate = reader.read(8);
        instance._rTradeTime = reader.read(6);
        instance._rIssCode = reader.read(6);
        instance._rAquCode = reader.read(6);
        instance._rAuthNo = reader.read(12);
        instance._rMessage1 = reader.read(16);
        instance._rMessage2 = reader.read(16);
        instance._rCardNo = reader.read(16);
        instance._rExpDate = reader.read(4);
        instance._rInstallment = reader.read(2);
        instance._rAmount = reader.read(9);
        instance._rMerchantNo = reader.read(15);
        instance._rAuthSendType = reader.read(1);
        instance._rApprovalSendType = reader.read(1);
        instance._rPoint1 = reader.read(12);
        instance._rPoint2 = reader.read(12);
        instance._rPoint3 = reader.read(12);
        instance._rPoint4 = reader.read(12);
        instance._rVanTransactionNo = reader.read(12);
        instance._rFiller = reader.read(82);
        instance._rAuthType = reader.read(1);
        instance._rMPIPositionType = reader.read(1);
        instance._rMPIReUseType = reader.read(1);
        instance._rFiller_1 = reader.read(header.getLength() - reader.getPrefaceBytes().length);
        instance.setBytes(reader.getPrefaceBytes());
        return instance;
    }

}
