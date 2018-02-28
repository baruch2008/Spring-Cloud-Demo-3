package com.huawei.tdt.common.authorization.constants;

public enum ResponseStatusEnum {
	FAIL(0), SUCCESS(1);

	private int value;

	private ResponseStatusEnum(int status) {
		this.value = status;
	}

	public int getValue() {
		return this.value;
	}

	public String toString() {
		return String.valueOf(value);
	}
}
