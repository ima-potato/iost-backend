INSERT INTO confetti.quizzes (id, prizepool, quiz_name, sponsor, start_date_time, status) VALUES (1, 100.00, 'testquiz', 'testSponsor', '2019-03-21 03:58:57', 'pending');
INSERT INTO confetti.questions (id, question, question_number) VALUES (1, 'What is the answer?', 1);
INSERT INTO confetti.questions_quiz (quiz_id, question_id) VALUES (1, 1);
INSERT INTO confetti.choices (id, correct, description) VALUES (1, true, 'A');
INSERT INTO confetti.choices (id, correct, description) VALUES (2, false, 'B');
INSERT INTO confetti.choices (id, correct, description) VALUES (3, false, 'C');
INSERT INTO confetti.choices_question (question_id, choice_id) VALUES (1, 1);
INSERT INTO confetti.choices_question (question_id, choice_id) VALUES (1, 2);
INSERT INTO confetti.choices_question (question_id, choice_id) VALUES (1, 3);
