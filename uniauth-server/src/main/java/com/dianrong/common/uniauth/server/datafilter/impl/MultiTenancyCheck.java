package com.dianrong.common.uniauth.server.datafilter.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.dianrong.common.uniauth.server.service.TenancyService;

public abstract class MultiTenancyCheck extends  AbstractDataFilter {
	// 目前是基于多租户的数据检验
	@Autowired
	private TenancyService tenancyService;

	public TenancyService getTenancyService() {
		return tenancyService;
	}

	public void setTenancyService(TenancyService tenancyService) {
		this.tenancyService = tenancyService;
	}
	
	/**.
	 * 获取当前的租户id
	 * @return
	 */
	public Long getTenancyId(){
		return tenancyService.getTenancyIdWithCheck();
	}
}
