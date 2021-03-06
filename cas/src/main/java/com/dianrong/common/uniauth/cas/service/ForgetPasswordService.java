package com.dianrong.common.uniauth.cas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dianrong.common.uniauth.cas.service.support.annotation.TenancyIdentity;
import com.dianrong.common.uniauth.cas.service.support.annotation.TenancyIdentity.Type;
import com.dianrong.common.uniauth.common.bean.Info;
import com.dianrong.common.uniauth.common.bean.Response;
import com.dianrong.common.uniauth.common.bean.dto.UserDto;
import com.dianrong.common.uniauth.common.bean.request.UserParam;
import com.dianrong.common.uniauth.common.client.UniClientFacade;
import com.dianrong.common.uniauth.sharerw.facade.UARWFacade;

@Service
public class ForgetPasswordService extends BaseService{

	@Autowired
	private UARWFacade uarwFacade;
	
	@Autowired
	private UniClientFacade uniClientFacade;
	
	@TenancyIdentity(type=Type.CODE, index=1)
	public UserDto checkUser(String email, String tenancyCode) throws Exception {
		UserParam userParam = new UserParam();
		userParam.setEmail(email);
		userParam.setTenancyCode(tenancyCode);
		Response<UserDto> response = uniClientFacade.getUserResource().getSingleUser(userParam);
		List<Info> infoList = response.getInfo();

		checkInfoList(infoList);
		return response.getData();
	}
	
	@TenancyIdentity(index=1)
	public void resetPassword(String email, Long tenancyId, String password) throws Exception {
		UserParam userParam = new UserParam();
		userParam.setEmail(email);
		userParam.setPassword(password);
		userParam.setTenancyId(tenancyId);
		Response<Void> response = uarwFacade.getUserRWResource().resetPassword(userParam);
		List<Info> infoList = response.getInfo();

		checkInfoList(infoList);
	}
}
