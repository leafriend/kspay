package com.leafriend.kspay.receiver;

import java.io.EOFException;
import java.io.IOException;

public class Header {
    /**
     * 길이
     */
    private String RecvLen;

    /**
     * 0: 암화안함, 1:openssl, 2: seed
     */
    private String EncType;

    /**
     * 전문버전
     */
    private String Version;

    /**
     * 구분
     */
    private String VersionType;

    /**
     * 전송구분 : 0 : 처음, 2: 재전송
     */
    private String Resend;

    /**
     * 요청일자 : yyyymmddhhmmss
     */
    private String RequestDate;

    /**
     * 상점아이디
     */
    private String StoreId;

    /**
     * 주문번호
     */
    private String OrderNumber;

    /**
     * 주문자명
     */
    private String UserName;

    /**
     * 주민번호 or 사업자번호
     */
    private String IdNum;

    /**
     * email
     */
    private String Email;

    /**
     * 제품구분 0 : 실물, 1 : 디지털
     */
    private String GoodType;

    /**
     * 제품명
     */
    private String GoodName;

    /**
     * KeyInType 여부 : 1 : Swap, 2: KeyIn
     */
    private String KeyInType;

    /**
     * lineType 0 : offline, 1:internet, 2:Mobile
     */
    private String LineType;

    /**
     * 휴대폰번호
     */
    private String PhoneNo;

    /**
     * 승인갯수
     */
    private String ApprovalCount;

    /**
     * 예비
     */
    private String HeadFiller;

    public static Header parse(MessageReader reader) throws EOFException, IOException {
        Header instance = new Header();
        instance.RecvLen = new String(reader.read(4));
        instance.EncType = new String(reader.read(1));
        instance.Version = new String(reader.read(4));
        instance.VersionType = new String(reader.read(2));
        instance.Resend = new String(reader.read(1));
        instance.RequestDate = new String(reader.read(14));
        instance.StoreId = new String(reader.read(10));
        instance.OrderNumber = new String(reader.read(50));
        instance.UserName = new String(reader.read(50));
        instance.IdNum = new String(reader.read(13));
        instance.Email = new String(reader.read(50));
        instance.GoodType = new String(reader.read(1));
        instance.GoodName = new String(reader.read(50));
        instance.KeyInType = new String(reader.read(1));
        instance.LineType = new String(reader.read(1));
        instance.PhoneNo = new String(reader.read(12));
        instance.ApprovalCount = new String(reader.read(1));
        instance.HeadFiller = new String(reader.read(35));
        return instance;
    }

    public int getLength() {
        // TODO Auto-generated method stub
        return 0;
    }

}
