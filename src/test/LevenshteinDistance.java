package test;
public class LevenshteinDistance {
    /*编辑距离，又称Levenshtein距离（也叫做Edit Distance），
           是指两个字串之间，由一个转成另一个所需的最少编辑操作次数。许可的编辑操作包括将一个字符替换成另一个字符，插入一个字符，删除一个字符。*/
    public static void main(String[] args) {
        String str1 = "今天天气真好，想出去玩";
        String str2 = "想出去玩";
        System.out.println("相似度 {0} %:" + new LevenshteinDistance().LevenshteinDistancePercent(str1, str2) * 100);
    }
    private int LowerOfThree(int first, int second, int third) {
        int min = Math.min(first, second);
        return Math.min(min, third);
    }
    private int Levenshtein_Distance(String str1, String str2) {
        int[][] Matrix;
        int n = str1.length();
        int m = str2.length();
        int temp = 0;
        char ch1;
        char ch2;
        int i = 0;
        int j = 0;
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        Matrix = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) {
            // 初始化第一列
            Matrix[i][0] = i;
        }
        for (j = 0; j <= m; j++) {
            // 初始化第一行
            Matrix[0][j] = j;
        }
        for (i = 1; i <= n; i++) {
            ch1 = str1.charAt(i - 1);
            for (j = 1; j <= m; j++) {
                ch2 = str2.charAt(j - 1);
                if (ch1 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                Matrix[i][j] = LowerOfThree(Matrix[i - 1][j] + 1, Matrix[i][j - 1] + 1, Matrix[i - 1][j - 1] + temp);
            }
        }
        return Matrix[n][m];
    }
    public double LevenshteinDistancePercent(String str1, String str2) {
        // int maxLenth = str1.Length > str2.Length ? str1.Length : str2.Length;
        int val = Levenshtein_Distance(str1, str2);
        return 1 - (double) val / Math.max(str1.length(), str2.length());
    }
}