package com.game.gb5.common.spring;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

import java.io.Serializable;

public class Gb5NamingStrategyImpl extends SpringPhysicalNamingStrategy implements Serializable {
	public static final Gb5NamingStrategyImpl INSTANCE = new Gb5NamingStrategyImpl();

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
		String changeName = name.getText().replace("Model", "");
		return apply(new Identifier(changeName, true), context);
	}

	private boolean isUnderscoreRequired(char before, char current, char after) {
		return Character.isLowerCase(before) && Character.isUpperCase(current) && Character.isLowerCase(after);
	}

	protected Identifier apply(Identifier name, JdbcEnvironment jdbcEnvironment) {
		if (name == null) {
			return null;
		}
		StringBuilder builder = new StringBuilder(name.getText().replace('.', '_'));
		for (int i = 1; i < builder.length() - 1; i++) {
			if (isUnderscoreRequired(builder.charAt(i - 1), builder.charAt(i), builder.charAt(i + 1))) {
				builder.insert(i++, '_');
			}
		}
		return getIdentifier(builder.toString(), name.isQuoted(), jdbcEnvironment);
	}
}
