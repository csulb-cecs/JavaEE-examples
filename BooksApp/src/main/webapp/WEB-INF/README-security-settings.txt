To setup security on this web app...

1: Use the same JDBC Resource used by the web app (see sun-resources.xml)
    Name of JDBC resource: jdbc/bookstore

2: Create a JDBC Realm under Security
    Start the GlassFish Admin Console (right-click on GlassFish server)

    On Configurations, expand server-config

    Select the Security category

    Enable the Default Principal To Role Mapping

    Select the Realms folder and click on New... to create a new Realm

    Name: SecuredAppRealm (The web app expects this as that's the value in web.xml)

    JAAS-context: jdbcRealm

    JNDI: jdbc/bookstore
        (That comes from step 1)

    All these values are from the SQL script provided with this project:
        User table: users
        User Name Column: email
        Password column: password
        Group table: groups_users
        Group Name Column: groupname

        Password Encryption Algorithm: AES
        Digest algorithm: MD5 -- app written with MD5 encryption, use SHA-256 in your apps

        Encoding: Hex
        Charset: UTF-8

3: You may need to restart GlassFish.