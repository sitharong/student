# Structure
- core: root-lv/independent, can import each other: utils/beans to be used internally outside features.
- app, db, externals: 2nd-lv, can import each other and core: utils/beans to be used in features. 
- features: lowest-lv, can only import 2nd-lv: each feature must be independent to each other.

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