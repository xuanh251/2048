package com.example.virtus.a2048;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

public class DataGame {
    private static DataGame dataGame;
    private ArrayList<Integer> arrSO = new ArrayList<>();
    private int[][] manghaichieu = new int[4][4];
    private int[] mangMau;
    private Random r = new Random();

    static {
        dataGame = new DataGame();
    }

    public static DataGame getDataGame() {
        return dataGame;
    }

    public void intt(Context context) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                manghaichieu[i][j] = 0;
                arrSO.add(0);
            }
        }
        TypedArray ta = context.getResources().obtainTypedArray(R.array.mauNenCuaSo);
        mangMau = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            mangMau[i] = ta.getColor(i, 0);
        }
        ta.recycle();
        taoSo();
        chuyenDoi();
    }

    public ArrayList<Integer> getArrSO() {
        return arrSO;
    }

    public int color(int so) {
        if (so == 0) {
            return Color.WHITE;
        } else {
            int a = (int) (Math.log(so) / Math.log(2));
            return mangMau[a - 1];
        }
    }

    private void taoSo() {
        int so0 = 0;
        for (int i = 0; i < 16; i++) {
            if (arrSO.get(i) == 0) {
                so0++;
            }
        }
        int soOTao;
        if (so0 > 1) {
            soOTao = r.nextInt(2) + 1;
        } else {
            if (so0 == 1) {
                soOTao = 1;
            } else {
                soOTao = 0;
            }
        }
        while (soOTao != 0) {
            int i = r.nextInt(4), j = r.nextInt(4);
            if (manghaichieu[i][j] == 0) {
                manghaichieu[i][j] = 2;
                soOTao--;
            }
        }
    }

    public void chuyenDoi() {
        arrSO.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arrSO.add(manghaichieu[i][j]);
            }
        }
    }

    public void vuotTrai() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int so = manghaichieu[i][j];
                if (so == 0) {
                    continue;
                } else {
                    for (int k = j + 1; k < 4; k++) {
                        int sox = manghaichieu[i][k];
                        if (sox == 0) {
                            continue;
                        } else {
                            if (sox == so) {
                                manghaichieu[i][j] = so * 2;
                                manghaichieu[i][k] = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int so = manghaichieu[i][j];
                if (so == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        int so1 = manghaichieu[i][k];
                        if (so1 == 0) {
                            continue;
                        } else {
                            manghaichieu[i][j] = manghaichieu[i][k];
                            manghaichieu[i][k] = 0;
                            break;
                        }
                    }
                }
            }
        }
        taoSo();
        chuyenDoi();
    }

    public void vuotphai() {
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int so = manghaichieu[i][j];
                if (so == 0) {
                    continue;
                } else {
                    for (int k = j - 1; k >= 0; k--) {
                        int sox = manghaichieu[i][k];
                        if (sox == 0) {
                            continue;
                        } else {
                            if (sox == so) {
                                manghaichieu[i][j] = so * 2;
                                manghaichieu[i][k] = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int so = manghaichieu[i][j];
                if (so == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        int so1 = manghaichieu[i][k];
                        if (so1 == 0) {
                            continue;
                        } else {
                            manghaichieu[i][j] = manghaichieu[i][k];
                            manghaichieu[i][k] = 0;
                            break;
                        }
                    }
                }
            }
        }
        taoSo();
        chuyenDoi();
    }

    public void vuotXuong() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int so = manghaichieu[j][i];
                if (so == 0) {
                    continue;
                } else {
                    for (int k = j + 1; k < 4; k++) {
                        int sox = manghaichieu[k][i];
                        if (sox == 0) {
                            continue;
                        } else {
                            if (sox == so) {
                                manghaichieu[j][i] = so * 2;
                                manghaichieu[k][i] = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int so = manghaichieu[j][i];
                if (so == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        int so1 = manghaichieu[k][i];
                        if (so1 == 0) {
                            continue;
                        } else {
                            manghaichieu[j][i] = manghaichieu[k][i];
                            manghaichieu[k][i] = 0;
                            break;
                        }
                    }
                }
            }
        }
        taoSo();
        chuyenDoi();
    }

    public void vuotLen() {
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int so = manghaichieu[j][i];
                if (so == 0) {
                    continue;
                } else {
                    for (int k = j - 1; k >= 0; k--) {
                        int sox = manghaichieu[k][i];
                        if (sox == 0) {
                            continue;
                        } else {
                            if (sox == so) {
                                manghaichieu[j][i] = so * 2;
                                manghaichieu[k][i] = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                int so = manghaichieu[j][i];
                if (so == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        int so1 = manghaichieu[k][i];
                        if (so1 == 0) {
                            continue;
                        } else {
                            manghaichieu[j][i] = manghaichieu[k][i];
                            manghaichieu[k][i] = 0;
                            break;
                        }
                    }
                }
            }
        }
        taoSo();
        chuyenDoi();
    }

    public String CapNhatDiem() {
        Integer diem = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (manghaichieu[i][j] != 0) diem += manghaichieu[i][j];
            }
        }
        return diem.toString();
    }

    public boolean KiemTraThang() {
        boolean thang = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (manghaichieu[i][j] == 2048) thang = true;
            }
        }
        return thang;
    }

    public boolean KiemTraChoTrong() {
        int dem = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (manghaichieu[i][j] == 0) dem++;
            }
        }
        if (dem == 0) return true;
        else return false;
    }

    public boolean KiemTraThua() {
        if (KiemTraChoTrong()) {//hết chỗ trống
            for (int i = 0; i < 4; i++) {//quét theo chiều ngang
                for (int j = 0; j < 3; j++) {
                    if (manghaichieu[i][j] == manghaichieu[i][j + 1]) {//còn kéo đc
                        return false;
                    }
                }
            }
            for (int j = 0; j < 4; j++) {//quét theo chiều ngang
                for (int i = 0; i < 3; i++) {
                    if (manghaichieu[i][j] == manghaichieu[i + 1][j]) {//còn kéo đc
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public void resetGame() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                manghaichieu[i][j] = 0;
            }
        }
        taoSo();
        chuyenDoi();
    }
}
