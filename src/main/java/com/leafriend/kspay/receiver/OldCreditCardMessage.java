package com.leafriend.kspay.receiver;

import java.io.EOFException;
import java.io.IOException;

public class OldCreditCardMessage implements Message {

    /**
     * ~B:승인요청, ~D:취소응답
     */
    private String busi_sele;

    /**
     * 오류코드 O, X
     */
    private String err_cd;

    /**
     * 거래일시 (YYYYMMDDHHMM)
     */
    private String trade_date;

    /**
     * 발급기관코드
     */
    private String make_comp_gove_code;

    /**
     * 매입 기관 코드
     */
    private String purc_gove_code;

    /**
     * 승인번호 - 오류시 오류코드
     */
    private String appr_numb;

    /**
     * 거래번호
     */
    private String pg_deal_numb;

    /**
     * 주문번호
     */
    private String regi_id;

    /**
     * 응답 message1
     */
    private String resp_mesg_1;

    /**
     * 응답 message2
     */
    private String resp_mesg_2;

    public static OldCreditCardMessage parse(MessageReader reader) throws EOFException, IOException {
        OldCreditCardMessage instance = new OldCreditCardMessage();
        instance.busi_sele = new String(reader.read(2));
        instance.err_cd = new String(reader.read(1));
        instance.trade_date = new String(reader.read(12));
        instance.make_comp_gove_code = new String(reader.read(2));
        instance.purc_gove_code = new String(reader.read(2));
        instance.appr_numb = new String(reader.read(8));
        instance.pg_deal_numb = new String(reader.read(12));
        instance.regi_id = new String(reader.read(30));
        instance.resp_mesg_1 = new String(reader.read(16));
        instance.resp_mesg_2 = new String(reader.read(16));
        return instance;
    }

}
