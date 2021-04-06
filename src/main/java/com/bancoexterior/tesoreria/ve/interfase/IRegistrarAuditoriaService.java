package com.bancoexterior.tesoreria.ve.interfase;

import com.bancoexterior.tesoreria.ve.model.RegistrarAuditoriaRequest;

public interface IRegistrarAuditoriaService {
	
	void registrarAuditoria(RegistrarAuditoriaRequest auditoria,  String codigo, String mensaje, String errorAdicional);

}
