package de.gupta.resolution.id.implementation.database.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "database.id.resolution")
public final class DatabaseIDResolutionProperties
{
	private String tableName = "user_persistence_model";
	private String idColumn = "id";
	private String usernameColumn = "username";

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getIdColumn()
	{
		return idColumn;
	}

	public void setIdColumn(String idColumn)
	{
		this.idColumn = idColumn;
	}

	public String getUsernameColumn()
	{
		return usernameColumn;
	}

	public void setUsernameColumn(String usernameColumn)
	{
		this.usernameColumn = usernameColumn;
	}
}