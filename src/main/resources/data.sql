INSERT INTO `users` (`id`, `email`, `first_name`, `last_name`, `password`)
VALUES
    (1, 'admin@email.com', 'admin', 'admin', 'f292ad79a41b8c2240891d1adad3afdad1671fa55f463b1c20ad992d822fe031f1fd3b7f34419ac4b7f49f978279725e'),
    (2, 'gosho@email.com', 'Gosho', 'Dimitrov', 'eba54a5352e9fb178a2a464af2f60355da8e0c53e8ecf69500d133f61570b1004cdaa493d5191f4b4438c15f60caf1e5'),
    (3, 'sashko@email.com', 'Aleksander', 'Aleksiev', '9322cff1b5252a14b81ccf972ab6f038ddbdf36a67defaab63d6b817beb701191c05b858429fda603eaee7b6d5079a76'),
    (4, 'petur@email.com', 'Petur', 'Yordanov', 'dd86672766be3db18aaa14ae352753bd62c72851bb52d78a6a1a84c580f476a4be7bb22399f23cfef63d65b65e5bcd1a'),
    (5, 'nikola@email.com', 'Nikola', 'Andreev', 'b8d0031f95a548ff863145a5a33204f4372bd33a6ea78e8981b793c0db52ec0d6fe92f9df9c4dc9686d64fcd58de5b9e'),
    (6, 'emily.johnson@email.com', 'Emily', 'Johnson', 'f292ad79a41b8c2240891d1adad3afdad1671fa55f463b1c20ad992d822fe031f1fd3b7f34419ac4b7f49f978279725e'),
    (7, 'michael.smith@email.com', 'Michael', 'Smith', 'eba54a5352e9fb178a2a464af2f60355da8e0c53e8ecf69500d133f61570b1004cdaa493d5191f4b4438c15f60caf1e5'),
    (8, 'sarah.lee@email.com', 'Sarah', 'Lee', '9322cff1b5252a14b81ccf972ab6f038ddbdf36a67defaab63d6b817beb701191c05b858429fda603eaee7b6d5079a76'),
    (9, 'david.patel@email.com', 'David', 'Patel', 'dd86672766be3db18aaa14ae352753bd62c72851bb52d78a6a1a84c580f476a4be7bb22399f23cfef63d65b65e5bcd1a'),
    (10, 'anna.rodriguez@email.com', 'Anna', 'Rodriguez', 'b8d0031f95a548ff863145a5a33204f4372bd33a6ea78e8981b793c0db52ec0d6fe92f9df9c4dc9686d64fcd58de5b9e'),
    (11, 'john.kim@email.com', 'John', 'Kim', '9322cff1b5252a14b81ccf972ab6f038ddbdf36a67defaab63d6b817beb701191c05b858429fda603eaee7b6d5079a76'),
    (12, 'lisa.wang@email.com', 'Lisa', 'Wang', 'f292ad79a41b8c2240891d1adad3afdad1671fa55f463b1c20ad992d822fe031f1fd3b7f34419ac4b7f49f978279725e'),
    (13, 'robert.garcia@email.com', 'Robert', 'Garcia', 'dd86672766be3db18aaa14ae352753bd62c72851bb52d78a6a1a84c580f476a4be7bb22399f23cfef63d65b65e5bcd1a'),
    (14, 'anastasia.mironova@email.com', 'Anastasia', 'Mironova', 'eba54a5352e9fb178a2a464af2f60355da8e0c53e8ecf69500d133f61570b1004cdaa493d5191f4b4438c15f60caf1e5'),
    (15, 'emily.harper@email.com', 'Emily', 'Harper', '9322cff1b5252a14b81ccf972ab6f038ddbdf36a67defaab63d6b817beb701191c05b858429fda603eaee7b6d5079a76');


INSERT INTO `users_roles` (`user_id`, `role_id`)
VALUES
    (1, 1),
    (2, 3),
    (3, 3),
    (4, 3),
    (5, 3),
    (6, 2),
    (7, 2),
    (8, 2),
    (9, 2),
    (10, 2),
    (11, 2),
    (12, 2),
    (13, 2),
    (14, 2),
    (15, 2);

INSERT INTO doctors (id, bio, experience, first_name, image_url, last_name, department_id, user_id, town, email, password, password_changed, added_time)
VALUES
    (3, 'Dr. Emily Johnson is a highly skilled cardiologist specializing in diagnosing and treating heart conditions. She is well-known for her compassionate care and her commitment to patient health. Dr. Johnson has extensive experience in managing complex cardiovascular diseases and is proficient in advanced diagnostic techniques.', 12, 'Emily', 'https://img.freepik.com/free-photo/doctor-with-white-robe-stethoscope_144627-43879.jpg', 'Johnson', 1, 6, 'Varna', 'emily.johnson@email.com', 'f292ad79a41b8c2240891d1adad3afdad1671fa55f463b1c20ad992d822fe031f1fd3b7f34419ac4b7f49f978279725e', 0, CURDATE()),
    (4, 'Dr. Michael Smith is an expert orthopedic surgeon with a focus on sports injuries and joint replacements. He is dedicated to helping patients recover mobility and live pain-free lives. Dr. Smith is renowned for his surgical precision and his innovative approach to minimally invasive procedures.', 15, 'Michael', 'https://www.shutterstock.com/image-photo/covid19-coronavirus-outbreak-healthcare-workers-260nw-1779353891.jpg', 'Smith', 4, 7, 'Veliko Turnovo', 'michael.smith@email.com', 'eba54a5352e9fb178a2a464af2f60355da8e0c53e8ecf69500d133f61570b1004cdaa493d5191f4b4438c15f60caf1e5', 0, CURDATE()),
    (5, 'Dr. Sarah Lee is a caring pediatrician known for her gentle approach with children and her ability to communicate effectively with parents. She specializes in preventive care, growth and development monitoring, and the management of chronic conditions in children. Dr. Lee is passionate about ensuring the well-being of her young patients.', 8, 'Sarah', 'https://assets.medpagetoday.net/media/images/73xxx/73617.jpg', 'Lee', 5, 8, 'Plovdiv', 'sarah.lee@email.com', '9322cff1b5252a14b81ccf972ab6f038ddbdf36a67defaab63d6b817beb701191c05b858429fda603eaee7b6d5079a76', 0, CURDATE()),
    (6, 'Dr. David Patel is a dermatologist who excels in treating a wide range of skin conditions, including acne, eczema, and psoriasis. He is also skilled in cosmetic dermatology, offering treatments such as Botox and laser therapy. Dr. Patel is known for his personalized care and his dedication to enhancing the health and appearance of his patients\' skin.', 10, 'David', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZ482r1Wj8jqyJMpsc4GhGtJltFY4nrSkiVA&s', 'Patel', 2, 9, 'Burgas', 'david.patel@email.com', 'dd86672766be3db18aaa14ae352753bd62c72851bb52d78a6a1a84c580f476a4be7bb22399f23cfef63d65b65e5bcd1a', 0, CURDATE()),
    (7, 'Dr. Anna Rodriguez is a neurologist with a deep understanding of the brain and nervous system. She specializes in treating conditions such as epilepsy, multiple sclerosis, and Parkinson\'s disease. Dr. Rodriguez is committed to providing comprehensive care and support to patients and their families, ensuring the best possible outcomes.', 14, 'Anna', 'https://kandidataasia.com/wp-content/uploads/2011/10/dr.jpg', 'Rodriguez', 3, 10, 'Sofia', 'anna.rodriguez@email.com', 'b8d0031f95a548ff863145a5a33204f4372bd33a6ea78e8981b793c0db52ec0d6fe92f9df9c4dc9686d64fcd58de5b9e', 0, CURDATE()),
    (8, 'Dr. John Kim is a general surgeon known for his expertise in performing a wide range of surgical procedures. He is dedicated to ensuring patient safety and achieving excellent surgical outcomes. Dr. Kim is particularly skilled in laparoscopic and robotic surgeries, which offer less invasive options for patients.', 11, 'John', 'https://t4.ftcdn.net/jpg/02/60/04/09/360_F_260040900_oO6YW1sHTnKxby4GcjCvtypUCWjnQRg5.jpg', 'Kim', 6, 11, 'Pleven', 'john.kim@email.com', 'ed61b19f49e0d93f40cb1ed30a0e208077c460a95247ca0f4c1f8d9ac0f0ac5687247b0c13393a93a0a310d8b5016477', 0, CURDATE()),
    (9, 'Dr. Lisa Wang is an endocrinologist specializing in hormonal disorders, including diabetes, thyroid diseases, and metabolic disorders. She is passionate about educating patients on managing their conditions and improving their quality of life. Dr. Wang is recognized for her thorough and empathetic approach to patient care.', 9, 'Lisa', 'https://img.freepik.com/free-photo/beautiful-young-female-doctor-looking-camera-office_1301-7807.jpg', 'Wang', 9, 12, 'Sofia', 'lisa.wang@email.com', 'f292ad79a41b8c2240891d1adad3afdad1671fa55f463b1c20ad992d822fe031f1fd3b7f34419ac4b7f49f978279725e', 0, CURDATE()),
    (10, 'Dr. Robert Garcia is an oncologist dedicated to the diagnosis and treatment of cancer. He has a wealth of experience in chemotherapy, radiation therapy, and immunotherapy. Dr. Garcia is known for his compassionate patient care and his commitment to staying at the forefront of cancer research and treatment.', 13, 'Robert', 'https://img.freepik.com/free-photo/handsome-elderly-doctor-uniform_144627-1195.jpg?size=626&ext=jpg', 'Garcia', 8, 13, 'Pleven', 'robert.garcia@email.com', 'dd86672766be3db18aaa14ae352753bd62c72851bb52d78a6a1a84c580f476a4be7bb22399f23cfef63d65b65e5bcd1a', 0, CURDATE()),
    (11, 'Dr. Anastasia Mironova is a highly skilled physician renowned for her dedication to patient care and excellence in medical practice. With extensive experience in various medical procedures, Dr. Mironova is committed to ensuring the best outcomes for her patients. She specializes in both preventive care and complex medical treatments, leveraging the latest advancements in medicine. Dr. Mironova’s approach is patient-centered, focusing on individualized care plans to meet each patient’s unique needs. Her expertise and compassionate care make her a trusted and respected member of the medical community.', 15, 'Anastasia', 'https://img.freepik.com/free-photo/beautiful-young-female-doctor-looking-camera-office_1301-7807.jpg', 'Mironova', 1, 14, 'Veliko Turnovo', 'anastasia.mironova@email.com', 'eba54a5352e9fb178a2a464af2f60355da8e0c53e8ecf69500d133f61570b1004cdaa493d5191f4b4438c15f60caf1e5', 0, '2024-07-01'),
    (15, 'Dr. Emily Harper is a highly skilled cardiologist specializing in diagnosing and treating heart conditions. She is well-known for her compassionate care and her commitment to patient health. Dr. Johnson has extensive experience in managing complex cardiovascular diseases and is proficient in advanced diagnostic techniques. Dr. Emily Johnson is a highly skilled cardiologist specializing in diagnosing and treating heart conditions. She is well-known for her compassionate care and her commitment to patient health. Dr. Johnson has extensive experience in managing complex cardiovascular diseases and is proficient in advanced diagnostic techniques.', 30, 'Emily', 'https://img.freepik.com/premium-photo/smiling-doctor-with-strethoscope_258943-53.jpg?size=626&ext=jpg&ga=GA1.1.1502090658.1720869845&semt=sph', 'Harper', 10, 15, 'Instanbul', 'emily.harper@email.com', '9322cff1b5252a14b81ccf972ab6f038ddbdf36a67defaab63d6b817beb701191c05b858429fda603eaee7b6d5079a76', 0, CURDATE());

INSERT INTO `patient_results` (`id`, `appointment_id`, `date`, `prescription`, `result`, `patient_id`)
VALUES
    (1, 6, '2024-08-09', 'Perform physical therapy exercises', 'Mobility improved', 4),
    (2, 15, '2024-08-02', 'Take antihypertensive medication', 'Blood pressure under control', 2),
    (3, 16, '2024-08-06', 'Follow a balanced diet', 'Growth normal', 2),
    (4, 24, '2024-08-02', 'Administer vaccination', 'Immunity boosted', 2),
    (5, 1, '2024-08-25', 'Continue current medication', 'Cardiac health stable', 2),
    (6, 2, '2024-08-26', 'Apply prescribed topical treatment', 'Skin condition improved', 4),
    (7, 5, '2024-08-24', 'Take prenatal vitamins', 'Healthy pregnancy progress', 5),
    (8, 7, '2024-08-25', 'Start new seizure medication', 'Seizures reduced', 5),
    (9, 8, '2024-08-25', 'Begin chemotherapy cycle', 'Tumor size decreased', 4),
    (10, 11, '2024-08-31', 'Continue antibiotic course', 'Infection subsided', 2),
    (11, 13, '2024-09-01', 'Adjust chemotherapy dosage', 'Cancer markers improved', 3),
    (12, 14, '2024-09-01', 'Increase beta-blocker dosage', 'Heart function improved', 3);


INSERT INTO `contacts` (`id`, `email`, `first_name`, `last_name`, `message`, `subject`)
VALUES
    (1, 'john.doe@email.com', 'John', 'Doe', 'Hello, I would like to inquire about your services. Please get back to me as soon as possible.', 'Service Inquiry'),
    (2, 'alice.smith@email.com', 'Alice', 'Smith', 'I have a question regarding my recent appointment. Can you assist?', 'Order Query'),
    (3, 'michael.jones@email.com', 'Michael', 'Jones', 'Your website is very informative. I have a few suggestions for improvement.', 'Website Feedback'),
    (5, 'daniel.brown@email.com', 'Daniel', 'Brown', 'I am interested in collaborating on a project. Let me know how we can proceed.', 'Collaboration Proposal');