package com.superwork.apcosplatform.service;


import com.superwork.apcosplatform.domain.PReportmessage;
import com.superwork.apcosplatform.domain.PSbcontrolOriginal;

import java.util.Map;

public interface AsyncTask {


    void sbControlRecord( PSbcontrolOriginal pSbcontrolOriginal);

    void msControlRecord(String msId,String userId,String type,String ex);

    void delInvalidApp();

    void delTemporaryHtml();

    void pushReportMsg( PReportmessage message);

    void  syncSbxx(Map map,String account) throws Exception;
}
