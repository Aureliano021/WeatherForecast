create table favorite_cities (
    id serial primary key,
    user_id uuid not null references users(id),
    city_name varchar(255) not null unique,
    created_at timestamp default current_timestamp,
    unique (user_id, city_name)
);