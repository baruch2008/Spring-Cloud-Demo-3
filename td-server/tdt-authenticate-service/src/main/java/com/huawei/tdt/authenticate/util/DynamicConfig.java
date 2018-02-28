package com.huawei.tdt.authenticate.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
final class DynamicConfig {
	@Value("${pITtoolCI.password}")
	private String ldapAdminPassword;

	public String getLdapAdminPassword() {
		return ldapAdminPassword;
	}
}
