package com.leafriend.kspay.receiver;

import java.io.EOFException;
import java.io.IOException;

public class OldVirtualAccountMessage implements Message {

    /**
     * 업무구분 - VR
     */
    private String busi_sele;

    /**
     * 거래번호
     */
    private String rVTransactionNo;

    /**
     * 식별(거래)코드
     */
    private String deal_code;

    /**
     * 업체코드(대행업체인경우 SPACE)
     */
    private String comp_code;

    /**
     * 모계좌은행
     */
    private String acco_code;

    /**
     * 메시지코드
     */
    private String mess_code;

    /**
     * 업무구분
     */
    private String job_diff;

    /**
     * 송신횟수
     */
    private String tran_numb;

    /**
     * 전문번호
     */
    private String seq_no;

    /**
     * 전송일자 - KSNET에서 입력
     */
    private String tran_date;

    /**
     * 전송시간 - KSNET에서 입력
     */
    private String tran_time;

    /**
     * 응답코드 - KSNET 공통에러코드
     */
    private String stan_resp_code;

    /**
     * 은행응답코드
     */
    private String bank_resp_code;

    /**
     * 조회일자
     */
    private String inqu_date;

    /**
     * 조회번호
     */
    private String inqu_no;

    /**
     * 예비
     */
    private String filler_1;

    /**
     * 계좌번호 - 모계좌번호
     */
    private String acco_numb;

    /**
     * 조립건수
     */
    private String comp_cnt;

    /**
     * 거래구분
     */
    private String deal_sele;

    /**
     * 은행코드
     */
    private String in_bank_cd;

    /**
     * 금액
     */
    private String total_amt;

    /**
     * 잔액, 대행업체인 경우 ALL '0'
     */
    private String bala_mone;

    /**
     * 입금점지로코드
     */
    private String giro;

    /**
     * 입금인 성명
     */
    private String rece_nm;

    /**
     * 수표번호
     */
    private String chec_no;

    /**
     * 현금(현금_당좌수표)
     */
    private String cash;

    /**
     * 타행수표금액
     */
    private String out_bank_chec;

    /**
     * 가계수표,기타
     */
    private String etc_chec;

    /**
     * 가상계좌번호(CMS NO)
     */
    private String cms_no;

    /**
     * 거래일자
     */
    private String deal_star_date;

    /**
     * 거래시간
     */
    private String deal_star_time;

    /**
     * 통장거래일련번호
     */
    private String acco_seri_no;

    /**
     * 예비
     */
    private String filler_2;

    public static OldVirtualAccountMessage parse(MessageReader reader) throws EOFException, IOException {
        OldVirtualAccountMessage instance = new OldVirtualAccountMessage();
        instance.busi_sele = reader.read(2);
        instance.rVTransactionNo = reader.read(12);
        instance.deal_code = reader.read(9);
        instance.comp_code = reader.read(8);
        instance.acco_code = reader.read(2);
        instance.mess_code = reader.read(4);
        instance.job_diff = reader.read(3);
        instance.tran_numb = reader.read(1);
        instance.seq_no = reader.read(6);
        instance.tran_date = reader.read(8);
        instance.tran_time = reader.read(6);
        instance.stan_resp_code = reader.read(4);
        instance.bank_resp_code = reader.read(4);
        instance.inqu_date = reader.read(8);
        instance.inqu_no = reader.read(6);
        instance.filler_1 = reader.read(31);
        instance.acco_numb = reader.read(15);
        instance.comp_cnt = reader.read(2);
        instance.deal_sele = reader.read(2);
        instance.in_bank_cd = reader.read(2);
        instance.total_amt = reader.read(13);
        instance.bala_mone = reader.read(13);
        instance.giro = reader.read(6);
        instance.rece_nm = reader.read(14);
        instance.chec_no = reader.read(10);
        instance.cash = reader.read(13);
        instance.out_bank_chec = reader.read(13);
        instance.etc_chec = reader.read(13);
        instance.cms_no = reader.read(16);
        instance.deal_star_date = reader.read(8);
        instance.deal_star_time = reader.read(6);
        instance.acco_seri_no = reader.read(6);
        instance.filler_2 = reader.read(48);
        return instance;
    }

}
