package com.example.deskworkoutservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeskworkoutServiceTest {
    //@InjectMockでDeskworkoutServiceを自動的にインスタンス化し、モックオブジェクトのdeskworkoutMapperを注入する
    @InjectMocks
    private DeskworkoutService deskworkoutService;//インスタンス変数

    @Mock
    private DeskworkoutMapper deskworkoutMapper;//インスタンス変数

    @Test
    public void nullのクエリ文字列を渡したときに全てのレコードを取得できること() {
        List<Deskworkout> deskworkouts = List.of(
                new Deskworkout(1, "シットアップ", "直角に曲げた膝を机裏にタッチする", 10, "お腹", "初級"),
                new Deskworkout(2, "バックエクステンション", "お尻を背もたれに付けたまま、デコルテを机に近づける", 10, "背中", "中級"),
                new Deskworkout(3, "ショルダーダウン", "肘で背もたれを押したまま、肩を引き下げる", 1, "肩", "初級"),
                new Deskworkout(4, "ニーエクステンション", "内ももをくっ付けたまま、膝を伸ばす", 10, "脚", "初級"),
                new Deskworkout(5, "スクワット", "片膝を斜め横に向けたまま、お尻を椅子から持ち上げる", 10, "お尻", "上級")
        );
        doReturn(deskworkouts).when(deskworkoutMapper).findAll();
        List<Deskworkout> actualNull = deskworkoutService.findByBodyPartsStartingWith(null);
        assertThat(actualNull).isEqualTo(deskworkouts);
        verify(deskworkoutMapper).findAll();
    }

    @Test
    public void 空のクエリ文字列を渡したときに全てのレコードを取得できること() {
        List<Deskworkout> deskworkouts = List.of(
                new Deskworkout(1, "シットアップ", "直角に曲げた膝を机裏にタッチする", 10, "お腹", "初級"),
                new Deskworkout(2, "バックエクステンション", "お尻を背もたれに付けたまま、デコルテを机に近づける", 10, "背中", "中級"),
                new Deskworkout(3, "ショルダーダウン", "肘で背もたれを押したまま、肩を引き下げる", 1, "肩", "初級"),
                new Deskworkout(4, "ニーエクステンション", "内ももをくっ付けたまま、膝を伸ばす", 10, "脚", "初級"),
                new Deskworkout(5, "スクワット", "片膝を斜め横に向けたまま、お尻を椅子から持ち上げる", 10, "お尻", "上級")
        );
        doReturn(deskworkouts).when(deskworkoutMapper).findAll();
        List<Deskworkout> actualEmptyString = deskworkoutService.findByBodyPartsStartingWith("");
        assertThat(actualEmptyString).isEqualTo(deskworkouts);
        verify(deskworkoutMapper).findAll();
    }

    @Test
    public void 存在するIDを指定したときに該当するレコードを取得できること() throws Exception {
        doReturn(Optional.of(new Deskworkout(1, "シットアップ", "直角に曲げた膝を机裏にタッチする", 10, "お腹", "初級")))
                .when(deskworkoutMapper).findById(1);
        Deskworkout actual = deskworkoutService.findById(1);
        assertThat(actual).isEqualTo(new Deskworkout(1, "シットアップ", "直角に曲げた膝を机裏にタッチする", 10, "お腹", "初級"));
        verify(deskworkoutMapper).findById(1);
    }

    @Test
    public void 存在しないIDを指定したときに例外が発生すること() {
        doReturn(Optional.empty()).when(deskworkoutMapper).findById(100);
        assertThatThrownBy(() -> deskworkoutService.findById(100))
                .isInstanceOf(DeskworkoutNotFoundException.class);
        verify(deskworkoutMapper).findById(100);
    }
}
