package com.harshith.news.model

import com.google.gson.Gson
import com.harshith.news.model.NewsArticle

data class NewsFeed(
    val homeFeedNews: List<NewsArticle>? = emptyList(),
    val indiaSportsNews: List<NewsArticle>? = emptyList(),
    val indiaTechNews: List<NewsArticle>? = emptyList(),
    val indiaPoliticsNews: List<NewsArticle>? = emptyList(),
    //THe plan is to add more and more content to newsFeed
){
    val allNews = homeFeedNews?.plus(indiaTechNews)?.plus(indiaPoliticsNews)?.plus(indiaSportsNews)
}

val newsResponseString = """
    {
        "articleId": "f7fbd17821e585b098efd02063fd8fc6",
        "title": "Saudi Arabia reveals high-tech stadium design atop cliff for 2034 World Cup, named for crown prince",
        "link": "https://sportstar.thehindu.com/football/saudi-arabia-new-stadium-fifa-world-cup-2024-al-nassr-hila-home-ground-ronaldo-neymar/article67743283.ece",
        "keywords": [
            "football"
        ],
        "creator": null,
        "videoUrl": null,
        "description": "It will be named Prince Mohammed bin Salman Stadium for the crown prince of the oil-rich kingdom that wants to become the major player in world sports over the next decade.",
        "content": "Saudi Arabia revealed designs Monday for a high-tech stadium atop a 200-meter high cliff near Riyadh where the kingdom plans to stage games at the 2034 World Cup. The 45,000-seat venue design has a retractable roof and field, plus an LED wall with hundreds of meters (yards) of screens to create an immersive experience for fans, the Qiddiya Investment Company said in a statement. It will be named Prince Mohammed bin Salman Stadium for the crown prince of the oil-rich kingdom that wants to become the major player in world sports over the next decade. The stadium is a centrepiece of the Qiddiya City project that aims to create an entertainment, gaming and sports hub 45 kilometres (30 miles) from downtown Riyadh, the Saudi capital. The QIC is wholly owned by the Public Investment Fund, which is chaired by the crown prince. It manages about ${'$'}700 billion in Saudi sovereign wealth and includes investments in the LIV Golf project and English Premier League club Newcastle. The QIC said the new stadium is intended to become the home venue of Al-Nassr and Al-Hilal, the Riyadh clubs who last year signed Cristiano Ronaldo and Neymar, respectively. Saudi Arabia is the only bidder to host the men’s World Cup in 2034, with FIFA set to confirm its pick late this year in a confirmation vote by more than 200 national soccer federations. FIFA has said 14 stadiums will be needed for the 48-team tournament. Comments",
        "pubDate": "2024-01-15 16:47:43",
        "image_url": "painterResource(id = R.drawable.image_post)",
        "sourceId": "sportstar",
        "sourceUrl": "https://sportstar.thehindu.com",
        "sourcePriority": 2972,
        "country": [
            "india"
        ],
        "category": [
            "sports"
        ],
        "language": "english",
        "ai_tag": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment_stats": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN"
    }
""".trimIndent()

val newsArticleClass: SerializedNewsArticle = Gson().fromJson(newsResponseString, SerializedNewsArticle::class.java)
val newsArticleClassAnother: NewsArticle = Gson().fromJson(newsResponseString, NewsArticle::class.java)

/*
,
    {
        "article_id": "d16dccedd9fdf0572c820123dba0c773",
        "title": "UFC 297 time: When does Sean Strickland vs Dricus Du Plessis begin in UK and US this weekend?",
        "link": "https://www.businesslend.com/sports/ufc-297-time-when-does-sean-strickland-vs-dricus-du-plessis-start-in-uk-and-us-this-weekend/",
        "keywords": [
            "sports"
        ],
        "creator": [
            "Mirza Shehnaz"
        ],
        "video_url": null,
        "description": "Sign as much as our free sport e-newsletter for all the newest information on every little thing from biking to boxing Sign as much as our free sport electronic mail for all the newest information Bad blood will come to the boil this weekend, as Sean Strickland defends the middleweight title in opposition to Dricus [...] The post UFC 297 time: When does Sean Strickland vs Dricus Du Plessis begin in UK and US this weekend? appeared first on BusinessLend.",
        "content": "Bad blood will come to the boil this weekend, as Sean Strickland defends the middleweight title in opposition to Dricus Du Plessis at UFC 297. Strickland dethroned Israel Adesanya in September, outpointing the modern-day nice in one of many greatest upsets in UFC historical past. Now the controversial American makes his first title defence, in opposition to a person whom he has already fought as soon as – in a way. Strickland and Du Plessis brawled within the crowd at UFC 296 in December, after the South African mocked Strickland over the alleged abuse that he suffered as a baby. Now the pair will get to settle their feud within the Octagon, as Du Plessis seems to be to construct upon his shock TKO of Robert Whittaker with one other massive win. In the co-main occasion, Raquel Pennington and Mayra Bueno Silva will conflict to crown a brand new girls’s bantamweight champion, after Amanda Nunes vacated the belt whereas retiring from MMA final yr. Here’s all it’s worthwhile to know. We might earn fee from a few of the hyperlinks on this article, however we by no means permit this to affect our content material. This income helps to fund journalism throughout The Independent. When is UFC 297 and what time does it begin? UFC 297 will happen on Saturday 20 January on the Scotiabank Arena in Toronto, Canada. The early prelims are set to start at 11.30pm GMT (4.30pm PT, 6.30pm CT, 7.30pm ET), with the common prelims following at 1am GMT on Sunday 21 January (6pm PT, 8pm CT, 9pm ET on Saturday). The predominant card will then start at 3am GMT on Sunday (8pm PT, 10pm CT, 11pm ET on Saturday). How can I watch it? The card will air stay on TNT Sports within the UK, with the broadcaster’s app and web site additionally streaming the fights. In the US, ESPN+ will stream the motion stay, as will the UFC’s Fight Pass. If you’re travelling overseas and wish to watch the occasion, you may want a VPN to unblock your streaming app. Our VPN round-up is right here to assist and consists of offers on VPNs out there. Viewers utilizing a VPN must guarantee that they adjust to any native rules the place they’re and likewise with the phrases of their service supplier. Odds A tearful Sean Strickland was topped UFC middleweight champion in September Strickland – 4/6 Du Plessis – 5/4 Via Bet365. Full card (topic to alter) Main card Sean Strickland (C) vs Dricus Du Plessis (middleweight title) Raquel Pennington vs Mayra Bueno Silva (vacant girls’s bantamweight title) Neil Magny vs Mike Malott (welterweight) Dominick Reyes vs Carlos Ulberg (light-heavyweight) Arnold Allen vs Movsar Evloev (featherweight) Prelims Chris Curtis vs Marc-Andre Barriault (middleweight) Brad Katona vs Garrett Armfield (bantamweight) Charles Jourdain vs Sean Woodson (featherweight) Serhiy Sidey vs Ramon Taveras (bantamweight) Gillian Robertson vs Polyana Viana (girls’s strawweight) Early prelims Yohan Lainesse vs Sam Patterson (welterweight) Jasmine Jasudavicius vs Priscila Cachoeira (girls’s flyweight) Malcolm Gordon vs Jimmy Flick (flyweight) F1: Former Alpine chief Alan Permane set for return with AlphaTauri",
        "pubDate": "2024-01-15 16:45:05",
        "image_url": "https://static.independent.co.uk/2023/07/09/06/GettyImages-1526952614.jpg?quality=75&width=1200&auto=webp",
        "source_id": "businesslend",
        "source_url": "https://www.businesslend.com",
        "source_priority": 716394,
        "country": [
            "india"
        ],
        "category": [
            "top"
        ],
        "language": "english",
        "ai_tag": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment_stats": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN"
    },
    {
        "article_id": "7c18586079a6b8cacfcdbd6826587849",
        "title": "State-owned KABIL Signs Rs 200 Cr Lithium Exploration Deal With Argentina",
        "link": "https://www.businessworld.in/article/State-owned-KABIL-Signs-Rs-200-Cr-Lithium-Exploration-Deal-With-Argentina/15-01-2024-506227",
        "keywords": null,
        "creator": null,
        "video_url": null,
        "description": "\"The project will help India strengthen lithium supplies while developing lithium mining and downstream sectors of both the countries,\" mines minister Pralhad Joshi said in a post on X",
        "content": "India's state-owned firm Khanij Bidesh India (KABIL) signed a Rs 200 crore lithium exploration pact for five blocks in Argentina, the Ministry of Mines said on Monday. \"The project will help India strengthen lithium supplies while developing lithium mining and downstream sectors of both the countries,\" mines minister Pralhad Joshi said in a post on X. India, among the world's top greenhouse gas emitters, has been pursuing overseas pacts to secure key minerals in resource-rich countries such as Australia, Argentina and Chile. The deal, signed with an Argentinian state-run enterprise, gives KABIL exploration and developmental rights for commercial production. India's top bureaucrat in the mining ministry, VL Kantha Rao, had said in December that the country was in preliminary discussions with Bolivia to acquire lithium assets. (REUTERS)",
        "pubDate": "2024-01-15 16:42:54",
        "image_url": "https://static.businessworld.in/article/article_extra_large_image/1705334367_d6H1qz_lithium_mining_2_1_.jpg",
        "source_id": "businessworld",
        "source_url": "https://www.businessworld.in",
        "source_priority": 150658,
        "country": [
            "india"
        ],
        "category": [
            "top"
        ],
        "language": "english",
        "ai_tag": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment_stats": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN"
    },
    {
        "article_id": "0ca370be2d077423782600a1ce3e4dba",
        "title": "China, Switzerland Sign Declaration To Expand Trade",
        "link": "https://www.businessworld.in/article/China-Switzerland-Sign-Declaration-To-Expand-Trade/15-01-2024-506229",
        "keywords": null,
        "creator": null,
        "video_url": null,
        "description": "Li, the most senior Chinese official to visit Switzerland since President Xi Jinping's visit in 2017, met President Viola Amherd alongside a high-ranking Chinese delegation ahead of the World Economic Forum in Davos",
        "content": "China and Switzerland signed a joint declaration on Monday agreeing to deepen their partnership after Premier Li Qiang met the Swiss President, the Swiss government said. Li, the most senior Chinese official to visit Switzerland since President Xi Jinping's visit in 2017, met President Viola Amherd alongside a high-ranking Chinese delegation ahead of the World Economic Forum in Davos. The declaration entails the finalisation of a joint study to develop the two countries' existing free trade agreement. \"This marks an important step towards the start of possible negotiations,\" the Swiss government said. China is Switzerland's third-biggest trading partner after the United States and the European Union. The two countries signed a free trade agreement in 2013, Beijing's first such deal with an economy in continental Europe. But efforts to refresh it previously stalled amid reports of concerns about China's human rights record. A dialogue between the Swiss and Chinese foreign ministries will resume this year and is set to include human rights. Li and Amherd, who met in an 18th-century manor house near the capital Bern, also discussed Russia's war against Ukraine and the Israel-Hamas conflict, the Swiss statement said. (REUTERS)",
        "pubDate": "2024-01-15 16:42:54",
        "image_url": "https://static.businessworld.in/article/article_extra_large_image/1705334976_fe8keP_Screenshot_2024_01_15_213712.jpg",
        "source_id": "businessworld",
        "source_url": "https://www.businessworld.in",
        "source_priority": 150658,
        "country": [
            "india"
        ],
        "category": [
            "top"
        ],
        "language": "english",
        "ai_tag": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment_stats": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN"
    },
    {
        "article_id": "cc60779f299021fb34e3ec6954e201f6",
        "title": "EAM Jaishankar Meets Iranian Prez Ebrahim Raisi; Discusses Bilateral, Global Issues",
        "link": "https://www.businessworld.in/article/EAM-Jaishankar-Meets-Iranian-Prez-Ebrahim-Raisi-Discusses-Bilateral-Global-Issues/15-01-2024-506230",
        "keywords": null,
        "creator": null,
        "video_url": null,
        "description": "At a joint press conference with his Iranian counterpart in Tehran, Jaishankar said the visit gave him an opportunity to personally convey \"our condolences to my counterpart on the recent terrorist attack in Kerman\"",
        "content": "External Affairs Minister S Jaishankar on Monday called on Iran President Dr Ebrahim Raisi and conveyed greetings of Prime Minister Narendra Modi. Jaishankar, who is on a two-day visit to Iran, apprised Dr Raisi of his discussions with Iranian ministers. \"Honoured to call on the President of the Islamic Republic of Iran Dr Ebrahim Raisi @raisi_com. Conveyed the greetings of PM @narendramodi. Expressed condolences over the Kerman attack. Apprised him of my productive discussions with the Iranian Ministers. Value his guidance for further development of ties,\" Jaishankar said in a post on X. Jaishankar also met Iran's Foreign Minister Dr Hossein Amir-Abdollahian and held discussions on bilateral, regional and global issues. Political cooperation, connectivity initiatives and strong people-to-people ties were important aspects on the agenda during the meeting. At a joint press conference with his Iranian counterpart in Tehran, Jaishankar said the visit gave him an opportunity to personally convey \"our condolences to my counterpart on the recent terrorist attack in Kerman\". PM Modi has written to President Raisi in this regard as well. \"Our people-to-people contacts have long been a strength...Let me share with you all that the government of India has decided to include Farsi as one of the nine classical languages of India in our New Education Policy,\" Jaishankar said. Iran media reports said that 93 people lost their lives and over 200 were injured in the terror attack. Jaishankar and Hossein Amir-Abdollahian also discussed the \"concerning situation in Gaza\". \"We also exchanged perspectives and assessments on certain regional and global issues and developments. Both of us are concerned about recent events in West Asia, which some call the Middle East, and we emphasised the importance of preventing further escalation of violence and hostilities...India has a longstanding and uncompromising position against terrorism in all its forms and manifestations. This remains so...The deeply concerning situation in Gaza was naturally a subject of our discussions. \"There is a visible humanitarian crisis that needs to be addressed, and the creation of sustainable humanitarian corridors is the need of the day. We welcome the international community's efforts in that direction. India itself has delivered shipments of relief material to Gaza,\" Jaishankar said. \"On the issue of Palestine, let me reiterate India's long-standing support for a two-state solution where the Palestinian people are able to live freely in an independent country within secure borders. I stressed on the need for all parties to avoid provocative and escalatory actions and to facilitate movement towards dialogue and diplomacy,\" he added. The External Affairs Minister said there has been an increase in threats to the safety of maritime commercial traffic and it has a direct bearing on India's energy and economic interests. \"As you are all aware, there have also been recently a perceptible increase in threats to the safety of maritime commercial traffic in this important part of the Indian Ocean. The minister also referred to it. We have even seen some attacks in the vicinity of India. This is a matter of grave concern to the international community. Obviously, it also has a direct bearing on India's energy and economic interests. This situation is not to the benefit of any party, and this must be clearly recognised.\" \"I reiterated India's interest in benefiting from Iran's unique geographical position to access markets in Central Asia, Afghanistan and Eurasia. We discussed the prospects of energising the International North-South Transport Corridor. In particular, we discussed India's involvement in the development and operation of the Chabahar port, a joint project with a joint vision of connectivity,\" he added. (ANI)",
        "pubDate": "2024-01-15 16:42:54",
        "image_url": "https://static.businessworld.in/article/article_extra_large_image/1705335586_DO2nPP_GD4_ANLbsAAGs2A.jpg",
        "source_id": "businessworld",
        "source_url": "https://www.businessworld.in",
        "source_priority": 150658,
        "country": [
            "india"
        ],
        "category": [
            "top"
        ],
        "language": "english",
        "ai_tag": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment_stats": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN"
    },
    {
        "article_id": "5f1a2203abb081e72c0b652b981d5238",
        "title": "Naomi Osaka disappointed with 1st round exit but feels she is making progress",
        "link": "https://www.indiatoday.in/sports/tennis/story/australian-open-2024-naomi-osaka-first-round-exit-caroline-garcia-melbourne-2489070-2024-01-15?utm_source=rss",
        "keywords": null,
        "creator": null,
        "video_url": null,
        "description": "Naomi Osaka disappointed with 1st round exit but feels she is making progress",
        "content": "Listen to Story Naomi Osaka admitted that she was disappointed with her first round exit in the Australian Open 2024 but feels that she is making progress after her comeback to the court. Osaka, the former world No. 1 and four-time Grand Slam champion, faced a challenging return to major tennis at the Australian Open 2024. After a 15-month hiatus from the sport for mental health reasons and maternity leave following the birth of her daughter Shai, Osaka stepped onto Rod Laver Arena with high hopes but experienced an early exit. She was defeated in the first round by Caroline Garcia, the French 16th seed, with a scoreline of 6-4, 7-6 (7/2). Despite showing glimpses of her renowned power game, Osaka's performance was understandably rusty, and she struggled to find her rhythm against Garcia's aggressive baseline play. The match was tightly contested, with Garcia's consistent serving and Osaka's unforced errors proving decisive. This loss marked Osaka's third consecutive first-round defeat in Grand Slams, following similar outcomes at the French and US Opens in 2022. Speaking after the loss, as quoted by Reuters, Osaka felt that it was a good match and admitted she was a bit nervous while facing Garcia. The former world number one said that she felt she was doing better as the match went on. \"I thought it was a really good match. For me, I felt like I did the best that I could possibly do,\" she told reporters. \"I think I still feel like a bit disappointed ... I did feel nervous, but I felt like I kept telling myself to be positive. I thought I kept doing better as the match went on. \"So, yeah, I'm not too happy right now, but I think I can learn from the progress.\" Osaka further stated that she was overthinking a little bit in her head and constantly felt she was on the back foot during the match. \"I felt like I was constantly on my back foot and really hesitant,\" Osaka added. \"I was a little bit overthinking in my head where to go. I think that's something that will come in time, but right now it kind of sucks a little.\"",
        "pubDate": "2024-01-15 16:36:44",
        "image_url": "https://akm-img-a-in.tosshub.com/indiatoday/images/story/202401/australian-open-2024-153302286-16x9_0.jpg?VersionId=bgeb_yw5PYRVBBFxtGmGhRAZhzsFEprN",
        "source_id": "indiatoday",
        "source_url": "https://www.indiatoday.in",
        "source_priority": 2885,
        "country": [
            "india"
        ],
        "category": [
            "sports"
        ],
        "language": "english",
        "ai_tag": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment_stats": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN"
    },
    {
        "article_id": "d7973f1dadad5296a58a8c3690eacba2",
        "title": "Railway Security In Jammu Reviewed Ahead Of R-Day",
        "link": "https://kashmirobserver.net/2024/01/15/railway-security-in-jammu-reviewed-ahead-of-r-day/",
        "keywords": [
            "local",
            "railways",
            "republic day",
            "security"
        ],
        "creator": [
            "Press Trust of India"
        ],
        "video_url": null,
        "description": "Ahead of the Republic Day, the railway security in Jammu region was reviewed on Monday and police were directed to remain alert to avoid any untoward incident, officials said.",
        "content": "Ahead of the Republic Day, the railway security in Jammu region was reviewed on Monday and police were directed to remain alert to avoid any untoward incident, officials said. Senior Superintendent of Police (Railways), Jammu, Mohan Lal Kaith, chaired a high-level meeting and discussed operational preparedness of police at assets of railways in the region, they said. According to the officials, security measures taken for rail tracks, bridges, tunnels, trains and other installations were discussed in view of prevailing security scenario and forthcoming Republic Day. “All the officers were asked to remain alert to avoid any untoward incident in view of the Republic Day function on January 26,” an official said. The SSP (Railways) stressed upon augmenting the security measures on ground and remaining extra vigilant and alert. In the meeting, it was emphasised that round-the-clock patrolling be conducted jointly by officials of the Government Railway Police (GRP) and Railway Protection Force (RPF) so that it becomes more effective, the officials said. Kaith further said that maximum use of security equipment at railway stations be made and visitors be frisked thoroughly to rule out any act of sabotage. All station house officers (SHOs) and officers in-charge of police posts were directed by the SSP to personally conduct regular checking particularly during night hours to ensure that directions are implemented on ground in letter and spirit, the officials said. The SDPOs were directed to regularly supervise and monitor the performance of officers and personnel and any lapse be immediately brought to the notice of the SSP for taking action against the erring officials, they added. During the meeting, a review of intelligence inputs was also carried out.",
        "pubDate": "2024-01-15 16:36:21",
        "image_url": "https://kashmirobserver.net/wp-content/uploads/2023/01/Jammu-Railway-Station-Security.jpg",
        "source_id": "kashmirobserver",
        "source_url": "https://kashmirobserver.net",
        "source_priority": 596003,
        "country": [
            "india"
        ],
        "category": [
            "top"
        ],
        "language": "english",
        "ai_tag": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment_stats": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN"
    },
    {
        "article_id": "7619cc841aaee06ac45d0cf706257efc",
        "title": "Who Is Prakhar Chaturvedi? Son Of Techies, Teenager Breaks Yuvraj's Record",
        "link": "https://sports.ndtv.com/cricket/who-is-prakhar-chaturvedi-son-of-drdo-advisor-and-software-firm-owner-teenager-breaks-yuvraj-singhs-record-4868869",
        "keywords": null,
        "creator": null,
        "video_url": null,
        "description": "The teenager gave a lot of credit to his coach Karthik Jeshwant for his development as a cricketer.",
        "content": "Prakhar Chaturvedi sounded bushed, understandable if an 18-year-old bats 638 balls to score a record-setting 404, but elation was unmistakable too as he trained eyes on Karnataka U23 and Ranji Trophy debuts. On Monday, Chaturvedi became the first quadruple centurion in the Cooch Behar Trophy final as his knock earned Karnataka their maiden title in the U19 tournament. During his unbeaten knock, Chaturvedi also broke former India batter Yuvraj Singh's 24-year-old record of 358, the previous highest score in a Cooch Behar title clash. Riding on Chaturvedi's innings, Karnataka made 890 for eight in 223 overs, replying to Mumbai's 380 all out in the final at Shivamogga. “It is a great feeling. I am delighted that the innings came in the final, and helped Karnataka earn the first title (Cooch Behar). Feeling a bit tired but, yes, nothing can match the happiness of a team winning the title and you are contributing to it,” Chaturvedi told PTI. Chaturvedi comes from a household that has placed academics as the first priority as his father, Sanjay Kumar Chaturvedi, owns a software firm in Electronics City, while Rupa, his mother, is a technical advisor in DRDO. But that has not prevented them from allowing their son to pursue his heart's calling. “They have been very supportive of me from the time I took up cricket as an eight-year-old. They had even availed me of the services of a private throwdown expert during the (Covid-19) lockdown. Fortunately, I have been able to carry both cricket and studies together,” he added. Chaturvedi gave a lot of credit to his coach Karthik Jeshwant for his development as a cricketer. Jeshwant coaches Chaturvedi at the SIX Cricket Academy at the Padukone-Dravid Centre for Excellence here. “Jeshwant sir is technically very knowledgeable. He quickly spots even the minutest technical error in my batting. It is a blessing that I am able to work with a coach like him,” said Chaturvedi. Jeshwant, a former Karnataka captain, coach and selector, is an impressed man. “Prakhar is a dedicated boy. He keeps coming to the academy (near Devanahalli) from his home (Bellandur), which is quite a distance away. “Apart from his good technique, his biggest quality is his ability to handle pressure. He has a mature head on his shoulders, and hopefully we can see him climbing the ladders soon,” said Jeshwant. Former Karnataka opener and their current Under-19 coach KB Pawan has little doubt about Chaturvedi's progress in the game. “It is an outstanding achievement. He was the highest run-getter (in Cooch Behar) last year, but his time he was having a little tough time despite getting two hundreds. However, he never took too much pressure on himself, which was quite heartening as a coach. \"But he chose the perfect occasion, the final, and a fine opponent, Mumbai, to play such an innings. He is such a keen listener and coachable kid. I have been watching him from the U14 days, and the kid has the potential to play for Karnataka soon,” said Pawan. Pawan had a word of praise for the whole Karnataka bunch as well. “There has been this desire to win within the camp even before the tournament started. We completely dominated the tournament except for an average match against Uttar Pradesh. “There was a good vibe within the team coming into the final after our commanding wins in the quarterfinal and semifinal. I am sure many of these kids will soon don the Karnataka jersey,” he added. Chaturvedi's aim too is not different. “Now, I want to graduate to state U23 and then to Ranji Trophy at the earliest. Hopefully, I can tackle bigger challenges,” he noted. Karnataka management too will be hoping to have a closer look at Chaturvedi after they crashed to a stunning six-run defeat against Gujarat while chasing 110 at Motera, Ahmedabad. Perhaps, Chaturvedi's climb will start much sooner than even he envisages.",
        "pubDate": "2024-01-15 16:36:14",
        "image_url": "https://c.ndtvimg.com/2024-01/m6kcfl88_prakhar-chaturvedi_625x300_15_January_24.jpg",
        "source_id": "ndtv",
        "source_url": "http://www.ndtv.com",
        "source_priority": 1204,
        "country": [
            "india"
        ],
        "category": [
            "sports"
        ],
        "language": "english",
        "ai_tag": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment_stats": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN"
    },
    {
        "article_id": "8bfe5083aab987b1b1b8721804c0ba83",
        "title": "Have we taken the rice water skincare trend a little too far?",
        "link": "https://www.vogue.in/content/have-you-been-using-rice-water-for-skincare-read-this",
        "keywords": [
            "lifestyle",
            "beauty",
            "style",
            "skin care",
            "trends"
        ],
        "creator": [
            "VOGUE India",
            "Vogue India"
        ],
        "video_url": null,
        "description": "Currently on the skincare trend radar? Rice water for your skin. While not a new one, rice water has paved its way back into the spotlight. Thanks to …",
        "content": "Currently on the skincare trend radar? . While not a new one, rice water has paved its way back into the spotlight. Thanks to the glass water trend, this Asian ingredient staple has become a beloved skincare ingredient for many. From toners to cleansers to masks, rice water has played an imperative role in a lot of DIYs. But, are we giving a little too much importance to this ingredient or is it worth the hype? We asked an expert. “ contains a medley of nutrients like vitamins, minerals, and amino acids, that can offer potential benefits to the skin,” Dr Manasi Shirolikar, founder of drmanasiskin.com. “The presence of inositol, a carbohydrate, may contribute to skin health by promoting strength and elasticity.” The traditional Asian ingredient hosts a plethora of antioxidants like ferulic and phytic acids—according to the expert, these help combat oxidative stress, which addresses the signs of ageing too. It also contains amino acids, vitamins A and E, and minerals. Additionally, rice water is also hydrating, which means that it aids in moisturising the skin, reducing the appearance of fine lines, wrinkles, and . While it clearly hosts many skin loving properties, Dr Shirolikar does state that the scientific evidence supporting its efficacy is limited. The use of rice water, its benefits, and its potential side effects is all subjective and varies between individuals. “While some may experience positive changes in skin texture, others may not,” she explains. “Concentration variations in homemade rice can make predicting absorption challenging.” According to the expert, it is safe for most , but caution is advised—allergies or irritations could occur, requiring a patch test before use. “Individuals should be vigilant not to leave rice water at room temperature for extended periods, as it may spoil. And, it's not a substitute for a skincare routine. Consulting with a dermatologist is advisable, especially for those with sensitive skin,” she advises. According to the expert, you can soak rice and use the strained liquid as a gentle toner or a DIY face mask. She believes that a is better for daily and consistent usage. “It’s essential to avoid leaving rice water at room temperature for more than 24 hours to preserve its benefits. Several rice water-based over-the-counter products can be used.” Always start with a patch test to ensure compatibility and monitor for adverse reactions.",
        "pubDate": "2024-01-15 16:35:09",
        "image_url": "https://assets.vogue.in/photos/65a55badb09f639db2a3d9d3/3:4/w_2560%2Cc_limit/Snapinsta.app_160217029_863153814250618_6511185686176497293_n_1080.jpg",
        "source_id": "vogue",
        "source_url": "https://www.vogue.com",
        "source_priority": 4162,
        "country": [
            "india"
        ],
        "category": [
            "entertainment"
        ],
        "language": "english",
        "ai_tag": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment_stats": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN"
    },
    {
        "article_id": "5467fc443b305274c73e9d97fbcb532b",
        "title": "An international body will need to oversee AI regulation, but we need to think carefully about what it looks like",
        "link": "https://techxplore.com/news/2024-01-international-body-oversee-ai.html",
        "keywords": [
            "business"
        ],
        "creator": null,
        "video_url": null,
        "description": "Artificial intelligence (AI) will have serious societal impact globally. So it is more urgent than ever that state leaders cooperate to regulate the technology.",
        "content": "Artificial intelligence (AI) will have serious societal impact globally. So it is more urgent than ever that state leaders cooperate to regulate the technology. There have been various calls already: the Bletchley Declaration at a recent UK summit and the 11 AI principles and code of conduct agreed on by G7 leaders , for example. But these largely state the obvious. The real question is not whether international cooperation on AI is needed, but how can it be realized? The most obvious way to secure this in a way that maximizes the benefits of AI, and puts in \"guardrails\"—controls—to manage the significant risks posed, is to set up an intergovernmental body. Indeed, one idea is to create a World Technology Organization . Others advocate for a body similar to the International Atomic Energy Agency (IAEA), drawing a comparison between AI and nuclear weapons in terms of the risks posed. Another view is to develop an institutional framework inspired by entities such as Cern, the Human Genome Project, or the International Space Station (ISS). However, creating an AI or technology-specific international organization, whatever it may be called, faces three particularly difficult challenges. First, with AI being a dual use technology—which means it is capable of being deployed for both peaceful and military purposes—it is unlikely that the major powers would be willing to come together to form a global institution that can meaningfully police its development and use. The so called chip war between the US and China is in full flow. AI technology is also the subject of intense geopolitical competition. The friction between the major powers creates serious hurdles for international cooperation on AI in particular. In fact, existing international institutions built following the Second World War are already structurally affected by interstate friction. For example, the UN Security Council continues to be paralyzed on the greatest controversies of international concern today. The Appellate Body of the World Trade Organization, one of the most successful international mechanisms for adjudicating on trade issues in the past, is currently dysfunctional because the US refuses to endorse judicial appointments to it. But, even before its demise, I argued that it encountered significant structural deficits . The major international financial institutions are also facing serious governance challenges. The G20 leaders recently called for reforms to the World Bank and the International Monetary Fund (IMF) and for their roles to become more clearly defined. With existing international institutions in crisis, it is hard to imagine that a stand-alone international organization to regulate AI can be created any time soon. Second, even if the international community somehow agrees to create an AI or tech-specific regulating body, the question remains what will this organization actually do? Would an AI-focused organization seek to enhance scientific cooperation between different research groups, or will it try to coordinate AI regulation across countries. Would any such organization create a monitoring regime to ensure that only human-centric, trustworthy and responsible AI is developed? How would such a regime come into operation and carry out enforcement? Would it also be mandated to help developing and the least developed countries realize AI's full potential? Sovereignty concerns, national security , perceived national interest and, ultimately, different approaches taken to AI, mean that reaching a valuable consensus on what such an organization should do is likely to remain elusive for some time. Already, we see different choices made on AI regulatory frameworks and deployment. While the EU's AI act outlaws social scoring and real-time facial recognition , authoritarian states take a different approach. It is thus important not to get carried away by generalized statements by the international community, giving the impression that an international law on AI may be emerging . Hardly anyone would disagree that society needs to be protected from the risks posed by AI. Its deployment should not undermine human rights and it should be safe and trustworthy. But it is the translation of such generalized principles into specific commitments made in international law that poses a significant challenge. Risk assessments on AI tools may yield distinct results depending on who carries them out. Which rights ought to be prioritized—individual rights versus security interests—might differ across different countries. So would what constitutes ethical forms of AI. The third main challenge in creating an international overseeing body relates to the institutional character that ought to be adopted. This includes the role that the private sector is given in any governance framework. Given the very significant role of the private sector in developing and deploying AI tools, a joint public-private governance model may be the only realistic option. Presently, it is countries that are the central actors in the international community. Incorporating private companies into an international governance structure that generally favors nations over everything else could pose problems. That's a challenge that must be overcome before such an organization is created. Finally, international cooperation on AI already exists to some extent. Organizations, including the OECD , UNESCO and the International Organization for Standardization have already developed recommendations or standards in the spheres of their expertise. Other bodies, such as the International Labor Organization and the World Health Organization have started to consider the impact of AI in their mandates. The UN has also established a High-Level Advisory Body on AI to undertake analysis and advance recommendations for the international governance of this technology. It is too early to conclude whether this fragmented approach can lead to a well-thought-out and coordinated response. Until the circumstances are right for creating a standalone AI-focused international organization, what is almost certain is that powerful actors, such as the US—where most tech companies are based—and the European Union's AI Act will have an outsized influence on the content of AI regulation and governance globally. This article is republished from The Conversation under a Creative Commons license. Read the original article .",
        "pubDate": "2024-01-15 16:35:05",
        "image_url": "https://scx1.b-cdn.net/csz/news/tmb/2019/8-ai.jpg",
        "source_id": "phys",
        "source_url": "https://phys.org",
        "source_priority": 6461,
        "country": [
            "australia",
            "india",
            "united states of america",
            "united kingdom",
            "singapore"
        ],
        "category": [
            "technology"
        ],
        "language": "english",
        "ai_tag": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN",
        "sentiment_stats": "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLAN"
    }
 */