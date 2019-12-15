package com.game.gb5.utils.hibernate;

import com.vladmihalcea.hibernate.type.array.internal.AbstractArrayTypeDescriptor;

public class FloatArrayTypeDescriptor extends AbstractArrayTypeDescriptor<float[]> {
	
	public static final FloatArrayTypeDescriptor INSTANCE =
			new FloatArrayTypeDescriptor();
	
	public FloatArrayTypeDescriptor() {
		super(float[].class);
	}
	
	@Override
	protected String getSqlArrayType() {
		return "float";
	}
}
