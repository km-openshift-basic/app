INSERT INTO notes (id, title, content, status, created_at) VALUES
    (nextval('notes_seq'), 'Welcome', 'Welcome to the OpenShift Workshop!', 'OPEN', CURRENT_TIMESTAMP),
    (nextval('notes_seq'), 'Setup Complete', 'Initial environment setup is done.', 'DONE', CURRENT_TIMESTAMP),
    (nextval('notes_seq'), 'Next Steps', 'Proceed to deploy the application.', 'OPEN', CURRENT_TIMESTAMP);
