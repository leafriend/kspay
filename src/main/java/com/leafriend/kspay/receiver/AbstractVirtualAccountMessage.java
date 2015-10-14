package com.leafriend.kspay.receiver;

public abstract class AbstractVirtualAccountMessage extends AbstractMessage {

    /**
     * 거래번호
     */
    protected String _rVTransactionNo;

    /**
     * 식별(거래)코드
     */
    protected String _deal_code;

    /**
     * 업체코드(대행업체인경우 SPACE)
     */
    protected String _comp_code;

    /**
     * 모계좌은행코드 2 -> 6 hyun
     */
    protected String _acco_code;

    /**
     * 메시지코드
     */
    protected String _mess_code;

    /**
     * 업무구분
     */
    protected String _job_diff;

    /**
     * 송신횟수
     */
    protected String _tran_numb;

    /**
     * 전문번호 6 -> 10 hyun
     */
    protected String _seq_no;

    /**
     * 전송일자 - KSNET에서 입력
     */
    protected String _tran_date;

    /**
     * 전송시간 - KSNET에서 입력
     */
    protected String _tran_time;

    /**
     * 응답코드 - KSNET 공통에러코드
     */
    protected String _stan_resp_code;

    /**
     * 은행응답코드
     */
    protected String _bank_resp_code;

    /**
     * 조회일자
     */
    protected String _inqu_date;

    /**
     * 조회번호
     */
    protected String _inqu_no;

    /**
     * 예비 30 -> 27 hyun FIXME 코드상에는 31바이트였는데 주석은 30바이트?
     */
    protected String _filler_1;

    /**
     * 계좌번호 - 모계좌번호
     */
    protected String _acco_numb;

    /**
     * 조립건수
     */
    protected String _comp_cnt;

    /**
     * 거래구분 - 20 : 입금, 51 : 입금취소
     */
    protected String _deal_sele;

    /**
     * 은행코드 2 -> 6 hyun
     */
    protected String _in_bank_cd;

    /**
     * 금액
     */
    protected String _total_amt;

    /**
     * 잔액, 대행업체인 경우 ALL '0'
     */
    protected String _bala_mone;

    /**
     * 입금점지로코드
     */
    protected String _giro;

    /**
     * 입금인 성명
     */
    protected String _rece_nm;

    /**
     * 수표번호
     */
    protected String _chec_no;

    /**
     * 현금(현금_당좌수표)
     */
    protected String _cash;

    /**
     * 타행수표금액
     */
    protected String _out_bank_chec;

    /**
     * 가계수표,기타
     */
    protected String _etc_chec;

    /**
     * 가상계좌번호(CMS NO)
     */
    protected String _cms_no;

    /**
     * 거래일자
     */
    protected String _deal_star_date;

    /**
     * 거래시간
     */
    protected String _deal_star_time;

    /**
     * 통장거래일련번호
     */
    protected String _acco_seri_no;

    /**
     * 예비
     */
    protected String _filler_2;

}
