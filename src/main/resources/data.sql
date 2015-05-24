-- load customer table
insert into customer (customer_name, address, tel, hashed_password, email, create_date, update_date, version) values ('山田太郎', 'ＸＸ県ＹＹ市ＺＺ町　1-2-3', '09000000000', '$2a$04$DjmAiHiqHUzanV/1JSxxGeSqnTkXcngTkWUxRlDw4K3W202RaRPlW' /* test1 */, 't.yamada@example.com', current_timestamp(), current_timestamp(), 1);
insert into customer (customer_name, address, tel, hashed_password, email, create_date, update_date, version) values ('鈴木次郎', 'ＡＡ県ＢＢ市ＣＣ町　3-2-1', '09000000001', '$2a$04$rhiyXmNNPqGapJ6Af/Npuu7X17u.P29Wh91H/yu0AVS/3D2x5.GHG' /* test2 */, 'j.suzuki@example.com', current_timestamp(), current_timestamp(), 1);

-- load product table
insert into product (item_code, item_name, price, pic, detail, create_date, update_date, version) values ('ITEM000001', 'productA', 1000, null, 'productA detail', current_timestamp(), current_timestamp(), 1);
insert into product (item_code, item_name, price, pic, detail, create_date, update_date, version) values ('ITEM000002', 'productB', 2000, null, 'productA detail', current_timestamp(), current_timestamp(), 1);
insert into product (item_code, item_name, price, pic, detail, create_date, update_date, version) values ('ITEM000003', 'productC', 3000, null, 'productA detail', current_timestamp(), current_timestamp(), 1);
insert into product (item_code, item_name, price, pic, detail, create_date, update_date, version) values ('ITEM000004', 'productD', 4000, null, 'productA detail', current_timestamp(), current_timestamp(), 1);
insert into product (item_code, item_name, price, pic, detail, create_date, update_date, version) values ('ITEM000005', 'productE', 5000, null, 'productA detail', current_timestamp(), current_timestamp(), 1);

-- load stock table
insert into stock (stock, product_id, create_date, update_date, version) values (4, 1,current_timestamp(), current_timestamp(), 1);
insert into stock (stock, product_id, create_date, update_date, version) values (3, 2,current_timestamp(), current_timestamp(), 1);
insert into stock (stock, product_id, create_date, update_date, version) values (2, 3,current_timestamp(), current_timestamp(), 1);
insert into stock (stock, product_id, create_date, update_date, version) values (1, 4,current_timestamp(), current_timestamp(), 1);
insert into stock (stock, product_id, create_date, update_date, version) values (0, 5,current_timestamp(), current_timestamp(), 1);
