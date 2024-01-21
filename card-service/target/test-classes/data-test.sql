
--table clients
insert into public.clients (client_id, public_id, deleted, date_created, date_modified, created_by, modified_by, client_name) values (11, 'a413b2ee-7ecb-4ae5-9fda-709ef2fcfa2f', false, now(), now(), 'anonymousUser', 'anonymousUser', 'Client A');
insert into public.clients (client_id, public_id, deleted, date_created, date_modified, created_by, modified_by, client_name) values (12, '41a80801-8119-487e-a84f-b70660983dc2', false, now(), now(), 'anonymousUser', 'anonymousUser', 'Client B');


--table accounts
insert into public.accounts (account_id, public_id, deleted, date_created, date_modified, created_by, modified_by, bic_swift, client_id, iban) values (51, RANDOM_UUID(), false, now(), now(), 'anonymousUser', 'anonymousUser', 'ABCLKENA', 11, 'KE2110001');
insert into public.accounts (account_id, public_id, deleted, date_created, date_modified, created_by, modified_by, bic_swift, client_id, iban) values (52, RANDOM_UUID(), false, now(), now(), 'anonymousUser', 'anonymousUser', 'AFRIKENX', 12, 'KE2110002');
insert into public.accounts (account_id, public_id, deleted, date_created, date_modified, created_by, modified_by, bic_swift, client_id, iban) values (53, RANDOM_UUID(), false, now(), now(), 'anonymousUser', 'anonymousUser', 'BARBKENA', 11, 'KE2110003');
insert into public.accounts (account_id, public_id, deleted, date_created, date_modified, created_by, modified_by, bic_swift, client_id, iban) values (54, RANDOM_UUID(), false, now(), now(), 'anonymousUser', 'anonymousUser', 'BARBKENA', 11, 'KE2110004');


--table cards
insert into public.cards (card_id, public_id, deleted, date_created, date_modified, created_by, modified_by, account_id, card_alias, card_type) values (71, RANDOM_UUID(), false, now(), now(), 'anonymousUser', 'anonymousUser', 51, 'Ken Doe', 'Virtual');
insert into public.cards (card_id, public_id, deleted, date_created, date_modified, created_by, modified_by, account_id, card_alias, card_type) values (72, RANDOM_UUID(), false, now(), now(), 'anonymousUser', 'anonymousUser', 53, 'Paul Doe', 'Physical');
insert into public.cards (card_id, public_id, deleted, date_created, date_modified, created_by, modified_by, account_id, card_alias, card_type) values (73, RANDOM_UUID(), false, now(), now(), 'anonymousUser', 'anonymousUser', 53, 'Francis Doe', 'Virtual');
insert into public.cards (card_id, public_id, deleted, date_created, date_modified, created_by, modified_by, account_id, card_alias, card_type) values (74, RANDOM_UUID(), false, now(), now(), 'anonymousUser', 'anonymousUser', 52, 'James Doe', 'Virtual');
insert into public.cards (card_id, public_id, deleted, date_created, date_modified, created_by, modified_by, account_id, card_alias, card_type) values (75, RANDOM_UUID(), false, now(), now(), 'anonymousUser', 'anonymousUser', 52, 'Mark Doe', 'Physical');