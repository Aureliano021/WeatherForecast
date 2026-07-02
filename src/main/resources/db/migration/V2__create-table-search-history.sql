CREATE TABLE search_history (
    id serial PRIMARY KEY,
    user_id uuid NOT NULL REFERENCES users(id),
    city VARCHAR(255) NOT NULL,
    temperature DOUBLE PRECISION,
    rain REAL,
    rain_probability REAL,
    searched_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
