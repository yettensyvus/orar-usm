CREATE TABLE faculty (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    logo VARCHAR(500) -- can store URL or relative path
);


INSERT INTO faculty (name, logo) VALUES
('Facultatea de Biologie și Geoștiințe', 'bi bi-tree'),
('Facultatea de Drept', 'bi bi-bank'),
('Facultatea de Chimie şi Tehnologie Chimică', 'bi bi-capsule-pill'),
('Facultatea de Fizică şi Inginerie', 'bi bi-lightning-charge-fill'),
('Facultatea de Istorie şi Filosofie', 'bi bi-book'),
('Facultatea de Jurnalism şi Ştiinţe ale Comunicării', 'bi bi-megaphone'),
('Facultatea de Litere', 'bi bi-type'),
('Facultatea de Matematică şi Informatică', 'bi bi-pc-display'),
('Facultatea Psihologie, Ştiinţe ale Educaţiei, Sociologie și Asistență Socială', 'bi bi-people'),
('Facultatea de Relaţii Internaţionale, Ştiinţe Politice şi Administrative', 'bi bi-globe'),
('Facultatea de Ştiinţe Economice', 'bi bi-currency-dollar'),
('Institutul de Educație Fizică și Sport', 'bi bi-bicycle');