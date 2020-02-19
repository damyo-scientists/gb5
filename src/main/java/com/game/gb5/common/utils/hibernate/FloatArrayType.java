package com.game.gb5.common.utils.hibernate;

import com.vladmihalcea.hibernate.type.array.internal.ArraySqlTypeDescriptor;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.usertype.DynamicParameterizedType;

import java.util.Properties;

public class FloatArrayType extends AbstractSingleColumnStandardBasicType<float[]>
		implements DynamicParameterizedType {
	
	public FloatArrayType() {
		super(ArraySqlTypeDescriptor.INSTANCE, FloatArrayTypeDescriptor.INSTANCE);
	}
	
	public String getName() {
		return "float-array";
	}
	
	@Override
	protected boolean registerUnderJavaType() {
		return true;
	}
	
	@Override
	public void setParameterValues(Properties parameters) {
		((FloatArrayTypeDescriptor)
				getJavaTypeDescriptor())
				.setParameterValues(parameters);
	}
}
