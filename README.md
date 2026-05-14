# Structure
- core: root-lv/independent, can import each other: utils/beans to be used internally outside features.
- app, db, externals: mid-lv, can import each other and core: utils/beans to be used in features. 
- features: outter-lv, can only import mid-lv: each feature must be independent to each other.

# Config
- application.global.yml: global configs for all environments.
- application-env-db.yml: db configs for its env.
- application-env.yml: unique configs for its env, can overwrite configs in the above files on dupplicate setting.

# Run
- map the actual db with the config file:
    resources/application-env-db.yml
- in project folder, run:
    ".\mvnw.cmd" clean install -f ".\pom.xml"
- run main class.

# DB Table
- clearly manage pk, unique, nullable, index.
- not using forienge key, only store pk from other table as raw column.

# DB JPA
- all entity classes are POJOs, no composition dependent.
- for readonly entities, use @Transactional(readonly=true) on the repository beans.
- for read-write entities, need to extends BaseEntity class
    use @Transactional(readonly=true) on the repository readonly methods and
    use @Transactional on the dao methods that group all the related(if any failed reset all) insert/update operations.
- services access daos, daos access repositories.
- use JPQL, no native query.