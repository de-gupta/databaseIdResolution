package de.gupta.resolution.id.implementation.database.client;

import de.gupta.resolution.id.api.IdentifierConverter;
import de.gupta.resolution.id.implementation.database.configuration.DatabaseIDResolutionProperties;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

final class UsernameToIdConverter implements IdentifierConverter<String, UUID>
{
	private final JdbcTemplate jdbc;
	private final String query;

	@Override
	public UUID convert(String username)
	{
		return jdbc.queryForObject(query, new Object[]{username}, UUID.class);
	}

	UsernameToIdConverter(JdbcTemplate jdbc, DatabaseIDResolutionProperties properties)
	{
		this.jdbc = jdbc;
		this.query = String.format("SELECT %s FROM %s WHERE %s = ?",
				properties.getIdColumn(), properties.getTableName(), properties.getUsernameColumn());
	}
}