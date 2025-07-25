package de.gupta.resolution.id.implementation.database.client;

import de.gupta.resolution.id.api.IdentifierConverter;
import de.gupta.resolution.id.implementation.database.configuration.DatabaseIDResolutionProperties;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.Optional;
import java.util.UUID;

final class UsernameToIdConverter implements IdentifierConverter<String, UUID>
{
	private final JdbcClient jdbcClient;
	private final String sql;

	@Override
	public Optional<UUID> convert(String username)
	{
		return jdbcClient
				.sql(sql)
				.param("username", username)
				.query(UUID.class)
				.optional();
	}

	UsernameToIdConverter(JdbcClient jdbcClient, DatabaseIDResolutionProperties properties)
	{
		this.jdbcClient = jdbcClient;
		this.sql = String.format(
				"SELECT %s FROM %s WHERE %s = :username",
				properties.getIdColumn(),
				properties.getTableName(),
				properties.getUsernameColumn()
		);
	}
}