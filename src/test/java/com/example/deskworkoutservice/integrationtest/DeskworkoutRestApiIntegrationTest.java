package com.example.deskworkoutservice.integrationtest;

import com.example.deskworkoutservice.DeskworkoutserviceApplication;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(classes = DeskworkoutserviceApplication.class)
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeskworkoutRestApiIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    //Read処理の結合テスト
    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @Transactional
    void nullのクエリ文字列を渡したときに全件取得できること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/deskworkouts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        [
                            {
                                "id": 1,
                                "name": "シットアップ",
                                "howto": "直角に曲げた膝を机裏にタッチする",
                                "repetition": 10,
                                "bodyParts": "お腹",
                                "difficulty": "初級"
                            },
                            {
                                "id": 2,
                                "name": "バックエクステンション",
                                "howto": "お尻を背もたれに付けたまま、デコルテを机に近づける",
                                "repetition": 10,
                                "bodyParts": "背中",
                                "difficulty": "中級"
                            },
                            {
                                "id": 3,
                                "name": "ショルダーダウン",
                                "howto": "肘で背もたれを押したまま、肩を引き下げる",
                                "repetition": 1,
                                "bodyParts": "肩",
                                "difficulty": "初級"
                            },
                            {
                                "id": 4,
                                "name": "ニーエクステンション",
                                "howto": "内ももをくっ付けたまま、膝を伸ばす",
                                "repetition": 10,
                                "bodyParts": "脚",
                                "difficulty": "初級"
                            },
                            {
                                "id": 5,
                                "name": "スクワット",
                                "howto": "片膝を斜め横に向けたまま、お尻を椅子から持ち上げる",
                                "repetition": 10,
                                "bodyParts": "お尻",
                                "difficulty": "上級"
                            }
                        ]
                        """, true));
    }

    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @Transactional
    void 空のクエリ文字列を渡したときに全件取得できること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/deskworkouts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        [
                            {
                                "id": 1,
                                "name": "シットアップ",
                                "howto": "直角に曲げた膝を机裏にタッチする",
                                "repetition": 10,
                                "bodyParts": "お腹",
                                "difficulty": "初級"
                            },
                            {
                                "id": 2,
                                "name": "バックエクステンション",
                                "howto": "お尻を背もたれに付けたまま、デコルテを机に近づける",
                                "repetition": 10,
                                "bodyParts": "背中",
                                "difficulty": "中級"
                            },
                            {
                                "id": 3,
                                "name": "ショルダーダウン",
                                "howto": "肘で背もたれを押したまま、肩を引き下げる",
                                "repetition": 1,
                                "bodyParts": "肩",
                                "difficulty": "初級"
                            },
                            {
                                "id": 4,
                                "name": "ニーエクステンション",
                                "howto": "内ももをくっ付けたまま、膝を伸ばす",
                                "repetition": 10,
                                "bodyParts": "脚",
                                "difficulty": "初級"
                            },
                            {
                                "id": 5,
                                "name": "スクワット",
                                "howto": "片膝を斜め横に向けたまま、お尻を椅子から持ち上げる",
                                "repetition": 10,
                                "bodyParts": "お尻",
                                "difficulty": "上級"
                            }
                        ]
                        """));
    }

    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @Transactional
    void 存在しないクエリ文字列を指定したときに空の配列が返ること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/deskworkouts?=bodyParts=腕"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @Transactional
    void 正常なクエリ文字列を渡したときそのレコードを取得できること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/deskworkouts?bodyParts=お腹"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        [
                            {
                                "id": 1,
                                "name": "シットアップ",
                                "howto": "直角に曲げた膝を机裏にタッチする",
                                "repetition": 10,
                                "bodyParts": "お腹",
                                "difficulty": "初級"
                            }
                        ]
                        """, true));
    }

    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @Transactional
    void 存在するIDを指定したときにそのレコードを取得できること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/deskworkouts/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "id": 2,
                            "name": "バックエクステンション",
                            "howto": "お尻を背もたれに付けたまま、デコルテを机に近づける",
                            "repetition": 10,
                            "bodyParts": "背中",
                            "difficulty": "中級"
                        }
                        """, true));
    }

    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @Transactional
    void 存在しないIDを指定したとき例外が発生すること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/deskworkouts/100"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                //JSONボディ内のstatusフィールドが404であることを確認
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("404"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Workout with id:100 not found"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Not Found"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.path").value("/deskworkouts/100"));
    }

    //Create処理の結合テスト
    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @ExpectedDataSet(value = "expected-datasets/deskworkouts-after-insert.yml", ignoreCols = "id")
    @Transactional
    void 新しいレコードを登録できること() throws Exception {
        String newDeskworkout = """
                {
                    "name": "new name",
                    "howto": "new howTo",
                    "repetition": 5,
                    "bodyParts": "new bodyParts",
                    "difficulty": "new level"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/deskworkouts")
                        .contentType(APPLICATION_JSON)
                        .content(newDeskworkout))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "message": "deskworkout created"
                        }
                        """));
    }

    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @Transactional
    void 不正なデータを登録したときに例外が発生すること() throws Exception {
        String invalidDeskworkout = """
                {
                    "name": "あいうえおかきくけこさしすせそたちつてと",
                    "howto": "　",
                    "repetition": -1,
                    "bodyParts": " ",
                    "difficulty":""
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/deskworkouts")
                        .contentType(APPLICATION_JSON)
                        .content(invalidDeskworkout))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "repetition": "レップ数は自然数で入力してください",
                            "bodyParts": "空白は許可されていません",
                            "difficulty": "空白は許可されていません"
                        }
                        """, true));
    }

    //Update処理の結合テスト
    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @ExpectedDataSet(value = "expected-datasets/deskworkouts-after-update.yml")
    @Transactional
    void 存在するIDを指定したときにそのレコードを更新できること() throws Exception {
        String newDeskworkout = """
                {
                    "name": "new name",
                    "howto": "new howTo",
                    "repetition": 5,
                    "bodyParts": "new bodyParts",
                    "difficulty": "new level"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.patch("/deskworkouts/1")
                        .contentType(APPLICATION_JSON)
                        .content(newDeskworkout))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "message": "deskworkout updated"
                        }
                        """));
    }

    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @Transactional
    void 存在しないIDを指定したときに例外が発生し更新できないこと() throws Exception {
        String notFoundDeskworkout = """
                {
                    "name": "new name",
                    "howto": "new howTo",
                    "repetition": 5,
                    "bodyParts": "new bodyParts",
                    "difficulty": "new level"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.patch("/deskworkouts/100")
                        .contentType(APPLICATION_JSON)
                        .content(notFoundDeskworkout))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("404"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Workout with id:100 not found"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Not Found"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.path").value("/deskworkouts/100"));
    }

    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @Transactional
    void 不正なデータを更新したときに例外が発生する() throws Exception {
        String invalidDeskworkout = """
                {
                    "name": "あいうえおかきくけこさしすせそたちつてと",
                    "howto": "　",
                    "repetition": -1,
                    "bodyParts": " ",
                    "difficulty":""
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.patch("/deskworkouts/2")
                        .contentType(APPLICATION_JSON)
                        .content(invalidDeskworkout))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "repetition": "レップ数は自然数で入力してください",
                            "bodyParts": "空白は許可されていません",
                            "difficulty": "空白は許可されていません"
                        }
                        """, true));
    }

    //Delete処理の結合テスト
    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @ExpectedDataSet(value = "expected-datasets/deskworkouts-after-delete.yml")
    @Transactional
    void 存在するIDを指定したときにそのレコードを削除できること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/deskworkouts/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "message": "deskworkout deleted"
                        }
                        """));
    }

    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @Transactional
    void 存在しないIDを指定したときに例外が発生しレコードを削除できないこと() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/deskworkouts/100"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("404"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Workout with id:100 not found"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Not Found"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.path").value("/deskworkouts/100"));
    }
}
