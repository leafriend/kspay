package com.leafriend.kspay.receiver;

import java.io.EOFException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Header {

    public static enum EncryptionType {

        NONE,

        OPENSSL,

        SEED,

    }

    public static enum KeyInType {

        SWAP,

        KEYIN,

    }

    public static enum LineType {

        OFFLINE,

        INTERNET,

        MOBILE,

    }

    /**
     * 길이
     */
    private String _RecvLen;

    /**
     * 0: 암화안함, 1:openssl, 2: seed
     */
    private String _EncType;

    /**
     * 전문버전
     */
    private String _Version;

    /**
     * 구분
     */
    private String _VersionType;

    /**
     * 전송구분 : 0 : 처음, 2: 재전송
     */
    private String _Resend;

    /**
     * 요청일자 : yyyymmddhhmmss
     */
    private String _RequestDate;

    /**
     * 상점아이디
     */
    private String _StoreId;

    /**
     * 주문번호
     */
    private String _OrderNumber;

    /**
     * 주문자명
     */
    private String _UserName;

    /**
     * 주민번호 or 사업자번호
     */
    private String _IdNum;

    /**
     * email
     */
    private String _Email;

    /**
     * 제품구분 0 : 실물, 1 : 디지털
     */
    private String _GoodType;

    /**
     * 제품명
     */
    private String _GoodName;

    /**
     * KeyInType 여부 : 1 : Swap, 2: KeyIn
     */
    private String _KeyInType;

    /**
     * lineType 0 : offline, 1:internet, 2:Mobile
     */
    private String _LineType;

    /**
     * 휴대폰번호
     */
    private String _PhoneNo;

    /**
     * 승인갯수
     */
    private String _ApprovalCount;

    /**
     * 예비
     */
    private String _HeadFiller;

    public static Header parse(MessageReader reader) throws EOFException, IOException {
        Header instance = new Header();
        instance._RecvLen = reader.read(4);
        instance._EncType = reader.read(1);
        instance._Version = reader.read(4);
        instance._VersionType = reader.read(2);
        instance._Resend = reader.read(1);
        instance._RequestDate = reader.read(14);
        instance._StoreId = reader.read(10);
        instance._OrderNumber = reader.read(50);
        instance._UserName = reader.read(50);
        instance._IdNum = reader.read(13);
        instance._Email = reader.read(50);
        instance._GoodType = reader.read(1);
        instance._GoodName = reader.read(50);
        instance._KeyInType = reader.read(1);
        instance._LineType = reader.read(1);
        instance._PhoneNo = reader.read(12);
        instance._ApprovalCount = reader.read(1);
        instance._HeadFiller = reader.read(35);
        return instance;
    }

    public int getLength() {
        return Integer.parseInt(_RecvLen);
    }

    public EncryptionType getEncryptionType() {
        if ("0".equals(_EncType))
            return EncryptionType.NONE;
        if ("1".equals(_EncType))
            return EncryptionType.OPENSSL;
        if ("2".equals(_EncType))
            return EncryptionType.SEED;
        return null;
    }

    public String getVersion() {
        return _Version.trim();
    }

    public String getVersionType() {
        return _VersionType.trim();
    }

    public boolean isResend() {
        if ("0".equals(_Resend))
            return false;
        if ("2".equals(_Resend))
            return true;
        throw new RuntimeException("Unsupported resend type: " + _Resend);
    }

    public Date getRequestDate() {
        try {
            return new SimpleDateFormat("yyyyMMddHHmmss").parse(_RequestDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getStoreId() {
        return _StoreId.trim();
    }

    public String getOrderNo() {
        return _OrderNumber.trim();
    }

    public String getBuyerName() {
        return _UserName.trim();
    }

    public String getBuyerId() {
        return _IdNum.trim();
    }

    public String getBuyerEmail() {
        return _Email.trim();
    }

    public boolean isPhysicalProduct() {
        return "0".equals(_GoodType);
    }

    public boolean isDigitalProduct() {
        return "1".equals(_GoodType);
    }

    public String getProductName() {
        return _GoodName.trim();
    }

    public KeyInType getKeyInType() {
        if ("1".equals(this._KeyInType))
            return KeyInType.SWAP;
        // FIXME 원본 소스의 주석은 KeyIn이 "2"라고 되어 있으나 원본 테스트에서는 "K"로 전달
        if ("K".equals(this._KeyInType))
            return KeyInType.KEYIN;
        return null;
    }

    public LineType getLineType() {
        if ("0".equals(this._LineType))
            return LineType.OFFLINE;
        if ("1".equals(this._LineType))
            return LineType.INTERNET;
        if ("2".equals(this._LineType))
            return LineType.MOBILE;
        return null;
    }

    public String getBuyerMobile() {
        return _PhoneNo.trim();
    }

    public int getApprovedCount() {
        return Integer.parseInt(_ApprovalCount);
    }

    public String getRemark() {
        return _HeadFiller.trim();
    }

}
