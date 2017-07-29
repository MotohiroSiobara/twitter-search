# Usersテーブルにカラムを追加

# --- !Ups
ALTER TABLE user ADD COLUMN follow_count INTEGER;
ALTER TABLE user ADD COLUMN follower_count INTEGER;
ALTER TABLE user ADD COLUMN favorite_count INTEGER;
ALTER TABLE user ADD COLUMN tweet_count INTEGER;
# --- !Downs
ALTER TABLE user DROP COLUMN follow_count;
ALTER TABLE user DROP COLUMN follower_count;
ALTER TABLE user DROP COLUMN favorite_count;
ALTER TABLE user DROP COLUMN tweet_count;