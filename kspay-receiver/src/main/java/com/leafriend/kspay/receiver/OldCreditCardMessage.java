package com.leafriend.kspay.receiver;

import java.io.EOFException;
import java.io.IOException;

public class OldCreditCardMessage extends AbstractMessage {

    /**
     * ~B:승인요청, ~D:취소응답
     */
    private String _busi_sele;

    /**
     * 오류코드 O, X
     */
    private String _err_cd;

    /**
     * 거래일시 (YYYYMMDDHHMM)
     */
    private String _trade_date;

    /**
     * 발급기관코드
     */
    private String _make_comp_gove_code;

    /**
     * 매입 기관 코드
     */
    private String _purc_gove_code;

    /**
     * 승인번호 - 오류시 오류코드
     */
    private String _appr_numb;

    /**
     * 거래번호
     */
    private String _pg_deal_numb;

    /**
     * 주문번호
     */
    private String _regi_id;

    /**
     * 응답 message1
     */
    private String _resp_mesg_1;

    /**
     * 응답 message2
     */
    private String _resp_mesg_2;

    public static OldCreditCardMessage parse(MessageReader reader) throws EOFException, IOException {
        OldCreditCardMessage instance = new OldCreditCardMessage();
        instance._busi_sele = reader.getPreface(); // TODO 이미 읽은 헤더 처리를 이렇게 하는 게 맞는지?
        instance._err_cd = reader.read(1);
        instance._trade_date = reader.read(12);
        instance._make_comp_gove_code = reader.read(2);
        instance._purc_gove_code = reader.read(2);
        instance._appr_numb = reader.read(8);
        instance._pg_deal_numb = reader.read(12);
        instance._regi_id = reader.read(30);
        instance._resp_mesg_1 = reader.read(16);
        instance._resp_mesg_2 = reader.read(16);
        instance.setBytes(reader.getPrefaceBytes());
        return instance;
    }

}
