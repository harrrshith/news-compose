package com.harshith.news.model

import com.google.gson.Gson
import com.harshith.news.model.NewsArticle

val newsResponseString = """
    {
        -"source": {
        "id": null,
        "name": "Gizmodo.com"
        },
        "author": "Rhett Jones",
        "title": "The Greatest App of All Time: March Madness Bracket Day 2",
        "description": "It’s day 2 of Gizmodo’s March Madness bracket challenge to crown the greatest app of all time! The mighty Fart App has fallen to Instagram in our first round and today’s challengers are WhatsApp vs Dark Sky. Will Mark Zuckerberg win again? That’s up to you. R…",
        "url": "https://gizmodo.com/the-greatest-app-of-all-time-march-madness-bracket-day-1851303211",
        "urlToImage": "https://i.kinja-img.com/image/upload/c_fill,h_675,pg_1,q_80,w_1200/43cc4ab1618b4ed4b74a428bd46f040e.jpg",
        "publishedAt": "2024-03-02T18:02:00Z",
        "content": "Its day 2 of Gizmodos March Madness bracket challenge to crown the greatest app of all time! The mighty Fart App has fallen to Instagram in our first round and todays challengers are WhatsApp vs Dark… [+1765 chars]"
    }
""".trimIndent()

val newsArticle: NewsArticle = Gson().fromJson(newsResponseString, NewsArticle::class.java)