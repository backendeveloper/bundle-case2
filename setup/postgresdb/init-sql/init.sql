CREATE TABLE IF NOT EXISTS "Message"
(
    "Id"           integer generated always as identity,
    "CreatedDate"  timestamp,
    "RandomNumber" integer,
    "HashChars"    integer
);

alter table "Message"
    owner to bundle;
