-- the second script that will be run by Ninja's migration engine script
ALTER table "user" ADD COLUMN email VARCHAR(30);

ALTER table "user" ADD COLUMN phone VARCHAR(10);

ALTER table "user" DROP COLUMN isAdmin;