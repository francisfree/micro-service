
alter sequence hibernate_sequence restart with 20000;

--insert into public.counters (id, public_id, deleted, date_created, date_modified, created_by, modified_by) values (11, ',a413b2ee-7ecb-4ae5-9fda-709ef2fcfa2f', false, now(), now(), 'anonymousUser', 'anonymousUser');

--table counters
insert into public.counters (id, public_id, deleted, date_created, date_modified, created_by, modified_by, counter_type, counter_current) values (11, 'a413b2ee-7ecb-4ae5-9fda-709ef2fcfa2f', false, now(), now(), 'anonymousUser', 'anonymousUser', 'Account', 12003);
insert into public.counters (id, public_id, deleted, date_created, date_modified, created_by, modified_by, counter_type, counter_current) values (12, '41a80801-8119-487e-a84f-b70660983dc2', false, now(), now(), 'anonymousUser', 'anonymousUser', 'Card', 808005);


--table accounts
insert into public.accounts (id, public_id, deleted, date_created, date_modified, created_by, modified_by, account_id, bic_swift, client_id, iban) values (51, RANDOM_UUID(), false, now(), now(), 'anonymousUser', 'anonymousUser', '12001', 'ABCLKENA', '61894943737', 'KE2110001');
insert into public.accounts (id, public_id, deleted, date_created, date_modified, created_by, modified_by, account_id, bic_swift, client_id, iban) values (52, RANDOM_UUID(), false, now(), now(), 'anonymousUser', 'anonymousUser', '12002', 'AFRIKENX', '62894943737', 'KE2110002');
insert into public.accounts (id, public_id, deleted, date_created, date_modified, created_by, modified_by, account_id, bic_swift, client_id, iban) values (53, 'de51fa65-3a15-4aca-9691-c561edb3ed1e', false, now(), now(), 'anonymousUser', 'anonymousUser', '12003', 'BARBKENA', '63894943737', 'KE2110003');


--table cards
insert into public.cards (id, public_id, deleted, date_created, date_modified, created_by, modified_by, account_id, card_alias, card_id, card_type) values (71, RANDOM_UUID(), false, now(), now(), 'anonymousUser', 'anonymousUser', 51, 'Ken Doe', '808001', 'Virtual');
insert into public.cards (id, public_id, deleted, date_created, date_modified, created_by, modified_by, account_id, card_alias, card_id, card_type) values (72, RANDOM_UUID(), false, now(), now(), 'anonymousUser', 'anonymousUser', 53, 'Paul Doe', '808002', 'Physical');
insert into public.cards (id, public_id, deleted, date_created, date_modified, created_by, modified_by, account_id, card_alias, card_id, card_type) values (73, RANDOM_UUID(), false, now(), now(), 'anonymousUser', 'anonymousUser', 53, 'Francis Doe', '808003', 'Virtual');
insert into public.cards (id, public_id, deleted, date_created, date_modified, created_by, modified_by, account_id, card_alias, card_id, card_type) values (74, RANDOM_UUID(), false, now(), now(), 'anonymousUser', 'anonymousUser', 52, 'James Doe', '808004', 'Virtual');
insert into public.cards (id, public_id, deleted, date_created, date_modified, created_by, modified_by, account_id, card_alias, card_id, card_type) values (75, RANDOM_UUID(), false, now(), now(), 'anonymousUser', 'anonymousUser', 52, 'Mark Doe', '808005', 'Physical');