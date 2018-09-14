INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('client1','$2a$10$iqNtpb8g4RavgxTGme0g0.l/lQuCq/Ed7ysjcsZdeqHG6ifqmuG..', 'trust, read, write',
	'client_credentials,authorization_code,implicit,password,refresh_token', null, null, 36000, 36000, null, true);
INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('client2', '$2a$10$7f5cLHulHER5oHLKcTulReDryURZUr41zFg2OijzcrszTJSI0xVFq', 'trust, read, write',
	'client_credentials,authorization_code,implicit,password,refresh_token', null, null, 36000, 36000, null, true);
INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('client3', '$2a$10$TtmV5avUwuLjeZSRdYqwbOjQy8uq6t0pfL.oO68fkjwAAY7XVaLAC', 'trust, read, write',
	'client_credentials,authorization_code,implicit,password,refresh_token', null, null, 36000, 36000, null, true);
