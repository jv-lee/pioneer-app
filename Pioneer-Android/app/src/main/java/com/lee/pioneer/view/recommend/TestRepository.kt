package com.lee.pioneer.view.recommend

/**
 * @author jv.lee
 * @date 2020/8/5
 * @description
 */
class TestRepository {
    companion object {

        fun testMall(): ArrayList<Recommend> {
            return arrayListOf<Recommend>().apply {
                //添加bannerList数据
                add(Recommend("banner", ViewStyle.BANNER, banners = ArrayList<Banner>().apply {
                    add(
                        Banner(
                            0,
                            KeyConstants.bannerUrl,
                            "title",
                            AppNavigation(
                                "com.stary.recommend.ui.activity.NavigationActivity",
                                params = "{'arg_path':0}"
                            )
                        )
                    )
                    add(
                        Banner(
                            0,
                            KeyConstants.bannerUrl,
                            "title"
                        )
                    )
                    add(
                        Banner(
                            0,
                            KeyConstants.bannerUrl,
                            "title"
                        )
                    )
                    add(
                        Banner(
                            0,
                            KeyConstants.bannerUrl,
                            "title"
                        )
                    )
                }))

                //添加icon数据
                add(Recommend("banner", ViewStyle.ICON, banners = ArrayList<Banner>().apply {
                    add(
                        Banner(
                            0,
                            KeyConstants.iconUrl,
                            "Ranking",
                            AppNavigation(
                                "com.stary.recommend.ui.activity.NavigationActivity",
                                params = "{'arg_path':0}"
                            )
                        )
                    )
                    add(
                        Banner(
                            0,
                            KeyConstants.iconUrl,
                            "Genres",
                            AppNavigation(
                                "com.stary.recommend.ui.activity.NavigationActivity",
                                params = "{'arg_path':3}"
                            )
                        )
                    )
                    add(
                        Banner(
                            0,
                            KeyConstants.iconUrl,
                            "Update"
                        )
                    )
                    add(
                        Banner(
                            0,
                            KeyConstants.iconUrl,
                            "Completed"
                        )
                    )
                }))

                //添加普通数据
                add(
                    Recommend(
                        "Editors Recommend",
                        ViewStyle.COMMON,
                        comics = ArrayList<Comic>().apply {
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                        })
                )

                //添加上升数据
                add(Recommend("Rising Trend", ViewStyle.RISING, comics = ArrayList<Comic>().apply {
                    add(
                        Comic(
                            "1",
                            "Start being a star today",
                            "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                            "Author Wook",
                            200,
                            KeyConstants.coverUrl,
                            Category("1", "happy"),
                            ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                            "2020/8/15",
                            0,
                            arrayListOf(),
                            1
                        )
                    )
                    add(
                        Comic(
                            "1",
                            "Start being a star today",
                            "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                            "Author Wook",
                            200,
                            KeyConstants.coverUrl,
                            Category("1", "happy"),
                            ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                            "2020/8/15",
                            0,
                            arrayListOf(),
                            1
                        )
                    )
                    add(
                        Comic(
                            "1",
                            "Start being a star today",
                            "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                            "Author Wook",
                            200,
                            KeyConstants.coverUrl,
                            Category("1", "happy"),
                            ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                            "2020/8/15",
                            0,
                            arrayListOf(),
                            1
                        )
                    )
                    add(
                        Comic(
                            "1",
                            "Start being a star today",
                            "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                            "Author Wook",
                            200,
                            KeyConstants.coverUrl,
                            Category("1", "happy"),
                            ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                            "2020/8/15",
                            0,
                            arrayListOf(),
                            1
                        )
                    )
                    add(
                        Comic(
                            "1",
                            "Start being a star today",
                            "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                            "Author Wook",
                            200,
                            KeyConstants.coverUrl,
                            Category("1", "happy"),
                            ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                            "2020/8/15",
                            0,
                            arrayListOf(),
                            1
                        )
                    )
                    add(
                        Comic(
                            "1",
                            "Start being a star today",
                            "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                            "Author Wook",
                            200,
                            KeyConstants.coverUrl,
                            Category("1", "happy"),
                            ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                            "2020/8/15",
                            0,
                            arrayListOf(),
                            1
                        )
                    )

                }))

                //添加普通数据
                add(
                    Recommend(
                        "Editors Recommend",
                        ViewStyle.COMMON,
                        comics = ArrayList<Comic>().apply {
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                        })
                )

                //添加tag数据
                add(Recommend("Hot Tag", ViewStyle.TAG, tags = ArrayList<Tag>().apply {
                    add(Tag("1", "CEO"))
                    add(Tag("1", "Action"))
                    add(Tag("1", "Drama"))
                    add(Tag("1", "BL"))
                    add(Tag("1", "Romance"))
                    add(Tag("1", "Fantasy"))
                    add(Tag("1", "GL"))
                    add(Tag("1", "Comedy"))
                }))

                //添加普通数据
                add(
                    Recommend(
                        "Editors Recommend",
                        ViewStyle.COMMON,
                        comics = ArrayList<Comic>().apply {
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                            add(
                                Comic(
                                    "1",
                                    "Start being a star today",
                                    "Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a ,Under the persecution of her wicked sister and stepmother, how can Yin Xiaomo, the modern Cinderella, break through the numerous obstacles a",
                                    "Author Wook",
                                    200,
                                    KeyConstants.coverUrl,
                                    Category("1", "happy"),
                                    ArrayList<Tag>().apply { add(Tag("1", "Drama / Comedy")) },
                                    "2020/8/15",
                                    0,
                                    arrayListOf(),
                                    1
                                )
                            )
                        })
                )
            }
        }

    }

}