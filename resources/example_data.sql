--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES
(1,'test@test.com','john','$2a$10$Ql9GmhZjEtIRLl1euiPh9ujAluHlcEbYrdrGPzbEEbsJj4IGfWJqa',0,'2024-04-12 21:57:08','2024-05-01 21:13:14'),
(2,'aa@test.com','simon','$2a$10$ncgwYdFl/0FhU0FCmSU7KOnSAQzvRp4uqVzHJ71MIVnLKc00gGbQK',0,'2024-04-22 09:56:27','2024-05-01 18:13:02'),
(3,'newuser@test.com','newuser','$2a$10$UVenagVc/32Ylutfkn.30uo2leIKq.W/qLbpJIBj42CSiC8VTcNNW',0,'2024-04-28 16:34:56','2024-04-28 18:34:56'),
(4,'','','$2a$10$vguYsAmRfaj67W52G/xTfuRMhsE.ylv7XgxrtzcDFs41dhZSlM1Ny',0,'2024-05-01 13:55:33','2024-05-01 15:55:33'),
(5,'maurice@test.com','maurice','$2a$10$1Qe.ObLGk/b460MGzhNzf.4QAFwULYcv0I7lef7mD8BhHeKPkoJRS',0,'2024-05-01 19:13:36','2024-05-01 21:13:36');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Topic`
--

LOCK TABLES `Topic` WRITE;
/*!40000 ALTER TABLE `Topic` DISABLE KEYS */;
INSERT INTO `Topic` VALUES
(1,'Python','Articles sur le langage de programmation Python','2024-04-22 17:11:20','2024-05-01 17:56:35'),
(2,'JavaScript','Articles sur le langage de programmation JavaScript','2024-04-22 17:11:20','2024-05-01 17:57:30'),
(3,'Java','Articles sur le langage de programmation Java','2024-04-22 17:11:20','2024-05-01 17:58:07'),
(4,'Web3','Articles sur le Web3','2024-04-22 17:11:20','2024-05-01 17:58:36'),
(5,'PHP','Articles sur le langage de programmation PHP','2024-04-22 17:11:20','2024-05-01 17:59:09');
/*!40000 ALTER TABLE `Topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Post`
--

LOCK TABLES `Post` WRITE;
/*!40000 ALTER TABLE `Post` DISABLE KEYS */;
INSERT INTO `Post` VALUES
(2,'Pourquoi le Web3 est mieux que le Web2 ?','Ce qu\'il permet est bien plus intéressant que ce que le Web2 permettait jusqu\'ici',1,4,'2024-04-26 21:55:52','2024-05-01 16:02:14'),
(5,'Python : La Puissance de la Simplicité','Python, langage de programmation de plus en plus populaire, continue de dominer le paysage de la technologie grâce à sa syntaxe simple, sa polyvalence et sa communauté active. Avec des bibliothèques robustes pour le développement web, l\'analyse de données, l\'intelligence artificielle et bien plus encore, Python reste un choix privilégié pour les développeurs du monde entier.',2,1,'2024-04-27 20:12:42','2024-05-01 16:07:11'),
(6,'PHP : L\'Art de la Programmation Web','PHP, souvent sous-estimé mais toujours omniprésent, demeure un choix populaire pour la création de sites web dynamiques et interactifs. Bien que d\'autres langages aient gagné en popularité, PHP reste une force majeure dans le développement web, offrant une flexibilité inégalée et une courbe d\'apprentissage douce. Avec des frameworks comme Laravel et Symfony propulsant le développement PHP vers de nouveaux sommets, le langage continue de jouer un rôle essentiel dans la construction du web moderne.',1,5,'2024-04-28 15:39:25','2024-05-01 16:09:13'),
(7,'JavaScript : Le Langage du Web','JavaScript, le langage de programmation essentiel du web, ne cesse d\'évoluer pour répondre aux besoins changeants des développeurs et des utilisateurs. Avec l\'émergence de nouvelles bibliothèques et frameworks comme React, Angular et Vue.js, JavaScript reste au cœur de l\'innovation dans le développement web. De plus, avec l\'essor des applications web progressives (PWA) et des technologies côté serveur comme Node.js, JavaScript étend son empreinte au-delà du navigateur.',2,2,'2024-04-29 10:54:28','2024-05-01 16:08:08'),
(8,'PHP : Fondation du Web Dynamique','PHP, le pilier du développement web dynamique depuis des décennies, continue d\'évoluer pour répondre aux exigences modernes. Bien que certains remettent en question sa pertinence face aux nouveaux concurrents, PHP reste au cœur de millions de sites web et d\'applications grâce à sa facilité d\'utilisation et à sa grande communauté de développeurs. Avec l\'introduction de PHP 8 et ses fonctionnalités améliorées, le langage continue de rester pertinent dans le paysage technologique actuel.',1,5,'2024-05-01 15:09:46','2024-05-01 16:08:44'),
(9,'JavaScript : Au Cœur de l\'Innovation Technologique','JavaScript, souvent appelé le langage de programmation le plus influent du Web, reste au centre de l\'innovation technologique. Avec son adoption rapide dans divers domaines, y compris le développement web, mobile et même l\'Internet des Objets (IoT), JavaScript continue de façonner l\'avenir de la technologie. L\'émergence de nouvelles spécifications ECMAScript, telles que ES6 et ES7, apporte des fonctionnalités modernes et une syntaxe améliorée, permettant aux développeurs de créer des applications plus puissantes et plus performantes. De plus, l\'avènement de frameworks et de bibliothèques populaires comme React, Vue.js et Node.js propulse JavaScript vers de nouveaux sommets, ouvrant la voie à des applications web plus réactives, des interfaces utilisateur plus riches et des expériences utilisateur plus engageantes. En tant que langage incontournable dans le monde de la programmation, JavaScript continue d\'inspirer l\'innovation et d\'offrir des possibilités infinies pour les développeurs créatifs.',1,2,'2024-05-01 16:11:15','2024-05-01 16:11:15'),
(10,'Java : La Stabilité au Service de l\'Innovation','Java, le langage de programmation robuste et mature, continue de jouer un rôle crucial dans le paysage technologique mondial. Grâce à sa portabilité, sa fiabilité et sa vaste communauté de développeurs, Java reste un choix de prédilection pour les applications d\'entreprise, les applications mobiles, les jeux vidéo et bien plus encore. Malgré l\'émergence de nouveaux langages, Java conserve sa pertinence en évoluant constamment pour répondre aux besoins changeants de l\'industrie. Avec des mises à jour régulières et des améliorations de performances, Java reste un pilier de l\'innovation technologique, offrant un équilibre parfait entre stabilité et nouveauté.',2,3,'2024-05-01 16:14:05','2024-05-01 16:14:05'),
(11,'Java : L\'Évolution d\'une Technologie Intemporelle','Java, depuis ses débuts dans les années 1990, a parcouru un long chemin pour devenir l\'une des technologies les plus utilisées dans le monde de la programmation. En tant que langage de programmation polyvalent, Java est largement utilisé dans une variété de domaines, notamment le développement d\'applications mobiles, les systèmes embarqués, le cloud computing et l\'Internet des Objets (IoT). L\'introduction de nouvelles fonctionnalités telles que les expressions lambda, les modules et l\'amélioration de la performance du garbage collector a renforcé la position de Java en tant que choix privilégié pour le développement logiciel. Avec une base solide et une roadmap de développement continue, Java demeure une technologie intemporelle qui continue d\'évoluer avec les tendances de l\'industrie.',2,3,'2024-05-01 16:14:28','2024-05-01 16:14:28');
/*!40000 ALTER TABLE `Post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Comment`
--

LOCK TABLES `Comment` WRITE;
/*!40000 ALTER TABLE `Comment` DISABLE KEYS */;
INSERT INTO `Comment` VALUES
(1,'Super article !','2024-04-27 22:58:00','2024-04-27 22:58:00',2,1);
/*!40000 ALTER TABLE `Comment` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `Subscriptions`
--

LOCK TABLES `Subscriptions` WRITE;
/*!40000 ALTER TABLE `Subscriptions` DISABLE KEYS */;
INSERT INTO `Subscriptions` VALUES
(1,1),
(2,1),
(3,2),
(1,3),
(2,3),
(3,3),
(1,4),
(2,4),
(2,5);
/*!40000 ALTER TABLE `Subscriptions` ENABLE KEYS */;
UNLOCK TABLES;
