package com.example.socialnetwork.model

import com.example.socialnetwork.R

data class PostData(
    val postId: Int,
    val postLogo: Int,
    val postImage: Int,
    val postAuthor: String,
    val postLike: Int,
    val postDescription: String,
    val postComments: String,
    val postTime: String
)
object SetPostData {

    val firstPostData = PostData(
        1,
        R.drawable.mes,
        R.drawable.messi,
        "leomessi",
        213123,
        "It was good game",
        "Посмотреть все комментарии (589)",
        "5 часов назад"
    )
    val secondPostData = PostData(
        2,
        R.drawable.nine_gag,
        R.drawable.nine_gag_image_2,
        "9gag",
        11231230,
        "I skipped right to week 5.",
        "Посмотреть все комментарии (2 433)",
        "6 часов назад"
    )
    val thirdPostData = PostData(
        3,
        R.drawable.kaznews,
        R.drawable.kaznews_image,
        "kaznews",
        113343,
        "Нариман Мукушев, занимающий должность вице-министра труда и соцзащиты," +
                " ответил на три вопроса о соцпособии в 42500 тенге, " +
                "которые чаще всего возникают у казахстанцев.,",
        "Посмотреть все комментарии (384)",
        "7 часов назад"
    )
    val fourthPostData = PostData(
        4,
        R.drawable.nine_gag,
        R.drawable.nine_gag_image,
        "9gag",
        213123,
        "My everyday struggle",
        "Посмотреть все комментарии (2 605)",
        "12 часов назад"
    )
    val fifthPostData = PostData(
        5,
        R.drawable.qazaqstories,
        R.drawable.qazaqstories_image,
        "qazaqstories",
        12345,
        "У всех так?",
        "Посмотреть все комментарии (109)",
        "день назад"
    )
    val sixthPostData = PostData(
        6,
        R.drawable.tut_slovar,
        R.drawable.tut_slovar_image,
        "tut_slovar",
        32345,
        "Узурпатор...",
        "Посмотреть все комментарии (73)",
        "день назад"
    )
    val seventhPostData = PostData(
        7,
        R.drawable.hyperpc,
        R.drawable.hyperpc_image,
        "hyperpc",
        1232145,
        "Кто-то уже успел увидеть еще вчера, а кто-то догадается \uD83D\uDE07\n" +
                "Хотите купить готовый компьютер или подбираете индивидуальную комплектацию?",
        "Посмотреть все комментарии (239)",
        "день назад"
    )
    val eighthPostData = PostData(
        8,
        R.drawable.banggood,
        R.drawable.banggood_image,
        "banggood",
        1232145,
        "Elephone’s newest gem is about to enter the market. " +
                "Let’s all come the beautifully designed E10 featuring " +
                "a long-lasting 4000mAh, NFC, " +
                "a 6.5” display, the latest version of Android 10.0" +
                " and a 48MP quad camera ID: 1661187",
        "Посмотреть все комментарии (339)",
        "день назад"
    )
    val ninthPostData = PostData(
        9,
        R.drawable.qazaqstories,
        R.drawable.qazaqstories_image_2,
        "qazaqstories",
        23554,
        "Жизағо \uD83D\uDE02",
        "Посмотреть все комментарии (49)",
        "день назад"
    )
    val tenthPostData = PostData(
        10,
        R.drawable.tut_slovar,
        R.drawable.tut_slovar_image_2,
        "tut_slovar",
        1232145,
        "#боке",
        "Посмотреть все ком ментарии (9)",
        "день назад"
    )
}