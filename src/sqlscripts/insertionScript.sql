INSERT INTO Role (role_name) VALUES('ROLE_USER');
INSERT INTO Role (role_name) VALUES('ROLE_ADMIN');

INSERT INTO "user" (user_email, user_password, user_name, role_id)
VALUES ('abosyaka@gmail.com', '$2a$10$YFDdYenU1bljLgOv84LxmOkR9ZwI51tsQzR7ks31d4ooApiB9cpR6', 'Ablay', 2);
INSERT INTO "user" (user_email, user_password, user_name, role_id)
VALUES ('igor@gmail.com', '$2a$10$YFDdYenU1bljLgOv84LxmOkR9ZwI51tsQzR7ks31d4ooApiB9cpR6', 'Igor', 1);
INSERT INTO "user" (user_email, user_password, user_name, role_id)
VALUES ('almat@gmail.com', '$2a$10$YFDdYenU1bljLgOv84LxmOkR9ZwI51tsQzR7ks31d4ooApiB9cpR6', 'Almat', 1);

INSERT INTO Category (category_name) VALUES ('cakes');
INSERT INTO Category (category_name) VALUES ('macaroons');
INSERT INTO Category (category_name) VALUES ('cupcakes');

INSERT INTO Good (
    good_name ,
    good_description,
    picture_url,
    good_weight,
    price,
    category_id,
    ingredients,
    storage_period)
VALUES (
           'Count of Monte Cristo',
           'Delicate, moist sponge cake with carrots, walnuts and cinnamon. Interlayer with cheese cream on boiled condensed milk and orange cooli filling.',
           'https://static.tildacdn.com/tild6531-6165-4862-a263-393436643035/125233572_3942222952.jpg',
           1.5,
           6500,
           1,
           'wet sponge cake with notes of carrots, cream 33%, egg, flour, walnuts, cinnamon',
           7
       );

INSERT INTO Good (
    good_name ,
    good_description,
    picture_url,
    good_weight,
    price,
    category_id,
    ingredients,
    storage_period)
VALUES (
           'Exotic',
           'Lovers of tropical fruits cannot always afford to break out to an islet of paradise. But here the freshest, delicate pasta with a bright taste of these exotic fruits comes to the rescue.',
           'https://static.tildacdn.com/tild6637-6433-4136-a430-356535373965/76818296_17244091057.jpg',
           0.75,
           450,
           2,
           '100% blanched premium almond flour (Spain), protein, icing sugar, white chocolate, cream, mango puree, butter, passion fruit puree, NH pectin, sugar',
           21
       );

INSERT INTO Good (
    good_name ,
    good_description,
    picture_url,
    good_weight,
    price,
    category_id,
    ingredients,
    storage_period)
VALUES (
           'honey cupcakes',
           'Delicate, moist sponge cake with carrots, walnuts and cinnamon. Interlayer with cheese cream on boiled condensed milk and orange cooli filling.',
           'https://static.tildacdn.com/tild3765-3537-4634-a331-666661353034/IMG_8589.JPG',
           1.0,
           1200,
           3,
           'Natural Altai honey, flour, powdered sugar, milk, butter, eggs, corn starch, natural cream 33%',
           14
       );

INSERT INTO Status (status_name) VALUES ('IN PROCESS');
INSERT INTO Status (status_name) VALUES ('COMPLETED');
INSERT INTO Status (status_name) VALUES ('WAITING');

INSERT INTO "order" (user_id, status_id) VALUES (2, 1);
INSERT INTO "order" (user_id, status_id) VALUES (2, 2);
INSERT INTO "order" (user_id, status_id) VALUES (3, 3);

INSERT INTO Order_Detail (order_id, good_id, count) VALUES (1, 1, 2);
INSERT INTO Order_Detail (order_id, good_id, count) VALUES (2, 2, 3);
INSERT INTO Order_Detail (order_id, good_id, count) VALUES (3, 3, 4);