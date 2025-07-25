package de.gupta.resolution.id.implementation.database.client;


import de.gupta.resolution.id.api.IdentifierConverter;
import de.gupta.resolution.id.implementation.database.configuration.DatabaseIDResolutionProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.Optional;
import java.util.UUID;

final class IdToUsernameConverter implements IdentifierConverter<UUID, String>
{
	private final JdbcClient jdbcClient;
	private final String query;

	@Override
	public Optional<String> convert(UUID uuid)
	{
		return jdbcClient.sql(query)
						 .param("id", uuid)
				.query(String.class)
				.optional();
	}

	IdToUsernameConverter(final JdbcClient jdbcClient, final DatabaseIDResolutionProperties properties)
	{
		this.jdbcClient = jdbcClient;
		this.query = String.format(
				"SELECT %s FROM %s WHERE %s = :id",
				properties.getUsernameColumn(), properties.getTableName(), properties.getIdColumn());
	}
}