/*
Table structure for table 'public.guest'
*/

DROP TABLE IF EXISTS "public"."guest" CASCADE;
CREATE TABLE "public"."guest" (
  "id" SERIAL PRIMARY KEY NOT NULL,
  "first_name" VARCHAR(255)  NOT NULL,
  "last_name" VARCHAR(255)  NOT NULL,
  "suffix" VARCHAR(255),
  "email" VARCHAR(255),
  "rsvpResponse" VARCHAR(255),
  "foodChoice" VARCHAR(255),
  "plansToUseRoomBlock" VARCHAR(255)
) WITH OIDS;

/*
Table structure for table 'ublic.guestbook'
*/

DROP TABLE IF EXISTS "public"."guestbook" CASCADE;
CREATE TABLE "public"."guestbook" (
	"id" SERIAL PRIMARY KEY NOT NULL,
	"name" VARCHAR(255)  NOT NULL,
	"message" VARCHAR(4095)  NOT NULL,
	"date_created" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
) WITH OIDS;
