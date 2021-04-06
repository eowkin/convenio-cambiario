package com.bancoexterior.tesoreria.ve.interfase;

import com.bancoexterior.tesoreria.ve.model.WSRequest;
import com.bancoexterior.tesoreria.ve.model.WSResponse;

public interface  IWSService {
	WSResponse post(WSRequest request) ;
}
