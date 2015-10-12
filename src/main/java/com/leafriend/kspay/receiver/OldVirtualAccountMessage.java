package com.leafriend.kspay.receiver;

import java.io.EOFException;
import java.io.IOException;

public class OldVirtualAccountMessage implements Message {

    /**
     * 업무구분 - VR
     */
    private String _busi_sele;

    /**
     * 거래번호
     */
    private String _rVTransactionNo;

    /**
     * 식별(거래)코드
     */
    private String _deal_code;

    /**
     * 업체코드(대행업체인경우 SPACE)
     */
    private String _comp_code;

    /**
     * 모계좌은행
     */
    private String _acco_code;

    /**
     * 메시지코드
     */
    private String _mess_code;

    /**
     * 업무구분
     */
    private String _job_diff;

    /**
     * 송신횟수
     */
    private String _tran_numb;

    /**
     * 전문번호
     */
    private String _seq_no;

    /**
     * 전송일자 - KSNET에서 입력
     */
    private String _tran_date;

    /**
     * 전송시간 - KSNET에서 입력
     */
    private String _tran_time;

    /**
     * 응답코드 - KSNET 공통에러코드
     */
    private String _stan_resp_code;

    /**
     * 은행응답코드
     */
    private String _bank_resp_code;

    /**
     * 조회일자
     */
    private String _inqu_date;

    /**
     * 조회번호
     */
    private String _inqu_no;

    /**
     * 예비
     */
    private String _filler_1;

    /**
     * 계좌번호 - 모계좌번호
     */
    private String _acco_numb;

    /**
     * 조립건수
     */
    private String _comp_cnt;

    /**
     * 거래구분
     */
    private String _deal_sele;

    /**
     * 은행코드
     */
    private String _in_bank_cd;

    /**
     * 금액
     */
    private String _total_amt;

    /**
     * 잔액, 대행업체인 경우 ALL '0'
     */
    private String _bala_mone;

    /**
     * 입금점지로코드
     */
    private String _giro;

    /**
     * 입금인 성명
     */
    private String _rece_nm;

    /**
     * 수표번호
     */
    private String _chec_no;

    /**
     * 현금(현금_당좌수표)
     */
    private String _cash;

    /**
     * 타행수표금액
     */
    private String _out_bank_chec;

    /**
     * 가계수표,기타
     */
    private String _etc_chec;

    /**
     * 가상계좌번호(CMS NO)
     */
    private String _cms_no;

    /**
     * 거래일자
     */
    private String _deal_star_date;

    /**
     * 거래시간
     */
    private String _deal_star_time;

    /**
     * 통장거래일련번호
     */
    private String _acco_seri_no;

    /**
     * 예비
     */
    private String _filler_2;

    public static OldVirtualAccountMessage parse(MessageReader reader) throws EOFException, IOException {
        OldVirtualAccountMessage instance = new OldVirtualAccountMessage();
        instance._busi_sele = reader.read(2);
        instance._rVTransactionNo = reader.read(12);
        instance._deal_code = reader.read(9);
        instance._comp_code = reader.read(8);
        instance._acco_code = reader.read(2);
        instance._mess_code = reader.read(4);
        instance._job_diff = reader.read(3);
        instance._tran_numb = reader.read(1);
        instance._seq_no = reader.read(6);
        instance._tran_date = reader.read(8);
        instance._tran_time = reader.read(6);
        instance._stan_resp_code = reader.read(4);
        instance._bank_resp_code = reader.read(4);
        instance._inqu_date = reader.read(8);
        instance._inqu_no = reader.read(6);
        instance._filler_1 = reader.read(31);
        instance._acco_numb = reader.read(15);
        instance._comp_cnt = reader.read(2);
        instance._deal_sele = reader.read(2);
        instance._in_bank_cd = reader.read(2);
        instance._total_amt = reader.read(13);
        instance._bala_mone = reader.read(13);
        instance._giro = reader.read(6);
        instance._rece_nm = reader.read(14);
        instance._chec_no = reader.read(10);
        instance._cash = reader.read(13);
        instance._out_bank_chec = reader.read(13);
        instance._etc_chec = reader.read(13);
        instance._cms_no = reader.read(16);
        instance._deal_star_date = reader.read(8);
        instance._deal_star_time = reader.read(6);
        instance._acco_seri_no = reader.read(6);
        instance._filler_2 = reader.read(48);
        return instance;
    }

}
