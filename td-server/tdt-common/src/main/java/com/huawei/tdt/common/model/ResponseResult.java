package com.huawei.tdt.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.huawei.tdt.common.authorization.constants.ResponseStatusEnum;

public class ResponseResult<T> implements Serializable {

	private static final long serialVersionUID = -7769210729209279489L;

	private ResponseStatusEnum status;

	private String code;

	private List<String> params = new ArrayList<String>();

	private T data;

	public ResponseStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ResponseStatusEnum status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<String> getParams() {
		return params;
	}

	public void addParams(List<String> params) {
		if (!StringUtils.isEmpty(params)) {
			this.params.addAll(params);
		}
	}

	public ResponseResult<T> addParam(String param) {
		if (null != param) {
			this.params.add(param);
		}

		return this;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
