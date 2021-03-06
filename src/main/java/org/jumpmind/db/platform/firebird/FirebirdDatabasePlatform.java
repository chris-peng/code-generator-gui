package org.jumpmind.db.platform.firebird;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import javax.sql.DataSource;

import org.jumpmind.db.platform.AbstractJdbcDatabasePlatform;
import org.jumpmind.db.platform.DatabaseNamesConstants;
import org.jumpmind.db.platform.PermissionResult;
import org.jumpmind.db.platform.PermissionResult.Status;
import org.jumpmind.db.platform.PermissionType;
import org.jumpmind.db.sql.SqlException;
import org.jumpmind.db.sql.SqlTemplateSettings;

/*
 * The platform implementation for the Firebird database.
 * It is assumed that the database is configured with sql dialect 3!
 */
public class FirebirdDatabasePlatform extends AbstractJdbcDatabasePlatform {

    /* The standard Firebird jdbc driver. */
    public static final String JDBC_DRIVER = "org.firebirdsql.jdbc.FBDriver";

    /* The subprotocol used by the standard Firebird driver. */
    public static final String JDBC_SUBPROTOCOL = "firebirdsql";

    /*
     * Creates a new Firebird platform instance.
     */
    public FirebirdDatabasePlatform(DataSource dataSource, SqlTemplateSettings settings) {
        super(dataSource, settings);
        supportsTruncate = false;
    }

    @Override
    protected FirebirdDdlBuilder createDdlBuilder() {
        return new FirebirdDdlBuilder();
    }

    @Override
    protected FirebirdDdlReader createDdlReader() {
        return new FirebirdDdlReader(this);
    }    
    
    @Override
    protected FirebirdJdbcSqlTemplate createSqlTemplate() {
        return new FirebirdJdbcSqlTemplate(dataSource, settings, null, getDatabaseInfo());   
    }

    public String getName() {
        return DatabaseNamesConstants.FIREBIRD;
    }
    
    public String getDefaultCatalog() {
        return null;
    }
    
    public String getDefaultSchema() {
        return null;
    }
    
    @Override
	public PermissionResult getCreateSymTriggerPermission() {
    	String delimiter = getDatabaseInfo().getDelimiterToken();
        delimiter = delimiter != null ? delimiter : "";
        
    	String triggerSql = "CREATE TRIGGER TEST_TRIGGER FOR " + delimiter + PERMISSION_TEST_TABLE_NAME + delimiter + " AFTER UPDATE AS BEGIN END";	

       	PermissionResult result = new PermissionResult(PermissionType.CREATE_TRIGGER, triggerSql);
		
		try {
			getSqlTemplate().update(triggerSql);
			result.setStatus(Status.PASS);
		} catch (SqlException e) {
			result.setException(e);
			result.setSolution("Grant CREATE TRIGGER permission or TRIGGER permission");
		}
		
		return result;
    }
}
