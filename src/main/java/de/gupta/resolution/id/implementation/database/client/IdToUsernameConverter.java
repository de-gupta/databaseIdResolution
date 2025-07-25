package de.gupta.resolution.id.implementation.database.client;


import de.gupta.resolution.id.api.IdentifierConverter;
import de.gupta.resolution.id.implementation.database.configuration.DatabaseIDResolutionProperties;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

final class IdToUsernameConverter implements IdentifierConverter<UUID, String>
{
	private final JdbcTemplate jdbc;
	private final String query;

	@Override
	public String convert(UUID uuid)
	{
		return jdbc.queryForObject(query, new Object[]{uuid}, String.class);
	}

	IdToUsernameConverter(final JdbcTemplate jdbc, final DatabaseIDResolutionProperties properties)
	{
		this.jdbc = jdbc;
		this.query = String.format("SELECT %s FROM %s WHERE %s = ?",
				properties.getUsernameColumn(), properties.getTableName(), properties.getIdColumn());
	}
}