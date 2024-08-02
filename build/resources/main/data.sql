INSERT INTO `users` (`id`, `email`, `first_name`, `last_name`, `password`)
VALUES
    (1, 'admin@email.com', 'admin', 'admin', 'f292ad79a41b8c2240891d1adad3afdad1671fa55f463b1c20ad992d822fe031f1fd3b7f34419ac4b7f49f978279725e'),
    (2, 'gosho@email.com', 'Gosho', 'Dimitrov', 'eba54a5352e9fb178a2a464af2f60355da8e0c53e8ecf69500d133f61570b1004cdaa493d5191f4b4438c15f60caf1e5'),
    (3, 'sashko@email.com', 'Aleksander', 'Aleksiev', '9322cff1b5252a14b81ccf972ab6f038ddbdf36a67defaab63d6b817beb701191c05b858429fda603eaee7b6d5079a76'),
    (4, 'petur@email.com', 'Petur', 'Yordanov', 'dd86672766be3db18aaa14ae352753bd62c72851bb52d78a6a1a84c580f476a4be7bb22399f23cfef63d65b65e5bcd1a'),
    (5, 'nikola@email.com', 'Nikola', 'Andreev', 'b8d0031f95a548ff863145a5a33204f4372bd33a6ea78e8981b793c0db52ec0d6fe92f9df9c4dc9686d64fcd58de5b9e'),
    (6, 'emily.johnson@example.com', 'Emily', 'Johnson', 'f292ad79a41b8c2240891d1adad3afdad1671fa55f463b1c20ad992d822fe031f1fd3b7f34419ac4b7f49f978279725e'),
    (7, 'michael.smith@example.com', 'Michael', 'Smith', 'eba54a5352e9fb178a2a464af2f60355da8e0c53e8ecf69500d133f61570b1004cdaa493d5191f4b4438c15f60caf1e5'),
    (8, 'sarah.lee@example.com', 'Sarah', 'Lee', '9322cff1b5252a14b81ccf972ab6f038ddbdf36a67defaab63d6b817beb701191c05b858429fda603eaee7b6d5079a76'),
    (9, 'david.patel@example.com', 'David', 'Patel', 'dd86672766be3db18aaa14ae352753bd62c72851bb52d78a6a1a84c580f476a4be7bb22399f23cfef63d65b65e5bcd1a'),
    (10, 'anna.rodriguez@example.com', 'Anna', 'Rodriguez', 'b8d0031f95a548ff863145a5a33204f4372bd33a6ea78e8981b793c0db52ec0d6fe92f9df9c4dc9686d64fcd58de5b9e'),
    (11, 'john.kim@example.com', 'John', 'Kim', '9322cff1b5252a14b81ccf972ab6f038ddbdf36a67defaab63d6b817beb701191c05b858429fda603eaee7b6d5079a76'),
    (12, 'lisa.wang@example.com', 'Lisa', 'Wang', 'f292ad79a41b8c2240891d1adad3afdad1671fa55f463b1c20ad992d822fe031f1fd3b7f34419ac4b7f49f978279725e'),
    (13, 'robert.garcia@example.com', 'Robert', 'Garcia', 'dd86672766be3db18aaa14ae352753bd62c72851bb52d78a6a1a84c580f476a4be7bb22399f23cfef63d65b65e5bcd1a'),
    (14, 'greta.marinova@example.com', 'Greta', 'Marinova', 'eba54a5352e9fb178a2a464af2f60355da8e0c53e8ecf69500d133f61570b1004cdaa493d5191f4b4438c15f60caf1e5'),
    (15, 'emily.harper@example.com', 'Emily', 'Harper', '9322cff1b5252a14b81ccf972ab6f038ddbdf36a67defaab63d6b817beb701191c05b858429fda603eaee7b6d5079a76');


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
    (3, 'Dr. Emily Johnson is a highly skilled cardiologist specializing in diagnosing and treating heart conditions. She is well-known for her compassionate care and her commitment to patient health. Dr. Johnson has extensive experience in managing complex cardiovascular diseases and is proficient in advanced diagnostic techniques.', 12, 'Emily', 'https://img.freepik.com/free-photo/doctor-with-white-robe-stethoscope_144627-43879.jpg', 'Johnson', 1, 1, 'Varna', 'emily.johnson@example.com', 'randomPassword1', 0, CURDATE()),
    (4, 'Dr. Michael Smith is an expert orthopedic surgeon with a focus on sports injuries and joint replacements. He is dedicated to helping patients recover mobility and live pain-free lives. Dr. Smith is renowned for his surgical precision and his innovative approach to minimally invasive procedures.', 15, 'Michael', 'https://www.shutterstock.com/image-photo/covid19-coronavirus-outbreak-healthcare-workers-260nw-1779353891.jpg', 'Smith', 4, 1, 'Veliko Turnovo', 'michael.smith@example.com', 'randomPassword2', 0, CURDATE()),
    (5, 'Dr. Sarah Lee is a caring pediatrician known for her gentle approach with children and her ability to communicate effectively with parents. She specializes in preventive care, growth and development monitoring, and the management of chronic conditions in children. Dr. Lee is passionate about ensuring the well-being of her young patients.', 8, 'Sarah', 'https://assets.medpagetoday.net/media/images/73xxx/73617.jpg', 'Lee', 5, 1, 'Plovdiv', 'sarah.lee@example.com', 'randomPassword3', 0, CURDATE()),
    (6, 'Dr. David Patel is a dermatologist who excels in treating a wide range of skin conditions, including acne, eczema, and psoriasis. He is also skilled in cosmetic dermatology, offering treatments such as Botox and laser therapy. Dr. Patel is known for his personalized care and his dedication to enhancing the health and appearance of his patients\' skin.', 10, 'David', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZ482r1Wj8jqyJMpsc4GhGtJltFY4nrSkiVA&s', 'Patel', 2, 1, 'Burgas', 'david.patel@example.com', 'randomPassword4', 0, CURDATE()),
    (7, 'Dr. Anna Rodriguez is a neurologist with a deep understanding of the brain and nervous system. She specializes in treating conditions such as epilepsy, multiple sclerosis, and Parkinson\'s disease. Dr. Rodriguez is committed to providing comprehensive care and support to patients and their families, ensuring the best possible outcomes.', 14, 'Anna', 'https://kandidataasia.com/wp-content/uploads/2011/10/dr.jpg', 'Rodriguez', 3, 1, 'Sofia', 'anna.rodriguez@example.com', 'randomPassword5', 0, CURDATE()),
    (8, 'Dr. John Kim is a general surgeon known for his expertise in performing a wide range of surgical procedures. He is dedicated to ensuring patient safety and achieving excellent surgical outcomes. Dr. Kim is particularly skilled in laparoscopic and robotic surgeries, which offer less invasive options for patients.', 11, 'John', 'https://t4.ftcdn.net/jpg/02/60/04/09/360_F_260040900_oO6YW1sHTnKxby4GcjCvtypUCWjnQRg5.jpg', 'Kim', 6, 1, 'Pleven', 'john.kim@example.com', 'randomPassword6', 0, CURDATE()),
    (9, 'Lisa Wang is an endocrinologist specializing in hormonal disorders, including diabetes, thyroid diseases, and metabolic disorders. She is passionate about educating patients on managing their conditions and improving their quality of life. Dr. Wang is recognized for her thorough and empathetic approach to patient care.', 9, 'Lisa', 'https://img.freepik.com/free-photo/beautiful-young-female-doctor-looking-camera-office_1301-7807.jpg', 'Wang', 9, 1, 'Sofia', 'lisa.wang@example.com', 'randomPassword7', 0, CURDATE()),
    (10, 'Dr. Robert Garcia is an oncologist dedicated to the diagnosis and treatment of cancer. He has a wealth of experience in chemotherapy, radiation therapy, and immunotherapy. Dr. Garcia is known for his compassionate patient care and his commitment to staying at the forefront of cancer research and treatment.', 13, 'Robert', 'https://img.freepik.com/free-photo/handsome-elderly-doctor-uniform_144627-1195.jpg?size=626&ext=jpg', 'Garcia', 8, 1, 'Pleven', 'robert.garcia@example.com', 'randomPassword8', 0, CURDATE()),
    (11, 'pekpoqkepqopekpoqkepqopekpoqkepqopekpoqkepqopekpoqkepqopekpoqkepqo', 15, 'Greta', 'https://img.freepik.com/free-photo/beautiful-young-female-doctor-looking-camera-office_1301-7807.jpg', 'Marinova', 1, 2, 'Veliko Turnovo', 'greta.marinova@example.com', 'randomPassword9', 0, '2021-06-01'),
    (15, 'Dr. Emily Harper is a highly skilled cardiologist specializing in diagnosing and treating heart conditions. She is well-known for her compassionate care and her commitment to patient health. Dr. Johnson has extensive experience in managing complex cardiovascular diseases and is proficient in advanced diagnostic techniques. Dr. Emily Johnson is a highly skilled cardiologist specializing in diagnosing and treating heart conditions. She is well-known for her compassionate care and her commitment to patient health. Dr. Johnson has extensive experience in managing complex cardiovascular diseases and is proficient in advanced diagnostic techniques.', 30, 'Emily', 'https://img.freepik.com/premium-photo/smiling-doctor-with-strethoscope_258943-53.jpg?size=626&ext=jpg&ga=GA1.1.1502090658.1720869845&semt=sph', 'Harper', 10, 1, 'Instanbul', 'emily.harper@example.com', 'randomPassword10', 0, CURDATE());

UPDATE doctors d
    JOIN users u ON d.email = u.email
SET d.password = u.password;

INSERT INTO `patient_results` (`id`, `prescription`, `result`, `date_time`, `appointment_id`)
VALUES
    (1, 'Take one tablet of aspirin daily', 'Normal blood pressure', '2023-10-01 10:00:00', 1),
    (2, 'Apply ointment twice daily', 'Skin condition improved', '2023-10-02 11:00:00', 2),
    (3, 'Follow a low-sodium diet', 'Cholesterol levels reduced', '2023-10-03 12:00:00', 3),
    (4, 'Use inhaler as needed', 'Breathing improved', '2023-10-04 13:00:00', 4),
    (5, 'Take vitamin D supplements', 'Bone density increased', '2023-10-05 14:00:00', 5),
    (6, 'Perform physical therapy exercises', 'Mobility improved', '2023-10-06 15:00:00', 6),
    (7, 'Take antibiotics for 7 days', 'Infection cleared', '2023-10-07 16:00:00', 7),
    (8, 'Monitor blood sugar levels', 'Diabetes under control', '2023-10-08 17:00:00', 8),
    (9, 'Use eye drops twice daily', 'Vision improved', '2023-10-09 18:00:00', 9),
    (10, 'Take antihistamines as needed', 'Allergy symptoms reduced', '2023-10-10 19:00:00', 10),
    (11, 'Follow a gluten-free diet', 'Digestive issues resolved', '2023-10-11 20:00:00', 11),
    (12, 'Take pain relievers as needed', 'Pain managed', '2023-10-12 21:00:00', 12),
    (13, 'Use nasal spray daily', 'Sinus congestion relieved', '2023-10-13 22:00:00', 13),
    (14, 'Take iron supplements', 'Anemia improved', '2023-10-14 23:00:00', 14),
    (15, 'Perform breathing exercises', 'Lung capacity increased', '2023-10-15 08:00:00', 15),
    (16, 'Take probiotics daily', 'Gut health improved', '2023-10-16 09:00:00', 16),
    (17, 'Use topical cream for rash', 'Rash cleared', '2023-10-17 10:00:00', 17),
    (18, 'Take anti-inflammatory medication', 'Swelling reduced', '2023-10-18 11:00:00', 18),
    (19, 'Follow a high-fiber diet', 'Bowel movements regular', '2023-10-19 12:00:00', 19);