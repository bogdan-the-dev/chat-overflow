--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: chatoverflow; Type: SCHEMA; Schema: -; Owner: bogdan
--

CREATE SCHEMA chatoverflow;


ALTER SCHEMA chatoverflow OWNER TO bogdan;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: answer; Type: TABLE; Schema: chatoverflow; Owner: bogdan
--

CREATE TABLE chatoverflow.answer (
    id integer NOT NULL,
    body character varying(255),
    date timestamp without time zone,
    edited boolean DEFAULT false,
    answer_author_id integer,
    question_id integer NOT NULL
);


ALTER TABLE chatoverflow.answer OWNER TO bogdan;

--
-- Name: answer_id_seq; Type: SEQUENCE; Schema: chatoverflow; Owner: bogdan
--

CREATE SEQUENCE chatoverflow.answer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE chatoverflow.answer_id_seq OWNER TO bogdan;

--
-- Name: answer_id_seq; Type: SEQUENCE OWNED BY; Schema: chatoverflow; Owner: bogdan
--

ALTER SEQUENCE chatoverflow.answer_id_seq OWNED BY chatoverflow.answer.id;


--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: chatoverflow; Owner: bogdan
--

CREATE SEQUENCE chatoverflow.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE chatoverflow.hibernate_sequence OWNER TO bogdan;

--
-- Name: question; Type: TABLE; Schema: chatoverflow; Owner: bogdan
--

CREATE TABLE chatoverflow.question (
    question_id integer NOT NULL,
    creation_date timestamp without time zone,
    text text,
    title character varying(255),
    question_author_id integer
);


ALTER TABLE chatoverflow.question OWNER TO bogdan;

--
-- Name: question_question_id_seq; Type: SEQUENCE; Schema: chatoverflow; Owner: bogdan
--

CREATE SEQUENCE chatoverflow.question_question_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE chatoverflow.question_question_id_seq OWNER TO bogdan;

--
-- Name: question_question_id_seq; Type: SEQUENCE OWNED BY; Schema: chatoverflow; Owner: bogdan
--

ALTER SEQUENCE chatoverflow.question_question_id_seq OWNED BY chatoverflow.question.question_id;


--
-- Name: roles; Type: TABLE; Schema: chatoverflow; Owner: bogdan
--

CREATE TABLE chatoverflow.roles (
    role_id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE chatoverflow.roles OWNER TO bogdan;

--
-- Name: roles_role_id_seq; Type: SEQUENCE; Schema: chatoverflow; Owner: bogdan
--

CREATE SEQUENCE chatoverflow.roles_role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE chatoverflow.roles_role_id_seq OWNER TO bogdan;

--
-- Name: roles_role_id_seq; Type: SEQUENCE OWNED BY; Schema: chatoverflow; Owner: bogdan
--

ALTER SEQUENCE chatoverflow.roles_role_id_seq OWNED BY chatoverflow.roles.role_id;


--
-- Name: tag; Type: TABLE; Schema: chatoverflow; Owner: bogdan
--

CREATE TABLE chatoverflow.tag (
    tag_id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE chatoverflow.tag OWNER TO bogdan;

--
-- Name: tag_item; Type: TABLE; Schema: chatoverflow; Owner: bogdan
--

CREATE TABLE chatoverflow.tag_item (
    id integer NOT NULL,
    question_id integer,
    tag_name_id integer
);


ALTER TABLE chatoverflow.tag_item OWNER TO bogdan;

--
-- Name: tag_item_id_seq; Type: SEQUENCE; Schema: chatoverflow; Owner: bogdan
--

CREATE SEQUENCE chatoverflow.tag_item_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE chatoverflow.tag_item_id_seq OWNER TO bogdan;

--
-- Name: tag_item_id_seq; Type: SEQUENCE OWNED BY; Schema: chatoverflow; Owner: bogdan
--

ALTER SEQUENCE chatoverflow.tag_item_id_seq OWNED BY chatoverflow.tag_item.id;


--
-- Name: tag_tag_id_seq; Type: SEQUENCE; Schema: chatoverflow; Owner: bogdan
--

CREATE SEQUENCE chatoverflow.tag_tag_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE chatoverflow.tag_tag_id_seq OWNER TO bogdan;

--
-- Name: tag_tag_id_seq; Type: SEQUENCE OWNED BY; Schema: chatoverflow; Owner: bogdan
--

ALTER SEQUENCE chatoverflow.tag_tag_id_seq OWNED BY chatoverflow.tag.tag_id;


--
-- Name: user; Type: TABLE; Schema: chatoverflow; Owner: bogdan
--

CREATE TABLE chatoverflow."user" (
    id integer NOT NULL,
    account_banned boolean,
    account_blocked boolean,
    account_verified boolean,
    email character varying(255),
    password_hash character varying(255),
    score integer DEFAULT 0,
    two_factor_authentication boolean,
    username character varying(255),
    user_role_id integer
);


ALTER TABLE chatoverflow."user" OWNER TO bogdan;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: chatoverflow; Owner: bogdan
--

CREATE SEQUENCE chatoverflow.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE chatoverflow.user_id_seq OWNER TO bogdan;

--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: chatoverflow; Owner: bogdan
--

ALTER SEQUENCE chatoverflow.user_id_seq OWNED BY chatoverflow."user".id;


--
-- Name: vote; Type: TABLE; Schema: chatoverflow; Owner: bogdan
--

CREATE TABLE chatoverflow.vote (
    vote_id integer NOT NULL,
    user_id integer,
    score integer,
    question_id integer,
    answer_id integer
);


ALTER TABLE chatoverflow.vote OWNER TO bogdan;

--
-- Name: vote_vote_id_seq; Type: SEQUENCE; Schema: chatoverflow; Owner: bogdan
--

CREATE SEQUENCE chatoverflow.vote_vote_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE chatoverflow.vote_vote_id_seq OWNER TO bogdan;

--
-- Name: vote_vote_id_seq; Type: SEQUENCE OWNED BY; Schema: chatoverflow; Owner: bogdan
--

ALTER SEQUENCE chatoverflow.vote_vote_id_seq OWNED BY chatoverflow.vote.vote_id;


--
-- Name: answer id; Type: DEFAULT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.answer ALTER COLUMN id SET DEFAULT nextval('chatoverflow.answer_id_seq'::regclass);


--
-- Name: question question_id; Type: DEFAULT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.question ALTER COLUMN question_id SET DEFAULT nextval('chatoverflow.question_question_id_seq'::regclass);


--
-- Name: roles role_id; Type: DEFAULT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.roles ALTER COLUMN role_id SET DEFAULT nextval('chatoverflow.roles_role_id_seq'::regclass);


--
-- Name: tag tag_id; Type: DEFAULT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.tag ALTER COLUMN tag_id SET DEFAULT nextval('chatoverflow.tag_tag_id_seq'::regclass);


--
-- Name: tag_item id; Type: DEFAULT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.tag_item ALTER COLUMN id SET DEFAULT nextval('chatoverflow.tag_item_id_seq'::regclass);


--
-- Name: user id; Type: DEFAULT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow."user" ALTER COLUMN id SET DEFAULT nextval('chatoverflow.user_id_seq'::regclass);


--
-- Name: vote vote_id; Type: DEFAULT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.vote ALTER COLUMN vote_id SET DEFAULT nextval('chatoverflow.vote_vote_id_seq'::regclass);


--
-- Data for Name: answer; Type: TABLE DATA; Schema: chatoverflow; Owner: bogdan
--

COPY chatoverflow.answer (id, body, date, edited, answer_author_id, question_id) FROM stdin;
2	fgd	2022-03-12 01:48:06	f	1	1
3	yes2	2022-03-15 22:49:10	f	1	1
\.


--
-- Data for Name: question; Type: TABLE DATA; Schema: chatoverflow; Owner: bogdan
--

COPY chatoverflow.question (question_id, creation_date, text, title, question_author_id) FROM stdin;
4	2022-03-15 11:09:47	Test question body	Test question title	1
3	2022-02-28 23:36:56	Body 2	Title 2	1
1	2022-02-28 23:36:56	text	text	1
2	2022-02-28 23:36:56	Body for title 3	Title 3	1
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: chatoverflow; Owner: bogdan
--

COPY chatoverflow.roles (role_id, name) FROM stdin;
2	moderator
\.


--
-- Data for Name: tag; Type: TABLE DATA; Schema: chatoverflow; Owner: bogdan
--

COPY chatoverflow.tag (tag_id, name) FROM stdin;
68	quiz
78	utcn
80	doro
84	lab
\.


--
-- Data for Name: tag_item; Type: TABLE DATA; Schema: chatoverflow; Owner: bogdan
--

COPY chatoverflow.tag_item (id, question_id, tag_name_id) FROM stdin;
5	1	68
77	3	68
79	3	78
81	3	80
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: chatoverflow; Owner: bogdan
--

COPY chatoverflow."user" (id, account_banned, account_blocked, account_verified, email, password_hash, score, two_factor_authentication, username, user_role_id) FROM stdin;
87	f	f	t	test2@gmail.com	sdoif;souio	0	f	test account	2
1	f	f	f	test@gmail.com	gkf'sdoif;souio	10	f	test	2
\.


--
-- Data for Name: vote; Type: TABLE DATA; Schema: chatoverflow; Owner: bogdan
--

COPY chatoverflow.vote (vote_id, user_id, score, question_id, answer_id) FROM stdin;
2	1	1	\N	2
3	1	1	\N	2
\.


--
-- Name: answer_id_seq; Type: SEQUENCE SET; Schema: chatoverflow; Owner: bogdan
--

SELECT pg_catalog.setval('chatoverflow.answer_id_seq', 4, true);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: chatoverflow; Owner: bogdan
--

SELECT pg_catalog.setval('chatoverflow.hibernate_sequence', 115, true);


--
-- Name: question_question_id_seq; Type: SEQUENCE SET; Schema: chatoverflow; Owner: bogdan
--

SELECT pg_catalog.setval('chatoverflow.question_question_id_seq', 2, true);


--
-- Name: roles_role_id_seq; Type: SEQUENCE SET; Schema: chatoverflow; Owner: bogdan
--

SELECT pg_catalog.setval('chatoverflow.roles_role_id_seq', 2, true);


--
-- Name: tag_item_id_seq; Type: SEQUENCE SET; Schema: chatoverflow; Owner: bogdan
--

SELECT pg_catalog.setval('chatoverflow.tag_item_id_seq', 5, true);


--
-- Name: tag_tag_id_seq; Type: SEQUENCE SET; Schema: chatoverflow; Owner: bogdan
--

SELECT pg_catalog.setval('chatoverflow.tag_tag_id_seq', 3, true);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: chatoverflow; Owner: bogdan
--

SELECT pg_catalog.setval('chatoverflow.user_id_seq', 1, true);


--
-- Name: vote_vote_id_seq; Type: SEQUENCE SET; Schema: chatoverflow; Owner: bogdan
--

SELECT pg_catalog.setval('chatoverflow.vote_vote_id_seq', 3, true);


--
-- Name: answer answer_pkey; Type: CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.answer
    ADD CONSTRAINT answer_pkey PRIMARY KEY (id);


--
-- Name: question question_pkey; Type: CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.question
    ADD CONSTRAINT question_pkey PRIMARY KEY (question_id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (role_id);


--
-- Name: tag_item tag_item_pkey; Type: CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.tag_item
    ADD CONSTRAINT tag_item_pkey PRIMARY KEY (id);


--
-- Name: tag tag_pkey; Type: CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (tag_id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: vote vote_pk; Type: CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.vote
    ADD CONSTRAINT vote_pk PRIMARY KEY (vote_id);


--
-- Name: roles_name_uindex; Type: INDEX; Schema: chatoverflow; Owner: bogdan
--

CREATE UNIQUE INDEX roles_name_uindex ON chatoverflow.roles USING btree (name);


--
-- Name: user_email_uindex; Type: INDEX; Schema: chatoverflow; Owner: bogdan
--

CREATE UNIQUE INDEX user_email_uindex ON chatoverflow."user" USING btree (email);


--
-- Name: user_username_uindex; Type: INDEX; Schema: chatoverflow; Owner: bogdan
--

CREATE UNIQUE INDEX user_username_uindex ON chatoverflow."user" USING btree (username);


--
-- Name: vote_vote_id_uindex; Type: INDEX; Schema: chatoverflow; Owner: bogdan
--

CREATE UNIQUE INDEX vote_vote_id_uindex ON chatoverflow.vote USING btree (vote_id);


--
-- Name: answer answer_question_question_id_fk; Type: FK CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.answer
    ADD CONSTRAINT answer_question_question_id_fk FOREIGN KEY (question_id) REFERENCES chatoverflow.question(question_id);


--
-- Name: vote fk4n24tk4fshw5eaoiqvjvi3vj0; Type: FK CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.vote
    ADD CONSTRAINT fk4n24tk4fshw5eaoiqvjvi3vj0 FOREIGN KEY (question_id) REFERENCES chatoverflow.question(question_id);


--
-- Name: tag_item fk7o8hyyt3nqj4ts12hrwsyjfyk; Type: FK CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.tag_item
    ADD CONSTRAINT fk7o8hyyt3nqj4ts12hrwsyjfyk FOREIGN KEY (tag_name_id) REFERENCES chatoverflow.tag(tag_id);


--
-- Name: vote fkcsaksoe2iepaj8birrmithwve; Type: FK CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.vote
    ADD CONSTRAINT fkcsaksoe2iepaj8birrmithwve FOREIGN KEY (user_id) REFERENCES chatoverflow."user"(id);


--
-- Name: answer fkolf6i0leyabfmmd5pwb8mt2cr; Type: FK CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.answer
    ADD CONSTRAINT fkolf6i0leyabfmmd5pwb8mt2cr FOREIGN KEY (answer_author_id) REFERENCES chatoverflow."user"(id) ON DELETE CASCADE;


--
-- Name: user fktisn3seai3lskimvg0s34vcpy; Type: FK CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow."user"
    ADD CONSTRAINT fktisn3seai3lskimvg0s34vcpy FOREIGN KEY (user_role_id) REFERENCES chatoverflow.roles(role_id);


--
-- Name: question question_user_id_fk; Type: FK CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.question
    ADD CONSTRAINT question_user_id_fk FOREIGN KEY (question_author_id) REFERENCES chatoverflow."user"(id);


--
-- Name: tag_item tag_item_question_question_id_fk; Type: FK CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.tag_item
    ADD CONSTRAINT tag_item_question_question_id_fk FOREIGN KEY (question_id) REFERENCES chatoverflow.question(question_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: vote vote_answer_id_fk; Type: FK CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.vote
    ADD CONSTRAINT vote_answer_id_fk FOREIGN KEY (answer_id) REFERENCES chatoverflow.answer(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: vote vote_answer_id_fk_2; Type: FK CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.vote
    ADD CONSTRAINT vote_answer_id_fk_2 FOREIGN KEY (answer_id) REFERENCES chatoverflow.answer(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: vote vote_question_question_id_fk; Type: FK CONSTRAINT; Schema: chatoverflow; Owner: bogdan
--

ALTER TABLE ONLY chatoverflow.vote
    ADD CONSTRAINT vote_question_question_id_fk FOREIGN KEY (question_id) REFERENCES chatoverflow.question(question_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

