package com.leafriend.kspay.receiver;

import java.io.EOFException;
import java.io.IOException;

public class BankTransferMessage implements Message {

    /**
     * ~0 : 승인, ~1 : 취소
     */
    private String rApprovalType;

    /**
     * 거래번호
     */
    private String rACTransactionNo;

    /**
     * 오류구분 O:승인 X:거절
     */
    private String rACStatus;

    /**
     * 거래 개시 일자(YYYYMMDD)
     */
    private String rACTradeDate;

    /**
     * 거래 개시 시간(HHMMSS)
     */
    private String rACTradeTime;

    /**
     * 계좌이체 구분 - 1:Dacom, 2:Pop Banking, 3:실시간계좌이체 4: 승인형계좌이체
     */
    private String rACAcctSele;

    /**
     * 선/후불제구분 - 1:선불, 2:후불
     */
    private String rACFeeSele;

    /**
     * 인자명(통장인쇄메세지-상점명)
     */
    private String rACInjaName;

    /**
     * 입금모계좌코드
     */
    private String rACPareBankCode;

    /**
     * 입금모계좌번호
     */
    private String rACPareAcctNo;

    /**
     * 출금모계좌코드
     */
    private String rACCustBankCode;

    /**
     * 출금모계좌번호
     */
    private String rACCustAcctNo;

    /**
     * 금액 (결제대상금액)
     */
    private String rACAmount;

    /**
     * 은행거래번호
     */
    private String rACBankTransactionNo;

    /**
     * 입금자명
     */
    private String rACIpgumNm;

    /**
     * 계좌이체 수수료
     */
    private String rACBankFee;

    /**
     * 총결제금액(결제대상금액+ 수수료
     */
    private String rACBankAmount;

    /**
     * 오류코드
     */
    private String rACBankRespCode;

    /**
     * 오류 message 1
     */
    private String rACMessage1;

    /**
     * 오류 message 2
     */
    private String rACMessage2;

    /**
     * 예비
     */
    private String rACFiller;

    public static BankTransferMessage parse(Header header, MessageReader reader) throws EOFException, IOException {
        BankTransferMessage instance = new BankTransferMessage();
        instance.rApprovalType = reader.read(4);
        instance.rACTransactionNo = reader.read(12);
        instance.rACStatus = reader.read(1);
        instance.rACTradeDate = reader.read(8);
        instance.rACTradeTime = reader.read(6);
        instance.rACAcctSele = reader.read(1);
        instance.rACFeeSele = reader.read(1);
        instance.rACInjaName = reader.read(16);
        instance.rACPareBankCode = reader.read(6);
        instance.rACPareAcctNo = reader.read(15);
        instance.rACCustBankCode = reader.read(6);
        instance.rACCustAcctNo = reader.read(15);
        instance.rACAmount = reader.read(13);
        instance.rACBankTransactionNo = reader.read(30);
        instance.rACIpgumNm = reader.read(20);
        instance.rACBankFee = reader.read(13);
        instance.rACBankAmount = reader.read(13);
        instance.rACBankRespCode = reader.read(4);
        instance.rACMessage1 = reader.read(16);
        instance.rACMessage2 = reader.read(16);
        instance.rACFiller = reader.read(header.getLength() - reader.getPrefaceBytes().length);
        return instance;
    }

}
