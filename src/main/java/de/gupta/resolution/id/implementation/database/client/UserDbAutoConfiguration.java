package de.gupta.resolution.id.implementation.database.client;

import de.gupta.resolution.id.api.IdentifierConverter;
import de.gupta.resolution.id.implementation.database.configuration.DatabaseIDResolutionProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

@AutoConfiguration
@EnableConfigurationProperties(DatabaseIDResolutionProperties.class)
class UserDbAutoConfiguration
{
	@Bean
	public IdentifierConverter<String, UUID> usernameToIdConverter(final JdbcTemplate jdbc,
																   final DatabaseIDResolutionProperties properties)
	{
		return new UsernameToIdConverter(jdbc, properties);
	}

	@Bean
	public IdentifierConverter<UUID, String> idToUsernameConverter(final JdbcTemplate jdbc,
																   final DatabaseIDResolutionProperties props)
	{
		return new IdToUsernameConverter(jdbc, props);
	}
}