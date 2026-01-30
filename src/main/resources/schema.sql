CREATE TABLE contact_request (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,

                                 name        VARCHAR(120)  NOT NULL,
                                 email       VARCHAR(180)  NOT NULL,
                                 interest    VARCHAR(80)   NOT NULL,
                                 message     VARCHAR(4000) NOT NULL,

                                 created_ts  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 updated_ts  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                 created_by  VARCHAR(120),
                                 updated_by  VARCHAR(120)
);
