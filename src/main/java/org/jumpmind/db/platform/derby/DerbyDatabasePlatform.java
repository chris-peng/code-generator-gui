package org.jumpmind.db.platform.derby;

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

import java.sql.Types;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.jumpmind.db.model.Column;
import org.jumpmind.db.platform.AbstractJdbcDatabasePlatform;
import org.jumpmind.db.platform.DatabaseNamesConstants;
import org.jumpmind.db.platform.PermissionResult;
import org.jumpmind.db.platform.PermissionResult.Status;
import org.jumpmind.db.platform.PermissionType;
import org.jumpmind.db.sql.SqlException;
import org.jumpmind.db.sql.SqlTemplateSettings;

/*
 * The platform implementation for Derby.
 */
public class DerbyDatabasePlatform extends AbstractJdbcDatabasePlatform {

    /* The derby jdbc driver for use as a client for a normal server. */
    public static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";

    /* The derby jdbc driver for use as an embedded database. */
    public static final String JDBC_DRIVER_EMBEDDED = "org.apache.derby.jdbc.EmbeddedDriver";

    /* The subprotocol used by the derby drivers. */
    public static final String JDBC_SUBPROTOCOL = "derby";

    /*
     * Creates a new Derby platform instance.
     */
    public DerbyDatabasePlatform(DataSource dataSource, SqlTemplateSettings settings) {
        super(dataSource, settings);        
    }

    @Override
    protected DerbyDdlBuilder createDdlBuilder() {
        return new DerbyDdlBuilder();
    }

    @Override
    protected DerbyDdlReader createDdlReader() {
        return new DerbyDdlReader(this);
    }

    @Override
    protected DerbyJdbcSqlTemplate createSqlTemplate() {
        return new DerbyJdbcSqlTemplate(dataSource, settings, null, getDatabaseInfo());
    }

    public String getName() {
        return DatabaseNamesConstants.DERBY;
    }
    
    public String getDefaultSchema() {
        if (StringUtils.isBlank(defaultSchema)) {
            defaultSchema = (String) getSqlTemplate().queryForObject("values CURRENT SCHEMA", String.class);
        }
        return defaultSchema;
    }
    
    public String getDefaultCatalog() {
        return "";
    }
    
    @Override
    public boolean isClob(int type) {
        return type == Types.CLOB;
    }
    
    @Override
    public boolean canColumnBeUsedInWhereClause(Column column) {
        return !column.isOfBinaryType();
    }

    @Override
   	public PermissionResult getCreateSymTriggerPermission() {
       	String delimiter = getDatabaseInfo().getDelimiterToken();
        delimiter = delimiter != null ? delimiter : "";
           
       	String triggerSql = "CREATE TRIGGER TEST_TRIGGER AFTER UPDATE ON " + delimiter + PERMISSION_TEST_TABLE_NAME + delimiter 
       			+ " FOR EACH ROW MODE DB2SQL INSERT INTO " + delimiter + PERMISSION_TEST_TABLE_NAME + delimiter + " VALUES(NULL,NULL)";
       	
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
