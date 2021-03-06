# Dreadball Toolkit Web Service

JSON web service handling Dreadball and Dreadball Extreme team creation and data query.

[![Release docs](https://img.shields.io/badge/docs-release-blue.svg)][site-release]
[![Development docs](https://img.shields.io/badge/docs-develop-blue.svg)][site-develop]

[![Release javadocs](https://img.shields.io/badge/javadocs-release-blue.svg)][javadoc-release]
[![Development javadocs](https://img.shields.io/badge/javadocs-develop-blue.svg)][javadoc-develop]

## Documentation

Documentation is always generated for the latest release, kept in the 'master' branch:

- The [latest release documentation page][site-release].
- The [the latest release Javadoc site][javadoc-release].

Documentation is also generated from the latest snapshot, taken from the 'develop' branch:

- The [the latest snapshot documentation page][site-develop].
- The [the latest snapshot Javadoc site][javadoc-develop].

The documentation site sources come along the source code (as it is a Maven site), so it is always possible to generate them using the following Maven command:

```
$ mvn verify site -P h2
```

The verify phase is required, as otherwise some of the reports won't be created.

## Usage

The application is coded in Java, using Maven to manage the project.

### Prerequisites

The project has been tested on the following Java versions:
* JDK 8

All other dependencies are handled through Maven, and noted in the included POM file.

### Installing

The project can be installed by creating the war file and deploying it into a server.

### Running

To just run the web service, useful for testing, the following command can be used:

```
$ mvn jetty:run-war -P h2
```

This will set up the web service to use the H2 in-memory database.

### Testing

The integration tests require a database, which can be set up automatically if using the H2 profile. For this reason these tests should be run with the following command:

```
$ mvn verify -P h2
```

### Packaging the WAR

When creating the WAR file the database connection credentials should be set:

```
$ mvn package -P mysql -Ddatabase.username=username -Ddatabase.password=password -Ddatabase.url=jdbc:mysql://localhost:3306/dreadball_toolkit
```

## Collaborate

Any kind of help with the project will be well received, and there are two main ways to give such help:

- Reporting errors and asking for extensions through the issues management
- or forking the repository and extending the project

### Issues management

Issues are managed at the GitHub [project issues tracker][issues], where any Github user may report bugs or ask for new features.

### Getting the code

If you wish to fork or modify the code, visit the [GitHub project page][scm], where the latest versions are always kept. Check the 'master' branch for the latest release, and the 'develop' for the current, and stable, development version.

## License

The project has been released under version 2.0 of the [Apache License][license].

[issues]: https://github.com/bernardo-mg/dreadball-toolkit-ws/issues
[javadoc-develop]: http://docs.wandrell.com/development/maven/dreadball-toolkit-ws/apidocs
[javadoc-release]: http://docs.wandrell.com/maven/dreadball-toolkit-ws/apidocs
[license]: http://www.apache.org/licenses/LICENSE-2.0
[scm]: https://github.com/Bernardo-MG/dreadball-toolkit-ws
[site-develop]: http://docs.wandrell.com/development/maven/dreadball-toolkit-ws
[site-release]: http://docs.wandrell.com/maven/dreadball-toolkit-ws
