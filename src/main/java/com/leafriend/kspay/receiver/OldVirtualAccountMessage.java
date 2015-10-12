package com.leafriend.kspay.receiver;

import java.io.EOFException;
import java.io.IOException;

public class OldVirtualAccountMessage extends AbstractVirtualAccountMessage {

    /**
     * 업무구분 - VR
     */
    protected String _busi_sele;

    public static OldVirtualAccountMessage parse(MessageReader reader) throws EOFException, IOException {
        OldVirtualAccountMessage instance = new OldVirtualAccountMessage();
        instance._busi_sele = reader.getPreface();
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
