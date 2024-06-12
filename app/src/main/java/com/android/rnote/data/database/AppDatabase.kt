package com.android.rnote.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.android.rnote.R
import com.android.rnote.data.database.dao.DailyActivityDao
import com.android.rnote.data.database.dao.FriendDao
import com.android.rnote.data.database.dao.GalleryDao
import com.android.rnote.data.database.dao.InterestDao
import com.android.rnote.data.database.dao.MusicFavoriteDao
import com.android.rnote.data.database.dao.ProfileDao
import com.android.rnote.data.database.dao.VideoFavoriteDao
import com.android.rnote.data.model.DailyActivity
import com.android.rnote.data.model.Friend
import com.android.rnote.data.model.Gallery
import com.android.rnote.data.model.Interest
import com.android.rnote.data.model.MusicFavorite
import com.android.rnote.data.model.Profile
import com.android.rnote.data.model.VideoFavorite
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Profile::class,
        Interest::class,
        DailyActivity::class,
        Friend::class,
        Gallery::class,
        MusicFavorite::class,
        VideoFavorite::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun interestDao(): InterestDao
    abstract fun dailyActivityDao(): DailyActivityDao
    abstract fun friendDao(): FriendDao
    abstract fun galleryDao(): GalleryDao
    abstract fun musicFavoriteDao(): MusicFavoriteDao
    abstract fun videoFavoriteDao(): VideoFavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addCallback(DatabaseCallback(context))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback(private val context: Context) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(getDatabase(context).profileDao(), getDatabase(context).interestDao(), getDatabase(context).galleryDao(), getDatabase(context).dailyActivityDao(), getDatabase(context).friendDao(), getDatabase(context).musicFavoriteDao(), getDatabase(context).videoFavoriteDao())
                }
            }
        }


        fun populateDatabase(profileDao: ProfileDao, interestDao: InterestDao, galeryDao: GalleryDao, dailyActivityDao: DailyActivityDao, friendDao: FriendDao, musicFavoriteDao: MusicFavoriteDao, videoFavoriteDao: VideoFavoriteDao) {

            val profile = Profile(
                name= "R",
                photoUrl = "https://hips.hearstapps.com/hmg-prod/images/barry-keoghan-65dc3c3c8b92f.jpg?crop=1xw:0.9140625xh;center,top&resize=640:*",
                description = "Saya adalah seorang mahasiswa jurusan Informatika pada Universitas Apel Pisang Jeruk, Programming telah menjadi passion saya semenjak saya kecil. Melalui programming, saya bisa menciptakan program yang dapat bermanfaat dan membantu banyak orang. ",
                phoneNumber = "123-456-7890",
                email = "r@example.com",
                socialMediaLink = "https://www.instagram.com/keoghan92/",
                latitude = -6.200000,
                longitude = 106.816666
            )
            profileDao.insert(profile)

            val makes = listOf(
                Pair("https://assets.unileversolutions.com/recipes-v2/242794.jpg", "Nasi Goreng"),
                Pair("https://img-global.cpcdn.com/recipes/708b7340ea7172fb/680x482cq70/pecel-ayam-sambal-tomat-foto-resep-utama.jpg", "Pecel Ayam"),
                Pair("https://cdn.idntimes.com/content-images/community/2023/11/snapinstaapp-343290591-3424751674509118-7364340412995712991-n-1080-66934c0cbeade5e56b2fec27e2f98983-6d81d4d1059fe4aa78161c2d747dba4d_600x400.jpg", "Martabak")
            )
            val gson = Gson()
            val jsonStringMakes = gson.toJson(makes)

            val mikes = listOf(
                Pair("https://assets.epicurious.com/photos/629f98926e3960ec24778116/4:3/w_4658,h_3493,c_limit/BubbleTea_RECIPE_052522_34811.jpg", "Milk Tea Boba"),
                Pair("https://dinnerthendessert.com/wp-content/uploads/2023/10/Caramel-Macchiato-7.jpg", "Caramel Macchiato")
            )
            val jsonStringMikes = gson.toJson(mikes)

            val interests = listOf(
                Interest(hobby = "Programming", makes = jsonStringMakes, mikes = jsonStringMikes, interest = "Technology", goal = "Software Developer"),
            )
            interests.forEach { interestDao.insert(it) }

            val gallerys = listOf(
                Gallery(0, "https://resizing.flixster.com/TG2dQRRH6wz_hShvN86hqUPh0PA=/218x280/v2/https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/738434_v9_bd.jpg"),
                Gallery(0, "https://m.media-amazon.com/images/M/MV5BMTcyNzEzMjY4NV5BMl5BanBnXkFtZTgwOTMzOTA5MjI@._V1_.jpg"),
                Gallery(0, "https://media.glamour.com/photos/65ef1d8920afb8e33c086e70/master/w_2560%2Cc_limit/2074972256"),
                Gallery(0, "https://www.nbc.com/sites/nbcblog/files/styles/scale_862/public/2024/05/sabrina-carpenter-barry-keoghan-1.jpg"),
                Gallery(0, "https://s.yimg.com/ny/api/res/1.2/MRG3kz94WyLb9ABQ7EUmCQ--/YXBwaWQ9aGlnaGxhbmRlcjt3PTY0MDtoPTQyNw--/https://media.zenfs.com/en/entertainment_weekly_785/3964eced68208f862e627368a7eb6d4f"),
                Gallery(0, "https://hips.hearstapps.com/hmg-prod/images/barry-keoghan-films-and-tv-shows-65a52aa77543f.jpg?crop=0.476xw:0.749xh;0.292xw,0.203xh&resize=1200:*"),
                Gallery(0, "https://hollywoodlife.com/wp-content/uploads/2022/03/barry-keoghan-5-things-shutter-embed-1.jpg"),
                Gallery(0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGuNgW5UgTIPbZ7RpwME2CEIb-IaYEMPOaVA&usqp=CAU"),
                Gallery(0, "https://media.gq.com/photos/65a00a7a4d1c79d41bcbe1aa/16:9/w_2560%2Cc_limit/1924138704"),
                Gallery(0, "https://www.redcarpet-fashionawards.com/wp-content/uploads/2024/01/Barry-Keoghan-Wore-Dolce-Gabbana-To-The-Masters-Of-The-Air-LA-Premiere.jpg"),
                Gallery(0, "https://news.harvard.edu/wp-content/uploads/2024/02/020224_Hasty_MOY_Roast_1090.jpg"),
                Gallery(0, "https://www.comingsoon.net/wp-content/uploads/sites/3/2024/01/Barry-Keoghan-Masters-of-the-Air.jpg"),
                Gallery(0, "https://people.com/thmb/DJYEmT75TaNsI4K5EqWm3gv6qBY=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc():focal(749x0:751x2)/barry-keoghan-AFI-awards-luncheon-012623-1-c7b9fe0736a94298a38c43397d5c49cb.jpg"),
                Gallery(0, "https://fashionista.com/.image/t_share/MjAzNTA4MTE1Njc5NzQ5NDY4/gq-feb-2024-barry-keoghan.jpg"),

            )

            gallerys.forEach { galeryDao.insert(it) }

            val dailyActivities = listOf(
                DailyActivity(0, "Olahraga","Workout ringan dirumah","https://d1vbn70lmn1nqe.cloudfront.net/prod/wp-content/uploads/2021/06/15012412/kesehatan-olahraga.jpg.webp"),
                DailyActivity(0, "Gaming","Bermain game bersama teman-teman","https://awsimages.detik.net.id/community/media/visual/2023/07/27/ilustrasi-bermain-game-online_169.jpeg?w=600&q=90"),
                DailyActivity(0, "Bebersih","Membantu keluarga membersihkan rumah","https://asset.kompas.com/crops/3aSQd4MsrYYnDWNqJO0ve9e_sPw=/0x0:1000x667/750x500/data/photo/2021/07/17/60f2dd339c138.jpg"),
                DailyActivity(0, "Memasak","Belajar membuat berbagai menu baru","https://i0.wp.com/resepkoki.id/wp-content/uploads/2017/01/memasak.png?fit=600%2C398&ssl=1"),
                DailyActivity(0, "Menonton","Menonton berbagai video baru di youtube","https://awsimages.detik.net.id/community/media/visual/2021/07/21/ilustrasi-streaming-youtube_169.jpeg?w=600&q=90"),
                DailyActivity(0, "Berbelanja","Belanja berbagai kebutuhan sehari-hari","https://rencanamu.id/assets/file_uploaded/blog/1464766124-shopping-t.jpg"),
            )

            dailyActivities.forEach { dailyActivityDao.insert(it) }

            val friendList = listOf(
                Friend(0, "Sabrina Carpenter", "https://assets.teenvogue.com/photos/663974e97d8dca2e35231c93/1:1/w_3333,h_3333,c_limit/2151797613"),
                Friend(0, "Jacob Elordi", "https://assets.teenvogue.com/photos/6547be41f653333fc6b89a57/1:1/w_2000,h_2000,c_limit/1743130997"),
                Friend(0, "Taika Waititi", "https://www.hollywoodreporter.com/wp-content/uploads/2023/11/Taika-Waititi-getty-H-2023-1.jpg"),
                Friend(0, "Colin Farrell", "https://m.media-amazon.com/images/M/MV5BMTg4NzM5NDk0MV5BMl5BanBnXkFtZTcwNzAzMTUxNw@@._V1_.jpg"),
                Friend(0, "Robert Pattinson", "https://m.media-amazon.com/images/M/MV5BNzk0MDQ5OTUxMV5BMl5BanBnXkFtZTcwMDM5ODk5Mg@@._V1_FMjpg_UX1000_.jpg")
                )

            friendList.forEach { friendDao.insert(it) }

            val musicList = listOf(
                MusicFavorite(0, "Mysterious Messenger","DoubleTO", R.raw.mysterious),
                MusicFavorite(0, "Bling-Bang-Bang-Born","Creepy Nuts", R.raw.mashle),
                MusicFavorite(0, "Mozaik Kakera","SunSet Swish", R.raw.mosaic),
                MusicFavorite(0, "夕暮れの鳥 (TV size)","Shinsei Kamattechan", R.raw.aot),
                MusicFavorite(0, "Again","Yui", R.raw.fma)

            )

            musicList.forEach { musicFavoriteDao.insert(it) }

            val videoList = listOf(
                VideoFavorite(0, "Bunguo Stray Dogs Season 2 Op 2 ",R.raw.bsd),
            )

            videoList.forEach { videoFavoriteDao.insert(it) }

        }
    }
}
