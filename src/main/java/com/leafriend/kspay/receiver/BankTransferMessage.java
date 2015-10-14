package com.leafriend.kspay.receiver;

import java.io.EOFException;
import java.io.IOException;

import org.h2.command.dml.SelectOrderBy;

public class BankTransferMessage extends AbstractMessage {

    /**
     * ~0 : 승인, ~1 : 취소
     */
    private String _rApprovalType;

    /**
     * 거래번호
     */
    private String _rACTransactionNo;

    /**
     * 오류구분 O:승인 X:거절
     */
    private String _rACStatus;

    /**
     * 거래 개시 일자(YYYYMMDD)
     */
    private String _rACTradeDate;

    /**
     * 거래 개시 시간(HHMMSS)
     */
    private String _rACTradeTime;

    /**
     * 계좌이체 구분 - 1:Dacom, 2:Pop Banking, 3:실시간계좌이체 4: 승인형계좌이체
     */
    private String _rACAcctSele;

    /**
     * 선/후불제구분 - 1:선불, 2:후불
     */
    private String _rACFeeSele;

    /**
     * 인자명(통장인쇄메세지-상점명)
     */
    private String _rACInjaName;

    /**
     * 입금모계좌코드
     */
    private String _rACPareBankCode;

    /**
     * 입금모계좌번호
     */
    private String _rACPareAcctNo;

    /**
     * 출금모계좌코드
     */
    private String _rACCustBankCode;

    /**
     * 출금모계좌번호
     */
    private String _rACCustAcctNo;

    /**
     * 금액 (결제대상금액)
     */
    private String _rACAmount;

    /**
     * 은행거래번호
     */
    private String _rACBankTransactionNo;

    /**
     * 입금자명
     */
    private String _rACIpgumNm;

    /**
     * 계좌이체 수수료
     */
    private String _rACBankFee;

    /**
     * 총결제금액(결제대상금액+ 수수료
     */
    private String _rACBankAmount;

    /**
     * 오류코드
     */
    private String _rACBankRespCode;

    /**
     * 오류 message 1
     */
    private String _rACMessage1;

    /**
     * 오류 message 2
     */
    private String _rACMessage2;

    /**
     * 예비
     */
    private String _rACFiller;

    public static BankTransferMessage parse(Header header, MessageReader reader) throws EOFException, IOException {
        BankTransferMessage instance = new BankTransferMessage();
        instance._rApprovalType = reader.read(4);
        instance._rACTransactionNo = reader.read(12);
        instance._rACStatus = reader.read(1);
        instance._rACTradeDate = reader.read(8);
        instance._rACTradeTime = reader.read(6);
        instance._rACAcctSele = reader.read(1);
        instance._rACFeeSele = reader.read(1);
        instance._rACInjaName = reader.read(16);
        instance._rACPareBankCode = reader.read(6);
        instance._rACPareAcctNo = reader.read(15);
        instance._rACCustBankCode = reader.read(6);
        instance._rACCustAcctNo = reader.read(15);
        instance._rACAmount = reader.read(13);
        instance._rACBankTransactionNo = reader.read(30);
        instance._rACIpgumNm = reader.read(20);
        instance._rACBankFee = reader.read(13);
        instance._rACBankAmount = reader.read(13);
        instance._rACBankRespCode = reader.read(4);
        instance._rACMessage1 = reader.read(16);
        instance._rACMessage2 = reader.read(16);
        instance._rACFiller = reader.read(header.getLength() - reader.getPrefaceBytes().length);
        instance.setBytes(reader.getPrefaceBytes());
        return instance;
    }

}
