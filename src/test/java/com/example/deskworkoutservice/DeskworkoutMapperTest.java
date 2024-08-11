package com.example.deskworkoutservice;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeskworkoutMapperTest {

    @Autowired
    DeskworkoutMapper deskworkoutMapper;

    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @Transactional
    void レコードを全件取得できること() {
        List<Deskworkout> actual = deskworkoutMapper.findAll();
        assertThat(actual)
                .hasSize(5)
                .contains(
                        new Deskworkout(1, "シットアップ", "直角に曲げた膝を机裏にタッチする", 10, "お腹", "初級"),
                        new Deskworkout(2, "バックエクステンション", "お尻を背もたれに付けたまま、デコルテを机に近づける", 10, "背中", "中級"),
                        new Deskworkout(3, "ショルダーダウン", "肘で背もたれを押したまま、肩を引き下げる", 1, "肩", "初級"),
                        new Deskworkout(4, "ニーエクステンション", "内ももをくっ付けたまま、膝を伸ばす", 10, "脚", "初級"),
                        new Deskworkout(5, "スクワット", "片膝を斜め横に向けたまま、お尻を椅子から持ち上げる", 10, "お尻", "上級")
                );
    }

    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @Transactional
    void 指定したIDが存在するときにそのレコードを取得できること() {
        Optional<Deskworkout> actual = deskworkoutMapper.findById(1);
        assertThat(actual).hasValue(new Deskworkout(1, "シットアップ", "直角に曲げた膝を机裏にタッチする", 10, "お腹", "初級"));
    }

    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @Transactional
    void 指定したIDが存在しないときに空のOptionalが返されること() {
        Optional<Deskworkout> actual = deskworkoutMapper.findById(100);
        assertThat(actual).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @ExpectedDataSet(value = "expected-datasets/deskworkouts-after-insert.yml", ignoreCols = "id")
    @Transactional
    void 新しいレコードを登録できること() {
        Deskworkout deskworkout = new Deskworkout("new name", "new howTo", 5, "new bodyParts", "new level");
        deskworkoutMapper.insert(deskworkout);
        Optional<Deskworkout> actual = deskworkoutMapper.findById(deskworkout.getId());
        assertThat(actual).isPresent();
        assertThat(actual).hasValue(deskworkout);
    }

    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @ExpectedDataSet(value = "expected-datasets/deskworkouts-after-update.yml")
    @Transactional
    void 指定したIDが存在するときにそのレコードを更新できること() {
        Deskworkout newDeskworkout = new Deskworkout(1, "new name", "new howTo", 5, "new bodyParts", "new level");
        deskworkoutMapper.update(newDeskworkout);
        Optional<Deskworkout> actual = deskworkoutMapper.findById(1);
        assertThat(actual).isPresent();
        assertThat(actual).hasValue(newDeskworkout);
    }

    @Test
    @DataSet(value = "datasets/deskworkouts.yml")
    @ExpectedDataSet(value = "expected-datasets/deskworkouts-after-delete.yml")
    @Transactional
    void 指定したIDが存在するときにそのレコードを削除できること() {
        deskworkoutMapper.delete(1);
        Optional<Deskworkout> actual = deskworkoutMapper.findById(1);
        assertThat(actual).isEmpty();
    }
}
